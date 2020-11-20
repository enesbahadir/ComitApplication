import {Injectable} from "@angular/core";
import {Product} from "../_models/product";
import { HttpClient, HttpHeaders} from "@angular/common/http";
import { Observable} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({providedIn: "root"})
export class ProductService {
    products: Product[];
    private productsUrl = 'http://localhost:8080/products';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
    constructor(private http: HttpClient) {
        this.products = []

    }


    // addProduct(product : Product){
    //   return this.http.post<Product>('${this.productsUrl}' , product, {observe : "response"});
    // }

    addProduct(product: Product) {
        this.products.push(product);
    }

  // updateProduct(product: Product) : Observable<any> {
  //   const url = '${this.productsUrl}/${product.id}';
  //   return this.http.put(url,product, this.httpOptions);
  //   }
  // }
  //
  //



    updateProduct(product: Product) {
        const index = this.products.findIndex(x => x.id === product.id);
        if (index !== -1) {
            this.products[index] = product;
        }
    }

  // deleteProduct(product: Product | number): Observable<Product> {
  //   const id = typeof product === 'number' ? product : product.id;
  //   const url = `${this.productsUrl}/${id}`;
  //
  //   return this.http.delete<Product>(url, this.httpOptions);
  // }



    deleteProduct(id: string) {
        const index = this.products.findIndex(x => x.id === id);
        if (index !== -1) {
            this.products.splice(index, 1);
        }
    }

    // getProduct(id : string):Observable<Product>{
    //   return  this.http.get<Product>('${this.productsUrl}/${id}');
    // }


    find(id: string): Product {
        return this.products.find(x => x.id === id);
    }

    // getProductAll() :Observable<Product[]>{
    //   return  this.http.get(this.productsUrl).pipe(map((data:any) => {return data._embedded.products}));
    // }

    findAll(): Product[] {
        return this.products.slice();
    }
}
