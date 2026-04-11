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
 
 
  
  .then(async response => {
      if (response.ok) {
          const data = await response.json(); 
          alert(data.message); 
          window.location.href = "/api/auth/patient-dashboard";
      } else {
          const errorText = await response.text();
          alert("Error: " + errorText);
      }
  })
    .catch(error => {
        console.error('Error:', error);
    });
});

