//权限
$(function() {
	//选项左右移动
	//全部右移
	$("#selectAll").click(function() {
		$(".left_select option").appendTo($(".right_select"));
	});
	//全部左移
	$("#deselectAll").click(function() {
		$(".left_select").append($(".right_select option"));
	});
	//选中右移
	$("#select").click(function() {
		$(".left_select option:selected").appendTo($(".right_select"));
	});
	//选中左移
	$("#deselect").click(function() {
		$(".left_select").append($(".right_select option:selected"));
	});
	
	//提交表单时选中所有option
	$("#editForm").submit(function() {
		$(".right_select option").prop("selected",true);
	});
	
	//遍历左边框，删除在右边框已经选中的option
	var values = $.map($(".right_select option"),function(option){
		return option.value;
	})
	$(".left_select option").each(function(index,option) {
		//值没有找到返回-1
		if($.inArray(option.value,values) != -1){
			$(option).remove();
		}
	})
});

//菜单
$(function() {
	//选项左右移动
	//全部右移
	$("#mselectAll").click(function() {
		$(".mleft_select option").appendTo($(".mright_select"));
	});
	//全部左移
	$("#mdeselectAll").click(function() {
		$(".mleft_select").append($(".mright_select option"));
	});
	//选中右移
	$("#mselect").click(function() {
		$(".mleft_select option:selected").appendTo($(".mright_select"));
	});
	//选中左移
	$("#mdeselect").click(function() {
		$(".mleft_select").append($(".mright_select option:selected"));
	});
	
	//提交表单时选中所有option
	$("#editForm").submit(function() {
		$(".mright_select option").prop("selected",true);
	});
	
	//遍历左边框，删除在右边框已经选中的option
	var values = $.map($(".mright_select option"),function(option){
		return option.value;
	})
	$(".mleft_select option").each(function(index,option) {
		//值没有找到返回-1
		if($.inArray(option.value,values) != -1){
			$(option).remove();
		}
	})
});
