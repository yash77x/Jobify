<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Jobify</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<body>

    <nav class="navbar navbar-expand-sm navbar-dark" style="background-color: #6f5596">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> &ensp; &ensp; JOBIFY</a>
		</div>
	</nav>

    <div class="main">

        <!-- Sign up form -->
        <section class="signup"> 
            <div class="container">
                <div class="signup-content">
                    <div class="signup-image">
                        <img src="images/young.jpg" style="border-radius: 20%; width: 100%;" alt="sing up image">
                        <img src="images/signup.png" width="100%" alt="sing up image">
                    </div>
                    <div style=" margin-left: 40px;   border-left: 1px dotted #5f656d; /* Border on the left */
                    width: 1px; /* Width instead of height */
                    "></div>
                    <div class="signup-form">
                    
                      <ul class="nav nav-pills nav-justified mb-3">
                            <a class="nav-link active" style="background-color: #6f5596;" href="#" >Login</a>
                            <a class="nav-link" style="color:#6f5596; " href="Register.jsp">Register</a>
                      </ul>
						
                        <h2>${saved}</h2>
                        
                        <br><br><br>
                        <form action="login" method="POST" class="register-form" id="register-form">

                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="email" name="email" id="email" placeholder="Your Email"/>
                            </div>
                            <p style="color: red;">${emailInvalid}</p> 
                            <p style="color: red;">${emailExist}</p>
                                                    
                            
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="password" id="pass" placeholder="Password"/>
                            </div>
                            <p style="color: red;">${passwordInvalid}</p>
                            <p style="color: red;">${wrong}</p>
                            <p style="color: red;">${block}</p>
                            <div class="form-group form-button" >
                                <input type="submit" style="background-color: #6f5596" name="signup" id="signup" class="form-submit" value="Login"/>
                            </div>
                        </form>
                    </div>

                </div>
            </div>

        </section>
    </div>
    <br>
    <footer>
        <div class="text-center">
              <p>
                Copyright &copy;
                2023 All rights reserved
              </p>
              </div>
    </footer>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>