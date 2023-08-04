package lk.ac.vau.BlackandWhite.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lk.ac.vau.BlackandWhite.Model.CartItem;
import lk.ac.vau.BlackandWhite.Model.CartItemRequest;
import lk.ac.vau.BlackandWhite.Model.Product;
import lk.ac.vau.BlackandWhite.Service.CartItemService;
import lk.ac.vau.BlackandWhite.Service.ProductService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    private final CartItemService cartItemService;
    private final ProductService productService;

    public CartItemController(CartItemService cartItemService, ProductService productService) {
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Long id) {
        CartItem cartItem = cartItemService.getCartItemById(id);
        if (cartItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItemRequest cartItemRequest) {
        Long productId = cartItemRequest.getProductId();
        int quantity = cartItemRequest.getQuantity();

        // Fetch the product by its ID using the ProductService
        Product product = productService.getProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Add the product to the cart using the CartItemService
        CartItem addedCartItem = cartItemService.addToCart(product, quantity);
        return new ResponseEntity<>(addedCartItem, HttpStatus.CREATED);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long id) {
        
        CartItem cartItem = cartItemService.getCartItemById(id);
        if (cartItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        
        boolean removed = cartItemService.removeCartItem(cartItem);
        if (removed) {
            
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    
    
    
}
