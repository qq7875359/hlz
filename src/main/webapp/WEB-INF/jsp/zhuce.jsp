<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="jquery.js">
</script>
	<script>
		function yanzheng(){
			var yan = $('#yan').val();
			alert(5678);
			$.ajax({
				type:"post",
				url:"checkyan",
				data:{"yan":yan},
				success:function(data){
					if(data)
						alert("注册成功！");
					else
						alert("验证码错误！注册失败！");
				},
			});
		};
		$(document).ready(function() {

			$("#23").blur(function() {				
			//	var category={"username":"hlz"}; 
				var categoty = $('#23').val();
		      //  var jsonData = JSON.stringify(categoty);
				$.ajax({
					type:"post",
					url:"check",
					//url:${pageContext.request.contextPath},
					data:{"username":categoty},
					//dataType:"json",
					//contentType : "application/json;charset=UTF-8",
					success:function(data){
						/* for(var i=0;i<data.length;i++){
							var id=data[i].name;
							console.log(id); */					
							var num=data.name;
							if(num==1){
								$('#123').css("display","block");
								$('#321').attr({"disabled":"disabled"});
							}																		
							else{
								$('#321').removeAttr("disabled");
								$('#123').css("display","none");
							}						
					},
				});
			});
			$('#cy').click(function(){
				$.ajax({
						type:"post",
						url:"yanzheng",
						success:function(data){					
							for(var i=0;i<data.length;i++){
								console.log(data[i]);
							}
						},
					});				
			});
		});
	</script>

<body>
	<div><c:forEach items="${yan}" var="init" ><c:out value="${init}" /></br></c:forEach></div>
		
	<div align="center">
		<form action="register" method="post" onsubmit="return yanzheng()">
			<div style="position: absolute;left: 890px; display:none;" id="123">
					<font style="color: red;">用户名重复</font>
				</div>
			<table>
				用户名：<input type="text" name="username" id="23"></br>
				密&nbsp;码：<input type="text" name="password">
				
			</table>
			验证码：<input type="text" name="yan" id="yan">
			<input type="button" value="更换验证码" id="cy"></br>
			<input type="submit" value="注册" id="321">
			<input  type="reset" value="重置">
			<%--<a href="">2222</a> --%>
		</form>
	</div>

</body>