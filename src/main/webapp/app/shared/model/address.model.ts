import { Moment } from 'moment';

export interface IAddress {
  id?: number;
  content?: string;
  status?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  userId?: number;
  districtId?: number;
  taskerId?: number;
  masterId?: number;
}

export class Address implements IAddress {
  constructor(
    public id?: number,
    public content?: string,
    public status?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public userId?: number,
    public districtId?: number,
    public taskerId?: number,
    public masterId?: number
  ) {}
}
