package com.rinha.backend.transactions.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = Balance.TABLE_NAME)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
    public static final String TABLE_NAME = "balance";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @JsonIgnore
    private Integer id;

    @Column(name = "total", nullable = false)
    private Integer total;

    @Column(name = "statement_date")
    @JsonProperty(value = "data_extrato")
    private LocalDateTime dataExtrato;

    @Column(name = "bound", nullable = false)
    private Integer limite;

    @OneToOne(mappedBy = "balance")
    @JsonIgnore
    private User user;
}
