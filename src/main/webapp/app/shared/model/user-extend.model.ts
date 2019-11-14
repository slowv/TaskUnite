import { IMessage } from 'app/shared/model/message.model';
import { IReview } from 'app/shared/model/review.model';
import { INotification } from 'app/shared/model/notification.model';

export interface IUserExtend {
  id?: number;
  name?: string;
  userLoginId?: number;
  sendingMessages?: IMessage[];
  receivingMessages?: IMessage[];
  reviews?: IReview[];
  notifications?: INotification[];
  taskerId?: number;
  masterId?: number;
  addressId?: number;
}

export class UserExtend implements IUserExtend {
  constructor(
    public id?: number,
    public name?: string,
    public userLoginId?: number,
    public sendingMessages?: IMessage[],
    public receivingMessages?: IMessage[],
    public reviews?: IReview[],
    public notifications?: INotification[],
    public taskerId?: number,
    public masterId?: number,
    public addressId?: number
  ) {}
}
