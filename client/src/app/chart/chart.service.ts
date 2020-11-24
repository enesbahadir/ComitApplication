import {Injectable} from "@angular/core";
import {Product} from "../_models/product";
import { StaticVariables } from '../static-variables';

@Injectable({providedIn: "root"})
export class ChartService {
  cartTotal: any  = 0;
  cartList: Product[];

  constructor() {
    this.cartList = StaticVariables.cartList;
  }

  addToCart(product: Product) {
    this.cartList.push(product);
  }

  deleteChartItem(id: number) {
    const index = this.cartList.findIndex(x => x.id === id);
    if (index !== -1) {
      this.cartList.splice(index, 1);
    }
  }

  findAll(): Product[] {
    return this.cartList.slice();
  }
}
