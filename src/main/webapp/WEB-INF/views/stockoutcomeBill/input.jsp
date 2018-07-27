<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery/plugin/artDialog/jquery.artDialog.source.js?skin=blue"></script>
    <script type="text/javascript" src="/js/jquery/plugin/artDialog/plugins/iframeTools.source.js"></script>
    <script type="text/javascript" src="/js/jquery/plugin/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$(".searchproduct").click(function(){
    			var currentTr = $(this).closest("tr");
    			var url = "/product/productList";
    			$.dialog.open(url,{
    				id: 'productInfo',  
	  			    title: '选择商品列表',  
	  			    width: 1020,  
	  			    height: 580,  
	  			    left: '50%',  
	  			    top: '50%',  
	  			    background: '#000000',  
	  			    opacity: 0.1,  
	  			    lock: true,  
	  			    resize: false,
	  			    close:function(){
	  			    	var productInfo = $.dialog.data("productInfo");//接收字窗口发过来的数据
	  			    	if(productInfo != null){
	  			    		currentTr.find("[tag=pid]").val(productInfo.id);
	  			    		currentTr.find("[tag=name]").val(productInfo.name);
	  			    		currentTr.find("[tag=brand]").text(productInfo.brandName);
	  			    		currentTr.find("[tag=salePrice]").val(productInfo.salePrice);
	  			    		$.dialog.removeData("productInfo");//清空数据
	  			    	}
	  			    }
    			})
    		})
    		//计算小计
    		$("[tag=salePrice],[tag=number]").change(function(){
    			var currentTr = $(this).closest("tr");
    			var salePrice = parseFloat(currentTr.find("[tag=salePrice]").val())||0;
    			var number = parseFloat(currentTr.find("[tag=number]").val())||0;
    			var amount = salePrice * number;
    			currentTr.find("[tag=amount]").text(amount.toFixed(2));
    		})
    		//添加明细
    		$(".appendRow").click(function(){
    			//克隆一行
    			var newTr = $("#edit_table_body tr:first").clone(true);
				newTr.find("input").val("");
				newTr.find("span").empty();
    			$("#edit_table_body").append(newTr);
    		})
    		//删除明细
    		$(".removeItem").click(function(){
    			var currentTr = $(this).closest("tr");
    			if($("#edit_table_body tr").size() == 1){
    				currentTr.find("input").val("");
    				currentTr.find("span").empty();
    			}else{
    				currentTr.remove();
    			}
    		})
    		//提交表单时修改表单的name属性值
    		$("#editForm").submit(function(){
    			$("#edit_table_body tr").each(function(index,tr){
    				$(tr).find("[tag=pid]").prop("name","items["+index+"].product.id");
    				$(tr).find("[tag=salePrice]").prop("name","items["+index+"].salePrice");
    				$(tr).find("[tag=number]").prop("name","items["+index+"].number");
    				$(tr).find("[tag=remark]").prop("name","items["+index+"].remark");
    			})
    		})
    	})
    </script>
</head>
<body>
<form name="editForm" action="/stockoutcomeBill/saveOrUpdate" method="post" id="editForm">
<input type="hidden" name="id" value="${stockoutcomeBill.id }">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">销售出库单编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">出库单编码</td>
                    <td class="ui_text_lt">
                        <input type="text" name="sn" class="ui_input_txt02" value="${stockoutcomeBill.sn }"/>
                    </td>
                </tr>
                 <tr>
                    <td class="ui_text_rt" width="140">仓库</td>
                    <td class="ui_text_lt">
                        <select name="depot.id" class="ui_select01">
                           <c:forEach items="${depots }" var="depot">
                           	<option value="${depot.id }" ${depot.id == stockoutcomeBill.depot.id ? 'selected':'' }>${depot.name }</option>
                            </c:forEach> 
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">客户</td>
                    <td class="ui_text_lt">
                        <select name="client.id" class="ui_select01">
                           <c:forEach items="${clients }" var="client">
                           	<option value="${client.id }" ${client.id == stockoutcomeBill.client.id ? 'selected':'' }>${client.name }</option>
                            </c:forEach> 
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                    	<fmt:formatDate value="${stockoutcomeBill.vdate }" pattern="yyyy-MM-dd" var="vdate"/>
                        <input name="vdate" class="ui_input_txt02 Wdate" value="${vdate }" onClick="WdatePicker()"/>
                    </td>
                </tr>
                 <tr>
                    <td class="ui_text_rt" width="140">单据明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <c:if test="${empty stockoutcomeBill.items }">
                            	<tr>
                                <td></td>
                                <td>
                                    <input disabled="true" readonly="true" class="ui_input_txt02" tag="name"/>
                                    <img src="/images/common/search.png" class="searchproduct"/>
                                    <input type="hidden" name="items[0].product.id" tag="pid"/>
                                </td>
                                <td><span tag="brand"></span></td>
                                <td><input tag="salePrice" name="items[0].salePrice"
                                                 class="ui_input_txt00"/></td>
                                <td><input tag="number" name="items[0].number"
                                                 class="ui_input_txt00"/></td>
                                <td><span tag="amount">${item.amount }</span></td>
                                <td><input tag="remark" name="items[0].remark"
                                                 class="ui_input_txt02"/></td>
                                <td>
                                    <a href="javascript:;" class="removeItem">删除明细</a>
                                </td>
                            </tr>
                            	
                            </c:if>
                            <c:forEach items="${stockoutcomeBill.items}" var="item">
                            <tr>
                                <td></td>
                                <td>
                                    <input disabled="true" readonly="true" class="ui_input_txt02" tag="name" value="${item.product.name }"/>
                                    <img src="/images/common/search.png" class="searchproduct"/>
                                    <input type="hidden" name="items[0].product.id" tag="pid" value="${item.product.id }"/>
                                </td>
                                <td><span tag="brand">${item.product.brandName }</span></td>
                                <td><input tag="salePrice" name="items[0].salePrice"
                                                 class="ui_input_txt00" value="${item.salePrice }"/></td>
                                <td><input tag="number" name="items[0].number"
                                                 class="ui_input_txt00" value="${item.number }"/></td>
                                <td><span tag="amount">${item.amount }</span></td>
                                <td><input tag="remark" name="items[0].remark"
                                                 class="ui_input_txt02" value="${item.remark }"/></td>
                                <td>
                                    <a href="javascript:;" class="removeItem">删除明细</a>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    </form>
</body>
</html>