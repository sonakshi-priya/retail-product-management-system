<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Retails Product</title>
<style>
html, body {
	height: 100%;
	margin: 0;
	background-attachment: fixed;
	background-image:
		url("https://cts-finalproject.s3.us-east-2.amazonaws.com/images/indexpage.jpg");
	background-size: cover;
}

.form-inline {
	position: absolute;
	left: 50%;
	transform: translatex(-50%);
}

.search-button {
	border-radius: 0%;
	border: 0%;
}

.form-group {
	width: 100%;
	padding: 0px;
}

.bottomMargin {
	margin-bottom: 2rem;
}

.fixCard {
	border-radius: 25px;
	background-color: white;
	border-style: solid;
	overflow: hidden;
	max-width: 18rem;
	margin: 1rem 1rem;
}

.fixImg {
	height: 16rem;
}

.description {
	height: 8rem;
}

.searchCard {
	width: 50%;
}

.centerFix {
	text-align:
}

.butt {
	margin: 0.5rem 0;
	width: 8rem;
	border: 0px;
	padding: 0.1rem;
	border-radius: 25px;
	background: #2691d9;
	font-size: 16px;
	color: #FFFFFF;
	cursor: pointer;
	outline: none;
}

.input-group {
	width: 90% !important;
}

.widthParam {
	width: 100%;
	margin: 0.5rem 0;
	border-radius: 25px;
}

.heading {
	margin-bottom: 0px;
	margin-left: 1rem;
	margin-right: 1.5rem;
	color: yellow;
}

.modeldata {
	width: 90% !important;
	margin-right: 10px;
	padding-left: 10px;
	padding-right: 10px;
}

.navBar {
	display: flex;
	flex-direction: row;
	align-items: center;
	margin: 0;
}

.expansion {
	flex-grow: 3;
	display: flex;
	flex-direction: row;
	margin: 0 1rem;
}

.navToggler {
	background-color: transparent;
	border: 1px solid transparent;
	border-radius: .25rem;
	padding: 0 0.5rem;
}

.flexProperty {
	display: flex;
	flex-direction: row;
	align-items: center;
	margin: 0 0.5rem;
}

.widthZero {
	padding: 0;
}

.heart {
	color: red;
	font-weight: 900;
}

.cart {
	color: yellow;
	font-weight: 900;
}

.marginEx {
	margin-right: 4px;
}

.logout {
	color: brown;
	font-weight: 900;
}

.cardColumns {
	max-width: 18rem;
	max-height: 20rem;
}

.Container {
	margin: 2rem;
}

.cardRow {
	display: flex;
	flex-direction: row;
	width: 100vw;
	flex-wrap: wrap;
}

.overPage {
	overflow-x: hidden;
}
</style>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<link rel="stylesheet" href="/rating.css" />

<body class="overPage">

	<!-- navbar for the pages -->
	<nav class="navBar" style="background-color: rgba(53, 57, 66, 255);">

		<a href="/index">
			<h5 class="heading">
				Retail@Products
				</h2>
		</a>


		<form class="my-lg-0 expansion" action="/searchByName" method="get">
			<input class="widthParam mr-sm-2" type="text" placeholder="   Search"
				name="search" id="search" /> <input class="butt" type="submit"
				value="Search">

		</form>

		<div id="navbarNav">
			<ul class="navBar ml-auto">
				<li class="flexProperty"><i class="fa fa-heart marginEx heart"></i>
					<a href="/getWishlist" class="widthZero heart">Wishlist</a></li>
				<li class="flexProperty"><i
					class="fa fa-shopping-cart marginEx cart"></i> <a href="/getCart"
					class="widthZero cart">Cart</a></li>
				<li class="flexProperty"><i
					class="fa fa-sign-out marginEx logout"></i> <a href="/logout"
					class="widthZero logout">Logout</a></li>
			</ul>
		</div>


	</nav>
	<!-- body section of the page use jsp -->
	<!-- use loop to fetch the data and display it -->
	<div class="Container">
		<div class="cardColumns">
			<!-- add a single item fech from database  -->
			<div class="cardRow">
				<c:forEach var="item" items="${list}">
					<div class="fixCard" id="box1">
						<img src="${item.imageName}" class="fixImg card-img-top" alt="..." />
						<div class="card-body">
							<div class="description">
								<h5 class="card-title">${item.name}
									<a href="#" class="badge badge-success"><span id="rating">${item.rating}</span></a>
								</h5>
								<h6 class="card-subtitle mb-2">Rs. ${item.price}</h6>
								<h6 class="card-subtitle mb-2 date_field">
									Expected Date:<span class="date">12/10/2020</span>
								</h6>
								<p class="card-text">${item.description}</p>
							</div>
							<section class="o-container">
								<ul class="c-rating-star u-text-center js-rating-star"
									data-name="test" id="${item.id}">
									<li class="c-rating-star__item" title="Poor" data-value="1">
										<i class="fa fa-star fa-fw c-rating-star__icon"></i>
									</li>
									<li class="c-rating-star__item" title="Fair" data-value="2">
										<i class="fa fa-star fa-fw c-rating-star__icon"></i>
									</li>
									<li class="c-rating-star__item" title="Good" data-value="3">
										<i class="fa fa-star fa-fw c-rating-star__icon"></i>
									</li>
									<li class="c-rating-star__item" title="Excellent"
										data-value="4"><i
										class="fa fa-star fa-fw c-rating-star__icon"></i></li>
									<li class="c-rating-star__item" title="WOW!!!" data-value="5">
										<i class="fa fa-star fa-fw c-rating-star__icon"></i>
									</li>
								</ul>
							</section>
							<span id="resultSpan"></span>
							<button class="butt btn-primary btn-block addcart"
								id="${item.id}" data-toggle="modal" data-target="#myModal"
								onClick="configureProductId(${item.id})">Add to Cart</button>

							<button class="butt btn-danger btn-block addcart" id="${item.id}"
								data-toggle="modal" data-target="#wishListModal"
								onClick="configureProductId(${item.id})">Add to
								Wishlist</button>
						</div>
					</div>
				</c:forEach>

			</div>


			<!-- modal starts -->
			<div class="modal centerFix" id="myModal">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Add to cart</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="input-group ">
								<span class="input-group-btn"> </span><label>Quantity</label> <input
									type="number" name="quant[1]" id="quantity"
									class="form-control input-number modeldata" value="1" min="1">
								<br>
								<br> <span class="input-group-btn"> </span><label>Zip
									Code</label> <input type="number" id="zipcode"
									class="form-control input-number modeldata" value="1" min="1">
								<br>
								<br>

								<button class="btn btn-warning btn-block addcart"
									data-dismiss="modal" onClick="addToCart()">Add to Cart</button>
<!-- 								<p id="alertMessage" style="color: green; display: none;">Item -->
<!-- 									added Successfully!</p> -->

								<div id="messageSpanCart"></div>
							</div>
							<br>
						</div>
					</div>
				</div>
			</div>
			<!-- modal ends -->
			<!-- modal starts -->
			<div class="modal" id="wishListModal">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Add to Wishlist</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="input-group">
								<span class="input-group-btn"> </span> <label>Quantity</label> <input
									type="number" name="quant[1]" id="quantityWish"
									class="form-control input-number modeldata" value="1" min="1"
									max="10"> <br>
								<br>

								<button class="btn btn-warning btn-block addcart"
									data-dismiss="modal" onClick="addToWishList()">Add to
									Wishlist</button>
								<div id="messageSpanWishlist"></div>
							</div>
							<br>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
	<script src="rating.js"></script>
	<script>
	var productId;
	function configureProductId(id)
	{
		productId=id;
		document.getElementById("messageSpanWishlist").innerHTML = "";
		document.getElementById("messageSpanCart").innerHTML = "";
	}
	
	   function showAlert() {
		   var x = document.getElementById("alertMessage");
		   if (x.style.display === "none") {
		     x.style.display = "block";
		   } else {
		     x.style.display = "none";
		   }
		 }
	
	function addToWishList()
	{
		var xhttp = new XMLHttpRequest();
		var response='';
		var x=document.getElementById("quantityWish").value;
		//showAlert();
		var json={"customerId":1,"productId":productId,"quantity":x};
		$.ajax({
        type: "POST",
        url: "/addToCustomerWishlist",
        async: false,
        dataType : "json",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(json),
        success: function(data){   
             alert(data.message);
        	 //$('#messageSpanWishlist').append(data.message);
        }
    });
	//showAlert();
   }
   

   
	function addToCart()
	{
		var x=document.getElementById("quantity").value;
		var xhttp = new XMLHttpRequest();
		var response='';
		var zipCode=document.getElementById("zipcode").value;
		document.getElementById("messageSpanCart").innerHTML = "";
		//showAlert();
		var json={"productId":productId,"customerId":1,"zipcode":zipCode,"quantity":x};
		$.ajax({
            type: "POST",
            url: "/addToCart",
            async: false,
            dataType : "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(json),
            success: function(data){   
            	 alert(data.message);
//             	 var obj=JSON.parse(data.message);
//                  $('#messageSpanCart').append(data.message);
            }
        });
		//showAlert();
	}

      const date = document.getElementsByClassName("date");
      const myrating = document.getElementsByClassName("my-rating");
      var today = new Date();
      var tomorrow = new Date();
      tomorrow.setDate(today.getDate() + 7);
      tomorrow = JSON.stringify(tomorrow);
      tomorrow = tomorrow.slice(1, 11);
      tomorrow = tomorrow.split("-").reverse();
      console.log(tomorrow);
      tomorrow = tomorrow[0] + "-" + tomorrow[1] + "-" + tomorrow[2];
      for (i of date) {
        i.innerText = tomorrow;
      }

      const container = document.getElementsByClassName("o-container");
      for (let topElement = 0; topElement < container.length; topElement++) {
        container[topElement].addEventListener("click", function (e) {
          let parent = e.target.parentElement.parentElement;
        
          let url='/addProductRating/'+parent.id+"/"+val;
          $.ajax({
            url: url,
            type: "POST",
            data: val,
            success: function () {
              //alert("Save Complete");
            	window.location.reload();
            	},
          });
          $(document).ajaxStop(function(){
        	    window.location.reload();
        	});
        });
      }

     </script>
</body>
</html>


