<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery/plugin/artDialog/jquery.artDialog.source.js?skin=blue"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$(".btn_loadPermission").click(function() {
    			var url = $(this).data("url");
    			commonDialog("亲,加载权限需要些时间",function(){
	    			$.get(url,function(data){
	    				if(data.success){
	    					commonDialog("权限加载成功",function(){
	    						window.location.reload();
	    					},false,"succeed");
	    				} else {
	    					commonDialog("权限加载失败",true,false,"error");
	    				}
	    			});
	    		},true,"warning");
			});
    	});
    </script>
    <title>叩丁狼教育PSS（演示版）-权限管理</title>
    <style>
        .alt td{ background:black !important;}
    </style>
</head>
<body>
<form id="searchForm" action="/permission/list" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">
                        <input type="button" value="加载权限" class="ui_input_btn01 btn_loadPermission" data-url="/permission/loadPermission"/>
                        <input type="button" value="批量删除" class="ui_input_btn01 btn_batchdelete" data-url="/employee/batchdelete"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all" /></th>
                        <th>编号</th>
                        <th>权限表达式</th>
                        <th>权限名称</th>
                        <th></th>
                    </tr>
                    <tbody>
					<c:forEach items="${result.data }" var="permission">
                    <tr>
                        <td><input type="checkbox" name="IDCheck" class="acb" /></td>
                        <td>${permission.id }</td>
                        <td>${permission.expression }</td>
                        <td>${permission.name }</td>
                        <td>
                            <a href="javascript:;" class="btn_delete" data-url="/permission/delete?id=${permission.id }">删除</a>
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
    