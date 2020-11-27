import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlertService } from '../_services';
import { ProductService } from './product.service';
import { HttpClient } from "@angular/common/http";
import { IProduct } from "../_models/product"
@Component({ templateUrl: 'add-edit.component.html' })
export class AddEditComponent implements OnInit {
  form: FormGroup;
  /** product Id */
  id: number;
  isAddMode: boolean;
  loading = false;
  submitted = false;
    product: IProduct;

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
      // Form kontrolü
      name: [null, Validators.required],
      description: [null, Validators.required],
      price: [0, Validators.required],
      picByte: [null]
    });
  }

  // Form alanalarındaki özellikleri kullanmak için kullanılan bir kolaylık.
  get f() {
    return this.form.controls;
  }

  onSubmit() {
    this.submitted = true;


    this.alertService.clear();


    if (this.form.invalid) {
      return;
    }
    // Ürün resminin eklenmesi
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
      this.productService.updateProduct(this.id, this.form.value);
    }
    this.router.navigate(['/products']);
  }

  // Güncellenen ürünün resminin güncellenmesi.
  onFileChange(event) {
    this.selectedFile = event.target.files[0];const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      reader.readAsDataURL(event.target.files[0]);

      reader.onload = () => {
        this.imgURL = reader.result
        ;
        this.cd.markForCheck();
      };
    }
  }
}
