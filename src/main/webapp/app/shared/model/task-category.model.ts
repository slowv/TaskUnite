import { Moment } from 'moment';
import { ITask } from 'app/shared/model/task.model';

export interface ITaskCategory {
  id?: number;
  name?: string;
  description?: string;
  image?: string;
  minPrice?: number;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  taskerCategoryId?: number;
  tasks?: ITask[];
}

export class TaskCategory implements ITaskCategory {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public image?: string,
    public minPrice?: number,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public taskerCategoryId?: number,
    public tasks?: ITask[]
  ) {}
}
