import { Moment } from 'moment';
import { IAddress } from 'app/shared/model/address.model';
import { IReview } from 'app/shared/model/review.model';
import { INotification } from 'app/shared/model/notification.model';

export interface IUserExtend {
  id?: number;
  address?: string;
  phone?: string;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  userId?: number;
  workingAddresses?: IAddress[];
  reviews?: IReview[];
  notifications?: INotification[];
  taskerId?: number;
  masterId?: number;
  statisticId?: number;
}

export class UserExtend implements IUserExtend {
  constructor(
    public id?: number,
    public address?: string,
    public phone?: string,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public userId?: number,
    public workingAddresses?: IAddress[],
    public reviews?: IReview[],
    public notifications?: INotification[],
    public taskerId?: number,
    public masterId?: number,
    public statisticId?: number
  ) {}
}
