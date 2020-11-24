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
    this.productService.getProductAll().subscribe(products =>
      this.handleImage(products));
  }

  addProductChart(product: Product) {
    this.chartService.addProduct(product);
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
