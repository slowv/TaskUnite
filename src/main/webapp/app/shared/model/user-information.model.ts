import { Moment } from 'moment';
import { IReview } from 'app/shared/model/review.model';
import { INotification } from 'app/shared/model/notification.model';

export interface IUserInformation {
  id?: number;
  gender?: number;
  phone?: string;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  userId?: number;
  addressId?: number;
  reviews?: IReview[];
  notifications?: INotification[];
  taskerId?: number;
  masterId?: number;
  statisticId?: number;
}

export class UserInformation implements IUserInformation {
  constructor(
    public id?: number,
    public gender?: number,
    public phone?: string,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public userId?: number,
    public addressId?: number,
    public reviews?: IReview[],
    public notifications?: INotification[],
    public taskerId?: number,
    public masterId?: number,
    public statisticId?: number
  ) {}
}
