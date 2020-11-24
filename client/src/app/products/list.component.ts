import { Component, OnInit } from '@angular/core';
import { Product } from '../_models/product';
import { ProductService } from './product.service';


@Component({ templateUrl: 'list.component.html' })
export class ListComponent implements OnInit {
  products: Product[];


  constructor(
    private productService: ProductService
  ) {}

  ngOnInit() {
    this.products = this.productService.findAll();
  }

  deleteProduct(id: number) {
    this.productService.deleteProduct(id);
    // refresh the list
    this.products = this.productService.findAll();
  }
}
