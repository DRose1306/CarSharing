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

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Payment getPaymentById(UUID id) {
        Payment payment = paymentRepository.getPaymentByPaymentId(id);
        if (payment == null) {
            throw new PaymentNotExistException(ErrorMessage.PAYMENT_NOT_EXIST);
        }
        return payment;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deletePaymentById(UUID id) {
        paymentRepository.findById(id).orElseThrow(() -> new PaymentNotExistException(ErrorMessage.PAYMENT_NOT_EXIST));
        paymentRepository.deleteByPaymentId(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public PaymentAfterCreationDto createPayment(PaymentCreateDto paymentCreateDto) {
        Payment payment = paymentRepository.findByPaymentDateAndUser_UserId(paymentCreateDto.getPaymentDate(), UUID.fromString(paymentCreateDto.getUserId()));
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

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Payment updatePaymentById(UUID id, PaymentCreateDto paymentCreateDto) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() ->
                new PaymentNotExistException(ErrorMessage.PAYMENT_NOT_EXIST));

        User user = userRepository.findById(UUID.fromString(paymentCreateDto.getUserId())).orElse(null);
        if (user == null) {
            throw new UserNotExistException(ErrorMessage.USER_NOT_EXIST);
        }

        payment.setAmount(paymentCreateDto.getAmount());
        payment.setPaymentDate(paymentCreateDto.getPaymentDate());
        payment.setPaymentMethod(paymentCreateDto.getPaymentMethod());
        payment.setUser(user);

        return paymentRepository.saveAndFlush(payment);
    }
}
