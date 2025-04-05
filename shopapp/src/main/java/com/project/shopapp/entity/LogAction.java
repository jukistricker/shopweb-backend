package com.project.shopapp.entity;

import com.project.shopapp.enums.ApiEnums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "log_action")
public class LogAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action_name")
    private String actionName;

    @Column(name = "action_type")
    private Action actionType;

    @Column(name = "action_status")
    private int actionStatus;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "created_by")
    private long createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt= LocalDateTime.now();


}
