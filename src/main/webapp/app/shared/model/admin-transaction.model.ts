import { Moment } from 'moment';

export interface IAdminTransaction {
  id?: number;
  amount?: number;
  type?: number;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  paymentId?: number;
}

export class AdminTransaction implements IAdminTransaction {
  constructor(
    public id?: number,
    public amount?: number,
    public type?: number,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public paymentId?: number
  ) {}
}
