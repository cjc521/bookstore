<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<script type="text/javascript">
	/* 	id : 产品id,书的id
	 num:更改的数量
	 totalNumber:产品总数量 */

	function changeNum(id, num, totalNumber) {
		console.log('图书ID:' + id + ' 购买数量:' + num + '总量:' + totalNumber);
		
		//当购买的数据大于总商品数量时
		if(num > totalNumber){
			alert('不能购买大于库存的数据');
			return;
		}
		
		//当商品数量等于0时，给个提醒
		if(num < 1){
			if(!confirm('是否要删除此商品')){//删除此商品
				return;
			}
		}
		
		console.log('执行操作....');
		//重新访问后台，更改session的数据
		location.href = '${pageContext.request.contextPath}/changeNum?id=' + id + '&num=' + num;
		
	}
</script>

</head>

<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td><div style="text-align: right; margin: 5px 10px 5px 0px">
						<a href="index.html">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;购物车
					</div>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td><img src="ad/page_ad.jpg" width="666" height="89" />
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td><img src="images/buy1.gif" width="635" height="38" />
										</td>
									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td width="10%">序号</td>
													<td width="20%">商品名称</td>
													<td width="10%">价格</td>
													<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp数量</td>
													<td width="10%">库存</td>
													<td width="10%">小计</td>
													<td width="10%">删除订单</td>
												</tr>
											</table>
											<table width="100%" border="0" cellspacing="0">
												<!-- 先存一个总价格为0的变量 -->
												<c:set var="totalPrice" value="0"></c:set>
												<!-- 遍历购物车 -->
												<c:forEach items="${cart}" var="cart" varStatus="vs">
													<tr>
														<td width="10%">${vs.count}</td>
														<td width="20%">${cart.key.name}</td>
														<td width="10%">${cart.key.price}</td>
														<td width="20%">
															<input type="button" value='-' style="width: 20px" onclick="changeNum(${cart.key.id},${cart.value-1},${cart.key.pnum})">															
															<input name="text" type="text" value="${cart.value}" style="width: 40px; text-align: center" />
															<input type="button" value='+' style="width: 20px" onclick="changeNum(${cart.key.id},${cart.value+1},${cart.key.pnum})">
														</td>
														<td width="10%">${cart.key.pnum}</td>
														<td width="10%">${cart.key.price * cart.value}</td>
<%--														<td width="10%"><a href="${pageContext.request.contextPath}/changeNum?num==0"--%>
														<td width="10%"><input type="button" onclick="changeNum(${cart.key.id},0,${cart.key.pnum})"
															value="x" style="color: #FF0000; font-weight: bold"></input></td>
													</tr>
													<!-- 累加价格 -->
													<c:set var="totalPrice"
														value="${totalPrice + cart.key.price * cart.value}"></c:set>
												</c:forEach>
											</table>
											<table cellspacing="1" class="carttable">
												<tr>
													<td style="text-align: right; padding-right: 40px;"><font
														style="color: #FF6600; font-weight: bold">合计：&nbsp;&nbsp;${totalPrice}元</font>
													</td>
												</tr>
											</table>
											<div style="text-align: right; margin-top: 10px">
												<a href="${pageContext.request.contextPath}/showProductByPage"><img src="images/gwc_jx.gif"
													border="0" /> </a> &nbsp;&nbsp;&nbsp;&nbsp;
												<a href="${pageContext.request.contextPath}/settleAccounts"><img
													src="images/gwc_buy.gif" border="0" /> </a>
											</div>
										</td>
									</tr>
								</table></td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html>