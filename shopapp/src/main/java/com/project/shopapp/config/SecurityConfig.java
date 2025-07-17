package com.project.shopapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.algorithm}")
    private String algorithm;

    private final String[] SELLER_URLS={
            "api/v1/product/create",
            "api/v1/product/updateById/{id}",
            "api/v1/product/deleteById/{id}",

            "api/v1/variant/delete/{id}",
            "api/v1/variant/create",

            "api/v1/attribute/create",
            "api/v1/attribute/update/{id}",
            "api/v1/attribute/delete/{id}",
    };

    private final String[] ADMIN_URLS ={
            "api/v1/cart/getAll",

            "api/v1/user/getAll",

            "api/v1/category/updateById/{id}",
            "api/v1/category/create",
            "api/v1/category/delete/{id}",

            "api/v1/payment/create",
            "api/v1/payment/update/{id}",

            "api/v1/payment/delete/{id}",

    };

    private final String[] AUTH_URLS = {

            "api/v1/user/updateById/{user_id}",
            "api/v1/user/deleteById/{user_id}",

            "api/v1/cart/create",

            "api/v1/cart/getById/{cart_id}",
            "api/v1/cart/delete/{id}",

            "api/v1/order/create",
            "api/v1/order/all/{id}",
            "api/v1/order/getById/{id}",
            "api/v1/order/update/{id}",
            "api/v1/order/delete/{id}",

            "api/v1/cart-item/all/{id}",
            "api/v1/cart-item/{id}",
            "api/v1/cart-item/update/{id}",
            "api/v1/cart-item/create",
            "api/v1/cart-item/delete/{id}",

            "api/v1/feedback/create",
            "api/v1/feedback/update/{id}",
            "api/v1/feedback/delete/{id}",
            "api/v1/feedback/all/{id}",

            "api/v1/purchase/create",
            "api/v1/purchase/update/{id}",
            "api/v1/purchase/delete/{id}",
            "api/v1/purchase/{id}",
            "api/v1/purchase/all/{id}",

            "api/v1/payment/getAll",
            "api/v1/payment/getById/{id}",

            "api/v1/item/create",
            "api/v1/item/update/{id}",
            "api/v1/item/delete/{id}",
            "api/v1/item/{id}",
            "api/v1/item/all/{id}",

            "api/v1/feedback/create",
            "api/v1/feedback/update/{id}",
            "api/v1/feedback/delete/{id}",

    };

    private final String[] PUBLIC_URLS = {

            "api/v1/auth/logout",
            "api/v1/auth/token",
            "api/v1/auth/introspect",

            "api/v1/user/register",
            "api/v1/user/login",
            "api/v1/user/getById/{user_id}",

            "api/v1/category/getAll",
            "/api/v1/category/getById/{id}",

            "api/v1/product/getAll",
            "api/v1/product/getById/{id}",
            "api/v1/product/user/{id}",
            "api/v1/product/cate/{id}",
            "api/v1/product/se",

            "api/v1/variant/all/{id}",
            "api/v1/variant/getById/{id}",

            "api/v1/attribute/all/{id}",
            "api/v1/attribute/getById/{id}",

            "api/v1/feedback/all/{id}",

    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(PUBLIC_URLS)
                        .permitAll()
                        .requestMatchers(ADMIN_URLS).hasAuthority("SCOPE_admin")

                        .requestMatchers(SELLER_URLS).hasAnyAuthority("SCOPE_admin", "SCOPE_seller")
                        .requestMatchers(AUTH_URLS).authenticated());

        httpSecurity.oauth2ResourceServer(oauth2 ->
             oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder()))
        );
        httpSecurity.cors(Customizer.withDefaults());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.exceptionHandling(exceptionHandling ->
                exceptionHandling.accessDeniedHandler(accessDeniedHandler())
        );


        return httpSecurity.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(),algorithm);
        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("""
            {
                "status": 403,
                "error": "Forbidden",
                "message": "Bạn không có quyền truy cập tài nguyên này!"
            }
        """);
        };
    }

    @Bean
    public CorsFilter corsFilter() {

        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }CorsConfiguration config = new CorsConfiguration();



}
