import {Injectable} from "@angular/core";
import {Product} from "../_models/product";

@Injectable({providedIn: "root"})
export class MenuService {

    products: Product[] = [];

    constructor() {

    }


}
