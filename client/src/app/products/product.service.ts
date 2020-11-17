import {Injectable} from "@angular/core";
import {Product} from "../_models/product";

@Injectable({providedIn: "root"})
export class ProductService {
    products: Product[];

    constructor() {
        this.products = [

        ]
    }

    addProduct(product: Product) {
        this.products.push(product);
    }

    updateProduct(product: Product) {
        const index = this.products.findIndex(x => x.id === product.id);
        if (index !== -1) {
            this.products[index] = product;
        }
    }

    deleteProduct(id: string) {
        const index = this.products.findIndex(x => x.id === id);
        if (index !== -1) {
            this.products.splice(index, 1);
        }
    }

    find(id: string): Product {
        return this.products.find(x => x.id === id);
    }

    findAll(): Product[] {
        return this.products.slice();
    }
}
