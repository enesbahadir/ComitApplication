import { IProduct } from './product';
import { IUser} from "./user";

export interface IOrder {
  products: IProduct[];
  user : IUser;
  date?: Date;
}
