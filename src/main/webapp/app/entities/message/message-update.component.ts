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
import { IMessage, Message } from 'app/shared/model/message.model';
import { MessageService } from './message.service';
import { ITask } from 'app/shared/model/task.model';
import { TaskService } from 'app/entities/task/task.service';
import { IUserExtend } from 'app/shared/model/user-extend.model';
import { UserExtendService } from 'app/entities/user-extend/user-extend.service';

@Component({
  selector: 'jhi-message-update',
  templateUrl: './message-update.component.html'
})
export class MessageUpdateComponent implements OnInit {
  isSaving: boolean;

  tasks: ITask[];

  userextends: IUserExtend[];

  editForm = this.fb.group({
    id: [],
    body: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    taskId: [],
    senderId: [],
    receiverId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected messageService: MessageService,
    protected taskService: TaskService,
    protected userExtendService: UserExtendService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ message }) => {
      this.updateForm(message);
    });
    this.taskService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ITask[]>) => mayBeOk.ok),
        map((response: HttpResponse<ITask[]>) => response.body)
      )
      .subscribe((res: ITask[]) => (this.tasks = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.userExtendService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUserExtend[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUserExtend[]>) => response.body)
      )
      .subscribe((res: IUserExtend[]) => (this.userextends = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(message: IMessage) {
    this.editForm.patchValue({
      id: message.id,
      body: message.body,
      status: message.status,
      createdAt: message.createdAt != null ? message.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: message.updatedAt != null ? message.updatedAt.format(DATE_TIME_FORMAT) : null,
      deletedAt: message.deletedAt != null ? message.deletedAt.format(DATE_TIME_FORMAT) : null,
      taskId: message.taskId,
      senderId: message.senderId,
      receiverId: message.receiverId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const message = this.createFromForm();
    if (message.id !== undefined) {
      this.subscribeToSaveResponse(this.messageService.update(message));
    } else {
      this.subscribeToSaveResponse(this.messageService.create(message));
    }
  }

  private createFromForm(): IMessage {
    return {
      ...new Message(),
      id: this.editForm.get(['id']).value,
      body: this.editForm.get(['body']).value,
      status: this.editForm.get(['status']).value,
      createdAt:
        this.editForm.get(['createdAt']).value != null ? moment(this.editForm.get(['createdAt']).value, DATE_TIME_FORMAT) : undefined,
      updatedAt:
        this.editForm.get(['updatedAt']).value != null ? moment(this.editForm.get(['updatedAt']).value, DATE_TIME_FORMAT) : undefined,
      deletedAt:
        this.editForm.get(['deletedAt']).value != null ? moment(this.editForm.get(['deletedAt']).value, DATE_TIME_FORMAT) : undefined,
      taskId: this.editForm.get(['taskId']).value,
      senderId: this.editForm.get(['senderId']).value,
      receiverId: this.editForm.get(['receiverId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMessage>>) {
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

  trackTaskById(index: number, item: ITask) {
    return item.id;
  }

  trackUserExtendById(index: number, item: IUserExtend) {
    return item.id;
  }
}
