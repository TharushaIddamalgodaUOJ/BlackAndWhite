// Array to hold cart items
const cartItems = [];

// Function to fetch product data from the API
function fetchProducts() {
    fetch('http://localhost:8080/api/products')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(products => {
            const productList = document.getElementById('product-list');
            productList.innerHTML = ''; // Clear existing products
            products.forEach(product => {
                productList.innerHTML += `
                    <div class="col mb-5">
                        <div class="card h-100">
                            <img class="card-img-top" src="${product.imageUrl}" alt="${product.name}" />
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <h5 class="fw-bolder">${product.name}</h5>
                                    <span>$${product.price.toFixed(2)}</span>
                                    <p>${product.description}</p>
                                    <button class="btn btn-primary" onclick="addToCart(${product.id})">Add to Cart</button>
                                </div>
                            </div>
                        </div>
                    </div>
                `;
            });
        })
        .catch(error => {
            console.error('Error fetching products:', error);
        });
}

// Call the fetchProducts function on page load
document.addEventListener('DOMContentLoaded', fetchProducts);

// Function to add an item to the cart and save it to the database
function addToCart(itemId) {
    // Save the item to the database using the backend API
    fetch('http://localhost:8080/api/cart', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            productId: itemId,
            quantity: 1, // You can modify this value based on your logic to handle quantity
        }),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            // If the item is successfully saved in the database, add it to the cartItems array
            return response.json();
        })
        .then(cartItem => {
            cartItems.push(cartItem);
            // Update the cart item count badge
            updateCartItemCount();
        })
        .catch(error => {
            console.error('Error saving item to cart:', error);
        });
}

// Function to update the cart item count badge
function updateCartItemCount() {
    const cartItemCountSpan = document.getElementById('cartItemCount');
    cartItemCountSpan.innerText = cartItems.length;
}

// Function to fetch cart items from the API
function fetchCartItems() {
    fetch('http://localhost:8080/api/cart')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(items => {
            // Update the cartItems array with the fetched items
            cartItems.push(...items);
            updateCartItemCount();
            renderCartItems();
        })
        .catch(error => {
            console.error('Error fetching cart items:', error);
        });
}

// Function to render the cart items in the cart page
function renderCartItems() {
    const cartItemsContainer = document.getElementById('cartItemsContainer');
    cartItemsContainer.innerHTML = '';

    cartItems.forEach(item => {
        cartItemsContainer.innerHTML += `
            <div class="row align-items-center mb-4">
                <div class="col-md-3">
                    <img src="${item.product.imageUrl}" alt="${item.product.name}" class="img-fluid rounded shadow-sm" />
                </div>
                <div class="col-md-6">
                    <h5 class="mb-0">${item.product.name}</h5>
                    <span class="text-muted">Price: $${item.product.price.toFixed(2)}</span>
                </div>
            </div>
        `;
    });
}

// Call the renderCartItems function on page load for the cart page
if (window.location.pathname === '/cart.html') {
    document.addEventListener('DOMContentLoaded', fetchCartItems);
}

// Function to navigate to the cart page
function openCartPage() {
    window.location.href = 'cart.html';
}

// Function to navigate to the account page
function openAccountPage() {
    window.location.href = 'account.html';
}
