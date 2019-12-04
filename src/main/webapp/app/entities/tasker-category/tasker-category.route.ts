import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TaskerCategory } from 'app/shared/model/tasker-category.model';
import { TaskerCategoryService } from './tasker-category.service';
import { TaskerCategoryComponent } from './tasker-category.component';
import { TaskerCategoryDetailComponent } from './tasker-category-detail.component';
import { TaskerCategoryUpdateComponent } from './tasker-category-update.component';
import { ITaskerCategory } from 'app/shared/model/tasker-category.model';

@Injectable({ providedIn: 'root' })
export class TaskerCategoryResolve implements Resolve<ITaskerCategory> {
  constructor(private service: TaskerCategoryService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITaskerCategory> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((taskerCategory: HttpResponse<TaskerCategory>) => taskerCategory.body));
    }
    return of(new TaskerCategory());
  }
}

export const taskerCategoryRoute: Routes = [
  {
    path: '',
    component: TaskerCategoryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'taskUniteApp.taskerCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TaskerCategoryDetailComponent,
    resolve: {
      taskerCategory: TaskerCategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.taskerCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TaskerCategoryUpdateComponent,
    resolve: {
      taskerCategory: TaskerCategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.taskerCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TaskerCategoryUpdateComponent,
    resolve: {
      taskerCategory: TaskerCategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.taskerCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
