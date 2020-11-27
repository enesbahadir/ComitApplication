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

  // Menüdeki sepeteki ürünlerin hepsini silme
  deleteAll() {
    console.log("first");
    this.cartService.deleteAll();
    this.getCartList();
  }


// Cart listesindeki ürünleri menüdeki sepette gösteririr.
  getCartList() {
    this.carts = this.cartService.findAll();
  }

  // Menüdeki sepetteki ürünlerin toplam fiyatını gösterir.
    getTotalPrice() {
      let total = 0;

      this.carts.map(item => {
        total += item.price;
      });
      return total
    }
}
