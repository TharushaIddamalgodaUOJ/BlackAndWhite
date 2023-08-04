package lk.ac.vau.BlackandWhite.Service;

import java.util.List;

import lk.ac.vau.BlackandWhite.Model.Product;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    boolean deleteProduct(Long id);
}


