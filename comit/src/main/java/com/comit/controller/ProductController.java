package com.comit.controller;

import com.comit.model.Product;
import com.comit.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.Deflater;

/**
 * Product CRUD işlemlerini gerçekleştiren api controller
 */
@RestController
public class ProductController {

    private final ProductService productService;

    private byte[] bytes; //Ürün resimleri için tutulan array

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Yeni bir product nesnesi oluşturmak için kullanılan HTTP-POST metodu
     *
     * @param newProduct eklenecek product nesnesi
     * @return Eklenen Product nesnesi
     */
    @PostMapping("/api/products")
    public Product createProduct(@RequestBody Product newProduct) {
        newProduct.setPicByte(bytes);
        return productService.createProduct(newProduct);
    }

    /**
     * Product'a ait resim eklemek için kullanılan HTTP-POST metodu
     *
     * @param file sisteme yüklenecek olan resim dosyası
     * @throws IOException
     */
    @PostMapping("/api/upload")
    public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        this.bytes = file.getBytes();
    }

    /**
     * Sistemde kayıtlı olan Product nesnesini güncellemek için kullanılan HTTP-PUT metodu
     *
     * @param newProduct güncellenmiş Product nesnesi
     * @param id         güncellenecek olan Prodcut'un id'si
     * @return Güncellenmiş olan Product nesnesi
     */
    @PutMapping("/api/products/{id}")
    public Product updateProduct(@RequestBody Product newProduct, @PathVariable Integer id) {
        return productService.updateProduct(newProduct, id);
    }

    /**
     * Sistemde kayıtlı olan Product nesnesini silmek için kullanılan HTTP-DELETE metodu. Bir Order-Sipariş nesnesinde
     * kullanıldı ise Delete metodu hata verir.
     *
     * @param id Silinecek olan Proeduct nesnesinin id'si
     */
    @DeleteMapping("/api/products/{id}")
    void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    /**
     * Sistemde kayıtlı olan Product nesnenelerini listelemek için kullanılan HTTP-GET metodu
     *
     * @return Product listesi
     */
    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    /**
     * Verilen id değerine göre ilgili Product nesnesini dönen HTTP-GET metodu
     *
     * @param id ilgili Product nesnesinin id'si
     * @return ilgili Product nesnesi
     */
    @GetMapping("api/products/{id}")
    public Product getProduct(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    /**
     * Sistemde kayıtlı olan Product nesnenelerini 10'ar 10'ar sayfalarda listelemek için kullanılan HTTP-GET metodu
     *
     * @param pageNumber listelenek olan sayfa numarası
     * @return ilgili sayfa numarasında bulunan Product listesi
     */
    @GetMapping("/api/products/page")
    @ResponseBody
    public List<Product> findAllPaginatedProducts(@RequestParam("pageNumber") int pageNumber) {
        Page<Product> resultPage = productService.getPaginatedProducts(pageNumber);
        return resultPage.getContent();
    }

    /**
     * Sistemde kayıtlı olan Product nesnenelerini verilen arama değerine göre listelemek için kullanılan HTTP-GET metodu
     *
     * @param keyword Arama kriteri
     * @return Ürün isminde, açıklamasında veya fiyatında arama kriterini içeren Product nesneleri listesi
     */
    @GetMapping("/api/products/search")
    public List<Product> findAllSearchedProducts(@RequestParam("search") String keyword) {
        return productService.getProdcuts(keyword);
    }
}
