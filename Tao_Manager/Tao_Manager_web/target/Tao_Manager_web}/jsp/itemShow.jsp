<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<table >
				<tr>
					<td>序号</td>
					<td>title</td>
				</tr>
				<c:forEach items="${pageInfo.list}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.title}</td>
					</tr>
				</c:forEach>
			</table>

            当前第 ${pageInfo.pageNum} 页.总共 ${pageInfo.pages} 页.一共 ${pageInfo.total} 条记录
        </div>

       
</body>
</html>