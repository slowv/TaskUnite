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
import { ITaskerCategory, TaskerCategory } from 'app/shared/model/tasker-category.model';
import { TaskerCategoryService } from './tasker-category.service';
import { ITaskCategory } from 'app/shared/model/task-category.model';
import { TaskCategoryService } from 'app/entities/task-category/task-category.service';
import { ITasker } from 'app/shared/model/tasker.model';
import { TaskerService } from 'app/entities/tasker/tasker.service';

@Component({
  selector: 'jhi-tasker-category-update',
  templateUrl: './tasker-category-update.component.html'
})
export class TaskerCategoryUpdateComponent implements OnInit {
  isSaving: boolean;

  taskcategories: ITaskCategory[];

  taskers: ITasker[];

  editForm = this.fb.group({
    id: [],
    description: [],
    price: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected taskerCategoryService: TaskerCategoryService,
    protected taskCategoryService: TaskCategoryService,
    protected taskerService: TaskerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ taskerCategory }) => {
      this.updateForm(taskerCategory);
    });
    this.taskCategoryService
      .query()
      .subscribe(
        (res: HttpResponse<ITaskCategory[]>) => (this.taskcategories = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.taskerService
      .query()
      .subscribe((res: HttpResponse<ITasker[]>) => (this.taskers = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(taskerCategory: ITaskerCategory) {
    this.editForm.patchValue({
      id: taskerCategory.id,
      description: taskerCategory.description,
      price: taskerCategory.price,
      status: taskerCategory.status,
      createdAt: taskerCategory.createdAt != null ? taskerCategory.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: taskerCategory.updatedAt != null ? taskerCategory.updatedAt.format(DATE_TIME_FORMAT) : null,
      deletedAt: taskerCategory.deletedAt != null ? taskerCategory.deletedAt.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const taskerCategory = this.createFromForm();
    if (taskerCategory.id !== undefined) {
      this.subscribeToSaveResponse(this.taskerCategoryService.update(taskerCategory));
    } else {
      this.subscribeToSaveResponse(this.taskerCategoryService.create(taskerCategory));
    }
  }

  private createFromForm(): ITaskerCategory {
    return {
      ...new TaskerCategory(),
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      price: this.editForm.get(['price']).value,
      status: this.editForm.get(['status']).value,
      createdAt:
        this.editForm.get(['createdAt']).value != null ? moment(this.editForm.get(['createdAt']).value, DATE_TIME_FORMAT) : undefined,
      updatedAt:
        this.editForm.get(['updatedAt']).value != null ? moment(this.editForm.get(['updatedAt']).value, DATE_TIME_FORMAT) : undefined,
      deletedAt:
        this.editForm.get(['deletedAt']).value != null ? moment(this.editForm.get(['deletedAt']).value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITaskerCategory>>) {
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

  trackTaskCategoryById(index: number, item: ITaskCategory) {
    return item.id;
  }

  trackTaskerById(index: number, item: ITasker) {
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
