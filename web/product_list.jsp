<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>bookStore列表</title>
<%--导入css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>

<body class="main">

	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${category}&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>

					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>${category}</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${pr.totalCount}种商品
								<hr />
								<div style="margin-top:20px; margin-bottom:5px">
									<img src="images/productlist.gif" width="100%" height="38" />
								</div>
								<table cellspacing="0" class="booklist">
									<tr>
										<!-- 遍历列表 -->
										<c:forEach items="${pr.list }" var="book">
											<td>
												<div class="divbookpic">
													<p>
														<%--<a href="${pageContext.request.contextPath }/productInfo?id=${book.id}"><img src="bookcover/101.jpg" width="115"
															height="129" border="0" /> </a>--%>
													</p>
													<a href="${pageContext.request.contextPath }/productInfo?id=${book.id}"><img src="${book.imgurl}" width="115"
																																 height="129" border="0" />
														</a>
												</div>
												<div class="divlisttitle">
													<a href="${pageContext.request.contextPath }/productInfo?id=${book.id}">书名:${book.name}<br />售价:${book.price} </a>
												</div>
											</td>
										</c:forEach>
										

									</tr>
								</table>
								
								<div class="pagination">
									<!--  disablepage nextpage-->
									<ul>
										<!-- 显示上一页 -->
										<c:if test="${pr.currentPage > 1}">
											<li><a href="${pageContext.request.contextPath}/showProductByPage?category=${category}&page=${pr.currentPage-1}">上一页</a></li>
										</c:if>
										<span>第${pr.currentPage}页/共 ${pr.totalPage}页</span> 
										<!-- 显示下一页 -->
										<c:if test="${pr.currentPage < pr.totalPage}">
											<li><a href="${pageContext.request.contextPath}/showProductByPage?category=${category}&page=${pr.currentPage+1}">下一页</a></li>
										</c:if>
										
										<%-- <c:choose>
											<c:when test="${pr.totalPage <= 4}">
												<c:forEach var="i" begin="1" end="${pr.totalPage}">
													<li><a href="#">${i}</a></li>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<c:forEach var="i" begin="${pr.currentPage}" end="${pr.currentPage + 3}">
														<li><a href="#">${i}</a></li>
												</c:forEach>
											</c:otherwise>
										</c:choose> --%>
										
										
										
										
									</ul>
									
								</div>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
