import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdminTransaction } from 'app/shared/model/admin-transaction.model';

@Component({
  selector: 'jhi-admin-transaction-detail',
  templateUrl: './admin-transaction-detail.component.html'
})
export class AdminTransactionDetailComponent implements OnInit {
  adminTransaction: IAdminTransaction;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ adminTransaction }) => {
      this.adminTransaction = adminTransaction;
    });
  }

  previousState() {
    window.history.back();
  }
}
