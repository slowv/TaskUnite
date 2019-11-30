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
import { IMaster, Master } from 'app/shared/model/master.model';
import { MasterService } from './master.service';
import { IUserInformation } from 'app/shared/model/user-information.model';
import { UserInformationService } from 'app/entities/user-information/user-information.service';

@Component({
  selector: 'jhi-master-update',
  templateUrl: './master-update.component.html'
})
export class MasterUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUserInformation[];

  editForm = this.fb.group({
    id: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    userId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected masterService: MasterService,
    protected userInformationService: UserInformationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ master }) => {
      this.updateForm(master);
    });
    this.userInformationService.query({ filter: 'master-is-null' }).subscribe(
      (res: HttpResponse<IUserInformation[]>) => {
        if (!this.editForm.get('userId').value) {
          this.users = res.body;
        } else {
          this.userInformationService
            .find(this.editForm.get('userId').value)
            .subscribe(
              (subRes: HttpResponse<IUserInformation>) => (this.users = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  updateForm(master: IMaster) {
    this.editForm.patchValue({
      id: master.id,
      status: master.status,
      createdAt: master.createdAt != null ? master.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: master.updatedAt != null ? master.updatedAt.format(DATE_TIME_FORMAT) : null,
      deletedAt: master.deletedAt != null ? master.deletedAt.format(DATE_TIME_FORMAT) : null,
      userId: master.userId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const master = this.createFromForm();
    if (master.id !== undefined) {
      this.subscribeToSaveResponse(this.masterService.update(master));
    } else {
      this.subscribeToSaveResponse(this.masterService.create(master));
    }
  }

  private createFromForm(): IMaster {
    return {
      ...new Master(),
      id: this.editForm.get(['id']).value,
      status: this.editForm.get(['status']).value,
      createdAt:
        this.editForm.get(['createdAt']).value != null ? moment(this.editForm.get(['createdAt']).value, DATE_TIME_FORMAT) : undefined,
      updatedAt:
        this.editForm.get(['updatedAt']).value != null ? moment(this.editForm.get(['updatedAt']).value, DATE_TIME_FORMAT) : undefined,
      deletedAt:
        this.editForm.get(['deletedAt']).value != null ? moment(this.editForm.get(['deletedAt']).value, DATE_TIME_FORMAT) : undefined,
      userId: this.editForm.get(['userId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMaster>>) {
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
}
