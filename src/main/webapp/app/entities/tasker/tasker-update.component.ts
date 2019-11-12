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
import { ITasker, Tasker } from 'app/shared/model/tasker.model';
import { TaskerService } from './tasker.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IAddress } from 'app/shared/model/address.model';
import { AddressService } from 'app/entities/address/address.service';
import { ITaskCategory } from 'app/shared/model/task-category.model';
import { TaskCategoryService } from 'app/entities/task-category/task-category.service';

@Component({
  selector: 'jhi-tasker-update',
  templateUrl: './tasker-update.component.html'
})
export class TaskerUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  adresses: IAddress[];

  taskcategories: ITaskCategory[];

  editForm = this.fb.group({
    id: [],
    level: [],
    pricePerHour: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    userId: [],
    adressId: [],
    taskCategories: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected taskerService: TaskerService,
    protected userService: UserService,
    protected addressService: AddressService,
    protected taskCategoryService: TaskCategoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tasker }) => {
      this.updateForm(tasker);
    });
    this.userService
      .query()
      .subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.addressService.query({ filter: 'tasker-is-null' }).subscribe(
      (res: HttpResponse<IAddress[]>) => {
        if (!this.editForm.get('adressId').value) {
          this.adresses = res.body;
        } else {
          this.addressService
            .find(this.editForm.get('adressId').value)
            .subscribe(
              (subRes: HttpResponse<IAddress>) => (this.adresses = [subRes.body].concat(res.body)),
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
  }

  updateForm(tasker: ITasker) {
    this.editForm.patchValue({
      id: tasker.id,
      level: tasker.level,
      pricePerHour: tasker.pricePerHour,
      status: tasker.status,
      createdAt: tasker.createdAt != null ? tasker.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: tasker.updatedAt != null ? tasker.updatedAt.format(DATE_TIME_FORMAT) : null,
      deletedAt: tasker.deletedAt != null ? tasker.deletedAt.format(DATE_TIME_FORMAT) : null,
      userId: tasker.userId,
      adressId: tasker.adressId,
      taskCategories: tasker.taskCategories
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const tasker = this.createFromForm();
    if (tasker.id !== undefined) {
      this.subscribeToSaveResponse(this.taskerService.update(tasker));
    } else {
      this.subscribeToSaveResponse(this.taskerService.create(tasker));
    }
  }

  private createFromForm(): ITasker {
    return {
      ...new Tasker(),
      id: this.editForm.get(['id']).value,
      level: this.editForm.get(['level']).value,
      pricePerHour: this.editForm.get(['pricePerHour']).value,
      status: this.editForm.get(['status']).value,
      createdAt:
        this.editForm.get(['createdAt']).value != null ? moment(this.editForm.get(['createdAt']).value, DATE_TIME_FORMAT) : undefined,
      updatedAt:
        this.editForm.get(['updatedAt']).value != null ? moment(this.editForm.get(['updatedAt']).value, DATE_TIME_FORMAT) : undefined,
      deletedAt:
        this.editForm.get(['deletedAt']).value != null ? moment(this.editForm.get(['deletedAt']).value, DATE_TIME_FORMAT) : undefined,
      userId: this.editForm.get(['userId']).value,
      adressId: this.editForm.get(['adressId']).value,
      taskCategories: this.editForm.get(['taskCategories']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITasker>>) {
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

  trackUserById(index: number, item: IUser) {
    return item.id;
  }

  trackAddressById(index: number, item: IAddress) {
    return item.id;
  }

  trackTaskCategoryById(index: number, item: ITaskCategory) {
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
