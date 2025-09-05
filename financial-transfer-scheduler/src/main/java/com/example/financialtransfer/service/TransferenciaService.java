package com.example.financialtransfer.service;

import com.example.financialtransfer.model.Transferencia;
import com.example.financialtransfer.repository.TransferenciaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public Transferencia agendarTransferencia(Transferencia transferencia) {
        BigDecimal taxa = calcularTaxa(transferencia.getValorTransferencia(), transferencia.getDataAgendamento(), transferencia.getDataTransferencia());
        if (taxa == null) {
            throw new IllegalArgumentException("Não há taxa aplicável para a data de transferência informada.");
        }
        transferencia.setTaxa(taxa);
        return transferenciaRepository.save(transferencia);
    }

    public List<Transferencia> listarTransferencias() {
        return transferenciaRepository.findAll();
    }

    private BigDecimal calcularTaxa(BigDecimal valor, LocalDate dataAgendamento, LocalDate dataTransferencia) {
        long dias = ChronoUnit.DAYS.between(dataAgendamento, dataTransferencia);

        if (dias == 0) {
            return new BigDecimal("3.00").add(valor.multiply(new BigDecimal("0.025")));
        } else if (dias >= 1 && dias <= 10) {
            return new BigDecimal("12.00");
        } else if (dias >= 11 && dias <= 20) {
            return valor.multiply(new BigDecimal("0.082"));
        } else if (dias >= 21 && dias <= 30) {
            return valor.multiply(new BigDecimal("0.069"));
        } else if (dias >= 31 && dias <= 40) {
            return valor.multiply(new BigDecimal("0.047"));
        } else if (dias >= 41 && dias <= 50) {
            return valor.multiply(new BigDecimal("0.017"));
        }
        return null; // Não há taxa aplicável
    }
}


