package com.comit.service;

import com.comit.execption.ProductNotFoundException;
import com.comit.model.Product;
import com.comit.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct (Product model)
    {
        Product newProduct = new Product( model.getName(), model.getDescription(), model.getPrice(), model.getPicByte());
        productRepository.save(newProduct);
        return newProduct;
    }

    @Transactional
    public Product updateProduct( Product newProduct, int id)
    {
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

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProducts () {
        return productRepository.findAll();
    }

    public List<Product> getProdcuts (String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword);
        }
        return productRepository.findAll();

    }

    public Product getProductById(int id) {
        return productRepository.findById(id).
                orElseThrow( () -> new ProductNotFoundException(id));
    }

    public Page<Product> getPaginatedProducts(int pageNumber) {
        PageRequest pageable = PageRequest.of(pageNumber - 1, 10);
        Page<Product> resultPage = productRepository.findAll(pageable);
        if (pageNumber > resultPage.getTotalPages()) {
            throw new ProductNotFoundException("Not Found Page Number:" + pageNumber);
        }
        return resultPage;
    }

}
