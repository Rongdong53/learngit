<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <title>叩丁狼教育PSS（演示版）-销售出库单管理</title>
    <style>
        .alt td{ background:black !important;}
    </style>
</head>
<body>
<form id="searchForm" action="/stockoutcomeBill/list" method="post">
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
                        	客户
                        <select name="clientId" class="ui_select01">
                            <option value="-1">所有客户</option>
                            <c:forEach items="${clients }" var="client">
                            	<option value="${client.id }" ${client.id == qo.clientId ? "selected" : "" }>${client.name }</option>
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
                        <input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="/stockoutcomeBill/input"/>
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
                        <th>出库单编码</th>
                        <th>业务时间</th>
                        <th>仓库</th>
                        <th>客户</th>
                        <th>总金额</th>
                        <th>总数量</th>
                        <th>录入人</th>
                        <th>审核人</th>
                        <th>状态</th>
                        <th></th>
                    </tr>
                    <tbody>
					<c:forEach items="${result.data }" var="stockoutcomeBill">
                    <tr>
                        <td><input type="checkbox" name="IDCheck" class="acb" /></td>
                        <td>${stockoutcomeBill.sn }</td>
                        <td><fmt:formatDate value="${stockoutcomeBill.vdate }" pattern="yyyy-MM-dd"/></td>
                        <td>${stockoutcomeBill.depot.name }</td>
                        <td>${stockoutcomeBill.client.name }</td>
                        <td>${stockoutcomeBill.totalAmount }</td>
                        <td>${stockoutcomeBill.totalNumber }</td>
                        <td>${stockoutcomeBill.inputUser.name }</td>
                        <td>${stockoutcomeBill.auditor.name }</td>
                        <td>
                        	<c:if test="${stockoutcomeBill.status == 0}">
                        		<span style="color: red">未审核</span>
                        	</c:if>
                        	<c:if test="${stockoutcomeBill.status == 1}">
                        		<span style="color: green">已审核</span>
                        	</c:if>
                        </td>
                        <td>
                        	<c:if test="${stockoutcomeBill.status == 0}">
	                            <a href="javascript:;" class="btn_auditor" data-url="/stockoutcomeBill/auditor?id=${stockoutcomeBill.id }">审核</a>
	                            <a href="/stockoutcomeBill/input?id=${stockoutcomeBill.id }">编辑</a>
	                            <a href="javascript:;" class="btn_delete" data-url="/stockoutcomeBill/delete?id=${stockoutcomeBill.id }">删除</a>
                       		</c:if>
                       		<c:if test="${stockoutcomeBill.status == 1}">
                       			<a href="/stockoutcomeBill/show?id=${stockoutcomeBill.id }">查看</a>
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
    