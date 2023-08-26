package com.banquito.core.productsaccounts.service;

import com.banquito.core.productsaccounts.model.InterestRate;
import com.banquito.core.productsaccounts.repository.InterestRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InterestRateServiceTest {
    @Mock
    private InterestRateRepository interestRateRepository;
    @InjectMocks
    private InterestRateService interestRateService;

    private InterestRate interestRate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.interestRate = new InterestRate();
        this.interestRate.setId(1);
        this.interestRate.setName("Nuevo interes");
        this.interestRate.setInterestRate(new BigDecimal(3));
        this.interestRate.setState("ACTIVO");
        this.interestRate.setStart(new Date());
        this.interestRate.setEnd(new Date());
    }

    @Test
    void listAllActives() {
        when(interestRateRepository.findAll()).thenReturn(Arrays.asList(interestRate));
        assertNotNull(interestRateService.listAllActives());
    }

    @Test
    void obtainById() {
        when(interestRateRepository.findById(any())).thenReturn(Optional.ofNullable(interestRate));
        assertNotNull(interestRateService.obtainById(1));
    }

    @Test
    void create() {
        when(interestRateRepository.save(any())).thenReturn(interestRate);
        interestRateService.create(interestRate);
        verify(interestRateRepository, times(1)).save(interestRate);
    }

    @Test
    void update() {
        when(interestRateRepository.findById(1)).thenReturn(Optional.of(new InterestRate()));
        assertDoesNotThrow(() -> interestRateService.update(1, interestRate));
        this.interestRate.setName("Nombre");
        verify(interestRateRepository).findById(1);
        verify(interestRateRepository).save(any(InterestRate.class));
        assertEquals("Nombre", interestRate.getName());
    }
}