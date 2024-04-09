package com.example.carsharing.entity;

import com.example.carsharing.entity.enums.PaymentMethod;
import com.example.carsharing.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "payments")
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "payment_id")
    private UUID paymentId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "payment_status")
    private boolean status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(amount, payment.amount) == 0 && status == payment.status && Objects.equals(paymentId, payment.paymentId) && Objects.equals(paymentDate, payment.paymentDate) && paymentMethod == payment.paymentMethod && Objects.equals(user, payment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, amount, paymentDate, paymentMethod, status, user);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", paymentMethod=" + paymentMethod +
                ", status=" + status +
                ", user=" + user +
                '}';
    }
}
