import { Component, OnInit } from '@angular/core';
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
  carts: Product[];


  constructor(
    private chartService: ChartService,
    private orderService: OrderService,
    private accountService : AccountService
  ) { }

  ngOnInit(): void {
    this.carts = this.chartService.findAll();

    this.getTotalPrice();

  }

  // addChartToOrderList() {
  //   this.orderService.addToOrder(this.carts);
  //   this.carts = this.chartService.findAll();
  // }

  addChartToOrderList() {
    const  order : Order = {
      products:this.carts,
      user:this.accountService.userValue,
      date:new Date()
    }
    this.orderService.addToOrder(order).subscribe();


    this.carts = this.chartService.findAll();
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
