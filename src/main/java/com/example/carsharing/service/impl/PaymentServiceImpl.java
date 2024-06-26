package com.example.carsharing.service.impl;

import com.example.carsharing.dto.PaymentAfterCreationDto;
import com.example.carsharing.dto.PaymentCreateDto;
import com.example.carsharing.entity.Payment;
import com.example.carsharing.entity.User;
import com.example.carsharing.exception.PaymentAlreadyExistsException;
import com.example.carsharing.exception.PaymentNotExistException;
import com.example.carsharing.exception.UserNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
import com.example.carsharing.mapper.PaymentMapper;
import com.example.carsharing.repository.PaymentRepository;
import com.example.carsharing.repository.UserRepository;
import com.example.carsharing.service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final UserRepository userRepository;

    /**
     * Retrieves a payment by its identifier.
     *
     * @param id Payment identifier.
     * @return Payment object.
     * @throws PaymentNotExistException if the payment does not exist.
     */
    @Override
    @Transactional
    public Payment getPaymentById(UUID id) {
        Payment payment = paymentRepository.getPaymentByPaymentId(id);
        if (payment == null) {
            throw new PaymentNotExistException(ErrorMessage.PAYMENT_NOT_EXIST);
        }
        return payment;
    }

    /**
     * Deletes a payment by its identifier.
     *
     * @param id Payment identifier.
     * @return A message about the successful deletion of the payment.
     * @throws PaymentNotExistException if the payment does not exist.
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String deletePaymentById(UUID id) {
        Payment payment = paymentRepository.getPaymentByPaymentId(id);
        if (payment != null) {
            paymentRepository.deleteById(id);
            return "Payment with this ID was deleted SUCCESSFULLY";
        } else {
            throw new PaymentNotExistException(ErrorMessage.PAYMENT_NOT_EXIST);
        }
    }

    /**
     * Creates a new payment.
     *
     * @param paymentCreateDto Data about the payment to be created.
     * @return Data about the created payment.
     * @throws PaymentAlreadyExistsException if the payment already exists.
     * @throws UserNotExistException         if the user does not exist.
     */
    @Override
    @Transactional
    public PaymentAfterCreationDto createPayment(PaymentCreateDto paymentCreateDto) {
        Payment payment = paymentRepository.findByPaymentDateAndUser_UserId(LocalDateTime.parse(paymentCreateDto.getPaymentDate()), UUID.fromString(paymentCreateDto.getUserId()));
        if (payment != null) {
            throw new PaymentAlreadyExistsException(ErrorMessage.PAYMENT_ALREADY_EXISTS);
        }
        User user = userRepository.findById(UUID.fromString(paymentCreateDto.getUserId())).orElse(null);
        if (user == null) {
            throw new UserNotExistException(ErrorMessage.USER_NOT_EXIST);
        }
        Payment entity = paymentMapper.toEntity(paymentCreateDto);
        entity.setUser(user);
        Payment paymentAfterCreation = paymentRepository.save(entity);
        return paymentMapper.toDto(paymentAfterCreation);
    }

    /**
     * Updates payment information by its identifier.
     *
     * @param id               Payment identifier.
     * @param paymentCreateDto Updated payment data.
     * @return Updated payment object.
     * @throws PaymentNotExistException if the payment does not exist.
     */
    @Override
    @Transactional
    public Payment updatePaymentById(UUID id, PaymentCreateDto paymentCreateDto) {
        Payment payment = getPaymentById(id);

        if (payment != null) {
            payment.setAmount(Objects.equals(payment.getAmount(), paymentCreateDto.getAmount()) ? payment.getAmount() : paymentCreateDto.getAmount());
            payment.setPaymentDate(Objects.equals(payment.getPaymentDate(), paymentCreateDto.getPaymentDate()) ? payment.getPaymentDate() : LocalDateTime.parse(paymentCreateDto.getPaymentDate()));
            payment.setPaymentMethod(Objects.equals(payment.getPaymentMethod(), paymentCreateDto.getPaymentMethod()) ? payment.getPaymentMethod() : paymentCreateDto.getPaymentMethod());
        }

        return paymentRepository.saveAndFlush(payment);
    }
}
