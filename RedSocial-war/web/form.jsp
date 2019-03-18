<%-- 
    Document   : form
    Created on : 16-mar-2019, 20:04:51
    Author     : Jesus
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>University Social Network</title>
    <!-- meta tags -->
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="keywords" content="Art Sign Up Form Responsive Widget, Audio and Video players, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, 
		Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design"
    />
    <!-- /meta tags -->
    <!-- custom style sheet -->
    <link href="login/css/style.css" rel="stylesheet" type="text/css" />
    <!-- /custom style sheet -->
    <!-- fontawesome css -->
    <link href="login/css/fontawesome-all.css" rel="stylesheet" />
    <!-- /fontawesome css -->
    <!-- google fonts-->
    <link href="//fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <!-- /google fonts-->

</head>


<body>
    <h1>University Social Network</h1>
    <div class=" w3l-login-form">
        <h2>Crear tu cuenta de University Social Network </h2>

        <form action="<%=request.getContextPath()%>/Register" method="POST">

            <div class=" w3l-form-group">
                <label>Email</label>
                <div class="group">
                    <i class="fas fa-user"></i>
                    <input name ="email" type="text" class="form-control" placeholder="Correo Electrónico" required="required" />
                </div>
            </div>
            
             <div class=" w3l-form-group">
                <label>Nombre</label>
                <div class="group">
                    <i class="fas fa-user"></i>
                    <input name ="nombre" type="text" class="form-control" placeholder="Nombre" required="required" />
                </div>
            </div>
            
            <div class=" w3l-form-group">
                <label>Apellidos</label>
                <div class="group">
                    <i class="fas fa-user"></i>
                    <input name ="apellidos" type="text" class="form-control" placeholder="Apellidos" required="required" />
                </div>
            </div>
            
             <div class=" w3l-form-group">
                <label>Teléfono</label>
                <div class="group">
                    <i class="fas fa-phone"></i>
                    <input name ="telefono" type="tel" class="form-control" placeholder="Teléfono" required="required" />
                </div>
            </div>
            
            <div class=" w3l-form-group">
                <label>Contraseña:</label>
                <div class="group">
                    <i class="fas fa-unlock"></i>
                    <input name="password" type="password" class="form-control" placeholder="Contraseña" required="required" />
                </div>
            </div>
            
            <div class=" w3l-form-group">
                  <label>Subir Foto Perfil</label>
                  <div class="custom-file">
                    <input type="file" class="custom-file-input" id="inputGroupFile02">
                    <label class="custom-file-label" for="inputGroupFile02" aria-describedby="inputGroupFileAddon02"></label>
                  </div>
                  
               
            </div>    
            
            <div class="forgot">
                <a href="#">¿Olvidaste tu contraseña?</a>
                <p><input type="checkbox">Recuérdame</p>
            </div>
            <button type="submit">Registrar</button>
        </form>
        <p class=" w3l-register-p">Prefiero iniciar sesión<a href="login.jsp" class="register"> Pulse aqui</a></p>
    </div>
    <footer>
        <p class="copyright-agileinfo"> &copy; 2018 Víctor Ramón. Todos los Derechos Reservados | Design by <a href="http://w3layouts.com">DAM</a></p>
    </footer>

</body>

</html>
