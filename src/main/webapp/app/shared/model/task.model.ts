import { Moment } from 'moment';
import { IReview } from 'app/shared/model/review.model';
import { IMessage } from 'app/shared/model/message.model';
import { IAdminProfit } from 'app/shared/model/admin-profit.model';

export interface ITask {
  id?: number;
  address?: string;
  name?: string;
  description?: string;
  price?: number;
  totalPrice?: number;
  from?: Moment;
  to?: Moment;
  duration?: number;
  type?: number;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  reviews?: IReview[];
  messages?: IMessage[];
  adminProfits?: IAdminProfit[];
  taskerId?: number;
  masterId?: number;
  taskCategoryId?: number;
}

export class Task implements ITask {
  constructor(
    public id?: number,
    public address?: string,
    public name?: string,
    public description?: string,
    public price?: number,
    public totalPrice?: number,
    public from?: Moment,
    public to?: Moment,
    public duration?: number,
    public type?: number,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public reviews?: IReview[],
    public messages?: IMessage[],
    public adminProfits?: IAdminProfit[],
    public taskerId?: number,
    public masterId?: number,
    public taskCategoryId?: number
  ) {}
}
