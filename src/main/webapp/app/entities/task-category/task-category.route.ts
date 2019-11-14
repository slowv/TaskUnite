import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TaskCategory } from 'app/shared/model/task-category.model';
import { TaskCategoryService } from './task-category.service';
import { TaskCategoryComponent } from './task-category.component';
import { TaskCategoryDetailComponent } from './task-category-detail.component';
import { TaskCategoryUpdateComponent } from './task-category-update.component';
import { TaskCategoryDeletePopupComponent } from './task-category-delete-dialog.component';
import { ITaskCategory } from 'app/shared/model/task-category.model';

@Injectable({ providedIn: 'root' })
export class TaskCategoryResolve implements Resolve<ITaskCategory> {
  constructor(private service: TaskCategoryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITaskCategory> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TaskCategory>) => response.ok),
        map((taskCategory: HttpResponse<TaskCategory>) => taskCategory.body)
      );
    }
    return of(new TaskCategory());
  }
}

export const taskCategoryRoute: Routes = [
  {
    path: '',
    component: TaskCategoryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'taskUniteApp.taskCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TaskCategoryDetailComponent,
    resolve: {
      taskCategory: TaskCategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.taskCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TaskCategoryUpdateComponent,
    resolve: {
      taskCategory: TaskCategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.taskCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TaskCategoryUpdateComponent,
    resolve: {
      taskCategory: TaskCategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.taskCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const taskCategoryPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TaskCategoryDeletePopupComponent,
    resolve: {
      taskCategory: TaskCategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.taskCategory.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
