import { Component, OnInit } from '@angular/core';
import { AccountService } from '../_services/account.service';
import { User } from '../_models';
import { Product } from '../_models/product';
import { OrderService } from '../order/order.service';
import { MenuService } from './menu.service';
import { ChartService } from '../chart/chart.service';


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  carts: Product[];
  user: User;

  constructor(
    private accountService: AccountService,
    private cartService: ChartService,
    private orderService: OrderService,
    private menuService: MenuService
  ) {
    this.accountService.user.subscribe(x => this.user = x);
  }

  ngOnInit(): void {

  }

  logout() {
    this.accountService.logout();
  }



  getCartList() {
    this.carts = this.cartService.findAll();
  }



}
