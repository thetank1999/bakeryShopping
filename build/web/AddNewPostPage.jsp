<%-- 
    Document   : AddNewProductPage
    Created on : Jun 10, 2021, 8:08:03 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Online Shopping</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }


            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }
        </style>
        <style>
            .navbar-brand {
                padding-left: 20%;
                padding-right: 20%;
            }
            .jumbotron-heading {
                font-family: sans-serif;
                color: #990000;
            }
        </style>
        <script>
            function showImage(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $('#imagePreview')
                                .attr('src', e.target.result);
                    };

                    reader.readAsDataURL(input.files[0]);
                }
                document.getElementById("imagePreview").style.display = "inline-block";
            }
        </script>
        <script>
            function ResetImage() {
                $("#postImage").val('');
                document.getElementById("imagePreview").style.display = "none";
            }
        </script>
    </head>
    <style type="text/css">
        #addPostForm label.error{
            color: red;
            width: 250px;
            font-style: italic;
        }
    </style>
    <style>
        .navbar-brand {
            padding-left: 20%;
            padding-right: 20%;
        }
        .jumbotron-heading {
            font-family: sans-serif;
            color: #990000;
            text-shadow: -2px -2px 0 white, 2px -2px 0 white, -2px 2px 0 white, 2px 2px 0 white;
        }
        .lead {

        }
    </style>
    <body>
        <script type="text/javascript" src="js/jquery-3.6.0.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/additional-methods.js"></script>
        <script type="text/javascript">
            $(function () {
                $("#addPostForm").validate({
                    rules: {
                        txtPostTitle: {
                            required: true,
                            rangelength: [5, 150]
                        },
                        postImage: {
                            required: true,
                            extension: "jpg|png"
                        },
                        txtPostDetail: {
                            required: true,
                            rangelength: [50, 10000]
                        }
                    },
                    messages: {
                        txtPostTitle: {
                            required: " Please input Post Title!",
                            rangelength: " Post Title must be 5-150 characters long!"
                        },
                        postImage: {
                            required: " Please input Post Image!",
                            extension: " Post Image can only be either .jpg or .png!"
                        },
                        txtPostDetail: {
                            required: " Please input Post Detail!",
                            rangelength: " Product Name must be 50-10000 characters long!"
                        }
                    }
                })
            });
        </script>
        <!--begin of menu-->
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <a class="navbar-brand" href="home"> <img src="images/logo.png" alt="Home" width="130px" height="50px" style="background: 100% 100%; margin: auto; padding: auto;"></img></a>

            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                <c:set var="userEmail" value="${sessionScope.CURRENT_USER.getEmail()}"/>
                <c:if test="${empty userEmail}">
                    <ul class="navbar-nav m-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="Login.jsp">Login <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="Registration.jsp">Sign Up</a>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${not empty userEmail}">
                    <ul class="navbar-nav m-auto">
                        <li class="nav-item active">
                            <b class="nav-link" style="color: white">Welcome, ${sessionScope.CURRENT_USER.getFullName()}</b>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="UpdateAccount?Email=${userEmail}">Profile <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="logOut">Log Out</a>
                        </li>
                    </ul>
                </c:if>
            </div>
        </nav>
        <section class="jumbotron text-center" style="background-image: url('images/Home_Background.jpg'); background-repeat: repeat;">
            <div class="container">
                <h1 class="jumbotron-heading">BAKERY STARTUP </h1>
                <p class="lead text-muted mb-0">High quality products only!</p>
            </div>
        </section>
        <div class="container">
            <div class="row">
                    <!--Left side of the page - The Dashboard-->
                    <div class="col-sm-3">
                        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Dashboard </div>
                        <ul class="list-group category_block">
                            <li class="list-group-item text-white" ><a href="home">Home</a></li>
                            <li class="list-group-item text-white" ><a href="ManageProduct">Manage Product</a></li>
                            <li class="list-group-item text-white" ><a href="CategoryManaging">Manage Category</a></li>
                            <li class="list-group-item text-white" ><a href="ManagePost">Manage Post</a></li>
                        </ul>
                    </div>
                    <!--End of the left side-->
            <div id="editProduct" class="col-sm-9">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <form action="addPost" id="addPostForm" method="post"
                              enctype="multipart/form-data">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add new Post</h4>
                            </div>
                            <div class="modal-body">
                                <input value="${postDto.id}" name="txtPostId" type="hidden">
                                <div class="form-group" style="display: flex; ">
                                    <span style="width: 100%;">
                                        <label>Post Title</label>
                                        <input value="${postDto.title.trim()}" name="txtPostTitle" type="text" minlength="5" maxlength="150" class="form-control" style="width: 100%;">
                                    </span>
                                </div>
                                <div class="form-group" style="display: flex;">
                                    <span style="width: 30%; float:left;">
                                    <label>Post Category</label><br/>
                                    <select id = "txtCategoryId" name="txtCategoryId" class="form-select">
                                        <c:set var="categoryPostList" value="${applicationScope.POST_CATEGORY_LIST}"/>
                                        <c:forEach items="${categoryPostList}" var="cateDto">
                                            <option value="${cateDto.categoryId}">${cateDto.name}</option>
                                        </c:forEach>
                                    </select>        
                                    </span>
                                    <span style="width: 20%; float:left;">
                                    <label>Post Thumbnail</label>
                                    <input type="file" id="postImage" name="postImage" value="" class="form-control" onchange="showImage(this)" style="width: 80%;">
                                    </span>
                                    <span style="width: 50%; float:right;">
                                    <img id="imagePreview" width="200px" height="120px" src="" style="display: none; padding-left: 10px;"/>
                                    <input type="button" value="Reset Image" onclick="ResetImage()" style="display: inline-block;"/>
                                    </span>
                                </div>
                                <div class="form-group" style="display: flex; ">
                                    <span style="width: 100%;">
                                        <label>Post Details</label><br/>
                                        <textarea name="txtPostDetail" rows="20" placeholder="Post Details here!" class="form-control" minlength="50" maxlength="10000">${postDto.detail}</textarea>
                                    </span>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-info" value="Save">
                                <a href="managePost"><input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel"></a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            </div>                   
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>
