import { Component, OnInit } from '@angular/core';
import {ChartService} from "../chart/chart.service";
import {Product} from "../_models/product";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  products : Product[];
  constructor(private  chartService: ChartService) { }

  ngOnInit(): void {
    this.products = this.chartService.products;

    this.getTotalPrice();
  }
  getTotalPrice() {
    let total = 0;

    this.products.map(item => {
      total += item.price;
    });
    return total
  }
}
