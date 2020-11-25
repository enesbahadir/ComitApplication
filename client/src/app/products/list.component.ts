import { Component, OnInit } from '@angular/core';
import { Product } from '../_models/product';
import { ProductService } from './product.service';


@Component({ templateUrl: 'list.component.html' })
export class ListComponent implements OnInit {
  products: Product[];
    loading = false;

  constructor(
    private productService: ProductService
  ) {}

  ngOnInit() {
    this.getProducts();
    }

    getProducts(): void {
      this.productService.getProductAll().subscribe(data => {
        this.handleImage(data)
        });
  }

    deleteProduct(id: number) {
    this.loading = true;
        this.productService.deleteProduct(id).subscribe(
         response => {
           this.loading = false;
           this.ngOnInit();
         });
        // refresh the list
        this.getProducts();
    }

    handleImage(response)
    {
      for (const product of response)
      {
        product.image = 'data:image/jpeg;base64,' + product.picByte;
      }
      this.products = response;
    }
}
