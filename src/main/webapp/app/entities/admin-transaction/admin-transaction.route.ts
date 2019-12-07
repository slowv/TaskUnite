import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AdminTransaction } from 'app/shared/model/admin-transaction.model';
import { AdminTransactionService } from './admin-transaction.service';
import { AdminTransactionComponent } from './admin-transaction.component';
import { AdminTransactionDetailComponent } from './admin-transaction-detail.component';
import { AdminTransactionUpdateComponent } from './admin-transaction-update.component';
import { IAdminTransaction } from 'app/shared/model/admin-transaction.model';

@Injectable({ providedIn: 'root' })
export class AdminTransactionResolve implements Resolve<IAdminTransaction> {
  constructor(private service: AdminTransactionService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAdminTransaction> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((adminTransaction: HttpResponse<AdminTransaction>) => adminTransaction.body));
    }
    return of(new AdminTransaction());
  }
}

export const adminTransactionRoute: Routes = [
  {
    path: '',
    component: AdminTransactionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'taskUniteApp.adminTransaction.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AdminTransactionDetailComponent,
    resolve: {
      adminTransaction: AdminTransactionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.adminTransaction.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AdminTransactionUpdateComponent,
    resolve: {
      adminTransaction: AdminTransactionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.adminTransaction.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AdminTransactionUpdateComponent,
    resolve: {
      adminTransaction: AdminTransactionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.adminTransaction.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
