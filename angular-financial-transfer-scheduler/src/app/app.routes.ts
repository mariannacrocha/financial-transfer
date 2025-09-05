import { Routes } from '@angular/router';
import { TransferFormComponent } from './components/transfer-form/transfer-form.component';
import { TransferListComponent } from './components/transfer-list/transfer-list.component';

export const routes: Routes = [
  { path: '', redirectTo: 'transfer-form', pathMatch: 'full' },
  { path: 'transfer-form', component: TransferFormComponent },
  { path: 'transfer-list', component: TransferListComponent }
];
