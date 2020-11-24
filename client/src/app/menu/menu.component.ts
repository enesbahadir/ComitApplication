import {Component, OnInit, ViewChild} from '@angular/core';
import {AccountService} from '../_services/account.service';
import {User} from '../_models';
import {MatMenuTrigger} from '@angular/material/menu';
import {Product} from "../_models/product";
import {ProductService} from "../products/product.service";
import {OrderService} from "../order/order.service";
import {MenuService} from "./menu.service";
import {ChartService} from "../chart/chart.service";


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  products: Product[];
  user: User;

  constructor(
    private accountService: AccountService,
    private  chartService: ChartService,
    private productService: ProductService,
    private  orderService: OrderService,
    private menuService: MenuService
  ) {
    this.accountService.user.subscribe(x => this.user = x);
  }

  ngOnInit(): void {
    this.products = this.menuService.products;

  }

  logout() {
    this.accountService.logout();
  }

  @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;

  deleteChartProduct(id: string) {
    const index = this.products.findIndex(x => x.id === id);
    if (index !== -1) {
      this.products.splice(index, 1);
    }
  }

}
