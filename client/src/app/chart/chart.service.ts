import {Injectable} from "@angular/core";
import {Product} from "../_models/product";

@Injectable({providedIn: "root"})
export class ChartService {

    products: Product[] = [];
    cartTotal: any  = 0;
    constructor() {

    }

    addProduct(product: Product) {
        this.products.push(product);
    }

    deleteChartProduct(id: string) {
        const index = this.products.findIndex(x => x.id === id);
        if (index !== -1) {
            this.products.splice(index, 1);
        }
    }

    findAll(): Product[] {
        return this.products.slice();
    }
}
