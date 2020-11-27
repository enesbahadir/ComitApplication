import { Component, OnInit } from '@angular/core';
import { Product } from '../_models/product';
import { ProductService } from '../products/product.service';
import { ChartService } from '../chart/chart.service';
import { MenuService } from '../menu/menu.service';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {
  products: Product[];

  fileToUpload: any;
  imageUrl: any;

  constructor(
    private productService: ProductService,
    private  chartService: ChartService,
    private menuService: MenuService
  ) {
  }

  ngOnInit(): void {
   this.productService.getProductAll().subscribe(products =>
         this.handleImage(products));
  }
  // Shoptaki seçili ürünü chart'a ekleme
  addProductChart(product: Product) {
    this.chartService.addToCart(product);
  }

  handleFileInput(file: FileList) {
    this.fileToUpload = file.item(0);
    let reader = new FileReader();
    reader.onload = (event: any) => {
      this.imageUrl = event.target.result;
    };
    reader.readAsDataURL(this.fileToUpload);
  }
  // Ürünün resminin chartta görüntülenmesi.
  handleImage(response)
  {
      for (const product of response)
      {
        product.image = 'data:image/jpeg;base64,' + product.picByte;
      }
      this.products = response;
  }
}
