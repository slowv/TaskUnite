import { Moment } from 'moment';
import { IMessage } from 'app/shared/model/message.model';
import { IReview } from 'app/shared/model/review.model';
import { ITaskCategory } from 'app/shared/model/task-category.model';

export interface ITask {
  id?: number;
  title?: string;
  description?: string;
  planDate?: Moment;
  totalPrice?: number;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  messages?: IMessage[];
  reviews?: IReview[];
  taskCategories?: ITaskCategory[];
  taskerId?: number;
  masterId?: number;
}

export class Task implements ITask {
  constructor(
    public id?: number,
    public title?: string,
    public description?: string,
    public planDate?: Moment,
    public totalPrice?: number,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public messages?: IMessage[],
    public reviews?: IReview[],
    public taskCategories?: ITaskCategory[],
    public taskerId?: number,
    public masterId?: number
  ) {}
}
