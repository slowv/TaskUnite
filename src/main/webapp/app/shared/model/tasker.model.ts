import { Moment } from 'moment';
import { ISchedule } from 'app/shared/model/schedule.model';
import { IRoom } from 'app/shared/model/room.model';
import { IAddress } from 'app/shared/model/address.model';
import { ITask } from 'app/shared/model/task.model';
import { ITaskerCategory } from 'app/shared/model/tasker-category.model';

export interface ITasker {
  id?: number;
  image?: string;
  description?: string;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  userId?: number;
  schedules?: ISchedule[];
  rooms?: IRoom[];
  workingAddresses?: IAddress[];
  tasks?: ITask[];
  taskerCategories?: ITaskerCategory[];
}

export class Tasker implements ITasker {
  constructor(
    public id?: number,
    public image?: string,
    public description?: string,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public userId?: number,
    public schedules?: ISchedule[],
    public rooms?: IRoom[],
    public workingAddresses?: IAddress[],
    public tasks?: ITask[],
    public taskerCategories?: ITaskerCategory[]
  ) {}
}
