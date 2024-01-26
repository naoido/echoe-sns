package com.naoido.echoev1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "echoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Echo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String echo;
    @Column(insertable = false, nullable = false)
    private Date createdAt;
    private Date deletedAt;

    public boolean isDeleted() {
        return deletedAt != null;
    }
}
