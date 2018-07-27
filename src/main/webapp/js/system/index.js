//加载当前日期
function loadDate(){
	var time = new Date();
	var myYear = time.getFullYear();
	var myMonth = time.getMonth() + 1;
	var myDay = time.getDate();
	if (myMonth < 10) {
		myMonth = "0" + myMonth;
	}
	document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."	+ myDay;
}

/**
 * 隐藏或者显示侧边栏
 * 
 **/
function switchSysBar(flag){
	var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
	if( flag==true ){	// flag==true
		left_menu_cnt.show(500, 'linear');
		side.css({width:'280px'});
		$('#top_nav').css({width:'77%', left:'304px'});
    	$('#main').css({left:'280px'});
	}else{
        if ( left_menu_cnt.is(":visible") ) {
			left_menu_cnt.hide(10, 'linear');
			side.css({width:'60px'});
        	$('#top_nav').css({width:'100%', left:'60px', 'padding-left':'28px'});
        	$('#main').css({left:'60px'});
        	$("#show_hide_btn").find('img').attr('src', 'images/common/nav_show.png');
        } else {
			left_menu_cnt.show(500, 'linear');
			side.css({width:'280px'});
			$('#top_nav').css({width:'77%', left:'304px', 'padding-left':'0px'});
        	$('#main').css({left:'280px'});
        	$("#show_hide_btn").find('img').attr('src', 'images/common/nav_hide.png');
        }
	}
}

$(function(){
	loadDate();
	
	// 显示隐藏侧边栏
	$("#show_hide_btn").click(function() {
        switchSysBar();
    });
});

//模块切换效果
$(function() {
	$("#TabPage2 li").click(function(){
		//先删除所有的li元素的class=selected样式
		$("#TabPage2 li").removeClass("selected");
		//修改当前元素的class属性
		$(this).addClass("selected");
		//修改当前li元素中的image元素的src属性值
		$("#TabPage2 li").each(function(index,li) {
			$(li).find("img").prop("src","/images/common/" + (index + 1) + ".jpg")
		});
		//index():获取当前元素在同辈元素中的索引位置
		var index = $(this).index() + 1;
		$(this).find("img").prop("src","/images/common/" + index + "_hover.jpg")
		
		//切换模块图片
		$("#nav_module img").prop("src","/images/common/module_" + index + ".png");
		
		//切换当前位置
		var title = $(this).prop("title");
		$("#here_area").html("当前位置：系统&nbsp;>&nbsp;" + title);
		
		//切换菜单模块
		var rootmenu=$(this).data("rootmenu");
		$.fn.zTree.init($(".ztree"), setting, zNodes[rootmenu]);
	});
});

//实现树状菜单
var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		//点击子菜单执行的函数
		callback: {
			onClick: function(event, treeId, treeNode){
				$("#rightMain").prop("src",treeNode.controller);
			}
		},
		//异步发送请求加载子节点
		async: {
			enable: true,
			url: "/systemmenu/loadmenu",
			autoParam: ["sn=parentSn"]
		}
	};

	var zNodes ={
			business:[
				{ id:1, pId:0, name:"业务模块", sn:"business", isParent:true},
			],
			systemManage:[
			    { id:2, pId:0, name:"系统模块", sn:"system", isParent:true},
				/*{ id:21, pId:2, name:"部门管理",controller:"/department/list"}*/
			],
			charts:[
		        { id:3, pId:0, name:"报表模块", sn:"charts", isParent:true},
			]
		};

	$(document).ready(function(){
		$.fn.zTree.init($(".ztree"), setting, zNodes.business);
	});