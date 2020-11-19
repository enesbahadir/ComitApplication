import { Component, OnInit } from '@angular/core';
import {ChartService} from "../chart/chart.service";
import {Product} from "../_models/product";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {

  }

}
