/** table鼠标悬停换色* */
$(function() {
	// 如果鼠标移到行上时，执行函数
	$(".table tr").mouseover(function() {
		$(this).css({
			background : "#CDDAEB"
		});
		$(this).children('td').each(function(index, ele) {
			$(ele).css({
				color : "#1D1E21"
			});
		});
	}).mouseout(function() {
		$(this).css({
			background : "#FFF"
		});
		$(this).children('td').each(function(index, ele) {
			$(ele).css({
				color : "#909090"
			});
		});
	});
});

// 新增地址跳转
$(function() {
	$(".btn_input").click(function() {
		window.location.href = $(this).data("url");
	});
});

// 翻页和设置每页显示条数
$(function() {
	// 翻页
	$(".btn_page").click(function() {
		// 为输入框设置按钮绑定的页数
		var page = $(this).data("page") || $("[name=currentPage]").val();
		$("[name=currentPage]").val(page);
		// 提交表单(获取到表单提交)
		$("#searchForm").submit();
	});

	// 当值改变时提交表单
	$(".pageSize").change(function() {
		// 设置当前页输入框的值为1
		$("[name=currentPage]").val(1);
		// 提交表单
		$("#searchForm").submit();
	});

});
// 删除
$(function() {
	$(".btn_delete").click(function() {
		var url = $(this).data("url");
		$.dialog({
			title : "温馨提示",
			content : "您确定要删除该数据?",
			ok : function() {
				$.get(url, function(data) {
					if (data.success) {
						$.dialog({
							title : "温馨提示",
							content : "删除成功",
							ok : function() {
								window.location.reload();
							},
							cancel : false,
							icon : "succeed",
						});
					} else {
						$.dialog({
							title : "温馨提示",
							content : data.msg,
							ok : true,
							cancel : false,
							icon : "error",
						})
					}
				});
			},
			cancel : true,
			icon : "warning",
			lock : true
		});
	});
});
// 抽取出提示框
function commonDialog(content, ok, cancel, icon) {
	$.dialog({
		title : "温馨提示",
		content : content,
		ok : ok,
		cancel : cancel,
		icon : icon,
	});
}
//禁用序列化数组
jQuery.ajaxSettings.traditional = true;
// 批量删除
$(function() {
	$(".btn_batchdelete").click(function() {
		//当用户没有选择复选框时提示用户
		if ($(".acb:checked").size() == 0) {
			commonDialog("请选择您要删除的数据",true,false,"face-smile")
			return;
		}
		//遍历选中的复选框
		var ids = $.map($(".acb:checked"),function(checked){
			return $(checked).data("id");
		});
		var url = $(this).data("url");
		commonDialog("您确定要删除这些数据吗",function(){
			$.get(url,{ids:ids},function(data){
				console.log(data);
				if(data.success){
					commonDialog("删除成功",function(){
						window.location.reload();
					},false,"succeed")
				}
			});
		},true,"warning");
	});
	//全部选中
	$("#all").change(function(){
		$(".acb").prop("checked",$(this).prop("checked"));
	});
});
//审核订单
$(function(){
	$(".btn_auditor").click(function(){
		var url = $(this).data("url");
		commonDialog("您确定要审核该订单吗",function(){
			$.get(url,function(data){
				if(data.success){
					commonDialog("审核成功",function(){
						window.location.reload();
					},false,"succeed")
				}else{
					commonDialog(data.msg,true,false,"error");
				}
			})
		},true,"face-smile")
	});
})
