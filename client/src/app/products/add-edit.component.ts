import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlertService } from '../_services';
import { ProductService } from './product.service';
import { HttpClient } from "@angular/common/http";
import { Product } from "../_models/product"
@Component({ templateUrl: 'add-edit.component.html' })
export class AddEditComponent implements OnInit {
  form: FormGroup;
  /** product Id */
  id: number;
  isAddMode: boolean;
  loading = false;
  submitted = false;
    product: Product;

    private selectedFile;
    imgURL: any;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private alertService: AlertService,
    private productService: ProductService,
    private fb: FormBuilder,
    private cd: ChangeDetectorRef,
    private http: HttpClient// public _d: DomSanitizer
  ) {
  }

    ngOnInit() {
        this.id = this.route.snapshot.params['id'];
        this.isAddMode = !this.id;
        this._initializeForm();


        if (!this.isAddMode) {
            this.productService.getProduct(this.id)
            .subscribe(productModel => this.product = productModel);
            this.form.setValue({
                id: this.product.id,
                name: this.product.name,
                description: this.product.description,
                price: this.product.price,
                picByte: this.product.picByte
            });
            this.form.updateValueAndValidity();
        }
    }


  _initializeForm() {
    this.form = this.formBuilder.group({

      name: [null, Validators.required],
      description: [null, Validators.required],
      price: [0, Validators.required],
      picByte: [null]
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
            const uploadData = new FormData();
            uploadData.append('imageFile', this.selectedFile, this.selectedFile.name);
            this.http.post('http://localhost:8080/api/upload', uploadData, { observe: 'response' })
              .subscribe((response) => {
                 if (response.status === 200) {
                  this.productService.addProduct(this.form.value).subscribe(
                               response => {
                               });
                 }
                 else {
                  console.log('Image not uploaded successfully');
                 }
              });

        } else {
            this.productService.updateProduct(this.form.value);
        }
        this.router.navigate(['/products']);
    }

    onFileChange(event) {
      this.selectedFile = event.target.files[0];
      const reader = new FileReader();
      if (event.target.files && event.target.files.length) {
          reader.readAsDataURL(event.target.files[0]);

          reader.onload = () => {
              this.imgURL = reader.result;
              this.cd.markForCheck();
          };
      }
    }
}
