import {Component, OnInit} from '@angular/core';
import { Product } from '../_models/product';
import { ChartService } from './chart.service';
import { OrderService } from '../order/order.service';
import {Order} from "../_models/order";
import {AccountService} from "../_services";

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})

export class ChartComponent implements OnInit {
  charts: Product[];
  constructor(
    private  chartService: ChartService,  private orderService: OrderService,
    private accountService : AccountService) {
  }

  ngOnInit(): void {

    this.charts = this.chartService.findAll();
    this.getTotalPrice();
  }

  // Chart sayfasında olan ürünleri order listesine ekler.
  addChartToOrderList() {
    const  order : Order = {
      products:this.charts,
      user:this.accountService.userValue,
      date:new Date()
    }

    this.orderService.addToOrder(order).subscribe();
    // Chart Listesini yenileme
    this.charts = this.chartService.findAll();
  }

// Chart sayfasından ürün silmek için kullanılır.
  deleteChartProduct(id: number) {
    this.chartService.deleteChartItem(id);

    // Chart Listesini yenileme
    this.charts = this.chartService.findAll();
  }

  // Chart sayfasında ürünlerin toplam fiyatını gösteren metod
  getTotalPrice() {
    let total = 0;

    this.charts.map(item => {
      total += item.price;
    });
    return total
  }
}

