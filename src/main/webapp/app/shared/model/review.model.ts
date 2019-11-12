import { Moment } from 'moment';

export interface IReview {
  id?: number;
  content?: string;
  start?: number;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  userId?: number;
  taskId?: number;
}

export class Review implements IReview {
  constructor(
    public id?: number,
    public content?: string,
    public start?: number,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public userId?: number,
    public taskId?: number
  ) {}
}
