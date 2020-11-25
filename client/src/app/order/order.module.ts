import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { OrderComponent} from "./order.component";
import {RouterModule, Routes} from "@angular/router";
import {MatListModule} from "@angular/material/list";
import {MatIconModule} from "@angular/material/icon";

const routes: Routes = [
    {
        path: '',
        component: OrderComponent
    }
]
@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
    MatListModule,
    MatIconModule
  ],
    declarations: [
        OrderComponent
    ]
})
export class OrderModule { }
