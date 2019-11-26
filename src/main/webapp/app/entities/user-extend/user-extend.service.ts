import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserExtend } from 'app/shared/model/user-extend.model';

type EntityResponseType = HttpResponse<IUserExtend>;
type EntityArrayResponseType = HttpResponse<IUserExtend[]>;

@Injectable({ providedIn: 'root' })
export class UserExtendService {
  public resourceUrl = SERVER_API_URL + 'api/user-extends';

  constructor(protected http: HttpClient) {}

  create(userExtend: IUserExtend): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userExtend);
    return this.http
      .post<IUserExtend>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(userExtend: IUserExtend): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userExtend);
    return this.http
      .put<IUserExtend>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IUserExtend>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IUserExtend[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(userExtend: IUserExtend): IUserExtend {
    const copy: IUserExtend = Object.assign({}, userExtend, {
      createdAt: userExtend.createdAt != null && userExtend.createdAt.isValid() ? userExtend.createdAt.format(DATE_FORMAT) : null,
      updatedAt: userExtend.updatedAt != null && userExtend.updatedAt.isValid() ? userExtend.updatedAt.format(DATE_FORMAT) : null,
      deletedAt: userExtend.deletedAt != null && userExtend.deletedAt.isValid() ? userExtend.deletedAt.format(DATE_FORMAT) : null
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
      res.body.forEach((userExtend: IUserExtend) => {
        userExtend.createdAt = userExtend.createdAt != null ? moment(userExtend.createdAt) : null;
        userExtend.updatedAt = userExtend.updatedAt != null ? moment(userExtend.updatedAt) : null;
        userExtend.deletedAt = userExtend.deletedAt != null ? moment(userExtend.deletedAt) : null;
      });
    }
    return res;
  }
}
