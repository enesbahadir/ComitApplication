import { Component, OnInit } from '@angular/core';
import {Product} from "../_models/product";
import {ProductService} from "./product.service";
import { DomSanitizer } from '@angular/platform-browser';


@Component({ templateUrl: 'list.component.html' })
export class ListComponent implements OnInit {
    products: Product[];


    constructor(
        private productService: ProductService
    ) {}

    ngOnInit() {
        this.products = this.productService.findAll();
    }

    deleteProduct(id: string) {
        this.productService.deleteProduct(id);
        // refresh the list
        this.products = this.productService.findAll();
    }
}
