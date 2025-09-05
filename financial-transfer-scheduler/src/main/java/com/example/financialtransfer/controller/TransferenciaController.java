package com.example.financialtransfer.controller;

import com.example.financialtransfer.model.Transferencia;
import com.example.financialtransfer.service.TransferenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping
    public ResponseEntity<Transferencia> agendarTransferencia(@RequestBody Transferencia transferencia) {
        try {
            Transferencia novaTransferencia = transferenciaService.agendarTransferencia(transferencia);
            return new ResponseEntity<>(novaTransferencia, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Transferencia>> listarTransferencias() {
        List<Transferencia> transferencias = transferenciaService.listarTransferencias();
        return new ResponseEntity<>(transferencias, HttpStatus.OK);
    }
}


