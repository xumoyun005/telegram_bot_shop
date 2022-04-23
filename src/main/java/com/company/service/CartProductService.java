package com.company.service;

import com.company.container.ComponentContainer;
import com.company.database.Database;
import com.company.model.CartProduct;
import com.company.model.Customer;
import com.company.model.Product;

import java.util.Optional;

public class CartProductService {
    public static void add(String userId, Integer productId, Integer quantity){
        Customer customer = CustomerService.getCustomerById(userId);
        Product product = ProductService.getProductById(productId);

        Optional<CartProduct> optional = Database.CART_PRODUCTS.stream()
                .filter(cartProduct -> {
                    if (!cartProduct.getCustomer().getId().equals(customer.getId())) return false;
                    assert product != null;
                    return cartProduct.getProduct().getId().equals(product.getId());
                })
                .findFirst();

        if(optional.isPresent()){
            CartProduct cartProduct = optional.get();
            cartProduct.setQuantity(cartProduct.getQuantity()+quantity);
        }else{
            CartProduct cartProduct = new CartProduct(++ComponentContainer.generalId, customer, product, quantity);
            Database.CART_PRODUCTS.add(cartProduct);
        }
    }
    public static void delete(Integer cartProductId) {
        Database.CART_PRODUCTS.removeIf(cartProduct -> cartProduct.getId().equals(cartProductId));
    }
}
