import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IAddress, Address } from 'app/shared/model/address.model';
import { AddressService } from './address.service';
import { IDistrict } from 'app/shared/model/district.model';
import { DistrictService } from 'app/entities/district/district.service';
import { IUserExtend } from 'app/shared/model/user-extend.model';
import { UserExtendService } from 'app/entities/user-extend/user-extend.service';

@Component({
  selector: 'jhi-address-update',
  templateUrl: './address-update.component.html'
})
export class AddressUpdateComponent implements OnInit {
  isSaving: boolean;

  districts: IDistrict[];

  userextends: IUserExtend[];
  createdAtDp: any;
  updatedAtDp: any;
  deletedAtDp: any;

  editForm = this.fb.group({
    id: [],
    street: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    dictrictId: [],
    userId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected addressService: AddressService,
    protected districtService: DistrictService,
    protected userExtendService: UserExtendService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ address }) => {
      this.updateForm(address);
    });
    this.districtService
      .query()
      .subscribe((res: HttpResponse<IDistrict[]>) => (this.districts = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.userExtendService
      .query()
      .subscribe(
        (res: HttpResponse<IUserExtend[]>) => (this.userextends = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(address: IAddress) {
    this.editForm.patchValue({
      id: address.id,
      street: address.street,
      status: address.status,
      createdAt: address.createdAt,
      updatedAt: address.updatedAt,
      deletedAt: address.deletedAt,
      dictrictId: address.dictrictId,
      userId: address.userId
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
      createdAt: this.editForm.get(['createdAt']).value,
      updatedAt: this.editForm.get(['updatedAt']).value,
      deletedAt: this.editForm.get(['deletedAt']).value,
      dictrictId: this.editForm.get(['dictrictId']).value,
      userId: this.editForm.get(['userId']).value
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

  trackDistrictById(index: number, item: IDistrict) {
    return item.id;
  }

  trackUserExtendById(index: number, item: IUserExtend) {
    return item.id;
  }
}
