import { Moment } from 'moment';
import { ISchedule } from 'app/shared/model/schedule.model';
import { IReview } from 'app/shared/model/review.model';
import { ITaskCategory } from 'app/shared/model/task-category.model';

export interface ITask {
  id?: number;
  description?: string;
  estimatedTime?: number;
  price?: number;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  roomId?: number;
  schedules?: ISchedule[];
  reviews?: IReview[];
  taskCategories?: ITaskCategory[];
  taskerId?: number;
  masterId?: number;
}

export class Task implements ITask {
  constructor(
    public id?: number,
    public description?: string,
    public estimatedTime?: number,
    public price?: number,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public roomId?: number,
    public schedules?: ISchedule[],
    public reviews?: IReview[],
    public taskCategories?: ITaskCategory[],
    public taskerId?: number,
    public masterId?: number
  ) {}
}
