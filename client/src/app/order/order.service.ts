import {Injectable} from "@angular/core";
import {IOrder} from "../_models/order";
import {StaticVariables} from '../static-variables';
import {IProduct} from "../_models/product";
import {AccountService} from "../_services";
import {ChartService} from "../chart/chart.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";


@Injectable({providedIn: "root"})
export class OrderService {
  private orderList: IOrder[];
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


// Chartta bulunan ürünleri order sayfasına ekleme.
  addToOrder(order: IOrder) {
    return this.http.post<IOrder>(`${this.orderUrl}`, order, {
      observe: 'response'
    });
  }

// Order'e eklenmiş ürünleri gösterir.(Admin görebilir)
  getOrdersAll() {
    return this.http.get<IOrder[]>('http://localhost:8080/api/orders');
  }

// Kullanıcının kendi eklediği ürüneleri görme.
  getOrdersUser(id : number) {
    return this.http.get<Order[]>('http://localhost:8080/api/orders/user/'+id);
  }


  findAll(): IOrder[] {
    return this.orderList.slice();
  }
}

