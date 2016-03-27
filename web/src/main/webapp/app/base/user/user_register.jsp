<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>用户注册</title>
    <meta content="text/html" charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/zTree/css/ztree.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-ztree-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/jquery-v1.8.3/jquery.md5.js"></script>
    <script type="text/javascript">
        window.angular.contextPathURL = "<%=contextPath%>";
    </script>
    <style>
        .row {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="main" ng-app="eccrm.base.user.register" ng-controller="Ctrl" style="margin-top:20px;">
    <form name="form" class="form-horizontal" role="form">
        <div class="row">
            <div class="form-label col-2-half">
                <label>用户名:</label>
            </div>
            <input class="col-3-half" type="text" ng-model="user.username"
                   validate validate-required validate-max-length="20"/>
            <div class="form-label col-2-half">
                <label>姓名:</label>
            </div>
            <input class="col-3-half" type="text" ng-model="user.employeeName"
                   validate validate-required validate-max-length="20"/>
        </div>
        <div class="row">
            <div class="form-label col-2-half">
                <label validate-error="form.password">密码:</label>
            </div>
            <input class="col-3-half" type="password" ng-model="user.password" name="password"
                   validate validate-required validate-max-length="20"/>
            <div class="form-label col-2-half">
                <label>确认密码:</label>
            </div>
            <input class="col-3-half" type="password" ng-model="user.password2"
                   validate validate-required validate-max-length="20"/>
        </div>
        <div class="row">
            <div class="form-label col-2-half">
                <label>县（市）区:</label>
            </div>
            <div class="col-3-half" style="position: relative">
                <input class="col-12" type="text" ng-model="user.deptName"
                       validate validate-required readonly ztree-single="orgTree"/>
            </div>
            <div class="form-label col-2-half">
                <label validate-error="form.position">角色类型:</label>
            </div>
            <select class="col-3-half" name="position" ng-model="user.position"
                    ng-options="foo.value as foo.name for foo in positions"
                    validate validate-required></select>
        </div>
        <div class="row">
            <div class="form-label col-2-half">
                <label validate-error="form.tzz">团组织:</label>
            </div>
            <select class="col-3-half" name="tzz" ng-model="user.tzz" ng-options="foo.value as foo.name for foo in tzz"
                    validate validate-required></select>

            <div class="form-label col-2-half">
                <label validate-error="form.ly">领域:</label>
            </div>
            <select class="col-3-half" name="ly" ng-model="user.ly" ng-options="foo.value as foo.name for foo in ly"
                    validate validate-required></select>
        </div>
        <div class="row">
            <div class="form-label col-2-half">
                <label>团组织名称:</label>
            </div>
            <input class="col-3-half" type="text" ng-model="user.tzzName"
                   validate validate-required maxlength="20"/>
            <div class="form-label col-2-half">
                <label>联系电话:</label>
            </div>
            <input class="col-3-half" type="text" ng-model="user.mobilePhone"
                   validate validate-required validate-int maxlength="20"/>
        </div>
        <div class="row">
            <div class="form-label col-2-half">
                <label>专职团干部人数:</label>
            </div>
            <input class="col-3-half" type="text" ng-model="user.zztgbCounts"
                   validate validate-required validate-int maxlength="4"/>
            <div class="form-label col-2-half">
                <label>兼职团干部人数:</label>
            </div>
            <input class="col-3-half" type="text" ng-model="user.jztgbCounts"
                   validate validate-required validate-int maxlength="4"/>
        </div>
        <div class="button-row" style="margin-top: 20px;">
            <button type="button" class="btn btn-default btn-primary"
                    ng-disabled="form.$invalid" ng-click="ok();">
                注册
            </button>
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/base/user/user.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/org/org.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/base/user/user_register.js"></script>
</html>