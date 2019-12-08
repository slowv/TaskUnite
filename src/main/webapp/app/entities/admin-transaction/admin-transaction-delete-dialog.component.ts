import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdminTransaction } from 'app/shared/model/admin-transaction.model';
import { AdminTransactionService } from './admin-transaction.service';

@Component({
  templateUrl: './admin-transaction-delete-dialog.component.html'
})
export class AdminTransactionDeleteDialogComponent {
  adminTransaction: IAdminTransaction;

  constructor(
    protected adminTransactionService: AdminTransactionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.adminTransactionService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'adminTransactionListModification',
        content: 'Deleted an adminTransaction'
      });
      this.activeModal.dismiss(true);
    });
  }
}
