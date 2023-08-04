// Function to fetch cart items from the API and render them as cards on the cart.html page
function fetchCartItems() {
    fetch('http://localhost:8080/api/cart')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(items => {
            // Get the container to display cart items
            const cartItemsContainer = document.getElementById('cartItemsContainer');

            // Clear the existing content
            cartItemsContainer.innerHTML = '';

            // Render cart items as cards
            items.forEach(item => {
                cartItemsContainer.innerHTML += `
                    <div class="col mb-5">
                        <div class="card h-100">
                            <img class="card-img-top" src="${item.product.imageUrl}" alt="${item.product.name}" />
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <h5 class="fw-bolder">${item.product.name}</h5>
                                    <span>Rs.${item.product.price.toFixed(2)}</span>
                                    <button class="btn btn-danger mt-2" onclick="removeItem(${item.id})">Remove</button>
                                </div>
                            </div>
                        </div>
                    </div>
                `;
            });
            // Update the cart count in the navbar
            const cartItemCountSpan = document.getElementById('cartItemCount');
            cartItemCountSpan.textContent = items.length;
            
        })
        .catch(error => {
            console.error('Error fetching cart items:', error);
        });
}

function removeItem(itemId) {
    // Send a request to the API to remove the item with the given itemId
    fetch(`http://localhost:8080/api/cart/${itemId}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        // If the removal is successful, re-fetch the cart items to update the displayed list
        fetchCartItems();
    })
    .catch(error => {
        console.error('Error removing item:', error);
    });
}


// Call the fetchCartItems function on page load for the cart page
if (window.location.pathname === '/cart.html') {
    fetchCartItems(); // Call the function directly without waiting for DOMContentLoaded event
}

// Function to navigate to the cart page
function openCartPage() {
    window.location.href = 'cart.html';
}

// Function to navigate to the account page
function openAccountPage() {
    window.location.href = 'account.html';
}
