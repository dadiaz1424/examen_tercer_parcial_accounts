package com.banquito.core.productsaccounts.service;

import com.banquito.core.productsaccounts.model.ProductAccount;
import com.banquito.core.productsaccounts.repository.InterestRateRepository;
import com.banquito.core.productsaccounts.repository.ProductAccountRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

class ProductAccountServiceTest {

    @Mock
    private ProductAccountRepository productAccountRepository;
    @InjectMocks
    private ProductAccountService productAccountService;

    private ProductAccount productAccount;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.productAccount = new ProductAccount();
        this.productAccount.setId("1");
        this.productAccount.setName("Computadora");
        this.productAccount.setDescription("Computadora core i5");
        this.productAccount.setMinimunBalance(new BigDecimal(20));
        this.productAccount.setPayInterest("3%");
        this.productAccount.setAcceptsChecks("Si");
        this.productAccount.setState("ACT");
        this.productAccount.setCreationDate(new Date());
    }
    @Test
    void create() {
        when(productAccountRepository.save(any())).thenReturn(productAccount);
        productAccountService.create(productAccount);
        verify(productAccountRepository, times(1)).save(productAccount);
    }

    @Test
    void listAllActives() {
        when(productAccountRepository.findAll()).thenReturn(Arrays.asList(productAccount));
        assertNotNull(productAccountService.listAllActives());
    }

    @Test
    void obtainById() {
        when(productAccountRepository.findById(any())).thenReturn(Optional.ofNullable(productAccount));
        assertNotNull(productAccountService.obtainById("1"));
    }


}