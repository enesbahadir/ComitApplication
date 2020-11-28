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

@RestController
public class ProductController {

    private final ProductService productService;

    private byte[] bytes;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/api/products")
    public Product createUser(@RequestBody Product newProduct)
    {
        newProduct.setPicByte(bytes);
        return productService.createProduct(newProduct);
                /*EntityModel.of(newProduct,
                linkTo(methodOn(RegisterController.class).getPreschoolById(newPreschool.getId())).withSelfRel(),
                linkTo(methodOn(RegisterController.class).listOfPreschools()).withRel("preschools"));*/
    }

    @PostMapping("/api/upload")
    public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        this.bytes = file.getBytes();
    }

    @PutMapping("/api/products/{id}")
    public Product updateProduct (@RequestBody Product newProduct, @PathVariable Integer id)
    {
        return productService.updateProduct(newProduct, id);

    }

    @DeleteMapping("/api/products/{id}")
    void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/api/products")
    public List<Product> getProducts()
    {
        return productService.getProducts();
    }

    @GetMapping("api/products/{id}")
    public Product getProduct(@PathVariable Integer id)
    {
        return productService.getProductById(id);
    }

    @GetMapping("/api/products/page")
    @ResponseBody
    public List<Product> findAllPaginatedProducts(@RequestParam("pageNumber") int pageNumber) {
        Page<Product> resultPage = productService.getPaginatedProducts(pageNumber);
        return resultPage.getContent();
    }

    @GetMapping("/api/products/search")
    public List<Product> findAllSearchedProducts(@RequestParam("search") String keyword) {
        return productService.getProdcuts(keyword);
    }

    public static byte[] compressBytes( byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }


}
