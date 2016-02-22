<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>贫困青年</title>
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
<div class="main" ng-app="spec.poorTeenagers.report" ng-controller="Ctrl" style="padding-top: 50px;">
    <div class="row text-center">
        <div style="margin: 0 auto;">
            <div class="form-label" style="display: inline-block;width: 120px;">
                <label>年份:</label>
            </div>
            <select ng-model="year" style="width: 200px;" ng-change="query();" ng-options="foo as foo for foo in years">
            </select>
        </div>
    </div>

    <div class="row" style="margin-top:20px;">
        <div id="pie" style="width: 400px;height:350px;border: 1px solid #dcdcdc;margin:0 auto;"></div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/spec/poorTeenagers/poorTeenagers.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/spec/poorTeenagers/list/poorTeenagers_report.js"></script>
</html>