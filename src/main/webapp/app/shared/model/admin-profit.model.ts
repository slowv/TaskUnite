import { Moment } from 'moment';

export interface IAdminProfit {
  id?: number;
  taskerProfit?: number;
  masterProfit?: number;
  totalProfit?: number;
  createdAt?: Moment;
  updatedAt?: Moment;
  deletedAt?: Moment;
  taskId?: number;
}

export class AdminProfit implements IAdminProfit {
  constructor(
    public id?: number,
    public taskerProfit?: number,
    public masterProfit?: number,
    public totalProfit?: number,
    public createdAt?: Moment,
    public updatedAt?: Moment,
    public deletedAt?: Moment,
    public taskId?: number
  ) {}
}
