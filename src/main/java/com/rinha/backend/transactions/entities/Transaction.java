package com.rinha.backend.transactions.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rinha.backend.transactions.entities.enums.TypeEnum;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Transaction.TABLE_NAME)
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    public static final String TABLE_NAME = "transaction";


    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @Column(name = "transaction_value", nullable = false)
    private String valor;

    @Column(name = "type", nullable = false)
    private TypeEnum tipo;

    @Column(name = "description", nullable = false, length = 10)
    @Size(min = 1, max = 10)
    private String descricao;

    @Column(name = "made_in", nullable = false)
    private LocalDateTime realizadaEm;

    @ManyToOne
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", valor=" + valor +
                ", tipo=" + tipo +
                ", descricao='" + descricao + '\'' +
                ", realizadaEm=" + realizadaEm +
                ", user=" + user.getId() +
                '}';
    }
}
