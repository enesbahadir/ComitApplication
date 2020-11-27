import { Injectable } from "@angular/core";
import { Product } from "../_models/product";
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
   addProduct(product : Product) : Observable<any>{
     return this.http.post(this.productsUrl, product);
   }
    // Ürü güncelleme
   updateProduct(id: number,product: Product) : Observable<any> {
     const url = '${this.productsUrl}/${id}';
     return this.http.put(url,product, this.httpOptions);
   }
    // Ürün silme
   deleteProduct(product: Product | number): Observable<Product> {
     const id = typeof product === 'number' ? product : product.id;
     const url = `${this.productsUrl}/${id}`;

     return this.http.delete<Product>(url, this.httpOptions);
   }
   // Eklenmiş ürününü ürün Id'sine göre ürünü getirme.
   getProduct(id : number):Observable<Product>{
     return  this.http.get<Product>('${this.productsUrl}/${id}');
   }
  // Eklenmiş ürünlerin hepsini getirme.
   getProductAll(): Observable<any>{
     return  this.http.get(this.productsUrl);
   }


}
