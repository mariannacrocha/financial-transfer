import { Component, OnInit } from '@angular/core';
import { TransferService } from '../../services/transfer.service';


@Component({
  selector: 'app-transfer-list',
  templateUrl: './transfer-list.component.html',
  styleUrls: ['./transfer-list.component.css']
})
export class TransferListComponent implements OnInit {
  transfers: any[] = [];

  constructor(private transferService: TransferService) { }

  ngOnInit(): void {
    this.loadTransfers();
  }

  loadTransfers(): void {
    this.transferService.getTransfers().subscribe(
      data => {
        this.transfers = data;
      },
      error => {
        console.error('Erro ao carregar transferências:', error);
        alert('Erro ao carregar transferências. Verifique se o backend está rodando.');
      }
    );
  }
}
