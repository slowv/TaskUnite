import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdminProfit } from 'app/shared/model/admin-profit.model';

@Component({
  selector: 'jhi-admin-profit-detail',
  templateUrl: './admin-profit-detail.component.html'
})
export class AdminProfitDetailComponent implements OnInit {
  adminProfit: IAdminProfit;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ adminProfit }) => {
      this.adminProfit = adminProfit;
    });
  }

  previousState() {
    window.history.back();
  }
}
