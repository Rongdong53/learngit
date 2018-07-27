<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery/plugin/validate/jquery.validate.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/system/employee/employee.js"></script>
  	<script type="text/javascript">
  		$(function() {
			$("#editForm").validate({
				rules: {
					name: {
						required: true,
						minlength: 2
					},
					password:{
						required:true,
						minlength: 6
					},
					repassword:{
						equalTo:"#password"
					},
					email:{
						required: true,
						email:true
					},
					age:{
						range:[18,80]
					}
				},
				messages: {
					name: {
						required: "用户名不能为空",
						minlength: "长度不能小于两个字符"
					},
					password:{
						required:"密码不能为空",
						minlength: "长度不能小于六个字符"
					},
				 	repassword:{
						equalTo:"两次输入的密码不一致"
					},
					email:{
						required: "邮箱不能为空",
						email:"请输入正确的邮箱格式:xx@qq.com"
					},
					age:{
						range:"未满18岁"
					}
				}
			});
		});
  	</script>
</head>
<body>
<form name="editForm" action="/employee/saveOrUpdate" method="post" id="editForm">
    <input type="hidden" name="id" value="${employee.id }">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">用户编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">用户名</td>
                    <td class="ui_text_lt">
                        <input name="name" class="ui_input_txt02" value="${employee.name }"/>
                    </td>
                </tr>
                <c:if test="${employee.id == null}">
                <tr>
                    <td class="ui_text_rt" width="140">密码</td>
                    <td class="ui_text_lt">
                        <input type="password" name="password" id="password" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">验证密码</td>
                    <td class="ui_text_lt">
                        <input name="repassword" type="password" class="ui_input_txt02" />
                    </td>
                </tr>
                </c:if>
                <tr>
                    <td class="ui_text_rt" width="140">Email</td>
                    <td class="ui_text_lt">
                        <input name="email" class="ui_input_txt02" value="${employee.email }"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">年龄</td>
                    <td class="ui_text_lt">
                        <input name="age" class="ui_input_txt02" value="${employee.age }"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">所属部门</td>
                    <td class="ui_text_lt">
                        <select name="dept.id" class="ui_select01">
                           <c:forEach items="${departments }" var="dept">
                           	<option value="${dept.id }" ${dept.id == employee.dept.id ? 'selected':'' }>${dept.name }</option>
                            </c:forEach> 
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">超级管理员</td>
                    <td class="ui_text_lt">
                        <input type="checkbox" name="admin" class="ui_checkbox01" ${employee.admin?"checked":null }></input>
                    </td>
                </tr>
               <tr>
                    <td class="ui_text_rt" width="140">角色</td>
                    <td class="ui_text_lt">
                        <table>
                            <tr>
                                <td>
                                    <select multiple="true" class="ui_multiselect01 left_select">
                                    	<c:forEach items="${roles }" var="role">
                                    		<option value="${role.id }">${role.name }</option>
                                    	</c:forEach>
                                    </select>
                                </td>
                                <td align="center">
                                    <input type="button" id="select" value="-->" class="left2right"/><br/>
                                    <input type="button" id="selectAll" value="==>" class="left2right"/><br/>
                                    <input type="button" id="deselect" value="<--" class="left2right"/><br/>
                                    <input type="button" id="deselectAll" value="<==" class="left2right"/>
                                </td>
                                <td>
                                    <select multiple="true" name="roleIds" class="ui_multiselect01 right_select">
                                    	<c:forEach items="${employee.roles }" var="role">
                                    		<option value="${role.id }">${role.name }</option>
                                    	</c:forEach>
                                    </select>
                                </td>
                            </tr>
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