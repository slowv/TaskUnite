import { Moment } from 'moment';

export interface IMessage {
  id?: number;
  content?: string;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  taskId?: number;
  userId?: number;
}

export class Message implements IMessage {
  constructor(
    public id?: number,
    public content?: string,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public taskId?: number,
    public userId?: number
  ) {}
}
