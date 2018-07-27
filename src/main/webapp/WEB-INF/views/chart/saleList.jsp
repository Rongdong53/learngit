<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery/plugin/artDialog/jquery.artDialog.source.js?skin=blue"></script>
    <script type="text/javascript" src="/js/jquery/plugin/artDialog/plugins/iframeTools.source.js"></script>
    <script type="text/javascript" src="/js/jquery/plugin/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".btn_bar").click(function () {
                    $.dialog.open("/chart/saleChartBar?" + $("#searchForm").serialize(), {
                        id: 'productInfo',
                        title: '选择商品列表',
                        width: 750,
                        height: 480,
                        left: '50%',
                        top: '50%',
                        background: '#000000',
                        opacity: 0.1,
                        lock: true,
                        resize: false
                    })
                }
            )
            $(".btn_pie").click(function () {
                $.dialog.open("/chart/saleChartPie?"+$("#searchForm").serialize(),{
                    id: 'productInfo',
                    title: '选择商品列表',
                    width: 850,
                    height: 580,
                    left: '50%',
                    top: '50%',
                    background: '#000000',
                    opacity: 0.1,
                    lock: true,
                    resize: false
                })
            })
        })
    </script>
    <title>叩丁狼教育PSS（演示版）-销售报表</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<form id="searchForm" action="/chart/sale" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        <fmt:formatDate value="${qo.beginDate }" var="beginDate"/>
                        <fmt:formatDate value="${qo.endDate }" var="endDate"/>
                        业务时间
                        <input name="beginDate" onclick="WdatePicker();" value="${beginDate }"
                               class="ui_input_txt02 Wdate beginDate"/>
                        ~
                        <input name="endDate" onclick="WdatePicker();" value="${endDate }"
                               class="ui_input_txt02 Wdate endDate"/>
                        客户
                        <select name="clientId" class="ui_select01">
                            <option value="-1">所有客户</option>
                            <c:forEach items="${clients }" var="client">
                                <option value="${client.id }" ${client.id == qo.clientId ? "selected" : "" }>${client.name }</option>
                            </c:forEach>
                        </select>
                        货品品牌
                        <select id="brandId" name="brandId" class="ui_select01">
                            <option value="-1">所有品牌</option>
                            <c:forEach items="${brands }" var="brand">
                                <option value="${brand.id }" ${brand.id == qo.brandId ? "selected" : "" }>${brand.name }</option>
                            </c:forEach>
                        </select>
                        分组
                        <select id="groupByType" name="groupByType" class="ui_select01">
                            <c:forEach items="${groupMaps }" var="groupMap">
                                <option value="${groupMap.key }" ${groupMap.key == qo.groupByType?"selected":"" }>
                                        ${groupMap.value }
                                </option>
                            </c:forEach>
                        </select>
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="柱状报表" class="left2right btn_bar"/>
                        <input type="button" value="饼图报表" class="left2right btn_pie"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>分组类型</th>
                        <th>销售总数量</th>
                        <th>销售总金额</th>
                        <th>毛利润</th>
                    </tr>
                    <tbody>
                    <c:forEach items="${sales }" var="sale">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb"/></td>
                            <td>${sale.groupByType }</td>
                            <td>${sale.totalNumber }</td>
                            <td>${sale.totalAmount }</td>
                            <td>${sale.profit }</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <%-- <jsp:include page="/WEB-INF/views/common/common-page.jsp"></jsp:include> --%>
        </div>
    </div>
</form>
</body>
</html>
    