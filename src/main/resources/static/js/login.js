

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
			
				// 'url' will be "/patient/dashboard", "/doctor/dashboard", etc.
				
				const url = await res.text();
				
				window.location.href = url;
			} else {
				const error = await res.text();
				document.getElementById("error-message").innerText = error;
			}
		});
}