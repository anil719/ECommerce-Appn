package com.example.ECommerceProject.Service.Impl;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.ECommerceProject.Dto.Request.ProductRequestDto;
import com.example.ECommerceProject.Dto.Response.DeleteProductResponseDto;
import com.example.ECommerceProject.Dto.Response.ProductResponseDto;
import com.example.ECommerceProject.Exceptions.InvalidSellerException;
import com.example.ECommerceProject.Models.Item;
import com.example.ECommerceProject.Models.Ordered;
import com.example.ECommerceProject.Models.Product;
import com.example.ECommerceProject.Models.Seller;
import com.example.ECommerceProject.Repository.ProductRepository;
import com.example.ECommerceProject.Repository.SellerRepository;
import com.example.ECommerceProject.Service.ProductService;
import com.example.ECommerceProject.Transformer.ProductTransformer;
import com.example.ECommerceProject.enums.ProductCategory;
import com.example.ECommerceProject.enums.ProductStatus;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {

        Seller seller;
        try{
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch (Exception e){
            throw new InvalidSellerException("Seller doesnt exist");
        }

        Product product = ProductTransformer.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        //add product to current product of seller
        seller.getProductList().add(product);
        sellerRepository.save(seller);          //saves both seller product

        //prepare ResponseDto

        return ProductTransformer.productToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category) {
        List<Product> products = productRepository.findByProductCategory(category);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : products){
            productResponseDtos.add(ProductTransformer.productToProductResponseDto(product));
        }
        return productResponseDtos;
    }

    @Override
    public List<ProductResponseDto> productsBySellerEmail(String email) throws Exception {
        Seller seller ;
        try{
            seller = sellerRepository.findByEmail(email);
        }
        catch (Exception e){
            throw new Exception("No products with this Sellers EmailId");
        }

        List<Product> products = seller.getProductList();
        List<ProductResponseDto> list = new ArrayList<>();
        for(Product p : products){
            ProductResponseDto prdto = ProductTransformer.productToProductResponseDto(p);
            list.add(prdto);
        }
        return list;
    }

    @Override
    public DeleteProductResponseDto deleteBySellerProductId(int sid, int pid) throws Exception {
        Seller seller;
        try{
            seller = sellerRepository.findById(sid).get();
        }
        catch (Exception e){
            throw new InvalidSellerException("Seller id is not present");
        }
        Product product;
        try{
            product = productRepository.findById(pid).get();
        }
        catch (Exception e){
            throw new Exception("Product with this Id is not present");
        }
        List<Product> products = seller.getProductList();
        boolean f = false;
        for(Product product1 : products){
            if(product1.getId() == pid){
                f = true;
                break;
            }
        }
        if(!f) throw new Exception("seller has no products with this productId");
        List<Product> products1 = seller.getProductList();
        products1.remove(product);
        productRepository.delete(product);
        sellerRepository.save(seller);
        return ProductTransformer.productToProductDto(product);
    }

    @Override
    public List<ProductResponseDto> getAvailableProducts() throws Exception {
        List<Product> list = productRepository.findAll();
        List<Product> availableProducts = new ArrayList<>();
        for(Product product : list){
            if(product.getProductStatus() == ProductStatus.AVAILABLE)
              availableProducts.add(product);
        }
        if(availableProducts.isEmpty()) throw new Exception("No Products are available Now");

        List<ProductResponseDto> prdList = new ArrayList<>();
        for(Product product : availableProducts){
            ProductResponseDto prdto = ProductTransformer.productToProductResponseDto(product);
            prdList.add(prdto);
        }
        return prdList;
    }

    @Override
    public List<ProductResponseDto> getProductWithQuantityLessThan10() throws Exception {
        List<Product> list = productRepository.findAll();
        List<Product> required = new ArrayList<>();
        for(Product product : list){
            if(product.getQuantity() < 10) required.add(product);
        }

        if(required.isEmpty()) throw new Exception("Oops! No products with quantity lesser than 10");


        List<ProductResponseDto> prdList = new ArrayList<>();
        for(Product product : required){
            ProductResponseDto prdto = ProductTransformer.productToProductResponseDto(product);
            prdList.add(prdto);
        }
        return prdList;

    }

    @Override
    public List<ProductResponseDto> getCheapestProducts() {
        List<Product> lowPriceProducts = productRepository.get5CheapestProducts();

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product p : lowPriceProducts){
            ProductResponseDto responseDto = ProductTransformer.productToProductResponseDto(p);
            productResponseDtoList.add(responseDto);
        }
        return productResponseDtoList;
    }


    @Override
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String category) {

        List<Product> products = productRepository.getAllProductsByPriceAndCategory(price, category);

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product p : products){
            ProductResponseDto responseDto = ProductTransformer.productToProductResponseDto(p);
            productResponseDtoList.add(responseDto);
        }
        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> getCostlyProducts() {

        List<Product> costlyProducts = productRepository.getTopCostlyProducts();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product p : costlyProducts){
            ProductResponseDto responseDto = ProductTransformer.productToProductResponseDto(p);
            productResponseDtoList.add(responseDto);
        }
        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto getCheaperProductInTheCategory(String category) {
        Product product = productRepository.getCheaperProductInTheCategory(category);

        return ProductTransformer.productToProductResponseDto(product);
    }

    @Override
    public ProductResponseDto getCostlierProductInTheCategory(String category) {
        Product product = productRepository.getCostlierProductInTheCategory(category);

        return ProductTransformer.productToProductResponseDto(product);
    }

    @Override
    public void decreaseProductQuantity(Item item) throws Exception {

            Product product = item.getProduct();
            int quantity = item.getRequiredQuantity();
            int currQntity = product.getQuantity();
            if(quantity > currQntity){
                throw new Exception("Out of stock");
            }

            product.setQuantity(currQntity-quantity);
            if(product.getQuantity() == 0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }
    }
}
