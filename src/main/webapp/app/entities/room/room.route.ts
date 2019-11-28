import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Room } from 'app/shared/model/room.model';
import { RoomService } from './room.service';
import { RoomComponent } from './room.component';
import { RoomDetailComponent } from './room-detail.component';
import { RoomUpdateComponent } from './room-update.component';
import { IRoom } from 'app/shared/model/room.model';

@Injectable({ providedIn: 'root' })
export class RoomResolve implements Resolve<IRoom> {
  constructor(private service: RoomService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRoom> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((room: HttpResponse<Room>) => room.body));
    }
    return of(new Room());
  }
}

export const roomRoute: Routes = [
  {
    path: '',
    component: RoomComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'taskUniteApp.room.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RoomDetailComponent,
    resolve: {
      room: RoomResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.room.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RoomUpdateComponent,
    resolve: {
      room: RoomResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.room.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RoomUpdateComponent,
    resolve: {
      room: RoomResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'taskUniteApp.room.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
