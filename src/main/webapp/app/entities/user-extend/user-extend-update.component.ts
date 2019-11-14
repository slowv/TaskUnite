import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IUserExtend, UserExtend } from 'app/shared/model/user-extend.model';
import { UserExtendService } from './user-extend.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { ITasker } from 'app/shared/model/tasker.model';
import { TaskerService } from 'app/entities/tasker/tasker.service';
import { IMaster } from 'app/shared/model/master.model';
import { MasterService } from 'app/entities/master/master.service';
import { IAddress } from 'app/shared/model/address.model';
import { AddressService } from 'app/entities/address/address.service';

@Component({
  selector: 'jhi-user-extend-update',
  templateUrl: './user-extend-update.component.html'
})
export class UserExtendUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  taskers: ITasker[];

  masters: IMaster[];

  addresses: IAddress[];

  editForm = this.fb.group({
    id: [],
    name: [],
    userLoginId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected userExtendService: UserExtendService,
    protected userService: UserService,
    protected taskerService: TaskerService,
    protected masterService: MasterService,
    protected addressService: AddressService,
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
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.taskerService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ITasker[]>) => mayBeOk.ok),
        map((response: HttpResponse<ITasker[]>) => response.body)
      )
      .subscribe((res: ITasker[]) => (this.taskers = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.masterService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMaster[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMaster[]>) => response.body)
      )
      .subscribe((res: IMaster[]) => (this.masters = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.addressService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IAddress[]>) => mayBeOk.ok),
        map((response: HttpResponse<IAddress[]>) => response.body)
      )
      .subscribe((res: IAddress[]) => (this.addresses = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(userExtend: IUserExtend) {
    this.editForm.patchValue({
      id: userExtend.id,
      name: userExtend.name,
      userLoginId: userExtend.userLoginId
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
      name: this.editForm.get(['name']).value,
      userLoginId: this.editForm.get(['userLoginId']).value
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

  trackAddressById(index: number, item: IAddress) {
    return item.id;
  }
}
