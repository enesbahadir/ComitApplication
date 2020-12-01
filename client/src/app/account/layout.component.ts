import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AccountService } from '../_services';

@Component({ templateUrl: 'layout.component.html' })
export class LayoutComponent {
    constructor(
        private router: Router,
        private accountService: AccountService
    ) {
        // Otorumu açtınız, anasayfaya yönlendiriliyorsunuz.
        if (this.accountService.userValue) {
            this.router.navigate(['/']);
        }
    }
}
