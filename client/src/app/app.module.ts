import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
// import {BrowserAnimationModule} from '@angular/platform-browser/animations'
// used to create fake backend
import { fakeBackendProvider } from './_helpers';

import { AppRoutingModule } from './app-routing.module';
import { JwtInterceptor, ErrorInterceptor } from './_helpers';
import { AppComponent } from './app.component';
import { AlertComponent } from './_components';
import { HomeComponent } from './home';
import { OrderComponent } from './order/order.component';
import { MenuComponent } from './menu/menu.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from "@angular/material/icon";
import {_MatMenuDirectivesModule, MatMenuModule} from "@angular/material/menu";


@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    MatIconModule,
    _MatMenuDirectivesModule,
    MatMenuModule,
    // BrowserAnimationModule
  ],
    declarations: [
        AppComponent,
        AlertComponent,
        HomeComponent,
        OrderComponent,
        MenuComponent
    ],
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },

        // provider used to create fake backend
        fakeBackendProvider
    ],
    bootstrap: [AppComponent]
})
export class AppModule { };
