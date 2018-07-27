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
    <title>叩丁狼教育PSS（演示版）-菜单管理</title>
    <style>
        .alt td{ background:black !important;}
    </style>
</head>
<body>
<form id="searchForm" action="/systemmenu/list" method="post">
    <input type="hidden" name="parentId" value="${qo.parentId }">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">
                        <input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="/systemmenu/input?parentId=${qo.parentId }"/>
                    	<input type="button" value="批量删除" class="ui_input_btn01 btn_batchdelete" data-url="/employee/batchdelete"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
            	当前位置&nbsp;:&nbsp;<a href="/systemmenu/list">根目录&nbsp;</a>
            	<c:forEach items="${parents }" var="parent">
            		><a href="/systemmenu/list?parentId=${parent.id }">&nbsp;${parent.name }</a>
            	</c:forEach>
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all" /></th>
                        <th>编号</th>
                        <th>菜单名称</th>
                        <th>菜单编码</th>
                        <th>父级菜单</th>
                        <th>URL</th>
                        <th></th>
                    </tr>
                    <tbody>
					<c:forEach items="${result.data }" var="systemmenu">
                    <tr>
                        <td><input type="checkbox" name="IDCheck" class="acb" /></td>
                        <td>${systemmenu.id }</td>
                        <td>${systemmenu.name }</td>
                        <td>${systemmenu.sn }</td>
                        <td>${systemmenu.parent.name }</td>
                        <td>${systemmenu.url }</td>
                        <td>
                            <a href="/systemmenu/input?id=${systemmenu.id }&parentId=${qo.parentId }">编辑</a>
                            <a href="javascript:;" class="btn_delete" data-url="/systemmenu/delete?id=${systemmenu.id }">删除</a>
                            <a href="/systemmenu/list?parentId=${systemmenu.id }">下级菜单</a>
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
    