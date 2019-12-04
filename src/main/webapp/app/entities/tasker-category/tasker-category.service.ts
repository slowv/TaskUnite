import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITaskerCategory } from 'app/shared/model/tasker-category.model';

type EntityResponseType = HttpResponse<ITaskerCategory>;
type EntityArrayResponseType = HttpResponse<ITaskerCategory[]>;

@Injectable({ providedIn: 'root' })
export class TaskerCategoryService {
  public resourceUrl = SERVER_API_URL + 'api/tasker-categories';

  constructor(protected http: HttpClient) {}

  create(taskerCategory: ITaskerCategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(taskerCategory);
    return this.http
      .post<ITaskerCategory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(taskerCategory: ITaskerCategory): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(taskerCategory);
    return this.http
      .put<ITaskerCategory>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITaskerCategory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITaskerCategory[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(taskerCategory: ITaskerCategory): ITaskerCategory {
    const copy: ITaskerCategory = Object.assign({}, taskerCategory, {
      createdAt: taskerCategory.createdAt != null && taskerCategory.createdAt.isValid() ? taskerCategory.createdAt.toJSON() : null,
      updatedAt: taskerCategory.updatedAt != null && taskerCategory.updatedAt.isValid() ? taskerCategory.updatedAt.toJSON() : null,
      deletedAt: taskerCategory.deletedAt != null && taskerCategory.deletedAt.isValid() ? taskerCategory.deletedAt.toJSON() : null
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
      res.body.forEach((taskerCategory: ITaskerCategory) => {
        taskerCategory.createdAt = taskerCategory.createdAt != null ? moment(taskerCategory.createdAt) : null;
        taskerCategory.updatedAt = taskerCategory.updatedAt != null ? moment(taskerCategory.updatedAt) : null;
        taskerCategory.deletedAt = taskerCategory.deletedAt != null ? moment(taskerCategory.deletedAt) : null;
      });
    }
    return res;
  }
}
