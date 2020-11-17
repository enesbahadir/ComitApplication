import { Component, OnInit } from '@angular/core';
import {Product} from "../_models/product";
import {ProductService} from "../products/product.service";
import {ChartService} from "../chart/chart.service";

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {
  products: Product[];

  fileToUpload: any;
  imageUrl: any;
  handleFileInput(file: FileList) {
    this.fileToUpload = file.item(0);
    let reader = new FileReader();
    reader.onload = (event: any) => {
      this.imageUrl = event.target.result;
    }
    reader.readAsDataURL(this.fileToUpload);
  }

  constructor( private productService: ProductService, private  chartService: ChartService) { }

  ngOnInit(): void
  {
    this.products = this.productService.findAll();
  }

  addProductChart(product: Product) {
this.chartService.addProduct(product);
  }
}
