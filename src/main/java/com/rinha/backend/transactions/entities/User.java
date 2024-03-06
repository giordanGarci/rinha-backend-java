package com.rinha.backend.transactions.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = User.TABLE_NAME)
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public static final String TABLE_NAME = "users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    @JsonIgnore
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "balance_id", referencedColumnName = "id")
    @JsonProperty(value = "saldo")
    private Balance balance;

    @OneToMany(mappedBy = "user")
    @JsonProperty(value = "ultimas_transacoes")
    private List<Transaction> lastTransactions;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", balance=" + balance +
                ", lastTransactions=" + lastTransactions +
                '}';
    }
}
