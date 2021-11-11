<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="<%=request.getContextPath()%>/resources/js/daumpost.js"></script> <!-- js폴더에 daumpost.js파일 가져오겠다 -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>

function register() {  //회원가입 버튼 누르면 register로 넘어간다
	addr1 = $("#addr1").val()
	addr2 = $("#addr2").val()
	addr3 = $("#addr3").val()
	addr = addr1 + "/" +addr2 + "/" + addr3 
	$("#addr1").val(addr)  //addr1에 모두 값이 들어간다
	fo.submit();
}
</script>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../default/header.jsp"/>
<div align="center">
   <form id = "fo" action="register" method="post">
      <table border="1">
         <tr><td>
            <input type="text" name="id" placeholder="아이디"><br>
            <input type="text" name="pw" placeholder="비밀번호"><br>
            
            <input type="text" readonly id="addr1" name="addr" placeholder="우편번호">
            <input type="button" onclick="daumPost()" value = "우편번호 찾기"><br>
            <input type="text" readonly id = "addr2" placeholder="주 소"><br>
            <input type="text" id = "addr3" placeholder="상 세 주 소"><br>
            <hr>
            <input type="button" onclick="register()" value="회원가입">  <!-- 회원가입 버튼 누르면 register로 넘어가기 -->
         </td></tr>
      </table>
   </form>
</div>
<c:import url="../default/footer.jsp"/>
</body>
</html>