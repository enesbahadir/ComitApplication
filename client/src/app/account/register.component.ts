import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { AccountService, AlertService } from '../_services';

@Component({ templateUrl: 'register.component.html' })
export class RegisterComponent implements OnInit {
    form: FormGroup;
    loading = false;
    submitted = false;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private accountService: AccountService,
        private alertService: AlertService
    ) { }

    ngOnInit() {
      // Register Form alan kontrolü
        this.form = this.formBuilder.group({
            name: ['', Validators.required],
            surName: ['', Validators.required],
            username: ['', Validators.required],
            password: ['', [Validators.required, Validators.minLength(6)]]
        });
    }

    // Form alanlarındaki özellikleri alamak için kullanılır.
    get f() { return this.form.controls; }

    onSubmit() {
        this.submitted = true;

        // Submit olduğunda uyaruları temizle.
        this.alertService.clear();

        // Form geçersiz olduğunda aynı yerde kal.
        if (this.form.invalid) {
            return;
        }

        this.loading = true;
        this.accountService.register(this.form.value)
            .pipe(first())
            .subscribe({
               // Form doğru gönderildiğinde başarılı mesa jı göndirir ve  login sayfasına yönlendirir.
              next: () => {
                    this.alertService.success('KAYIT BAŞARILI', { keepAfterRouteChange: true });
                    this.router.navigate(['../login'], { relativeTo: this.route });
                },
              // Form hatalı olduğunda alert mesajı (hata mesajı) gönderir.
                error: error => {
                    this.alertService.error(error);
                    this.loading = false;
                }
            });
    }
}
