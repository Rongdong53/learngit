<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <link href="/js/jquery/plugin/fancyBox/jquery.fancybox.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery/plugin/fancyBox/jquery.fancybox.js"></script>
    <script type="text/javascript" src="/js/jquery/plugin/artDialog/jquery.artDialog.source.js?skin=blue"></script>
    <script type="text/javascript" src="/js/jquery/plugin/artDialog/plugins/iframeTools.source.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$('.fancybox').fancybox();
    		
    		//选择商品，向父窗口共享数据
        	$(".btn_selectProduct").click(function(){
        		var productInfo = $(this).data("productinfo");
        		//发送数据到父窗口
        		$.dialog.data("productInfo", productInfo);
        		//关闭当前窗口
                $.dialog.close();  
        		
        	});
    	});
    </script>
    <title>叩丁狼教育PSS（演示版）-商品管理</title>
    <style>
        .alt td{ background:black !important;}
    </style>
</head>
<body>
<form id="searchForm" action="/product/productList" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                 <div id="box_top">搜索</div>
                 <div id="box_center">
                        关键字
                        <input type="text" class="ui_input_txt02" name="keywords" value="${qo.keywords }"/>
                         货品品牌 
                        <select class="ui_select01" name=brandId>
                            <option value="-1">全部</option>
	                    <c:forEach items="${brands }" var="brand">
	                        <option value="${brand.id }" ${brand.id == qo.brandId ? "selected" : "" }>${brand.name }</option>
                        </c:forEach>
                        </select>
                        <input type="button" value="查询" class="ui_input_btn01 btn_page"/>
                    </div>
                    <div id="box_bottom">
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all" /></th>
                        <th>商品图片</th>
                        <th>商品名称</th>
                        <th>商品编码</th>
                        <th>商品品牌</th>
                        <th>商品成本价</th>
                        <th>商品销售价</th>
                        <th></th>
                    </tr>
                    <tbody>
					<c:forEach items="${result.data }" var="product">
                    <tr>
                        <td><input type="checkbox" name="IDCheck" class="acb" /></td>
                        <td><a class="fancybox" href="${product.imagePath }" data-fancybox-group="gallery" title="${product.name }"><img src="${product.smallImagePath }" width="80px" /></a></td>
                        <td>${product.name }</td>
                        <td>${product.sn }</td>
                        <td>${product.brandName }</td>
                        <td>${product.costPrice }</td>
                        <td>${product.salePrice }</td>
                        <td>
                        	<input type="button" value="选择该商品" data-productinfo='${product.productInfo }' class="ui_input_btn01 btn_selectProduct"/>
                        </td>
                    </tr>
					</c:forEach>
                    </tbody>
                </table>
            </div>
        	<jsp:include page="/WEB-INF/views/common/common-page.jsp"></jsp:include>
        </div>
    </div>
</form>
</body>
</html>
    