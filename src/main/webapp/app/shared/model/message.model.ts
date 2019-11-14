import { Moment } from 'moment';

export interface IMessage {
  id?: number;
  body?: string;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  taskId?: number;
  senderId?: number;
  receiverId?: number;
}

export class Message implements IMessage {
  constructor(
    public id?: number,
    public body?: string,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public taskId?: number,
    public senderId?: number,
    public receiverId?: number
  ) {}
}
