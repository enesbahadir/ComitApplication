import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '../_services';
import {ProductService} from "./product.service";


@Component({templateUrl: 'add-edit.component.html'})
export class AddEditComponent implements OnInit {
    form: FormGroup;
    id: string;
    isAddMode: boolean;
    loading = false;
    submitted = false;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private alertService: AlertService,
        private productService: ProductService,
        private fb: FormBuilder,
        private cd: ChangeDetectorRef
        // public _d: DomSanitizer
    ) {
    }

    // fileChange(e) {
    //     const file = e.srcElement.files[0];
    //     this.imgsrc = window.URL.createObjectURL(file);
    // }

    ngOnInit() {
        this.id = this.route.snapshot.params['id'];
        this.isAddMode = !this.id;
        this._initializeForm();


        if (!this.isAddMode) {
            const product = this.productService.find(this.id);
            this.form.setValue({
                // id: product.id,
                name: product.name,
                description: product.description,
                price: product.price,
                file: product.file
            });
            this.form.updateValueAndValidity();
        }
    }

    _initializeForm() {
        this.form = this.formBuilder.group({
            id: [null, Validators.required],
            name: [null, Validators.required],
            description: [null, Validators.required],
            price: [0, Validators.required],
            file: [null]
        });
    }

    // convenience getter for easy access to form fields
    get f() {
        return this.form.controls;
    }

    onSubmit() {
        this.submitted = true;

        // reset alerts on submit
        this.alertService.clear();

        // stop here if form is invalid
        if (this.form.invalid) {
            return;
        }

        this.loading = true;
        if (this.isAddMode) {
            this.productService.addProduct(this.form.value);
        } else {
            this.productService.updateProduct(this.form.value);
        }
        this.router.navigate(['/products']);
    }

    onFileChange(event) {
        const reader = new FileReader();

        if (event.target.files && event.target.files.length) {
            const [file] = event.target.files;
            reader.readAsDataURL(file);

            reader.onload = () => {
                this.form.patchValue({
                    file: reader.result
                });

                // need to run CD since file load runs outside of zone
                this.cd.markForCheck();
            };
        }
    }
}
