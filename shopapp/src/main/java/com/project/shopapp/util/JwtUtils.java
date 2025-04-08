package com.project.shopapp.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.project.shopapp.aspect.Loggable;
import com.project.shopapp.config.JwtProperties;
import com.project.shopapp.dto.UserDto;
import com.project.shopapp.dto.base.UserClaims;
import com.project.shopapp.dto.request.AuthenticationRequest;
import com.project.shopapp.dto.request.IntrospectRequest;
import com.project.shopapp.dto.response.AuthenticationResponse;
import com.project.shopapp.dto.response.IntrospectResponse;
import com.project.shopapp.entity.User;
import com.project.shopapp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {
    private Set<String> blacklist = new HashSet<>();

    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;

    @Autowired
    public JwtUtils(JwtProperties jwtProperties, UserRepository userRepository) {
        this.jwtProperties = jwtProperties;
        this.userRepository = userRepository;
    }

    @Loggable("Đăng nhập")
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest){
        if (authenticationRequest.getEmail()== null || authenticationRequest.getEmail().isEmpty()){
      throw new IllegalArgumentException("Email or password is empty");
        }
        if (authenticationRequest.getPassword()== null || authenticationRequest.getPassword().isEmpty()){
            throw new IllegalArgumentException("Email or password is empty");
        }
        User user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(()-> new EntityNotFoundException("Email not found") );
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        if (!authenticated){
      throw new BadCredentialsException("email or password is incorrect");
        }


        String token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public String generateToken(User user) {
        try {


            String secretKey = jwtProperties.getSecretKey();
            String issuer = jwtProperties.getIssuer();
            int expiration = jwtProperties.getExpiration();
            String algorithm = jwtProperties.getAlgorithm();


            JWSHeader header = new JWSHeader(JWSAlgorithm.parse(algorithm));
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .claim("id", user.getId())
                    .claim("scope", user.getRole())
                    .claim("fullname",user.getFullname())
                    .subject(user.getEmail())
                    .issuer(issuer)
                    .issueTime(new Date())
                    .expirationTime(new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(expiration)))
                    .build();


            JWSObject jwsObject = new JWSObject(header, new Payload(claimsSet.toJSONObject()));
            jwsObject.sign(new MACSigner(secretKey.getBytes()));


            return jwsObject.serialize();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating JWT token", e);
        }
    }

    @Loggable("Check token")
    public IntrospectResponse introspect(IntrospectRequest request)
            throws JOSEException, ParseException {

        String secretKey = jwtProperties.getSecretKey();
        String token = request.getToken();

        if (!isTokenValid(token)) {
            return IntrospectResponse.builder().valid(false).message("Token is blacklisted").build();
        }

        JWSVerifier verifier = new MACVerifier(secretKey.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();

        String email = signedJWT.getJWTClaimsSet().getSubject();

        User user =userRepository.findByEmail(email).orElseThrow(()-> new EntityNotFoundException("User not found"));

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(email);
        userDto.setRole(user.getRole());

        boolean verified = signedJWT.verify(verifier);
        boolean notExpired = expirationDate.after(new Date());

        if (!verified) {
            return IntrospectResponse.builder()
                    .valid(false)
                    .message("Invalid token")
                    .build();
        }
        if (!notExpired) {
            return IntrospectResponse.builder()
                    .valid(false)
                    .message("Token is expired")
                    .build();
        }



        return  IntrospectResponse.builder()
                .valid(true)
                .user(userDto)
                .build();
    }

    @Loggable("Đăng xuất")
    public void logout(String token) {
        // Thêm token vào blacklist
        blacklist.add(token);
    }

    // Phương thức kiểm tra token có hợp lệ hay không
    public boolean isTokenValid(String token) {
        // Kiểm tra token có nằm trong blacklist không
        return !blacklist.contains(token);
    }

    public UserClaims getUserClaims(String token){
        try{
            JWSObject jwsObject = JWSObject.parse(token);
            JWTClaimsSet claims = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
            return new UserClaims(
                claims.getLongClaim("id"),
                claims.getStringClaim("scope"),
                claims.getStringClaim("fullname"),
                claims.getSubject()
            );

        } catch (Exception e) {
            throw new RuntimeException("Error while extracting claims from token", e);
        }
    }

}

