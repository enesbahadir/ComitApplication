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
  isAdmin: boolean = false;

  constructor(
    private accountService: AccountService,
    private cartService: ChartService,
    private orderService: OrderService,
    private menuService: MenuService
  ) {
    this.accountService.user.subscribe(x => this.user = x);
  }

  ngOnInit(): void {
  if(this.user && this.user.role.includes("ADMIN"))
  {
    this.isAdmin = true;
  }
   this.getCartList();
   this.getTotalPrice();
  }

  logout() {
    this.accountService.logout();
  }

  deleteChartProduct(id: number) {
      this.cartService.deleteChartItem(id);
      this.getCartList();
  }

  deleteAll() {
    this.cartService.clear();
    this.getCartList();
  }

  getCartList() {
    this.carts = this.cartService.findAll();
  }

  getTotalPrice() {
    let total = 0;

    this.carts.map(item => {
      total += item.price;
    });
    return total
  }
}
