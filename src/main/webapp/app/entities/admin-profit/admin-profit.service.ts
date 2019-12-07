import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAdminProfit } from 'app/shared/model/admin-profit.model';

type EntityResponseType = HttpResponse<IAdminProfit>;
type EntityArrayResponseType = HttpResponse<IAdminProfit[]>;

@Injectable({ providedIn: 'root' })
export class AdminProfitService {
  public resourceUrl = SERVER_API_URL + 'api/admin-profits';

  constructor(protected http: HttpClient) {}

  create(adminProfit: IAdminProfit): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(adminProfit);
    return this.http
      .post<IAdminProfit>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(adminProfit: IAdminProfit): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(adminProfit);
    return this.http
      .put<IAdminProfit>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAdminProfit>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAdminProfit[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(adminProfit: IAdminProfit): IAdminProfit {
    const copy: IAdminProfit = Object.assign({}, adminProfit, {
      createdAt: adminProfit.createdAt != null && adminProfit.createdAt.isValid() ? adminProfit.createdAt.toJSON() : null,
      updatedAt: adminProfit.updatedAt != null && adminProfit.updatedAt.isValid() ? adminProfit.updatedAt.toJSON() : null,
      deletedAt: adminProfit.deletedAt != null && adminProfit.deletedAt.isValid() ? adminProfit.deletedAt.toJSON() : null
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
      res.body.forEach((adminProfit: IAdminProfit) => {
        adminProfit.createdAt = adminProfit.createdAt != null ? moment(adminProfit.createdAt) : null;
        adminProfit.updatedAt = adminProfit.updatedAt != null ? moment(adminProfit.updatedAt) : null;
        adminProfit.deletedAt = adminProfit.deletedAt != null ? moment(adminProfit.deletedAt) : null;
      });
    }
    return res;
  }
}
