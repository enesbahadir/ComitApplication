import { Order } from './_models/order';
import { Product } from './_models/product';

export class StaticVariables {
  static readonly productList: Product[] = [];
  static readonly cartList: Product[] = [];
  static readonly orderList: Order[] = [];
}
