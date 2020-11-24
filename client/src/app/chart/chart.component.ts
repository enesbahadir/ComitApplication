import { Component, OnInit } from '@angular/core';
import { Product } from '../_models/product';
import { ChartService } from './chart.service';
import { OrderService } from '../order/order.service';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})

export class ChartComponent implements OnInit {
  carts: Product[];

  constructor(
    private chartService: ChartService,
    private orderService: OrderService
  ) { }

  ngOnInit(): void {
    this.carts = this.chartService.findAll();

    this.getTotalPrice();

  }

  deleteChartProduct(id: number) {
    this.chartService.deleteChartItem(id);
    // refresh the list
    this.carts = this.chartService.findAll();
  }

  getTotalPrice() {
    let total = 0;

    this.carts.map(item => {
      total += item.price;
    });
    return total
  }
}

