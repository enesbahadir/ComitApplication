import {Component, OnInit} from '@angular/core';
import {OrderService} from "./order.service";
import {IOrder} from "../_models/order";
import {AccountService} from "../_services";
import {IUser} from "../_models";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  orders: IOrder[];
  user : IUser;
  constructor(private orderService: OrderService, private accountService : AccountService) {
    this.user = this.accountService.userValue;

  }

  // Kullanıcı rolüne göre order listesini görme kontrolü.
  ngOnInit(): void {
    if(this.user && this.user.role.includes("ADMIN")){
      this.orderService.getOrdersAll().subscribe(data =>{
        this.orders =data;});
    }
    else{
      this.orderService.getOrdersUser(this.user.id).subscribe( data => {
        this.orders = data;
      });
    }
  }
}
