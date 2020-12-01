import { Component } from '@angular/core';

import { IUser } from '../_models';
import { AccountService } from '../_services';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent {
    user: IUser;

    constructor(private accountService: AccountService) {
        this.user = this.accountService.userValue;
    }
}
