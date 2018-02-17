<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>amCharts examples</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/components/chart/amcharts_3.14.4/style.css" type="text/css">
        <script src="${pageContext.request.contextPath }/components/chart/amcharts_3.14.4/amcharts/amcharts.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath }/components/chart/amcharts_3.14.4/amcharts/serial.js" type="text/javascript"></script>
        <script>
            var chart;
			var chartData = ${result};
/*             var chartData = [
                {
                    "country": "USA",
                    "visits": 4025,
                    "color": "#FF0F00"
                },
                {
                    "country": "China",
                    "visits": 1882,
                    "color": "#FF6600"
                },
                {
                    "country": "Japan",
                    "visits": 1809,
                    "color": "#FF9E01"
                },
                {
                    "country": "Germany",
                    "visits": 1322,
                    "color": "#FCD202"
                },
                {
                    "country": "UK",
                    "visits": 1122,
                    "color": "#F8FF01"
                },
                {
                    "country": "France",
                    "visits": 1114,
                    "color": "#B0DE09"
                },
                {
                    "country": "India",
                    "visits": 984,
                    "color": "#04D215"
                },
                {
                    "country": "Spain",
                    "visits": 711,
                    "color": "#0D8ECF"
                },
                {
                    "country": "Netherlands",
                    "visits": 665,
                    "color": "#0D52D1"
                },
                {
                    "country": "Russia",
                    "visits": 580,
                    "color": "#2A0CD0"
                },
                {
                    "country": "South Korea",
                    "visits": 443,
                    "color": "#8A0CCF"
                },
                {
                    "country": "Canada",
                    "visits": 441,
                    "color": "#CD0D74"
                },
                {
                    "country": "Brazil",
                    "visits": 395,
                    "color": "#754DEB"
                },
                {
                    "country": "Italy",
                    "visits": 386,
                    "color": "#DDDDDD"
                },
                {
                    "country": "Australia",
                    "visits": 384,
                    "color": "#999999"
                },
                {
                    "country": "Taiwan",
                    "visits": 338,
                    "color": "#333333"
                },
                {
                    "country": "Poland",
                    "visits": 328,
                    "color": "#000000"
                }
            ]; */

            AmCharts.ready(function () {
                // SERIAL CHART
                chart = new AmCharts.AmSerialChart();
                chart.dataProvider = chartData;
                /* chart.categoryField = "country"; */
                chart.categoryField = "factory";// 修改为json串中的参数
                // the following two lines makes chart 3D
                chart.depth3D = 20;
                chart.angle = 30;

                // AXES
                // category
                var categoryAxis = chart.categoryAxis;
                categoryAxis.labelRotation = 90;
                categoryAxis.dashLength = 5;
                categoryAxis.gridPosition = "start";

                // value
                var valueAxis = new AmCharts.ValueAxis();
               /*  valueAxis.title = "Visitors"; */
               valueAxis.title = "Sales";// 改为相应的标题
                valueAxis.dashLength = 5;
                chart.addValueAxis(valueAxis);

                // GRAPH
                var graph = new AmCharts.AmGraph();
                /* graph.valueField = "visits"; */
                graph.valueField = "amount";// 修改为json串中的参数
                graph.colorField = "color";
                graph.balloonText = "<span style='font-size:14px'>[[category]]: <b>[[value]]</b></span>";
                graph.type = "column";
                graph.lineAlpha = 0;
                graph.fillAlphas = 1;
                chart.addGraph(graph);

                // CURSOR
                var chartCursor = new AmCharts.ChartCursor();
                chartCursor.cursorAlpha = 0;
                chartCursor.zoomable = false;
                chartCursor.categoryBalloonEnabled = false;
                chart.addChartCursor(chartCursor);

                chart.creditsPosition = "top-right";


                // WRITE
                chart.write("chartdiv");
            });
        </script>
    </head>

    <body>
        <div id="chartdiv" style="width: 100%; height: 400px;"></div>
    </body>

</html>