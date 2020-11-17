import {Injectable} from "@angular/core";
import {Product} from "../_models/product";

@Injectable({providedIn: "root"})
export class OrderService {

    products: Product[] = [];
    cartTotal: any  = 0;
    constructor() {

    }

    addProduct(product: Product) {
        this.products.push(product);
    }


    findAll(): Product[] {
        return this.products.slice();
    }
}
