<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Users</title>
</head>
<link rel="stylesheet"
	href="fontello-6b060c2b/fontello-6b060c2b/css/fontello.css" />
<link
	href='https://fonts.googleapis.com/css?family=Lora&subset=latin,latin-ext'
	rel='stylesheet' />
<link href='https://fonts.googleapis.com/css?family=Indie+Flower'
	rel='stylesheet' />
<style>
body {
	background-color: #303030;
	font-family: 'Lora', serif;
	color: white;
}

#container {
	width: 1000px;
	margin-left: auto;
	margin-right: auto;
	padding: 30px;
}

.naglowek {
	float: left;
	height: 130px;
	background-color: #93c748
}

#przerwa {
	clear: both;
	height: 20px;
}

#dane {
	width: 660px;
	font-size: 30px;
	font-family: 'Indie Flower', cursive;
	text-align: center;
	display: table-cell;
	vertical-align: middle;
	padding: 20px;
}

#czas {
	width: 260px;
	font-size: 20px;
	padding: 20px;
	text-align: right;
}
</style>

<script type="text/javascript">
	function odliczanie() {
		var dzisiaj = new Date();

		var dzien = dzisiaj.getDate();
		var miesiac = dzisiaj.getMonth() + 1;
		var rok = dzisiaj.getFullYear();

		var godzina = dzisiaj.getHours();
		var minuta = dzisiaj.getMinutes();
		var sekunda = dzisiaj.getSeconds();

		if (sekunda >= 0 && sekunda < 10) {
			sekunda = "0" + sekunda;
		}
		if (minuta >= 0 && minuta < 10) {
			minuta = "0" + minuta;
		}
		if (godzina >= 0 && godzina < 10) {
			godzina = "0" + godzina;
		}
		document.getElementById("czas").innerHTML = dzien + "." + miesiac + "."
				+ rok + "<br>" + godzina + ":" + minuta + ":" + sekunda;
		setTimeout("odliczanie(),1000");
	}
</script>

<body onload="odliczanie();">

	<div id="container">
		<div class="naglowek" id="dane">User list based on MySql
			Database.</div>

		<div class="naglowek" id="czas"></div>
		<div id="przerwa"></div>
		<center>

			<div class="cialo" id="tabela">
				<table border="3">

					<tr>
						<th>User Id:</th>
						<th>User firstname:</th>
						<th>User lastname:</th>
						<th>User birthdate:</th>
						<th>User e-mail:</th>
					</tr>
					<c:forEach items="${lista}" var="user">

						<tr>
							<th><a href="/user/<c:out value='${user.userId}' />">${user.userId}</a></th>
							<th>${user.firstName}</th>
							<th>${user.lastName}</th>
							<th>${user.birthDate}</th>
							<th>${user.emailAddress}</th>
						</tr>
					</c:forEach>
				</table>
				Click on selected user id to show more
			</div>

				<form id="myform" action="/userByAccountId/" method="GET">
    Account id: <input type="text" name="id" id="id">
    <input type="submit" name="submit" value="Get user by account id">
</form>	</center>

	</div>
</body>
</html>