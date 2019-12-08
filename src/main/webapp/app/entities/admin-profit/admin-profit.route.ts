import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AdminProfit } from 'app/shared/model/admin-profit.model';
import { AdminProfitService } from './admin-profit.service';
import { AdminProfitComponent } from './admin-profit.component';
import { AdminProfitDetailComponent } from './admin-profit-detail.component';
import { AdminProfitUpdateComponent } from './admin-profit-update.component';
import { IAdminProfit } from 'app/shared/model/admin-profit.model';

@Injectable({ providedIn: 'root' })
export class AdminProfitResolve implements Resolve<IAdminProfit> {
  constructor(private service: AdminProfitService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAdminProfit> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((adminProfit: HttpResponse<AdminProfit>) => adminProfit.body));
    }
    return of(new AdminProfit());
  }
}

export const adminProfitRoute: Routes = [
  {
    path: '',
    component: AdminProfitComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'taskUniteApp.adminProfit.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AdminProfitDetailComponent,
    resolve: {
      adminProfit: AdminProfitResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.adminProfit.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AdminProfitUpdateComponent,
    resolve: {
      adminProfit: AdminProfitResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.adminProfit.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AdminProfitUpdateComponent,
    resolve: {
      adminProfit: AdminProfitResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.adminProfit.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
