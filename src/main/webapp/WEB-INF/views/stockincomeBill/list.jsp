<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery/plugin/artDialog/jquery.artDialog.source.js?skin=blue"></script>
    <script type="text/javascript" src="/js/jquery/plugin/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>叩丁狼教育PSS（演示版）-采购入库单管理</title>
    <style>
        .alt td{ background:black !important;}
    </style>
</head>
<body>
<form id="searchForm" action="/stockincomeBill/list" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                	<div id="box_top">搜索</div>
                    <div id="box_center">
                    <fmt:formatDate value="${qo.beginDate }" var="beginDate"/>
                    <fmt:formatDate value="${qo.endDate }" var="endDate"/>
                        开始时间<input name="beginDate" onclick="WdatePicker();" value="${beginDate }" class="ui_input_txt02 Wdate beginDate"/>~
                        结束时间<input name="endDate" onclick="WdatePicker();" value="${endDate }" class="ui_input_txt02 Wdate endDate"/>
                        仓库
                        <select name="depotId" class="ui_select01">
                            <option value="-1">所有仓库</option>
                            <c:forEach items="${depots }" var="depot">
                            	<option value="${depot.id }" ${depot.id == qo.depotId ? "selected" : "" }>${depot.name }</option>
                            </c:forEach>
                            
                        </select>
                        状态
                        <select id="status" name="status" class="ui_select01">
                            <option value="-1" >所有状态</option>
                            <option value="0" ${qo.status == 0 ? "selected" : "" }>待审核</option>
                            <option value="1" ${qo.status == 1 ? "selected" : "" }>已审核</option>
                        </select>
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                    </div>
                
                    <div id="box_bottom">
                        <input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="/stockincomeBill/input"/>
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
                        <th>订单编码</th>
                        <th>业务时间</th>
                        <th>仓库</th>
                        <th>总金额</th>
                        <th>总数量</th>
                        <th>录入人</th>
                        <th>审核人</th>
                        <th>状态</th>
                        <th></th>
                    </tr>
                    <tbody>
					<c:forEach items="${result.data }" var="stockincomeBill">
                    <tr>
                        <td><input type="checkbox" name="IDCheck" class="acb" /></td>
                        <td>${stockincomeBill.sn }</td>
                        <td><fmt:formatDate value="${stockincomeBill.vdate }" pattern="yyyy-MM-dd"/></td>
                        <td>${stockincomeBill.depot.name }</td>
                        <td>${stockincomeBill.totalAmount }</td>
                        <td>${stockincomeBill.totalNumber }</td>
                        <td>${stockincomeBill.inputUser.name }</td>
                        <td>${stockincomeBill.auditor.name }</td>
                        <td>
                        	<c:if test="${stockincomeBill.status == 0}"><span style="color: red;">待审核</span></c:if>
             		        <c:if test="${stockincomeBill.status == 1}"><span style="color: green;">已审核</span></c:if>
                        </td>
                        <td>
                        	<c:if test="${stockincomeBill.status == 0}">
	                        	<a href="javascript:;" class="btn_auditor" data-url="/stockincomeBill/auditor?id=${stockincomeBill.id }">审核</a>
	                            <a href="/stockincomeBill/input?id=${stockincomeBill.id }">编辑</a>
	                            <a href="javascript:;" class="btn_delete" data-url="/stockincomeBill/delete?id=${stockincomeBill.id }">删除</a>
                        	</c:if>
                        	<c:if test="${stockincomeBill.status == 1}">
	                            <a href="/stockincomeBill/show?id=${stockincomeBill.id }">查看</a>
                        	</c:if>
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
    