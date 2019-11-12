import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IRole {
  id?: number;
  name?: string;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  users?: IUser[];
}

export class Role implements IRole {
  constructor(
    public id?: number,
    public name?: string,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public users?: IUser[]
  ) {}
}
