import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IRoom, Room } from 'app/shared/model/room.model';
import { RoomService } from './room.service';
import { ITask } from 'app/shared/model/task.model';
import { TaskService } from 'app/entities/task/task.service';
import { ITasker } from 'app/shared/model/tasker.model';
import { TaskerService } from 'app/entities/tasker/tasker.service';
import { IMaster } from 'app/shared/model/master.model';
import { MasterService } from 'app/entities/master/master.service';

@Component({
  selector: 'jhi-room-update',
  templateUrl: './room-update.component.html'
})
export class RoomUpdateComponent implements OnInit {
  isSaving: boolean;

  tasks: ITask[];

  taskers: ITasker[];

  masters: IMaster[];
  createdAtDp: any;
  updatedAtDp: any;
  deletedAtDp: any;

  editForm = this.fb.group({
    id: [],
    messageId: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    taskerId: [],
    masterId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected roomService: RoomService,
    protected taskService: TaskService,
    protected taskerService: TaskerService,
    protected masterService: MasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ room }) => {
      this.updateForm(room);
    });
    this.taskService
      .query()
      .subscribe((res: HttpResponse<ITask[]>) => (this.tasks = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.taskerService
      .query()
      .subscribe((res: HttpResponse<ITasker[]>) => (this.taskers = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.masterService
      .query()
      .subscribe((res: HttpResponse<IMaster[]>) => (this.masters = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(room: IRoom) {
    this.editForm.patchValue({
      id: room.id,
      messageId: room.messageId,
      status: room.status,
      createdAt: room.createdAt,
      updatedAt: room.updatedAt,
      deletedAt: room.deletedAt,
      taskerId: room.taskerId,
      masterId: room.masterId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const room = this.createFromForm();
    if (room.id !== undefined) {
      this.subscribeToSaveResponse(this.roomService.update(room));
    } else {
      this.subscribeToSaveResponse(this.roomService.create(room));
    }
  }

  private createFromForm(): IRoom {
    return {
      ...new Room(),
      id: this.editForm.get(['id']).value,
      messageId: this.editForm.get(['messageId']).value,
      status: this.editForm.get(['status']).value,
      createdAt: this.editForm.get(['createdAt']).value,
      updatedAt: this.editForm.get(['updatedAt']).value,
      deletedAt: this.editForm.get(['deletedAt']).value,
      taskerId: this.editForm.get(['taskerId']).value,
      masterId: this.editForm.get(['masterId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRoom>>) {
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

  trackTaskerById(index: number, item: ITasker) {
    return item.id;
  }

  trackMasterById(index: number, item: IMaster) {
    return item.id;
  }
}
