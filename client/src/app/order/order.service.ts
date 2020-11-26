import {Injectable} from "@angular/core";
import {Order} from "../_models/order";
import {StaticVariables} from '../static-variables';
import {Product} from "../_models/product";
import {AccountService} from "../_services";
import {ChartService} from "../chart/chart.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";


@Injectable({providedIn: "root"})
export class OrderService {
  private orderList: Order[];
  private orderUrl = 'http://localhost:8080/api/orders';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private accountService: AccountService,
    private cartService: ChartService,private http: HttpClient
  ) {
    this.orderList = StaticVariables.orderList;
  }



  addToOrder(order: Order) {
    return this.http.post<Order>(`${this.orderUrl}`, order, {
      observe: 'response'
    });
  }


  // addToOrder(products: Product[]) {
  //   debugger
  //   this.orderList.push({
  //     products: products,
  //     user: this.accountService.userValue,
  //     date: new Date()
  //   });
  //   this.cartService.clear();
  // }




  getOrdersAll() {
    return this.http.get<Order[]>('http://localhost:8080/api/orders');
  }


  getOrdersUser(id : number) {
    return this.http.get<Order[]>('http://localhost:8080/api/orders/user/'+id);
  }



  findAll(): Order[] {
    return this.orderList.slice();
  }


}

