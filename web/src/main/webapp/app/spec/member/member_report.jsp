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
    <div class="row" style="margin-top:20px;">
        <div class="col-6 text-center">
            <div id="timesPie" style="width: 400px;height:350px;border: 1px solid #dcdcdc;margin:0 auto;"></div>
        </div>
        <div class="col-6 text-center">
            <div id="ldtyChart" style="width: 400px;height:350px;border: 1px solid #dcdcdc;margin:0 auto;"></div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/employee/employee.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/spec/member/member_report.js"></script>
</html>