import {Injectable} from "@angular/core";
import {Product} from "../_models/product";

@Injectable({providedIn: "root"})
export class MenuService {

    products: Product[] = [];

    constructor() {

    }

    addProduct(product: Product) {
        this.products.push(product);
    }


    findAll(): Product[] {
        return this.products.slice();
    }
}
