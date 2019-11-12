import { Moment } from 'moment';

export interface IMessage {
  id?: number;
  body?: string;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  senderId?: number;
  receiverId?: number;
  taskId?: number;
}

export class Message implements IMessage {
  constructor(
    public id?: number,
    public body?: string,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public senderId?: number,
    public receiverId?: number,
    public taskId?: number
  ) {}
}
