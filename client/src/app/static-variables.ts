import { IOrder } from './_models/order';
import { IProduct } from './_models/product';

export class StaticVariables {
  static readonly productList: IProduct[] = [];
  static readonly cartList: IProduct[] = [];
  static readonly orderList: IOrder[] = [];
}
