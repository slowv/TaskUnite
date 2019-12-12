import { Moment } from 'moment';
import { IMessage } from 'app/shared/model/message.model';
import { ITask } from 'app/shared/model/task.model';
import { IReview } from 'app/shared/model/review.model';
import { INotification } from 'app/shared/model/notification.model';
import { ITaskerCategory } from 'app/shared/model/tasker-category.model';

export interface IUserInformation {
  id?: number;
  address?: string;
  bday?: number;
  bmonth?: number;
  byear?: number;
  gender?: number;
  phone?: string;
  image?: string;
  description?: string;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  userId?: number;
  messages?: IMessage[];
  tasksAsTaskers?: ITask[];
  tasksAsMasters?: ITask[];
  reviews?: IReview[];
  notifications?: INotification[];
  taskerCategories?: ITaskerCategory[];
  statisticId?: number;
  paymentId?: number;
  districtId?: number;
}

export class UserInformation implements IUserInformation {
  constructor(
    public id?: number,
    public address?: string,
    public bday?: number,
    public bmonth?: number,
    public byear?: number,
    public gender?: number,
    public phone?: string,
    public image?: string,
    public description?: string,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public userId?: number,
    public messages?: IMessage[],
    public tasksAsTaskers?: ITask[],
    public tasksAsMasters?: ITask[],
    public reviews?: IReview[],
    public notifications?: INotification[],
    public taskerCategories?: ITaskerCategory[],
    public statisticId?: number,
    public paymentId?: number,
    public districtId?: number
  ) {}
}
