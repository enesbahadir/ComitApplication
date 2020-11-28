import { Injectable } from "@angular/core";
import { IProduct } from "../_models/product";
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

    // Ürün ekleme
   addProduct(product : IProduct) : Observable<any>{
     return this.http.post(this.productsUrl, product);
   }

    // Ürün güncelleme
   updateProduct(id: number, product: IProduct) : Observable<any> {
     return this.http.put<Product>('http://localhost:8080/api/products/'+id, product, {
       observe:'response'
     });

   }
    // Ürün silme
   deleteProduct(product: IProduct | number): Observable<IProduct> {
     const id = typeof product === 'number' ? product : product.id;
     const url = `${this.productsUrl}/${id}`;

     return this.http.delete<IProduct>(url, this.httpOptions);
   }
   // Eklenmiş ürününü ürün Id'sine göre ürünü getirme.
   getProduct(id : number):Observable<HttpResponse<IProduct>>{
     return  this.http.get<Product>('http://localhost:8080/api/products/'+id, {
       observe:'response'
     });

   }
  // Eklenmiş ürünlerin hepsini getirme.
   getProductAll(): Observable<any>{
     return  this.http.get(this.productsUrl);
   }


}
