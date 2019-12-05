import { Moment } from 'moment';
import { IRoom } from 'app/shared/model/room.model';
import { ITask } from 'app/shared/model/task.model';

export interface IMaster {
  id?: number;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  userId?: number;
  rooms?: IRoom[];
  tasks?: ITask[];
}

export class Master implements IMaster {
  constructor(
    public id?: number,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public userId?: number,
    public rooms?: IRoom[],
    public tasks?: ITask[]
  ) {}
}
