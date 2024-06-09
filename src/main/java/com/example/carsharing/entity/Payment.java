package com.example.carsharing.entity;

import com.example.carsharing.entity.enums.PaymentMethod;
import com.example.carsharing.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * This class represents a payment in the system.
 * It stores detailed information about the payment
 * which includes the amount, method, and date of the payment.
 * <p>
 * This class provides a representation of a payment
 * with various attributes like amount, method, date, etc.
 * <p>
 * This is used throughout the application to manage and persist payment data.
 *
 * @author Urmat Bolotbek Uulu
 */
@Entity
@Getter
@Setter
@Table(name = "payments")
@NoArgsConstructor
@ToString
@Schema(description = "Entity representing a payment in the carsharing system.")
public class Payment {

    /**
     * Unique identifier of the payment.
     * Generated using a custom UUID generator.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = UuidTimeSequenceGenerator.class)
    @Column(name = "payment_id")
    @Schema(description = "Unique identifier of the payment.")
    private UUID paymentId;

    /**
     * Amount of the payment.
     */
    @Column(name = "amount")
    @Schema(description = "Amount of the payment.")
    private double amount;

    /**
     * Date and time of the payment.
     */
    @Column(name = "payment_date")
    @Schema(description = "Date and time of the payment.")
    private LocalDateTime paymentDate;

    /**
     * Method of the payment.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    @Schema(description = "Method of the payment.")
    private PaymentMethod paymentMethod;

    /**
     * Status of the payment.
     */
    @Column(name = "payment_status")
    @Schema(description = "Status of the payment.")
    private boolean status;

    /**
     * User associated with this payment.
     * This relationship is unidirectional and managed by the payment entity.
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Equals method for comparing Payment objects.
     * It compares based on the payment ID and other payment fields.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.amount, amount) == 0 &&
                status == payment.status &&
                Objects.equals(paymentId, payment.paymentId) &&
                Objects.equals(paymentDate, payment.paymentDate) &&
                paymentMethod == payment.paymentMethod;
    }

    /**
     * Generates the hash code for the Payment object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(paymentId, amount, paymentDate, paymentMethod, status);
    }
}
