import { Component, OnInit } from '@angular/core';
import {Product} from "../_models/product";
import {ChartService} from "./chart.service";
import {ProductService} from "../products/product.service";
import {OrderService} from "../order/order.service";

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})

export class ChartComponent implements OnInit {
products : Product[];
  constructor(
      private  chartService : ChartService,private productService: ProductService, private  orderService: OrderService
  ) { }

  ngOnInit(): void {
    this.products = this.chartService.products;

    this.getTotalPrice();

  }

  deleteChartProduct(id: string) {
    this.chartService.deleteChartProduct(id);
    // refresh the list
    this.products = this.chartService.findAll();
  }
  getTotalPrice() {
    let total = 0;

    this.products.map(item => {
      total += item.price;
    });
    return total
  }


    addProductOrder(product: Product) {
      this.orderService.addProduct(product);
    }
}

