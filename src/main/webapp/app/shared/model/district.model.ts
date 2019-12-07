import { Moment } from 'moment';
import { IUserInformation } from 'app/shared/model/user-information.model';

export interface IDistrict {
  id?: number;
  name?: string;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  users?: IUserInformation[];
  cityId?: number;
}

export class District implements IDistrict {
  constructor(
    public id?: number,
    public name?: string,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public users?: IUserInformation[],
    public cityId?: number
  ) {}
}
