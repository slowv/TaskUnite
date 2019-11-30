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
import { ITaskCategory } from 'app/shared/model/task-category.model';
import { TaskCategoryService } from 'app/entities/task-category/task-category.service';
import { ITasker } from 'app/shared/model/tasker.model';
import { TaskerService } from 'app/entities/tasker/tasker.service';
import { IMaster } from 'app/shared/model/master.model';
import { MasterService } from 'app/entities/master/master.service';

@Component({
  selector: 'jhi-task-update',
  templateUrl: './task-update.component.html'
})
export class TaskUpdateComponent implements OnInit {
  isSaving: boolean;

  rooms: IRoom[];

  taskcategories: ITaskCategory[];

  taskers: ITasker[];

  masters: IMaster[];

  editForm = this.fb.group({
    id: [],
    description: [],
    price: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    roomId: [],
    taskCategories: [],
    taskerId: [],
    masterId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected taskService: TaskService,
    protected roomService: RoomService,
    protected taskCategoryService: TaskCategoryService,
    protected taskerService: TaskerService,
    protected masterService: MasterService,
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
    this.taskCategoryService
      .query()
      .subscribe(
        (res: HttpResponse<ITaskCategory[]>) => (this.taskcategories = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.taskerService
      .query()
      .subscribe((res: HttpResponse<ITasker[]>) => (this.taskers = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.masterService
      .query()
      .subscribe((res: HttpResponse<IMaster[]>) => (this.masters = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(task: ITask) {
    this.editForm.patchValue({
      id: task.id,
      description: task.description,
      price: task.price,
      status: task.status,
      createdAt: task.createdAt != null ? task.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: task.updatedAt != null ? task.updatedAt.format(DATE_TIME_FORMAT) : null,
      deletedAt: task.deletedAt != null ? task.deletedAt.format(DATE_TIME_FORMAT) : null,
      roomId: task.roomId,
      taskCategories: task.taskCategories,
      taskerId: task.taskerId,
      masterId: task.masterId
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
      description: this.editForm.get(['description']).value,
      price: this.editForm.get(['price']).value,
      status: this.editForm.get(['status']).value,
      createdAt:
        this.editForm.get(['createdAt']).value != null ? moment(this.editForm.get(['createdAt']).value, DATE_TIME_FORMAT) : undefined,
      updatedAt:
        this.editForm.get(['updatedAt']).value != null ? moment(this.editForm.get(['updatedAt']).value, DATE_TIME_FORMAT) : undefined,
      deletedAt:
        this.editForm.get(['deletedAt']).value != null ? moment(this.editForm.get(['deletedAt']).value, DATE_TIME_FORMAT) : undefined,
      roomId: this.editForm.get(['roomId']).value,
      taskCategories: this.editForm.get(['taskCategories']).value,
      taskerId: this.editForm.get(['taskerId']).value,
      masterId: this.editForm.get(['masterId']).value
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

  trackTaskCategoryById(index: number, item: ITaskCategory) {
    return item.id;
  }

  trackTaskerById(index: number, item: ITasker) {
    return item.id;
  }

  trackMasterById(index: number, item: IMaster) {
    return item.id;
  }

  getSelected(selectedVals: any[], option: any) {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
