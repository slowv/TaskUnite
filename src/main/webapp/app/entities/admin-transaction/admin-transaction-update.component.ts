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
import { IAdminTransaction, AdminTransaction } from 'app/shared/model/admin-transaction.model';
import { AdminTransactionService } from './admin-transaction.service';
import { IPaymentInformation } from 'app/shared/model/payment-information.model';
import { PaymentInformationService } from 'app/entities/payment-information/payment-information.service';

@Component({
  selector: 'jhi-admin-transaction-update',
  templateUrl: './admin-transaction-update.component.html'
})
export class AdminTransactionUpdateComponent implements OnInit {
  isSaving: boolean;

  paymentinformations: IPaymentInformation[];

  editForm = this.fb.group({
    id: [],
    amount: [],
    type: [],
    status: [],
    createdAt: [],
    updatedAt: [],
    deletedAt: [],
    paymentId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected adminTransactionService: AdminTransactionService,
    protected paymentInformationService: PaymentInformationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ adminTransaction }) => {
      this.updateForm(adminTransaction);
    });
    this.paymentInformationService
      .query()
      .subscribe(
        (res: HttpResponse<IPaymentInformation[]>) => (this.paymentinformations = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(adminTransaction: IAdminTransaction) {
    this.editForm.patchValue({
      id: adminTransaction.id,
      amount: adminTransaction.amount,
      type: adminTransaction.type,
      status: adminTransaction.status,
      createdAt: adminTransaction.createdAt != null ? adminTransaction.createdAt.format(DATE_TIME_FORMAT) : null,
      updatedAt: adminTransaction.updatedAt != null ? adminTransaction.updatedAt.format(DATE_TIME_FORMAT) : null,
      deletedAt: adminTransaction.deletedAt != null ? adminTransaction.deletedAt.format(DATE_TIME_FORMAT) : null,
      paymentId: adminTransaction.paymentId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const adminTransaction = this.createFromForm();
    if (adminTransaction.id !== undefined) {
      this.subscribeToSaveResponse(this.adminTransactionService.update(adminTransaction));
    } else {
      this.subscribeToSaveResponse(this.adminTransactionService.create(adminTransaction));
    }
  }

  private createFromForm(): IAdminTransaction {
    return {
      ...new AdminTransaction(),
      id: this.editForm.get(['id']).value,
      amount: this.editForm.get(['amount']).value,
      type: this.editForm.get(['type']).value,
      status: this.editForm.get(['status']).value,
      createdAt:
        this.editForm.get(['createdAt']).value != null ? moment(this.editForm.get(['createdAt']).value, DATE_TIME_FORMAT) : undefined,
      updatedAt:
        this.editForm.get(['updatedAt']).value != null ? moment(this.editForm.get(['updatedAt']).value, DATE_TIME_FORMAT) : undefined,
      deletedAt:
        this.editForm.get(['deletedAt']).value != null ? moment(this.editForm.get(['deletedAt']).value, DATE_TIME_FORMAT) : undefined,
      paymentId: this.editForm.get(['paymentId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdminTransaction>>) {
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

  trackPaymentInformationById(index: number, item: IPaymentInformation) {
    return item.id;
  }
}
