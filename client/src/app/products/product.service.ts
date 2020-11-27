import { Injectable } from "@angular/core";
import { IProduct } from "../_models/product";
import { HttpClient, HttpHeaders} from "@angular/common/http";
import { observable, Observable, of } from 'rxjs';
import { map } from "rxjs/operators";
import { StaticVariables } from '../static-variables';

@Injectable({ providedIn: 'root' })
export class ProductService {

    private productsUrl = 'http://localhost:8080/api/products';

    httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) {
    }

    // Ürün ekleme
   addProduct(product : IProduct) : Observable<any>{
     return this.http.post(this.productsUrl, product);
   }
    // Ürü güncelleme
   updateProduct(id: number,product: IProduct) : Observable<any> {
     const url = '${this.productsUrl}/${id}';
     return this.http.put(url,product, this.httpOptions);
   }
    // Ürün silme
   deleteProduct(product: IProduct | number): Observable<IProduct> {
     const id = typeof product === 'number' ? product : product.id;
     const url = `${this.productsUrl}/${id}`;

     return this.http.delete<IProduct>(url, this.httpOptions);
   }
   // Eklenmiş ürününü ürün Id'sine göre ürünü getirme.
   getProduct(id : number):Observable<IProduct>{
     return  this.http.get<IProduct>('${this.productsUrl}/${id}');
   }
  // Eklenmiş ürünlerin hepsini getirme.
   getProductAll(): Observable<any>{
     return  this.http.get(this.productsUrl);
   }


}
