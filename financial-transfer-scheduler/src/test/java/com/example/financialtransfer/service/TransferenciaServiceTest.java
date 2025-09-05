package com.example.financialtransfer.service;

import com.example.financialtransfer.model.Transferencia;
import com.example.financialtransfer.repository.TransferenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferenciaServiceTest {

    @Mock
    private TransferenciaRepository transferenciaRepository;

    @InjectMocks
    private TransferenciaService transferenciaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void agendarTransferencia_taxaZeroDias() {
        Transferencia transferencia = new Transferencia("12345", "67890", new BigDecimal("1000.00"), null, LocalDate.now(), LocalDate.now());
        when(transferenciaRepository.save(any(Transferencia.class))).thenReturn(transferencia);

        Transferencia result = transferenciaService.agendarTransferencia(transferencia);

        assertNotNull(result.getTaxa());
        assertEquals(new BigDecimal("28.000").setScale(3, BigDecimal.ROUND_HALF_UP), result.getTaxa().setScale(3, BigDecimal.ROUND_HALF_UP));
        verify(transferenciaRepository, times(1)).save(transferencia);
    }

    @Test
    void agendarTransferencia_taxaUmADezDias() {
        Transferencia transferencia = new Transferencia("12345", "67890", new BigDecimal("1000.00"), null, LocalDate.now().plusDays(5), LocalDate.now());
        when(transferenciaRepository.save(any(Transferencia.class))).thenReturn(transferencia);

        Transferencia result = transferenciaService.agendarTransferencia(transferencia);

        assertNotNull(result.getTaxa());
        assertEquals(new BigDecimal("12.00").setScale(2, BigDecimal.ROUND_HALF_UP), result.getTaxa().setScale(2, BigDecimal.ROUND_HALF_UP));
        verify(transferenciaRepository, times(1)).save(transferencia);
    }

    @Test
    void agendarTransferencia_taxaOnzeAVinteDias() {
        Transferencia transferencia = new Transferencia("12345", "67890", new BigDecimal("1000.00"), null, LocalDate.now().plusDays(15), LocalDate.now());
        when(transferenciaRepository.save(any(Transferencia.class))).thenReturn(transferencia);

        Transferencia result = transferenciaService.agendarTransferencia(transferencia);

        assertNotNull(result.getTaxa());
        assertEquals(new BigDecimal("82.00").setScale(2, BigDecimal.ROUND_HALF_UP), result.getTaxa().setScale(2, BigDecimal.ROUND_HALF_UP));
        verify(transferenciaRepository, times(1)).save(transferencia);
    }

    @Test
    void agendarTransferencia_taxaVinteEUmATrintaDias() {
        Transferencia transferencia = new Transferencia("12345", "67890", new BigDecimal("1000.00"), null, LocalDate.now().plusDays(25), LocalDate.now());
        when(transferenciaRepository.save(any(Transferencia.class))).thenReturn(transferencia);

        Transferencia result = transferenciaService.agendarTransferencia(transferencia);

        assertNotNull(result.getTaxa());
        assertEquals(new BigDecimal("69.00").setScale(2, BigDecimal.ROUND_HALF_UP), result.getTaxa().setScale(2, BigDecimal.ROUND_HALF_UP));
        verify(transferenciaRepository, times(1)).save(transferencia);
    }

    @Test
    void agendarTransferencia_taxaTrintaEUmAQuarentaDias() {
        Transferencia transferencia = new Transferencia("12345", "67890", new BigDecimal("1000.00"), null, LocalDate.now().plusDays(35), LocalDate.now());
        when(transferenciaRepository.save(any(Transferencia.class))).thenReturn(transferencia);

        Transferencia result = transferenciaService.agendarTransferencia(transferencia);

        assertNotNull(result.getTaxa());
        assertEquals(new BigDecimal("47.00").setScale(2, BigDecimal.ROUND_HALF_UP), result.getTaxa().setScale(2, BigDecimal.ROUND_HALF_UP));
        verify(transferenciaRepository, times(1)).save(transferencia);
    }

    @Test
    void agendarTransferencia_taxaQuarentaEUmACinquentaDias() {
        Transferencia transferencia = new Transferencia("12345", "67890", new BigDecimal("1000.00"), null, LocalDate.now().plusDays(45), LocalDate.now());
        when(transferenciaRepository.save(any(Transferencia.class))).thenReturn(transferencia);

        Transferencia result = transferenciaService.agendarTransferencia(transferencia);

        assertNotNull(result.getTaxa());
        assertEquals(new BigDecimal("17.00").setScale(2, BigDecimal.ROUND_HALF_UP), result.getTaxa().setScale(2, BigDecimal.ROUND_HALF_UP));
        verify(transferenciaRepository, times(1)).save(transferencia);
    }

    @Test
    void agendarTransferencia_semTaxaAplicavel() {
        Transferencia transferencia = new Transferencia("12345", "67890", new BigDecimal("1000.00"), null, LocalDate.now().plusDays(60), LocalDate.now());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            transferenciaService.agendarTransferencia(transferencia);
        });

        assertEquals("Não há taxa aplicável para a data de transferência informada.", exception.getMessage());
        verify(transferenciaRepository, never()).save(any(Transferencia.class));
    }
}


