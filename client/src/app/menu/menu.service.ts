import { Injectable } from '@angular/core';
import { Product } from '../_models/product';
import {StaticVariables} from "../static-variables";

@Injectable({ providedIn: 'root' })
export class MenuService {
  cartTotal: any  = 0;
  products: Product[] = [];
  cartList: Product[];

  constructor() {
}

  addProduct(product: Product) {
    this.products.push(product);
  }

  deleteChartProduct(id: number) {
    const index = this.products.findIndex(x => x.id === id);
    if (index !== -1) {
      this.products.splice(index, 1);
    }
  }

  findAll(): Product[] {
    return this.products.slice();
  }

}
