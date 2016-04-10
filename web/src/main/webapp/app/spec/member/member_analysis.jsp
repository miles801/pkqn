<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>县区团员占比统计</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/echart/echarts.min.js"></script>
    <script>
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
</head>
<body>
<div class="main" ng-app="spec.member.report" ng-controller="Ctrl" style="overflow: auto;">
    <div class="row text-center">
        <div style="margin: 0 auto;">
            <div class="form-label" style="display: inline-block;width: 120px;">
                <label>年份:</label>
            </div>
            <select ng-model="year" style="width: 200px;" ng-change="query();" ng-options="foo as foo for foo in years">
            </select>
        </div>
    </div>
    <div class="table-responsive panel panel-table  first-min">
        <table class="table table-striped table-hover" ng-cloak>
            <tr>
                <td colspan="2" rowspan="2">类别/领域</td>
                <td colspan="4">团组织</td>
                <td>团员</td>
                <td colspan="2">团干部</td>
            </tr>
            <tr>
                <td>基层团委数</td>
                <td>基层团工委数</td>
                <td>团总支数</td>
                <td>团支部数</td>
                <td>团员数</td>
                <td>专职团干部数</td>
                <td>兼职团干部数</td>
            </tr>
            <tr>
                <td rowspan="3">公办学校</td>
                <td>高校</td>
                <td>{{beans['11'][2]}}</td>
                <td>{{beans['11'][3]}}</td>
                <td>{{beans['11'][4]}}</td>
                <td>{{beans['11'][5]}}</td>
                <td>{{beans['11'][6]}}</td>
                <td>{{beans['11'][7]}}</td>
                <td>{{beans['11'][8]}}</td>
            </tr>
            <tr>
                <td>中学</td>
                <td>{{beans['12'][2]}}</td>
                <td>{{beans['12'][3]}}</td>
                <td>{{beans['12'][4]}}</td>
                <td>{{beans['12'][5]}}</td>
                <td>{{beans['12'][6]}}</td>
                <td>{{beans['12'][7]}}</td>
                <td>{{beans['12'][8]}}</td>
            </tr>
            <tr>
                <td>中职</td>
                <td>{{beans['13'][2]}}</td>
                <td>{{beans['13'][3]}}</td>
                <td>{{beans['13'][4]}}</td>
                <td>{{beans['13'][5]}}</td>
                <td>{{beans['13'][6]}}</td>
                <td>{{beans['13'][7]}}</td>
                <td>{{beans['13'][8]}}</td>
            </tr>
            <tr>
                <td rowspan="3">民办学校</td>
                <td>高校</td>
                <td>{{beans['21'][2]}}</td>
                <td>{{beans['21'][3]}}</td>
                <td>{{beans['21'][4]}}</td>
                <td>{{beans['21'][5]}}</td>
                <td>{{beans['21'][6]}}</td>
                <td>{{beans['21'][7]}}</td>
                <td>{{beans['21'][8]}}</td>
            </tr>
            <tr>
                <td>中学</td>
                <td>{{beans['22'][2]}}</td>
                <td>{{beans['22'][3]}}</td>
                <td>{{beans['22'][4]}}</td>
                <td>{{beans['22'][5]}}</td>
                <td>{{beans['22'][6]}}</td>
                <td>{{beans['22'][7]}}</td>
                <td>{{beans['22'][8]}}</td>
            </tr>
            <tr>
                <td>中职</td>
                <td>{{beans['23'][2]}}</td>
                <td>{{beans['23'][3]}}</td>
                <td>{{beans['23'][4]}}</td>
                <td>{{beans['23'][5]}}</td>
                <td>{{beans['23'][6]}}</td>
                <td>{{beans['23'][7]}}</td>
                <td>{{beans['23'][8]}}</td>
            </tr>
            <tr>
                <td colspan="2">国有企业</td>
                <td>{{beans['3null'][2]}}</td>
                <td>{{beans['3null'][3]}}</td>
                <td>{{beans['3null'][4]}}</td>
                <td>{{beans['3null'][5]}}</td>
                <td>{{beans['3null'][6]}}</td>
                <td>{{beans['3null'][7]}}</td>
                <td>{{beans['3null'][8]}}</td>
            </tr>
            <tr>
                <td colspan="2">非公企业</td>
                <td>{{beans['4null'][2]}}</td>
                <td>{{beans['4null'][3]}}</td>
                <td>{{beans['4null'][4]}}</td>
                <td>{{beans['4null'][5]}}</td>
                <td>{{beans['4null'][6]}}</td>
                <td>{{beans['4null'][7]}}</td>
                <td>{{beans['4null'][8]}}</td>
            </tr>
            <tr>
                <td colspan="2">机关事业单位</td>
                <td>{{beans['5null'][2]}}</td>
                <td>{{beans['5null'][3]}}</td>
                <td>{{beans['5null'][4]}}</td>
                <td>{{beans['5null'][5]}}</td>
                <td>{{beans['5null'][6]}}</td>
                <td>{{beans['5null'][7]}}</td>
                <td>{{beans['5null'][8]}}</td>
            </tr>
            <tr>
                <td colspan="2">社会组织</td>
                <td>{{beans['6null'][2]}}</td>
                <td>{{beans['6null'][3]}}</td>
                <td>{{beans['6null'][4]}}</td>
                <td>{{beans['6null'][5]}}</td>
                <td>{{beans['6null'][6]}}</td>
                <td>{{beans['6null'][7]}}</td>
                <td>{{beans['6null'][8]}}</td>
            </tr>
            <tr>
                <td colspan="2">农 村</td>
                <td>{{beans['7null'][2]}}</td>
                <td>{{beans['7null'][3]}}</td>
                <td>{{beans['7null'][4]}}</td>
                <td>{{beans['7null'][5]}}</td>
                <td>{{beans['7null'][6]}}</td>
                <td>{{beans['7null'][7]}}</td>
                <td>{{beans['7null'][8]}}</td>
            </tr>
            <tr>
                <td colspan="2">城市社区</td>
                <td>{{beans['8null'][2]}}</td>
                <td>{{beans['8null'][3]}}</td>
                <td>{{beans['8null'][4]}}</td>
                <td>{{beans['8null'][5]}}</td>
                <td>{{beans['8null'][6]}}</td>
                <td>{{beans['8null'][7]}}</td>
                <td>{{beans['8null'][8]}}</td>
            </tr>
        </table>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/employee/employee.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/spec/member/member_analysis.js"></script>
</html>