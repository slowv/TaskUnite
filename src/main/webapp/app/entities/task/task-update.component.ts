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
import { IUserInformation } from 'app/shared/model/user-information.model';
import { UserInformationService } from 'app/entities/user-information/user-information.service';
import { ITaskerCategory } from 'app/shared/model/tasker-category.model';
import { TaskerCategoryService } from 'app/entities/tasker-category/tasker-category.service';

@Component({
  selector: 'jhi-task-update',
  templateUrl: './task-update.component.html'
})
export class TaskUpdateComponent implements OnInit {
  isSaving: boolean;

  userinformations: IUserInformation[];

  taskercategories: ITaskerCategory[];

  editForm = this.fb.group({
    id: [],
    address: [],
    name: [],
    description: [],
    totalPrice: [],
    from: [],
    to: [],
    duration: [],
    type: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    taskerId: [],
    masterId: [],
    taskerCategoryId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected taskService: TaskService,
    protected userInformationService: UserInformationService,
    protected taskerCategoryService: TaskerCategoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ task }) => {
      this.updateForm(task);
    });
    this.userInformationService
      .query()
      .subscribe(
        (res: HttpResponse<IUserInformation[]>) => (this.userinformations = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.taskerCategoryService
      .query()
      .subscribe(
        (res: HttpResponse<ITaskerCategory[]>) => (this.taskercategories = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(task: ITask) {
    this.editForm.patchValue({
      id: task.id,
      address: task.address,
      name: task.name,
      description: task.description,
      totalPrice: task.totalPrice,
      from: task.from != null ? task.from.format(DATE_TIME_FORMAT) : null,
      to: task.to != null ? task.to.format(DATE_TIME_FORMAT) : null,
      duration: task.duration,
      type: task.type,
      status: task.status,
      createdAt: task.createdAt != null ? task.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: task.updatedAt != null ? task.updatedAt.format(DATE_TIME_FORMAT) : null,
      deletedAt: task.deletedAt != null ? task.deletedAt.format(DATE_TIME_FORMAT) : null,
      taskerId: task.taskerId,
      masterId: task.masterId,
      taskerCategoryId: task.taskerCategoryId
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
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      totalPrice: this.editForm.get(['totalPrice']).value,
      from: this.editForm.get(['from']).value != null ? moment(this.editForm.get(['from']).value, DATE_TIME_FORMAT) : undefined,
      to: this.editForm.get(['to']).value != null ? moment(this.editForm.get(['to']).value, DATE_TIME_FORMAT) : undefined,
      duration: this.editForm.get(['duration']).value,
      type: this.editForm.get(['type']).value,
      status: this.editForm.get(['status']).value,
      createdAt:
        this.editForm.get(['createdAt']).value != null ? moment(this.editForm.get(['createdAt']).value, DATE_TIME_FORMAT) : undefined,
      updatedAt:
        this.editForm.get(['updatedAt']).value != null ? moment(this.editForm.get(['updatedAt']).value, DATE_TIME_FORMAT) : undefined,
      deletedAt:
        this.editForm.get(['deletedAt']).value != null ? moment(this.editForm.get(['deletedAt']).value, DATE_TIME_FORMAT) : undefined,
      taskerId: this.editForm.get(['taskerId']).value,
      masterId: this.editForm.get(['masterId']).value,
      taskerCategoryId: this.editForm.get(['taskerCategoryId']).value
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

  trackUserInformationById(index: number, item: IUserInformation) {
    return item.id;
  }

  trackTaskerCategoryById(index: number, item: ITaskerCategory) {
    return item.id;
  }
}
