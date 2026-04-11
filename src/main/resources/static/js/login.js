

function loginUser() {
	const email = document.getElementById("email").value;
	const password = document.getElementById("password").value;
	const role = document.getElementById("role").value;
	
	const loginData = {
		email: email,
		password: password,
		role : role
	};
	fetch("/api/auth/login", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify(loginData)
	})
		
		
		.then(async res => {

		if (res.ok) {

		// 1. CHANGE THIS: Use .json() instead of .text()

		const data = await res.json();


		// 2. CHANGE THIS: Access the redirectUrl property from the object

		
		window.location.href = window.location.origin + data.redirectUrl;

		} else {

		const error = await res.text();

		document.getElementById("error-message").innerText = error;

		}

		})

		.catch(err => {

		console.error("Login failed:", err);
})
}