package com.ecommerce.microcommerce.controller;

import com.ecommerce.microcommerce.entity.Product;
import com.ecommerce.microcommerce.exceptions.productNotFoundException;
import com.ecommerce.microcommerce.repository.ProductRepository;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api( description = "API CRUD product")
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public MappingJacksonValue allProducts(){
        List<Product> products = productRepository.findAll();
        // filter with a given bean "serializeAllExcept("what we want")". the opposite is "filterOutAllExcept("")"
        SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("purchasePrice");
        // apply the filter only on the bean named "myDynamicFilter",  to the desired entity.
        FilterProvider ourFilters = new SimpleFilterProvider().addFilter("myDynamicFilter", myFilter);
        //convert in MappingJackson == Product with filters, to access to all methods
        MappingJacksonValue filterProducts = new MappingJacksonValue(products);
        //apply those filters
        filterProducts.setFilters(ourFilters);
        return filterProducts;
    }
    @ApiOperation(value ="if there is a product on this id, Get product with this id")
    @GetMapping("/product/{id}")
    public Product showOneProduct(@PathVariable int id){
        Product product = productRepository.findById(id);
        if(product==null) throw new productNotFoundException("product with id=" + id +" not found");
            return product;
    }

    @GetMapping("test/product/{limitPrice}")
    public List<Product> RequestTest(@PathVariable int limitPrice){
        return productRepository.findByPriceGreaterThan(limitPrice);
    }
    /*@GetMapping("test/product/{search}")
    public List<Product> requestTestName(@PathVariable String search){
        return productRepository.findByNameLike("%"+ search+"%");
    }*/



    @PostMapping("/product")
    public ResponseEntity<Void> addProduct(@Valid @RequestBody Product product){
        Product productAdded = productRepository.save(product);
        // if product is empty => return http code : 204 No content
        if(productAdded == null){
            return ResponseEntity.noContent().build();
        }
        //declaration of the class instance URI
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                //add id to URI (future URL)
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();
        //Create a product object, accepts l'URI and return http code 201
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/product")
    public void productUpdate(@RequestBody Product product){
        productRepository.save(product);
    }

    @DeleteMapping("/product/{id}")
    public void productDeleted(@PathVariable int id){
        productRepository.deleteById(id);
    }
}
