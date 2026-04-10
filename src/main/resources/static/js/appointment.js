document.getElementById('appointmentForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Stop page from refreshing

    // Create a FormData object from the HTML form
    const formData = new FormData(this);
    
    // Convert FormData to a plain JavaScript Object
    const data = Object.fromEntries(formData.entries());

    console.log("Sending Data:", data); // You will see the doctorId here!

    // Send data to Spring Boot Controller
    fetch('/api/appointment/book-appointment', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
		credentials: 'include',
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            alert("Appointment Booked Successfully!");
            window.location.href = "/api/auth/patient-dashboard";
        } else {
            alert("Error booking appointment. Please try again.");
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
});

