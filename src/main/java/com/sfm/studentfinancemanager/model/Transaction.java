package com.sfm.studentfinancemanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id")  // correspond à la colonne dans MySQL
    private Long userId;

    @NotBlank
    private String description;

    @NotNull
    @Min(0)
    private Double amount;

    @NotNull
    private LocalDateTime date; // car en SQL tu as DATETIME

    @NotBlank
    private String category;
}
