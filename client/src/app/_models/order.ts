import { Product } from './product';

export interface Order {
  products: Product[];
  date?: Date;
}
