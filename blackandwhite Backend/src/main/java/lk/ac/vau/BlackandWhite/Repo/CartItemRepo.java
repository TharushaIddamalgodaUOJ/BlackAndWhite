package lk.ac.vau.BlackandWhite.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.ac.vau.BlackandWhite.Model.CartItem;
import lk.ac.vau.BlackandWhite.Model.Product;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    
    List<CartItem> findAll();

    
    CartItem findById(long id);

    
    List<CartItem> findByProduct(Product product);

    
    List<CartItem> findByQuantityGreaterThan(int quantity);

    
    List<CartItem> findByQuantityLessThan(int quantity);

    
    List<CartItem> findByQuantityBetween(int minQuantity, int maxQuantity);
}

