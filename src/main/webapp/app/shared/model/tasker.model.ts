import { Moment } from 'moment';
import { IAddress } from 'app/shared/model/address.model';
import { ITask } from 'app/shared/model/task.model';
import { ITaskCategory } from 'app/shared/model/task-category.model';

export interface ITasker {
  id?: number;
  level?: number;
  pricePerHour?: number;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  userId?: number;
  adressId?: number;
  workingAddresses?: IAddress[];
  tasks?: ITask[];
  taskCategories?: ITaskCategory[];
}

export class Tasker implements ITasker {
  constructor(
    public id?: number,
    public level?: number,
    public pricePerHour?: number,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public userId?: number,
    public adressId?: number,
    public workingAddresses?: IAddress[],
    public tasks?: ITask[],
    public taskCategories?: ITaskCategory[]
  ) {}
}
