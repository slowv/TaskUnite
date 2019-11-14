import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { INotification, Notification } from 'app/shared/model/notification.model';
import { NotificationService } from './notification.service';
import { IUserExtend } from 'app/shared/model/user-extend.model';
import { UserExtendService } from 'app/entities/user-extend/user-extend.service';

@Component({
  selector: 'jhi-notification-update',
  templateUrl: './notification-update.component.html'
})
export class NotificationUpdateComponent implements OnInit {
  isSaving: boolean;

  userextends: IUserExtend[];

  editForm = this.fb.group({
    id: [],
    content: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    userId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected notificationService: NotificationService,
    protected userExtendService: UserExtendService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ notification }) => {
      this.updateForm(notification);
    });
    this.userExtendService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUserExtend[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUserExtend[]>) => response.body)
      )
      .subscribe((res: IUserExtend[]) => (this.userextends = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(notification: INotification) {
    this.editForm.patchValue({
      id: notification.id,
      content: notification.content,
      status: notification.status,
      createdAt: notification.createdAt != null ? notification.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: notification.updatedAt != null ? notification.updatedAt.format(DATE_TIME_FORMAT) : null,
      deletedAt: notification.deletedAt != null ? notification.deletedAt.format(DATE_TIME_FORMAT) : null,
      userId: notification.userId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const notification = this.createFromForm();
    if (notification.id !== undefined) {
      this.subscribeToSaveResponse(this.notificationService.update(notification));
    } else {
      this.subscribeToSaveResponse(this.notificationService.create(notification));
    }
  }

  private createFromForm(): INotification {
    return {
      ...new Notification(),
      id: this.editForm.get(['id']).value,
      content: this.editForm.get(['content']).value,
      status: this.editForm.get(['status']).value,
      createdAt:
        this.editForm.get(['createdAt']).value != null ? moment(this.editForm.get(['createdAt']).value, DATE_TIME_FORMAT) : undefined,
      updatedAt:
        this.editForm.get(['updatedAt']).value != null ? moment(this.editForm.get(['updatedAt']).value, DATE_TIME_FORMAT) : undefined,
      deletedAt:
        this.editForm.get(['deletedAt']).value != null ? moment(this.editForm.get(['deletedAt']).value, DATE_TIME_FORMAT) : undefined,
      userId: this.editForm.get(['userId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INotification>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackUserExtendById(index: number, item: IUserExtend) {
    return item.id;
  }
}
