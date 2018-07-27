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
<div id="main" style="height:400px;width: 800px"></div>
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
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:${groupByType}
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'left',
                            max: ${maxTotalAmount}
                        }
                    }
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'销售总额',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:${datas}
            }
        ]
    };


    // 为echarts对象加载数据
    myChart.setOption(option);
</script>
</body>
</html>
