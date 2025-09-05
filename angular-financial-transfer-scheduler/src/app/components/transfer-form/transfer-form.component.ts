import { Component, OnInit } from '@angular/core';
import { TransferService } from '../../services/transfer.service'; 

@Component({
  selector: 'app-transfer-form',
  templateUrl: './transfer-form.component.html',
  styleUrls: ['./transfer-form.component.css']
})
export class TransferFormComponent implements OnInit {
  transfer: any = {
    contaOrigem: '',
    contaDestino: '',
    valorTransferencia: null,
    dataTransferencia: ''
  };

  constructor(private transferService: TransferService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.transfer.dataAgendamento = new Date().toISOString().split('T')[0];

    this.transferService.scheduleTransfer(this.transfer).subscribe(
      response => {
        alert('Transferência agendada com sucesso!');
        this.transfer = {
          contaOrigem: '',
          contaDestino: '',
          valorTransferencia: null,
          dataTransferencia: ''
        };
        window.location.reload();
      },
      error => {
        console.error('Erro ao agendar transferência:', error);
        alert('Erro ao agendar transferência: ' + (error.error || error.message));
      }
    );
  }
}
