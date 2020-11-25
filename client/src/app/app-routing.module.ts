import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home';
import { AuthGuard } from './_helpers';

const accountModule = () => import('./account/account.module').then(x => x.AccountModule);
const usersModule = () => import('./users/users.module').then(x => x.UsersModule);
const productsModule = () => import('./products/products.module').then(x => x.ProductsModule);
const shopModule = () => import('./shop/shop.module').then(x => x.ShopModule);
const chartModule = () => import('./chart/chart.module').then(x => x.ChartModule);
const orderModule = () => import('./order/order.module').then(x => x.OrderModule);


const routes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard]},
    { path: 'users', loadChildren: usersModule, canActivate: [AuthGuard] },
    { path: 'account', loadChildren: accountModule},
    { path: 'products', loadChildren: productsModule,  canActivate: [AuthGuard] },
    { path: 'shop', loadChildren: shopModule,  canActivate: [AuthGuard]},
    { path: 'chart',loadChildren: chartModule, canActivate: [AuthGuard]},
    { path: 'order', loadChildren: orderModule,  canActivate: [AuthGuard]},

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
