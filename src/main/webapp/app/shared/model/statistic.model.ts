import { Moment } from 'moment';

export interface IStatistic {
  id?: number;
  level?: number;
  experience?: number;
  completedTask?: number;
  rating?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  userId?: number;
}

export class Statistic implements IStatistic {
  constructor(
    public id?: number,
    public level?: number,
    public experience?: number,
    public completedTask?: number,
    public rating?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public userId?: number
  ) {}
}
