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
import { IUserInformation, UserInformation } from 'app/shared/model/user-information.model';
import { UserInformationService } from './user-information.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IStatistic } from 'app/shared/model/statistic.model';
import { StatisticService } from 'app/entities/statistic/statistic.service';
import { IPaymentInformation } from 'app/shared/model/payment-information.model';
import { PaymentInformationService } from 'app/entities/payment-information/payment-information.service';
import { IDistrict } from 'app/shared/model/district.model';
import { DistrictService } from 'app/entities/district/district.service';

@Component({
  selector: 'jhi-user-information-update',
  templateUrl: './user-information-update.component.html'
})
export class UserInformationUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  statistics: IStatistic[];

  paymentinformations: IPaymentInformation[];

  districts: IDistrict[];

  editForm = this.fb.group({
    id: [],
    address: [],
    bday: [],
    bmonth: [],
    byear: [],
    gender: [],
    phone: [],
    image: [],
    description: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    userId: [],
    districtId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected userInformationService: UserInformationService,
    protected userService: UserService,
    protected statisticService: StatisticService,
    protected paymentInformationService: PaymentInformationService,
    protected districtService: DistrictService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ userInformation }) => {
      this.updateForm(userInformation);
    });
    this.userService
      .query()
      .subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.statisticService
      .query()
      .subscribe((res: HttpResponse<IStatistic[]>) => (this.statistics = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.paymentInformationService
      .query()
      .subscribe(
        (res: HttpResponse<IPaymentInformation[]>) => (this.paymentinformations = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.districtService
      .query()
      .subscribe((res: HttpResponse<IDistrict[]>) => (this.districts = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(userInformation: IUserInformation) {
    this.editForm.patchValue({
      id: userInformation.id,
      address: userInformation.address,
      bday: userInformation.bday,
      bmonth: userInformation.bmonth,
      byear: userInformation.byear,
      gender: userInformation.gender,
      phone: userInformation.phone,
      image: userInformation.image,
      description: userInformation.description,
      status: userInformation.status,
      createdAt: userInformation.createdAt != null ? userInformation.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: userInformation.updatedAt != null ? userInformation.updatedAt.format(DATE_TIME_FORMAT) : null,
      deletedAt: userInformation.deletedAt != null ? userInformation.deletedAt.format(DATE_TIME_FORMAT) : null,
      userId: userInformation.userId,
      districtId: userInformation.districtId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const userInformation = this.createFromForm();
    if (userInformation.id !== undefined) {
      this.subscribeToSaveResponse(this.userInformationService.update(userInformation));
    } else {
      this.subscribeToSaveResponse(this.userInformationService.create(userInformation));
    }
  }

  private createFromForm(): IUserInformation {
    return {
      ...new UserInformation(),
      id: this.editForm.get(['id']).value,
      address: this.editForm.get(['address']).value,
      bday: this.editForm.get(['bday']).value,
      bmonth: this.editForm.get(['bmonth']).value,
      byear: this.editForm.get(['byear']).value,
      gender: this.editForm.get(['gender']).value,
      phone: this.editForm.get(['phone']).value,
      image: this.editForm.get(['image']).value,
      description: this.editForm.get(['description']).value,
      status: this.editForm.get(['status']).value,
      createdAt:
        this.editForm.get(['createdAt']).value != null ? moment(this.editForm.get(['createdAt']).value, DATE_TIME_FORMAT) : undefined,
      updatedAt:
        this.editForm.get(['updatedAt']).value != null ? moment(this.editForm.get(['updatedAt']).value, DATE_TIME_FORMAT) : undefined,
      deletedAt:
        this.editForm.get(['deletedAt']).value != null ? moment(this.editForm.get(['deletedAt']).value, DATE_TIME_FORMAT) : undefined,
      userId: this.editForm.get(['userId']).value,
      districtId: this.editForm.get(['districtId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserInformation>>) {
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

  trackStatisticById(index: number, item: IStatistic) {
    return item.id;
  }

  trackPaymentInformationById(index: number, item: IPaymentInformation) {
    return item.id;
  }

  trackDistrictById(index: number, item: IDistrict) {
    return item.id;
  }
}
