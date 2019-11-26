import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IUserExtend, UserExtend } from 'app/shared/model/user-extend.model';
import { UserExtendService } from './user-extend.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { ITasker } from 'app/shared/model/tasker.model';
import { TaskerService } from 'app/entities/tasker/tasker.service';
import { IMaster } from 'app/shared/model/master.model';
import { MasterService } from 'app/entities/master/master.service';
import { IStatistic } from 'app/shared/model/statistic.model';
import { StatisticService } from 'app/entities/statistic/statistic.service';

@Component({
  selector: 'jhi-user-extend-update',
  templateUrl: './user-extend-update.component.html'
})
export class UserExtendUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  taskers: ITasker[];

  masters: IMaster[];

  statistics: IStatistic[];
  createdAtDp: any;
  updatedAtDp: any;
  deletedAtDp: any;

  editForm = this.fb.group({
    id: [],
    address: [],
    phone: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    userId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected userExtendService: UserExtendService,
    protected userService: UserService,
    protected taskerService: TaskerService,
    protected masterService: MasterService,
    protected statisticService: StatisticService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ userExtend }) => {
      this.updateForm(userExtend);
    });
    this.userService
      .query()
      .subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.taskerService
      .query()
      .subscribe((res: HttpResponse<ITasker[]>) => (this.taskers = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.masterService
      .query()
      .subscribe((res: HttpResponse<IMaster[]>) => (this.masters = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.statisticService
      .query()
      .subscribe((res: HttpResponse<IStatistic[]>) => (this.statistics = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(userExtend: IUserExtend) {
    this.editForm.patchValue({
      id: userExtend.id,
      address: userExtend.address,
      phone: userExtend.phone,
      status: userExtend.status,
      createdAt: userExtend.createdAt,
      updatedAt: userExtend.updatedAt,
      deletedAt: userExtend.deletedAt,
      userId: userExtend.userId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const userExtend = this.createFromForm();
    if (userExtend.id !== undefined) {
      this.subscribeToSaveResponse(this.userExtendService.update(userExtend));
    } else {
      this.subscribeToSaveResponse(this.userExtendService.create(userExtend));
    }
  }

  private createFromForm(): IUserExtend {
    return {
      ...new UserExtend(),
      id: this.editForm.get(['id']).value,
      address: this.editForm.get(['address']).value,
      phone: this.editForm.get(['phone']).value,
      status: this.editForm.get(['status']).value,
      createdAt: this.editForm.get(['createdAt']).value,
      updatedAt: this.editForm.get(['updatedAt']).value,
      deletedAt: this.editForm.get(['deletedAt']).value,
      userId: this.editForm.get(['userId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserExtend>>) {
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

  trackTaskerById(index: number, item: ITasker) {
    return item.id;
  }

  trackMasterById(index: number, item: IMaster) {
    return item.id;
  }

  trackStatisticById(index: number, item: IStatistic) {
    return item.id;
  }
}
