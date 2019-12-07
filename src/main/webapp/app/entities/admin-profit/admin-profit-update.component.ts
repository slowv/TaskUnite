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
import { IAdminProfit, AdminProfit } from 'app/shared/model/admin-profit.model';
import { AdminProfitService } from './admin-profit.service';
import { ITask } from 'app/shared/model/task.model';
import { TaskService } from 'app/entities/task/task.service';

@Component({
  selector: 'jhi-admin-profit-update',
  templateUrl: './admin-profit-update.component.html'
})
export class AdminProfitUpdateComponent implements OnInit {
  isSaving: boolean;

  tasks: ITask[];

  editForm = this.fb.group({
    id: [],
    taskerProfit: [],
    masterProfit: [],
    totalProfit: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    taskId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected adminProfitService: AdminProfitService,
    protected taskService: TaskService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ adminProfit }) => {
      this.updateForm(adminProfit);
    });
    this.taskService
      .query()
      .subscribe((res: HttpResponse<ITask[]>) => (this.tasks = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(adminProfit: IAdminProfit) {
    this.editForm.patchValue({
      id: adminProfit.id,
      taskerProfit: adminProfit.taskerProfit,
      masterProfit: adminProfit.masterProfit,
      totalProfit: adminProfit.totalProfit,
      createdAt: adminProfit.createdAt != null ? adminProfit.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: adminProfit.updatedAt != null ? adminProfit.updatedAt.format(DATE_TIME_FORMAT) : null,
      deletedAt: adminProfit.deletedAt != null ? adminProfit.deletedAt.format(DATE_TIME_FORMAT) : null,
      taskId: adminProfit.taskId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const adminProfit = this.createFromForm();
    if (adminProfit.id !== undefined) {
      this.subscribeToSaveResponse(this.adminProfitService.update(adminProfit));
    } else {
      this.subscribeToSaveResponse(this.adminProfitService.create(adminProfit));
    }
  }

  private createFromForm(): IAdminProfit {
    return {
      ...new AdminProfit(),
      id: this.editForm.get(['id']).value,
      taskerProfit: this.editForm.get(['taskerProfit']).value,
      masterProfit: this.editForm.get(['masterProfit']).value,
      totalProfit: this.editForm.get(['totalProfit']).value,
      createdAt:
        this.editForm.get(['createdAt']).value != null ? moment(this.editForm.get(['createdAt']).value, DATE_TIME_FORMAT) : undefined,
      updatedAt:
        this.editForm.get(['updatedAt']).value != null ? moment(this.editForm.get(['updatedAt']).value, DATE_TIME_FORMAT) : undefined,
      deletedAt:
        this.editForm.get(['deletedAt']).value != null ? moment(this.editForm.get(['deletedAt']).value, DATE_TIME_FORMAT) : undefined,
      taskId: this.editForm.get(['taskId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdminProfit>>) {
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
}
