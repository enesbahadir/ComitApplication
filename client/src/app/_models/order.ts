import { Product } from './product';
import {User} from "./user";

export interface Order {
  products: Product[];
  user : User;
  date?: Date;
}
