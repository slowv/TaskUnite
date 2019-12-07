import { Moment } from 'moment';
import { IAdminTransaction } from 'app/shared/model/admin-transaction.model';

export interface IPaymentInformation {
  id?: number;
  balance?: number;
  hold?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  userId?: number;
  adminTransactions?: IAdminTransaction[];
}

export class PaymentInformation implements IPaymentInformation {
  constructor(
    public id?: number,
    public balance?: number,
    public hold?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public userId?: number,
    public adminTransactions?: IAdminTransaction[]
  ) {}
}
