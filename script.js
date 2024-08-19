document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("login-form");
    const dashboard = document.getElementById("dashboard");
    
    loginForm.addEventListener("submit", function (event) {
        event.preventDefault();
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;
        // Send a POST request to the backend to authenticate the user
        fetch('/login', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({ username, password })
        })
            .then(response => response.json())
            .then(function(data) {
                if (data.authenticated) {
                    //Redirect to the dashboard page
                    window.location.href = '/dashboard';           
                } else {
                    alert("Invalid username or password");
                }
            })
            .catch(function(error) {
                console.error(error) });
    });
});
   