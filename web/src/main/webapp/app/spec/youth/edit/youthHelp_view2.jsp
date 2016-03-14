<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>帮扶记录浏览</title>
    <meta content="text/html" charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/zTree/css/ztree.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-ztree-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-upload.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/org/org.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/kindeditor-4.1.10/kindeditor-min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/kindeditor-4.1.10/lang/zh_CN.js"></script>

    <script type="text/javascript">
        window.angular.contextPathURL = "<%=contextPath%>";
    </script>
</head>
<body>
<div class="main" ng-app="spec.youth.help.view2" ng-controller="Ctrl" style="overflow: auto;">
    <form name="form" class="form-horizontal" role="form" style="position: relative;">
        <div style="display: none;">
            <input type="hidden" id="id" value="${param.id}"/>
        </div>
        <div style="margin:20px auto;text-align: center;">
            <h3 class="text-center" ng-cloak>{{beans.title}}</h3>
            <h5 ng-cloak>
                <span style="float: left;margin-left:5%;">志愿者: {{beans.volunteerNames}}</span>
                <span style="float: right;margin-right:5%;">时间: {{beans.occurDate|eccrmDate}}</span>
                <span style="clear: both;">&nbsp;</span>
            </h5>
            <div class="ycrl split" style="margin-top:15px;"></div>
            <div id="content"></div>
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/spec/youth/youth.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/spec/youth/edit/youthHelp_view2.js"></script>
</html>