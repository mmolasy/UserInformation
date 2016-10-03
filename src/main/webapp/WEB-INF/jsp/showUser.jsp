
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
	font-size: 60px;
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
		<div class="naglowek" id="dane">User id ${user.userId}</div>

		<div class="naglowek" id="czas"></div>
		<div id="przerwa"></div>
		<div id="daneUser">
			User id:
			<c:out value="${user.userId}"></c:out>
			<br /> User firstname:
			<c:out value="${user.firstName}"></c:out>
			<br /> User lastname:
			<c:out value="${user.lastName}"></c:out>
			<br /> User birthdate:
			<c:out value="${user.birthDate}"></c:out>
			<br /> User e-mail:
			<c:out value="${user.emailAddress}"></c:out>
			<br /> User created date:
			<c:out value="${user.createdDate}"></c:out>
			<br />
		</div>

		<div id="tabela">
			<center>
				<table border="2">
					<caption>User accounts</caption>
					<tr>
						<th>Account id:</th>
						<th>Account name:</th>
						<th>Account current balance:</th>
						<th>Account last updated date:</th>
						<th>Account created date:</th>
					</tr>
					<c:forEach items="${user.accounts}" var="account">

						<tr>
							<th>${account.accountId}</th>
							<th>${account.name}</th>
							<th>${account.currentBalance}</th>
							<th>${account.lastUpdatedDate}</th>
							<th>${account.createdDate}</th>
						</tr>
					</c:forEach>
				</table>
				<br />



				<table border="2">
					<caption>User addresses</caption>
					<tr>
						<th>Address line 1</th>
						<th>Address line 2</th>
						<th>City</th>
						<th>State</th>
						<th>Zip code</th>
					</tr>
					<c:forEach items="${user.addresses}" var="address">

						<tr>
							<th>${address.addressLine1}</th>
							<th>${address.addressLine2}</th>
							<th>${address.city}</th>
							<th>${address.state}</th>
							<th>${address.zipCode}</th>
						</tr>
					</c:forEach>
				</table>

<form method="POST" action="/deleteuser/${user.userId}">
					<input type="submit" value="Delete user">
				</form>				

				<br /> <a href="<c:url value="/users"/>">Back to the user list</a>
			</center>
		</div>


	</div>
</body>
</html>