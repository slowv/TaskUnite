import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRoom } from 'app/shared/model/room.model';

type EntityResponseType = HttpResponse<IRoom>;
type EntityArrayResponseType = HttpResponse<IRoom[]>;

@Injectable({ providedIn: 'root' })
export class RoomService {
  public resourceUrl = SERVER_API_URL + 'api/rooms';

  constructor(protected http: HttpClient) {}

  create(room: IRoom): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(room);
    return this.http
      .post<IRoom>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(room: IRoom): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(room);
    return this.http
      .put<IRoom>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRoom>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRoom[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(room: IRoom): IRoom {
    const copy: IRoom = Object.assign({}, room, {
      createdAt: room.createdAt != null && room.createdAt.isValid() ? room.createdAt.toJSON() : null,
      updatedAt: room.updatedAt != null && room.updatedAt.isValid() ? room.updatedAt.toJSON() : null,
      deletedAt: room.deletedAt != null && room.deletedAt.isValid() ? room.deletedAt.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdAt = res.body.createdAt != null ? moment(res.body.createdAt) : null;
      res.body.updatedAt = res.body.updatedAt != null ? moment(res.body.updatedAt) : null;
      res.body.deletedAt = res.body.deletedAt != null ? moment(res.body.deletedAt) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((room: IRoom) => {
        room.createdAt = room.createdAt != null ? moment(room.createdAt) : null;
        room.updatedAt = room.updatedAt != null ? moment(room.updatedAt) : null;
        room.deletedAt = room.deletedAt != null ? moment(room.deletedAt) : null;
      });
    }
    return res;
  }
}
