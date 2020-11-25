import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ListComponent} from "./list.component";
import { RouterModule, Routes} from "@angular/router";
import { AddEditComponent} from "./add-edit.component";

import { HttpClientModule } from '@angular/common/http';

const routes: Routes = [
    {
        path: '',
        component: ListComponent
    },
    {
        path: 'add',
        component: AddEditComponent
    },
    {
        path: 'edit/:id',
        component: AddEditComponent
    }
];

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        HttpClientModule,
        RouterModule.forChild(routes)
    ],
    declarations: [
        ListComponent,
        AddEditComponent
    ]
})
export class ProductsModule { }
