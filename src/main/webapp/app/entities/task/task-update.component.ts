import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ITask, Task } from 'app/shared/model/task.model';
import { TaskService } from './task.service';
import { IRoom } from 'app/shared/model/room.model';
import { RoomService } from 'app/entities/room/room.service';
import { ISchedule } from 'app/shared/model/schedule.model';
import { ScheduleService } from 'app/entities/schedule/schedule.service';
import { ITasker } from 'app/shared/model/tasker.model';
import { TaskerService } from 'app/entities/tasker/tasker.service';
import { IMaster } from 'app/shared/model/master.model';
import { MasterService } from 'app/entities/master/master.service';
import { ITaskCategory } from 'app/shared/model/task-category.model';
import { TaskCategoryService } from 'app/entities/task-category/task-category.service';

@Component({
  selector: 'jhi-task-update',
  templateUrl: './task-update.component.html'
})
export class TaskUpdateComponent implements OnInit {
  isSaving: boolean;

  rooms: IRoom[];

  schedules: ISchedule[];

  taskers: ITasker[];

  masters: IMaster[];

  taskcategories: ITaskCategory[];

  editForm = this.fb.group({
    id: [],
    address: [],
    title: [],
    description: [],
    estimatedTime: [],
    price: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    roomId: [],
    scheduleId: [],
    taskerId: [],
    masterId: [],
    taskCategoryId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected taskService: TaskService,
    protected roomService: RoomService,
    protected scheduleService: ScheduleService,
    protected taskerService: TaskerService,
    protected masterService: MasterService,
    protected taskCategoryService: TaskCategoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ task }) => {
      this.updateForm(task);
    });
    this.roomService.query({ filter: 'task-is-null' }).subscribe(
      (res: HttpResponse<IRoom[]>) => {
        if (!this.editForm.get('roomId').value) {
          this.rooms = res.body;
        } else {
          this.roomService
            .find(this.editForm.get('roomId').value)
            .subscribe(
              (subRes: HttpResponse<IRoom>) => (this.rooms = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.scheduleService.query({ filter: 'task-is-null' }).subscribe(
      (res: HttpResponse<ISchedule[]>) => {
        if (!this.editForm.get('scheduleId').value) {
          this.schedules = res.body;
        } else {
          this.scheduleService
            .find(this.editForm.get('scheduleId').value)
            .subscribe(
              (subRes: HttpResponse<ISchedule>) => (this.schedules = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.taskerService
      .query()
      .subscribe((res: HttpResponse<ITasker[]>) => (this.taskers = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.masterService
      .query()
      .subscribe((res: HttpResponse<IMaster[]>) => (this.masters = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.taskCategoryService
      .query()
      .subscribe(
        (res: HttpResponse<ITaskCategory[]>) => (this.taskcategories = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(task: ITask) {
    this.editForm.patchValue({
      id: task.id,
      address: task.address,
      title: task.title,
      description: task.description,
      estimatedTime: task.estimatedTime,
      price: task.price,
      status: task.status,
      createdAt: task.createdAt != null ? task.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: task.updatedAt != null ? task.updatedAt.format(DATE_TIME_FORMAT) : null,
      deletedAt: task.deletedAt != null ? task.deletedAt.format(DATE_TIME_FORMAT) : null,
      roomId: task.roomId,
      scheduleId: task.scheduleId,
      taskerId: task.taskerId,
      masterId: task.masterId,
      taskCategoryId: task.taskCategoryId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const task = this.createFromForm();
    if (task.id !== undefined) {
      this.subscribeToSaveResponse(this.taskService.update(task));
    } else {
      this.subscribeToSaveResponse(this.taskService.create(task));
    }
  }

  private createFromForm(): ITask {
    return {
      ...new Task(),
      id: this.editForm.get(['id']).value,
      address: this.editForm.get(['address']).value,
      title: this.editForm.get(['title']).value,
      description: this.editForm.get(['description']).value,
      estimatedTime: this.editForm.get(['estimatedTime']).value,
      price: this.editForm.get(['price']).value,
      status: this.editForm.get(['status']).value,
      createdAt:
        this.editForm.get(['createdAt']).value != null ? moment(this.editForm.get(['createdAt']).value, DATE_TIME_FORMAT) : undefined,
      updatedAt:
        this.editForm.get(['updatedAt']).value != null ? moment(this.editForm.get(['updatedAt']).value, DATE_TIME_FORMAT) : undefined,
      deletedAt:
        this.editForm.get(['deletedAt']).value != null ? moment(this.editForm.get(['deletedAt']).value, DATE_TIME_FORMAT) : undefined,
      roomId: this.editForm.get(['roomId']).value,
      scheduleId: this.editForm.get(['scheduleId']).value,
      taskerId: this.editForm.get(['taskerId']).value,
      masterId: this.editForm.get(['masterId']).value,
      taskCategoryId: this.editForm.get(['taskCategoryId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITask>>) {
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

  trackRoomById(index: number, item: IRoom) {
    return item.id;
  }

  trackScheduleById(index: number, item: ISchedule) {
    return item.id;
  }

  trackTaskerById(index: number, item: ITasker) {
    return item.id;
  }

  trackMasterById(index: number, item: IMaster) {
    return item.id;
  }

  trackTaskCategoryById(index: number, item: ITaskCategory) {
    return item.id;
  }
}
