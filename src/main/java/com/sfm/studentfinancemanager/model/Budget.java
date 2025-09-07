package com.sfm.studentfinancemanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "budgets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long userId; // lien avec l’utilisateur

    @NotBlank
    private String category;

    @NotNull
    @Min(0)
    private Double limitAmount; // montant maximum

    private LocalDateTime createdAt = LocalDateTime.now();
}
