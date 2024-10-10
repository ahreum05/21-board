<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style type="text/css">
#main {
	width: 650px;
	margin: auto;  /* 브라우저 기준 가운데 배치 */
}
header {
	text-align: center;
	color: black;
	border-bottom: 2px solid black;
	width: 100%; height: 70px;
}
#container {
	width: 100%;
	height: 500px;
}
nav {
	width: 150px;
	height: 100%;
	background: orange;
	float: left;
}
section {
	width: 500px;
	height: 100%;
	background: white;
	float:left;
}
footer {
	clear: both;	/* float 속성 해지 */
	background: white;	
	text-align: center;
	color: black;
	height: 150px;
	border-top: 2px solid black;
}
nav a:link {color: black; text-decoration: none;}
nav a:visited {color: black; text-decoration: none;}
nav a:hover {color: skyblue; text-decoration: underline;}
nav a:active {color: black; text-decoration: none;}
</style>
</head>
<div id="main">
	<header>
		<h2>이미지 게시판</h2>
	</header>
	
	<div id="container">
		<nav>
			<h3>** <a href="/21-board/index.jsp">메인 화면</a> **</h3><br>
			<a href="boardWriteForm.do">게시판 글쓰기</a><br>
			<a href="boardList.do">게시판 목록</a><br>
		</nav>
		
		<section>
			<c:if test="${req_page == null}">
            <img alt="이미지" src="image/lion.jpg">
         </c:if>
         
         <c:if test="${req_page != null}">
            <jsp:include page="${req_page}"/>
         </c:if>
         
        
     
		</section>
	</div>
	
	<footer>
		KGITBANK
	</footer>	
</div>		
	
	
</body>
</html>