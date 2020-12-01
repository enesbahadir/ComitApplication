import { Injectable } from '@angular/core';
import { IProduct } from '../_models/product';
import {StaticVariables} from "../static-variables";

@Injectable({ providedIn: 'root' })
export class MenuService {
  chartTotal = 0;
  products: IProduct[] = [];
  cartList: IProduct[];

  constructor() {
}
// Shoptan Menüdeki sepet listesine ürün ekleme.
  addProduct(product: IProduct) {
    this.products.push(product);
  }

  // Menü sepetinden seçilen ürünü silme.
  deleteChartProduct(id: number) {
    const index = this.products.findIndex(x => x.id === id);
    if (index !== -1) {
      this.products.splice(index, 1);
    }
  }

  findAll(): IProduct[] {
    return this.products.slice();
  }

}
