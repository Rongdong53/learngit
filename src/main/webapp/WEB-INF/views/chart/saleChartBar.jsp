<%--
  Created by IntelliJ IDEA.
  User: Sunsh
  Date: 2018/7/19
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/jquery/plugin/artDialog/jquery.artDialog.source.js?skin=blue"></script>
<script type="text/javascript" src="/js/jquery/plugin/My97DatePicker/WdatePicker.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="height:400px;width: 700px"></div>
<!-- ECharts单文件引入 -->
<script type="text/javascript" src="/js/jquery/plugin/chart/echarts-all.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('main'));

    var option = {
        title : {
            text: '销售报表',
            subtext: '${groupByTypeName}',
            x:'center'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['销售总额'],
            x:'left'
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ${groupByType}
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'销售总额',
                type:'bar',
                data:${totalAmount},
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };

    // 为echarts对象加载数据
    myChart.setOption(option);
</script>
</body>
</html>
