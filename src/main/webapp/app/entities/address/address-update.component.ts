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
import { IAddress, Address } from 'app/shared/model/address.model';
import { AddressService } from './address.service';
import { IUserInformation } from 'app/shared/model/user-information.model';
import { UserInformationService } from 'app/entities/user-information/user-information.service';
import { IDistrict } from 'app/shared/model/district.model';
import { DistrictService } from 'app/entities/district/district.service';
import { ITasker } from 'app/shared/model/tasker.model';
import { TaskerService } from 'app/entities/tasker/tasker.service';
import { IMaster } from 'app/shared/model/master.model';
import { MasterService } from 'app/entities/master/master.service';

@Component({
  selector: 'jhi-address-update',
  templateUrl: './address-update.component.html'
})
export class AddressUpdateComponent implements OnInit {
  isSaving: boolean;

  userinformations: IUserInformation[];

  districts: IDistrict[];

  taskers: ITasker[];

  masters: IMaster[];

  editForm = this.fb.group({
    id: [],
    content: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    dictrictId: [],
    taskerId: [],
    masterId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected addressService: AddressService,
    protected userInformationService: UserInformationService,
    protected districtService: DistrictService,
    protected taskerService: TaskerService,
    protected masterService: MasterService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ address }) => {
      this.updateForm(address);
    });
    this.userInformationService
      .query()
      .subscribe(
        (res: HttpResponse<IUserInformation[]>) => (this.userinformations = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.districtService
      .query()
      .subscribe((res: HttpResponse<IDistrict[]>) => (this.districts = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.taskerService
      .query()
      .subscribe((res: HttpResponse<ITasker[]>) => (this.taskers = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.masterService
      .query()
      .subscribe((res: HttpResponse<IMaster[]>) => (this.masters = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(address: IAddress) {
    this.editForm.patchValue({
      id: address.id,
      content: address.content,
      status: address.status,
      createdAt: address.createdAt != null ? address.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: address.updatedAt != null ? address.updatedAt.format(DATE_TIME_FORMAT) : null,
      deletedAt: address.deletedAt != null ? address.deletedAt.format(DATE_TIME_FORMAT) : null,
      dictrictId: address.dictrictId,
      taskerId: address.taskerId,
      masterId: address.masterId
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
      content: this.editForm.get(['content']).value,
      status: this.editForm.get(['status']).value,
      createdAt:
        this.editForm.get(['createdAt']).value != null ? moment(this.editForm.get(['createdAt']).value, DATE_TIME_FORMAT) : undefined,
      updatedAt:
        this.editForm.get(['updatedAt']).value != null ? moment(this.editForm.get(['updatedAt']).value, DATE_TIME_FORMAT) : undefined,
      deletedAt:
        this.editForm.get(['deletedAt']).value != null ? moment(this.editForm.get(['deletedAt']).value, DATE_TIME_FORMAT) : undefined,
      dictrictId: this.editForm.get(['dictrictId']).value,
      taskerId: this.editForm.get(['taskerId']).value,
      masterId: this.editForm.get(['masterId']).value
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

  trackUserInformationById(index: number, item: IUserInformation) {
    return item.id;
  }

  trackDistrictById(index: number, item: IDistrict) {
    return item.id;
  }

  trackTaskerById(index: number, item: ITasker) {
    return item.id;
  }

  trackMasterById(index: number, item: IMaster) {
    return item.id;
  }
}
