import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAdminTransaction } from 'app/shared/model/admin-transaction.model';

type EntityResponseType = HttpResponse<IAdminTransaction>;
type EntityArrayResponseType = HttpResponse<IAdminTransaction[]>;

@Injectable({ providedIn: 'root' })
export class AdminTransactionService {
  public resourceUrl = SERVER_API_URL + 'api/admin-transactions';

  constructor(protected http: HttpClient) {}

  create(adminTransaction: IAdminTransaction): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(adminTransaction);
    return this.http
      .post<IAdminTransaction>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(adminTransaction: IAdminTransaction): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(adminTransaction);
    return this.http
      .put<IAdminTransaction>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAdminTransaction>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAdminTransaction[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(adminTransaction: IAdminTransaction): IAdminTransaction {
    const copy: IAdminTransaction = Object.assign({}, adminTransaction, {
      createdAt: adminTransaction.createdAt != null && adminTransaction.createdAt.isValid() ? adminTransaction.createdAt.toJSON() : null,
      updatedAt: adminTransaction.updatedAt != null && adminTransaction.updatedAt.isValid() ? adminTransaction.updatedAt.toJSON() : null,
      deletedAt: adminTransaction.deletedAt != null && adminTransaction.deletedAt.isValid() ? adminTransaction.deletedAt.toJSON() : null
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
      res.body.forEach((adminTransaction: IAdminTransaction) => {
        adminTransaction.createdAt = adminTransaction.createdAt != null ? moment(adminTransaction.createdAt) : null;
        adminTransaction.updatedAt = adminTransaction.updatedAt != null ? moment(adminTransaction.updatedAt) : null;
        adminTransaction.deletedAt = adminTransaction.deletedAt != null ? moment(adminTransaction.deletedAt) : null;
      });
    }
    return res;
  }
}
