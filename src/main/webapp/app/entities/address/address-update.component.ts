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
import { IAddress, Address } from 'app/shared/model/address.model';
import { AddressService } from './address.service';
import { IUserExtend } from 'app/shared/model/user-extend.model';
import { UserExtendService } from 'app/entities/user-extend/user-extend.service';
import { IDistrict } from 'app/shared/model/district.model';
import { DistrictService } from 'app/entities/district/district.service';
import { ITasker } from 'app/shared/model/tasker.model';
import { TaskerService } from 'app/entities/tasker/tasker.service';

@Component({
  selector: 'jhi-address-update',
  templateUrl: './address-update.component.html'
})
export class AddressUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUserExtend[];

  districts: IDistrict[];

  taskers: ITasker[];

  editForm = this.fb.group({
    id: [],
    street: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    userId: [],
    dictrictId: [],
    taskerId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected addressService: AddressService,
    protected userExtendService: UserExtendService,
    protected districtService: DistrictService,
    protected taskerService: TaskerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ address }) => {
      this.updateForm(address);
    });
    this.userExtendService
      .query({ filter: 'address-is-null' })
      .pipe(
        filter((mayBeOk: HttpResponse<IUserExtend[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUserExtend[]>) => response.body)
      )
      .subscribe(
        (res: IUserExtend[]) => {
          if (!this.editForm.get('userId').value) {
            this.users = res;
          } else {
            this.userExtendService
              .find(this.editForm.get('userId').value)
              .pipe(
                filter((subResMayBeOk: HttpResponse<IUserExtend>) => subResMayBeOk.ok),
                map((subResponse: HttpResponse<IUserExtend>) => subResponse.body)
              )
              .subscribe(
                (subRes: IUserExtend) => (this.users = [subRes].concat(res)),
                (subRes: HttpErrorResponse) => this.onError(subRes.message)
              );
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.districtService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IDistrict[]>) => mayBeOk.ok),
        map((response: HttpResponse<IDistrict[]>) => response.body)
      )
      .subscribe((res: IDistrict[]) => (this.districts = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.taskerService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ITasker[]>) => mayBeOk.ok),
        map((response: HttpResponse<ITasker[]>) => response.body)
      )
      .subscribe((res: ITasker[]) => (this.taskers = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(address: IAddress) {
    this.editForm.patchValue({
      id: address.id,
      street: address.street,
      status: address.status,
      createdAt: address.createdAt != null ? address.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: address.updatedAt != null ? address.updatedAt.format(DATE_TIME_FORMAT) : null,
      deletedAt: address.deletedAt != null ? address.deletedAt.format(DATE_TIME_FORMAT) : null,
      userId: address.userId,
      dictrictId: address.dictrictId,
      taskerId: address.taskerId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const address = this.createFromForm();
    if (address.id !== undefined) {
      this.subscribeToSaveResponse(this.addressService.update(address));
    } else {
      this.subscribeToSaveResponse(this.addressService.create(address));
    }
  }

  private createFromForm(): IAddress {
    return {
      ...new Address(),
      id: this.editForm.get(['id']).value,
      street: this.editForm.get(['street']).value,
      status: this.editForm.get(['status']).value,
      createdAt:
        this.editForm.get(['createdAt']).value != null ? moment(this.editForm.get(['createdAt']).value, DATE_TIME_FORMAT) : undefined,
      updatedAt:
        this.editForm.get(['updatedAt']).value != null ? moment(this.editForm.get(['updatedAt']).value, DATE_TIME_FORMAT) : undefined,
      deletedAt:
        this.editForm.get(['deletedAt']).value != null ? moment(this.editForm.get(['deletedAt']).value, DATE_TIME_FORMAT) : undefined,
      userId: this.editForm.get(['userId']).value,
      dictrictId: this.editForm.get(['dictrictId']).value,
      taskerId: this.editForm.get(['taskerId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAddress>>) {
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

  trackUserExtendById(index: number, item: IUserExtend) {
    return item.id;
  }

  trackDistrictById(index: number, item: IDistrict) {
    return item.id;
  }

  trackTaskerById(index: number, item: ITasker) {
    return item.id;
  }
}
