package com.example.carsharing.service.impl;

import com.example.carsharing.dto.PaymentAfterCreationDto;
import com.example.carsharing.dto.PaymentCreateDto;
import com.example.carsharing.entity.Payment;
import com.example.carsharing.exception.PaymentAlreadyExistsException;
import com.example.carsharing.exception.PaymentNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
import com.example.carsharing.mapper.PaymentMapper;
import com.example.carsharing.mapper.UserMapper;
import com.example.carsharing.repository.PaymentRepository;
import com.example.carsharing.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final UserMapper userMapper;

    @Override
    public Payment getPaymentById(UUID id) {
        Payment payment = paymentRepository.getPaymentByPaymentId(id);
        if (payment == null) {
            throw new PaymentNotExistException(ErrorMessage.PAYMENT_NOT_EXIST);
        }
        return payment;
    }

    @Override
    @Transactional
    public void deletePaymentById(UUID id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment == null) {
            throw new PaymentNotExistException(ErrorMessage.PAYMENT_NOT_EXIST);
        }
        paymentRepository.deletePaymentByPaymentId(id);
    }

    //TODO проверить правильность работы
    @Override
    @Transactional
    public PaymentAfterCreationDto createPayment(PaymentCreateDto paymentCreateDto) {
        Payment payment = paymentRepository.findByPaymentDateAndUser_UserId(paymentCreateDto.getPaymentDate(), userMapper.toEntity(paymentCreateDto.getUser()).getUserId());
        if (payment != null) {
            throw new PaymentAlreadyExistsException(ErrorMessage.PAYMENT_ALREADY_EXISTS);
        }
        Payment entity = paymentMapper.toEntity(paymentCreateDto);
        Payment paymentAfterCreation = paymentRepository.save(entity);
        return paymentMapper.toDto(paymentAfterCreation);
    }

    @Override
    @Transactional
    public Payment updatePaymentById(UUID id, Payment updatedPayment) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment == null) {
            throw new PaymentNotExistException(ErrorMessage.PAYMENT_NOT_EXIST);
        }
        payment.setAmount(updatedPayment.getAmount());
        payment.setPaymentDate(updatedPayment.getPaymentDate());
        payment.setPaymentMethod(updatedPayment.getPaymentMethod());
        payment.setStatus(updatedPayment.isStatus());
        payment.setUser(updatedPayment.getUser());

        return paymentRepository.saveAndFlush(payment);
    }
}
