<%-- 
    Document   : nuevoPerfil
    Created on : 12-mar-2019, 16:38:37
    Author     : Jesus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>University Social Network</title>

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <!--<link href="/nuevoPerfil/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">-->

        <!-- Custom fonts for this template -->
        <link href="https://fonts.googleapis.com/css?family=Saira+Extra+Condensed:500,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Muli:400,400i,800,800i" rel="stylesheet">
        <link href="nuevoPerfil/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="nuevoPerfil/css/resume.min.css" rel="stylesheet">

    </head>

    <body id="page-top">

        <nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top" id="sideNav">
            <a class="navbar-brand js-scroll-trigger" href="#page-top">
                <span class="d-block d-lg-none">${usuario.nombre} ${usuario.apellidos}</span>
                <span class="d-none d-lg-block">
                    <img class="img-fluid img-profile rounded-circle mx-auto mb-2" src="nuevoPerfil/img/profile.jpg" alt="">
                </span>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="#about">Mi perfil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="#experience">Mis amigos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="#education">Mis grupos</a>
                    </li>
                    <li class="nav-item">
                        <div class="alert alert-dark" role="alert">
                            Añadir nuevo...
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="#skills">Publicación</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="#interests">Amigo</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="#awards">Grupo</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="container-fluid p-0">

            <section class="resume-section p-3 p-lg-5 d-flex align-items-center" id="about">
                <div class="w-100">
                    <h1 class="mb-0">${usuario.nombre}
                        <span class="text-primary">${usuario.apellidos}</span>
                    </h1>
                    <div class="subheading mb-5">
                        <a href="mailto:name@email.com">${usuario.email}</a>
                    </div>
                    <div class="subheading mb-2 text-secondary">Mis últimas Publicaciones</div>
                    <tr>
                        <c:forEach items="${usuario.postList}" var="Post">
                        <form action="BorrarControlador" method="POST">
                            <div class="container">
                                <div class="row">

                                    <div class="col-8">
                                        <p class="lead mb-5">${Post.contenido}</p>
                                    </div>
                                    <div class="col-4">
                                        <button type="submit" value="${Post.idPost}" name="borrarPost" class="btn btn-secondary btn-lg active" aria-pressed="true">Borrar Comentario</button>
                                    </div>

                                </div>
                            </div>
                        </form>
                    </c:forEach>
                    </tr>
                    <div class="social-icons">
                        <a href="#">
                            <i class="fab fa-linkedin-in"></i>
                        </a>
                        <a href="#">
                            <i class="fab fa-instagram"></i>
                        </a>
                        <a href="#">
                            <i class="fab fa-twitter"></i>
                        </a>
                        <a href="#">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                    </div>
                </div>
            </section>

            <hr class="m-0">

            <section class="resume-section p-3 p-lg-5 d-flex justify-content-center" id="experience">
                <div class="w-100">
                    <h2 class="mb-5">Mis amigos</h2>

                    <c:forEach items="${usuario.amistadesList}" var="amistad">  

                        <div class="resume-item d-flex flex-column flex-md-row justify-content-between mb-5">
                            <form action="BorrarControlador" method="POST">
                                <div class="col-sm-4">
                                    <img class="img-fluid img-profile rounded-circle mx-auto mb-2" src="nuevoPerfil/img/profile3.jpg" alt="">
                                    <h3 name="amigoABorrar" value="${amistad.amistadesid}" class="mb-0 text-secondary text-center">${amistad.idUsuario2.nombre}</h3>
                                    <!--<input name="amigoABorrar" value="${amistad.amistadesid}"></input>-->
                                    <button name="amigoABorrar" type="submit" value="${amistad.amistadesid}" class="btn btn-primary btn-lg btn-block">Dejar de seguir</button>

                                </div>
                                <div class="col-sm-8">
                                    <div class="resume-content">
                                        <div class="subheading mb-3 text-primary">Sus últimos comentario</div>
                                        <c:forEach items="${amistad.idUsuario2.postList}" var="post">  
                                            <p>${post.contenido}</p>
                                        </c:forEach>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </c:forEach>  

                    <!--<div class="resume-item d-flex flex-column flex-md-row justify-content-between mb-5">
                     <div class="col-sm-4">
                       <img class="img-fluid img-profile rounded-circle mx-auto mb-2" src="img/profile2.jpg" alt="">
                       <h3 class="mb-0 text-secondary text-center">Jorge Cruzado</h3>
                       
                     </div>
             
             
                     <div class="col-sm-8">
                       <div class="resume-content">
                         <div class="subheading mb-3 text-primary">Su último comentario</div>
                         <p>Bring to the table win-win survival strategies to ensure proactive domination. At the end of the day, going forward, a new normal that has evolved from generation X is on the runway heading towards a streamlined cloud solution. User generated content in real-time will have multiple touchpoints for offshoring.</p>
                       </div>
                     </div>
                   </div>
                    
                    -->
            </section>

            <hr class="m-0">

            <section class="resume-section p-3 p-lg-5 d-flex align-items-center" id="education">
                <div class="w-100">
                    <h2 class="mb-5">mis grupos</h2>
                    <c:forEach items="${usuario.gruposList}" var="grupos">  
                        <div class="resume-item d-flex flex-column flex-md-row justify-content-between mb-5">
                            <div class="col-sm-4 align-items-left">
                                <h3 class="mb-0 text-primary text-center">${grupos.nombregrupo}</h3>
                                <p></p>
                                <img class="img-fluid img-profile rounded mx-auto mb-2" src="perfil/img/biketeam.jpg" alt="">
                                <button type="button" class="btn btn-primary btn-lg btn-block">Integrantes
                                    <span class="badge badge-light">${grupos.getUsuarioList().size()}</span>
                                    <span class="sr-only">unread messages</span>
                                </button>
                            </div>
                            <div class="col-sm-8">
                                <div class="resume-content">
                                    <div class="subheading mb-3 text-dark">Últimos comentarios del grupo</div>
                                    <c:forEach items="${grupos.comentariosList}" var="comentariosgrupos"> 
                                        <h5 class="mb-0 text-secondary text-left">${comentariosgrupos.comentario}</h5>
                                    </c:forEach> 
                                </div>
                            </div>

                        </div>
                    </c:forEach>  

                    <div class="resume-item d-flex flex-column flex-md-row justify-content-between mb-5">
                        <div class="col-sm-4 align-items-left">
                            <h3 class="mb-0 text-primary text-center">Cardio Workout</h3>
                            <p></p>
                            <img class="img-fluid img-profile rounded mx-auto mb-2" src="perfil/img/cardioworkout.jpg" alt="">
                            <button type="button" class="btn btn-primary btn-lg btn-block">Integrantes
                                <span class="badge badge-light">16</span>
                                <span class="sr-only">unread messages</span>
                            </button>
                        </div>
                        <div class="col-sm-8">
                            <div class="resume-content">
                                <div class="subheading mb-3 text-dark">Últimos comentarios del grupo</div>
                                <p>Bring to the table win-win survival strategies to ensure proactive domination. At the end of the day, going forward, a new normal that has evolved from generation X is on the runway heading towards a streamlined cloud solution. User generated content in real-time will have multiple touchpoints for offshoring.</p>
                            </div>
                        </div>
                    </div>

            </section>

            <hr class="m-0">

            <section class="resume-section p-3 p-lg-5 d-flex align-items-center" id="skills">
                <div class="w-100">
                    <form action="PerfilUsuario" method="POST">
                        <h2 class="mb-5">Nueva Publicación</h2>
                        <div class="form-group text-primary">
                            <label for="exampleFormControlTextarea1">¿Sobre qué quieres hablar?</label>
                            <textarea name="contenido" class="form-control" id="exampleFormControlTextarea1" rows="4"></textarea>
                        </div>

                        <div class="input-group">
                            <select name="privacidad" class="custom-select" id="inputGroupSelect04" aria-label="Example select with button addon">
                                <option selected>Elige donde quieres publicar...</option>
                                <option value="0">A todo el mundo</option>
                                <c:forEach items="${usuario.gruposList}" var="grupos"> 
                                    <option value="${grupos.idgrupos}">${grupos.nombregrupo}</option>
                                </c:forEach>
                            </select>
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="submit">Publicar</button>
                            </div>
                        </div>
                    </form>

                </div>
            </section>

            <hr class="m-0">

            <section class="resume-section p-3 p-lg-5 d-flex align-items-center" id="interests">
                <div class="w-100">
                    <h2 class="mb-5">Añadir Nuevo Amigo</h2>
                    <div class="input-group mb-3">
                        <form action="PerfilUsuario" method="POST">  
                            <input name="nuevoAmigo" type="text" class="form-control" placeholder="Escribe el nombre de tu amigo" aria-label="Escribe el nombre de tu amigo" aria-describedby="button-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Buscar</button>
                            </div>
                            <form>
                                </div>
                                <br>

                                <h3 class="mb-5 text-primary">Amigos Sugeridos</h3>

                                <div class="container px-lg-5">
                                    <div class="row mx-lg-n5">

                                        <div class="col py-3 px-lg-5 border bg-light">
                                            <h3 class="mb-0 text-secondary text-center">Victor Ramón</h3>
                                            <br>
                                            <img class="img-fluid img-profile rounded-circle mx-auto mb-2" src="nuevoPerfil/img/profile6.jpg" alt="">
                                            <br>
                                            <button type="button align-items-center text-primary" class="justify-content-center btn btn-outline-danger">Seguir</button>
                                        </div>

                                        <div class="col py-3 px-lg-5 border bg-light">
                                            <h3 class="mb-0 text-secondary text-center">Andrea Silva</h3>
                                            <br>
                                            <img class="img-fluid img-profile rounded-circle mx-auto mb-2" src="nuevoPerfil/img/profile4.jpg" alt="">
                                            <br>
                                            <button type="button align-items-center text-primary" class="justify-content-center btn btn-outline-danger">Seguir</button>
                                        </div>

                                        <div class="col py-3 px-lg-5 border bg-light">
                                            <h3 class="mb-0 text-secondary text-center">Marta Ferreiro</h3>
                                            <br>
                                            <img class="img-fluid img-profile rounded-circle mx-auto mb-2" src="nuevoPerfil/img/profile5.jpg" alt="">
                                            <br>
                                            <button type="button align-items-center text-primary" class="btn btn-outline-danger">Seguir</button>
                                        </div>

                                    </div>
                                </div>
                                </div>

                                </section>

                                <hr class="m-0">

                                <section class="resume-section p-3 p-lg-5 d-flex align-items-center center-block" id="awards">
                                    <div class="w-100">
                                        <h2 class="mb-5">Unirse a Grupos Existentes</h2>
                                        <form action="PerfilUsuario" method="POST">
                                            <div class="center-block container-fluid px-lg-5">

                                                <div class="row mx-lg-n5 center-block">
                                                    <c:forEach items="${gruposExistentes}" var="grupo">  
                                                    <div class="col-sm-4 py-3 px-lg-5 border bg-light">
                                                        <h3 class="mb-0 text-secondary text-center">${grupo.nombregrupo}</h3>
                                                        <br>
                                                        <img class="img-fluid img-profile rounded-circle mx-auto mb-2" src="perfil/img/biketeam.jpg" alt="">
                                                        <br>
                                                        <button name="grupoAUnirse" value="${grupo.idgrupos}" type="submit" class="btn btn-primary btn-lg btn-block">Unirse</button>
                                                    </div>
                                                      </c:forEach>
                                                </div>
                                            </div>
                                        </form>
                                        <br>
                                        <h2 class="mb-5">Añadir Nuevo Grupo</h2>
                                        <form action="PerfilUsuario" method="POST">
                                            <div class="input-group mb-3">
                                                <input name="nombregrupo" type="text" class="form-control" placeholder="Escribe el nombre del grupo" aria-label="Escribe el nombre del grupo" aria-describedby="button-addon2">
                                                <div class="input-group-append">
                                                    <div class="input-group">
                                                        <div class="custom-file">
                                                            <input type="file" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04">
                                                            <label class="custom-file-label" for="inputGroupFile04">Elegir Imagen</label>
                                                        </div>
                                                        <div class="input-group-append">
                                                            <button class="btn btn-outline-secondary" type="submit" id="inputGroupFileAddon04">Crear Grupo</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>

                                    </div>

                                    </div>

                                    </div>
                                </section>

                                </div>

                                <!-- Bootstrap core JavaScript -->
                                <script src="nuevoPerfil/vendor/jquery/jquery.min.js"></script>
                                <script src="nuevoPerfil/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

                                <!-- Plugin JavaScript -->
                                <script src="nuevoPerfil/vendor/jquery-easing/jquery.easing.min.js"></script>

                                <!-- Custom scripts for this template -->
                                <script src="nuevoPerfil/js/resume.min.js"></script>

                                </body>

                                </html>
