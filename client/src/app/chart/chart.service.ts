import {Injectable} from "@angular/core";
import {IProduct} from "../_models/product";
import { StaticVariables } from '../static-variables';

@Injectable({providedIn: "root"})
export class ChartService {
  chartTotal = 0;
  readonly chartList: IProduct[];

  constructor() {
    this.chartList = StaticVariables.cartList;
  }

  // Shop sayfasından cartListe ürünleri ekleme
  addToCart(product: IProduct) {
    this.chartList.push(product);
  }

  // Chart sayafasında eklenen ürünü silme
  deleteChartItem(id: number) {
    const index = this.chartList.findIndex(x => x.id === id);
    if (index !== -1) {
      this.chartList.splice(index, 1);
    }
  }

  // Chart listesini silme.
  deleteAll() {
      this.chartList.length = 0;
    }

    // Chart listesindeki ürünleri getirme.
  findAll(): IProduct[] {
    return this.chartList.slice();
  }

  // Chart listesinden ordere aktarılan ürünleri charttan silme.
  clear() {
    this.chartList.splice(0);
  }
}
