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
import { IDistrict, District } from 'app/shared/model/district.model';
import { DistrictService } from './district.service';
import { ICity } from 'app/shared/model/city.model';
import { CityService } from 'app/entities/city/city.service';

@Component({
  selector: 'jhi-district-update',
  templateUrl: './district-update.component.html'
})
export class DistrictUpdateComponent implements OnInit {
  isSaving: boolean;

  cities: ICity[];

  editForm = this.fb.group({
    id: [],
    name: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    cityId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected districtService: DistrictService,
    protected cityService: CityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ district }) => {
      this.updateForm(district);
    });
    this.cityService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICity[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICity[]>) => response.body)
      )
      .subscribe((res: ICity[]) => (this.cities = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(district: IDistrict) {
    this.editForm.patchValue({
      id: district.id,
      name: district.name,
      status: district.status,
      createdAt: district.createdAt != null ? district.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: district.updatedAt != null ? district.updatedAt.format(DATE_TIME_FORMAT) : null,
      deletedAt: district.deletedAt != null ? district.deletedAt.format(DATE_TIME_FORMAT) : null,
      cityId: district.cityId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const district = this.createFromForm();
    if (district.id !== undefined) {
      this.subscribeToSaveResponse(this.districtService.update(district));
    } else {
      this.subscribeToSaveResponse(this.districtService.create(district));
    }
  }

  private createFromForm(): IDistrict {
    return {
      ...new District(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      status: this.editForm.get(['status']).value,
      createdAt:
        this.editForm.get(['createdAt']).value != null ? moment(this.editForm.get(['createdAt']).value, DATE_TIME_FORMAT) : undefined,
      updatedAt:
        this.editForm.get(['updatedAt']).value != null ? moment(this.editForm.get(['updatedAt']).value, DATE_TIME_FORMAT) : undefined,
      deletedAt:
        this.editForm.get(['deletedAt']).value != null ? moment(this.editForm.get(['deletedAt']).value, DATE_TIME_FORMAT) : undefined,
      cityId: this.editForm.get(['cityId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDistrict>>) {
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

  trackCityById(index: number, item: ICity) {
    return item.id;
  }
}
