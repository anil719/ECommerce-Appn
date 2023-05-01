package com.example.ECommerceProject.Controller;

import com.example.ECommerceProject.Dto.Request.ProductRequestDto;
import com.example.ECommerceProject.Dto.Response.DeleteProductResponseDto;
import com.example.ECommerceProject.Dto.Response.ProductResponseDto;
import com.example.ECommerceProject.Exceptions.InvalidSellerException;
import com.example.ECommerceProject.Service.ProductService;
import com.example.ECommerceProject.enums.ProductCategory;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidSellerException {
        try{
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity(productResponseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    //get all products of a particular category
    @GetMapping("/get/{category}")
    public List<ProductResponseDto> getAllProductsByCategory(@PathVariable("category")ProductCategory category){
        return productService.getAllProductsByCategory(category);
    }


    // Get all product by seller email id
    @GetMapping("/withGivenSellerEmail")
    public ResponseEntity productsBySellerEmail(@RequestParam("email")String email) throws Exception {

        try{
            List<ProductResponseDto> list = productService.productsBySellerEmail(email);
            return new ResponseEntity(list, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // delete a product by seller id and product id

    @DeleteMapping("/deleteBySellandProductId")
    public ResponseEntity deleteBySellerProductId(@RequestParam("sid")int sid, @RequestParam("pid")int pid) throws Exception {
        try{
            DeleteProductResponseDto deleteProductResponseDto = productService.deleteBySellerProductId(sid, pid);
            return new ResponseEntity(deleteProductResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // return top 5 cheapest products

    @GetMapping("/top5CheapestProducts")
    public List<ProductResponseDto> getCheapestProducts(){
        return productService.getCheapestProducts();
    }

    // return top 5 costliest products ;

    @GetMapping("/getTopCostlyProducts")
    public List<ProductResponseDto> getCostlyProducts(){
        return productService.getCostlyProducts();
    }


    // return all available products
    @GetMapping("/getAvailable")
    public List<ProductResponseDto> getAvailableProducts() throws Exception {
        return productService.getAvailableProducts();
    }

    // return all products that have quantity less than 10
    @GetMapping("/productsWithQuantiyLessThan10")
    public List<ProductResponseDto> getProductWithQuantityLessThan10() throws Exception {
        return productService.getProductWithQuantityLessThan10();
    }


    // return the cheapest product in a particular category

    @GetMapping("/cheaperProductInCategory")
    public ProductResponseDto getCheaperProductInGivenCategory(@RequestParam("category")String category){
        return productService.getCheaperProductInTheCategory(category);
    }

    // return the costliest product in a particular category
    @GetMapping("/costlierProductInCategory")
    public ProductResponseDto getCostlierProductInGivenCategory(@RequestParam("category")String category){
        return productService.getCostlierProductInTheCategory(category);
    }

    //api where product price is greater than and category is sports

    @GetMapping("/get/{price}/{category}")
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(@PathVariable("price")int price,
                                                                     @PathVariable("category")String category){
        return productService.getAllProductsByPriceAndCategory(price, category);
    }
}
