import { Injectable } from "@angular/core";
import { Product } from "../_models/product";
import { HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
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

   addProduct(product : Product) : Observable<any>{
     return this.http.post(this.productsUrl, product);
   }

   updateProduct(id: number, product: Product) : Observable<any> {
     return this.http.put<Product>('http://localhost:8080/api/products/'+id, product, {
       observe:'response'
     });
   }

   deleteProduct(product: Product | number): Observable<Product> {
     const id = typeof product === 'number' ? product : product.id;
     const url = `${this.productsUrl}/${id}`;

     return this.http.delete<Product>(url, this.httpOptions);
   }

   getProduct(id : number):Observable<HttpResponse<Product>>{
     return  this.http.get<Product>('http://localhost:8080/api/products/'+id, {
       observe:'response'
     });
   }

   getProductAll(): Observable<any>{
     return  this.http.get(this.productsUrl);
   }


}
