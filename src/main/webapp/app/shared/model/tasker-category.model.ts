import { Moment } from 'moment';
import { ITask } from 'app/shared/model/task.model';

export interface ITaskerCategory {
  id?: number;
  description?: string;
  price?: number;
  type?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  tasks?: ITask[];
  taskCategoryId?: number;
  userId?: number;
}

export class TaskerCategory implements ITaskerCategory {
  constructor(
    public id?: number,
    public description?: string,
    public price?: number,
    public type?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public tasks?: ITask[],
    public taskCategoryId?: number,
    public userId?: number
  ) {}
}
