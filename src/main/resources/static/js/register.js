function registerUser() {
    const errorDiv = document.getElementById("error-message");
    
    // Clear previous errors
    errorDiv.innerText = "";

    const data = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
        password: document.getElementById("password").value
    };

    fetch("/api/auth/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(async res => {
        if (res.ok) {
            // Success! Redirect to login
            window.location.href = "/api/auth/login";
        } else {
            // Error! Get the text "Email already exists" from the response
            const errorText = await res.text();
            errorDiv.innerText = errorText;
        }
    })
    .catch(err => {
        console.error("Error:", err);
        errorDiv.innerText = "Something went wrong. Please try again.";
    });
}