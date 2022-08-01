<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">

<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {
  box-sizing: border-box;
  font-family: Arial, Helvetica, sans-serif;
}

body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

/* style the container */
.container {
  position: fixed;
  bottom: 330px;
  right: 530px;
  width: 400px;
  border: 3px solid #73AD21;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px 20px 60px 60px;
  
} 

/* style the submit button */
input[type=submit] {
  background-color: #04AA6D;
  color: white;
  cursor: pointer;
  
}

input[type=submit]:hover {
  background-color: #45a049;
}

.col {
  width: 100%;
  margin-top: 0;
}

/* show the hidden text on small screens */
.hide-md-lg {
  display: block;
  text-align: center;
}


/* Style the content */
.content {
  background-color: #E4E9F7;
  padding: 10px;
  height: 700px; 
}

/* Style the footer */
.footer {
  background-color: #333;
  padding: 20px;
}
</style>
</head>
<body>


<div class="content">
  <h1>Forum</h1>
  <p>Explore with Telnet.....</p>
  <div class="container">
  <form action="Login" method="POST">
    
     <div class="col">
        <div class="hide-md-lg">
          <p>Sign in manually</p>
        </div>
    
	User Name <input type="text" name="username" placeholder="Enter User Name" required><br><br>
	Password <input type="password" name="psw" placeholder="Enter Password" required><br><br>
	<input type="submit" value="Login"><br>
    </div>
</form>
</div>
</div>

<div class="footer">
  <p>Footer</p>
</div>

</body>
</html>