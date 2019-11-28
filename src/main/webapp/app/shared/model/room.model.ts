import { Moment } from 'moment';

export interface IRoom {
  id?: number;
  messageId?: string;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  taskId?: number;
  taskerId?: number;
  masterId?: number;
}

export class Room implements IRoom {
  constructor(
    public id?: number,
    public messageId?: string,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public taskId?: number,
    public taskerId?: number,
    public masterId?: number
  ) {}
}
