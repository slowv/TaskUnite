import { Moment } from 'moment';

export interface ITaskerCategory {
  id?: number;
  description?: string;
  price?: number;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  taskCategoryId?: number;
  taskerId?: number;
}

export class TaskerCategory implements ITaskerCategory {
  constructor(
    public id?: number,
    public description?: string,
    public price?: number,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public taskCategoryId?: number,
    public taskerId?: number
  ) {}
}
