package com.comit.service;

import com.comit.model.Product;
import com.comit.execption.ProductNotFoundException;
import com.comit.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Sistemde kayıtlı Product sınıfının CRUD işlemlerinin gerçekleştirildiği service sınıfıdır.
 */
@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Product nesnesi oluşturmak için kullanılan metod. Product nesnesi üzerinden yeni Product'ı repository'e kayıt eder.
     *
     * @param model Eklenecek olan Product nesnesi
     * @return Eklenen Product nesnesi
     */
    public Product createProduct(Product model) {
        Product newProduct = new Product(model.getName(), model.getDescription(), model.getPrice(), model.getPicByte());
        productRepository.save(newProduct);
        return newProduct;
    }

    /**
     * Product nesnesinde değişiklik yapmak için kullanılan metod. Product nesnesi üzerinden yeni Product'ı repository'e kayıt eder.
     *
     * @param newProduct Değiştirilecek olan Product nesnesi
     * @param id         Değişim yapılacan olan product nesnesinin id'si
     * @return Değişim yapılmış olan Product nesnes,
     */
    @Transactional
    public Product updateProduct(Product newProduct, int id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    product.setDescription(newProduct.getDescription());
                    product.setPicByte(newProduct.getPicByte());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
    }

    /**
     * Product nesnesi silmek için kullanılan metod
     *
     * @param id Silinecek olan Product nesnesine ait id değeri
     */
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    /**
     * Sistemde kayıtlı olan tüm product nesnelerini listeleyen metod
     *
     * @return Kayıtlı product'ların olduğu liste
     */
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    /**
     * Adında açıklamasında veya fiyatında ilgili keyword değeri bulunan product nesnelerini listeleyen
     *
     * @param keyword arama yapılacak olan keyword değeri
     * @return bulunan product listesi
     */
    public List<Product> getProdcuts(String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword);
        }
        return productRepository.findAll();
    }

    /**
     * Verilen id değeri ile bu id değerine sahip Product nesnesi döner
     *
     * @param id Aranan id değeri
     * @return id değerine sahip Product nesnesi
     */
    public Product getProductById(int id) {
        return productRepository.findById(id).
                orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * Verilen sayfa numarasına göre 10'ar 10'ar Product nesnelerini listeleyen metod
     *
     * @param pageNumber ilgili sayfa numarası
     * @return bulunan product listesi
     */
    public Page<Product> getPaginatedProducts(int pageNumber) {
        PageRequest pageable = PageRequest.of(pageNumber - 1, 10);
        Page<Product> resultPage = productRepository.findAll(pageable);
        if (pageNumber > resultPage.getTotalPages()) {
            throw new ProductNotFoundException("Not Found Page Number:" + pageNumber);
        }
        return resultPage;
    }

}
