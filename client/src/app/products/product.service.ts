import { Injectable } from '@angular/core';
import { Product } from '../_models/product';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import products from '../static-variables';

@Injectable({ providedIn: 'root' })
export class ProductService {
  // products: Product[];
  private productsUrl = 'http://localhost:8080/products';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {
  }


  // addProduct(product : Product){
  //   return this.http.post<Product>('${productsUrl}' , product, {observe : "response"});
  // }


  addProduct(product: Product) {
    products.push(product);
  }

  // updateProduct(product: Product) : Observable<any> {
  //   const url = '${productsUrl}/${product.id}';
  //   return this.http.put(url,product, this.httpOptions);
  //   }
  // }
  //
  //


  updateProduct(productId: number, product: Product) {
    const index = products.findIndex(x => x.id === productId);
    if (index !== -1) {
      products[index] = product;
    }
  }

  // deleteProduct(product: Product | number): Observable<Product> {
  //   const id = typeof product === 'number' ? product : product.id;
  //   const url = `${this.productsUrl}/${id}`;
  //
  //   return this.http.delete<Product>(url, this.httpOptions);
  // }


  deleteProduct(id: number) {
    const index = products.findIndex(x => x.id === id);
    if (index !== -1) {
      products.splice(index, 1);
    }
  }

  // getProduct(id : string):Observable<Product>{
  //   return  this.http.get<Product>('${this.productsUrl}/${id}');
  // }


  find(id: number): Product {
    return products.find(x => x.id === id);
  }

  // getProductAll() :Observable<Product[]>{
  //   return  this.http.get(this.productsUrl).pipe(map((data:any) => {return data._embedded.products}));
  // }

  findAll(): Product[] {
    return products.slice();
  }
}
