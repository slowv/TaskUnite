import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdminProfit } from 'app/shared/model/admin-profit.model';
import { AdminProfitService } from './admin-profit.service';

@Component({
  templateUrl: './admin-profit-delete-dialog.component.html'
})
export class AdminProfitDeleteDialogComponent {
  adminProfit: IAdminProfit;

  constructor(
    protected adminProfitService: AdminProfitService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.adminProfitService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'adminProfitListModification',
        content: 'Deleted an adminProfit'
      });
      this.activeModal.dismiss(true);
    });
  }
}
