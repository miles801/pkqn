<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>二级管理员待审核列表</title>
    <meta content="text/html" charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/artDialog/artDialog.css"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/angular-motion-v0.3.2/angular-motion.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript">
        window.angular.contextPathURL = "<%=contextPath%>";
    </script>
</head>
<body>
<div class="main condition-row-1" ng-app="eccrm.base.user.list" ng-controller="UserListController">
    <div class="list-condition">
        <div class="block">
            <div class="block-header">
                <span class="header-button">
                        <button type="button" class="btn btn-green btn-min" ng-click="query();">
                            <span class="glyphicons search"></span>
                            查询
                        </button>
                </span>
            </div>
            <div class="block-content">
                <div class="row">
                    <div class="form-label col-1-half">
                        <label>用户名:</label>
                    </div>
                    <input class="col-2-half" type="text" ng-model="condition.username"/>

                    <div class="form-label col-1-half">
                        <label>姓名:</label>
                    </div>
                    <input class="col-2-half" type="text" ng-model="condition.employeeName"/>
                </div>
            </div>
        </div>
    </div>
    <div class="list-result ">
        <div class="block">
            <div class="block-header">
                <div class="header-text">
                    <span class="glyphicons list"></span>
                    <span>待审核列表</span>
                </div>
            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <div class="table-responsive panel panel-table ">
                        <table class="table table-striped table-hover text-center">
                            <thead class="table-header">
                            <tr>
                                <td>用户名</td>
                                <td>姓名</td>
                                <td>所属县区</td>
                                <td>电话</td>
                                <td>注册时间</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <tr ng-show="!users || !users.total">
                                <td colspan="6" class="text-center">没有符合条件的记录！</td>
                            </tr>
                            <tr bindonce ng-repeat="foo in users.data" ng-cloak>

                                <td title="点击查询明细！" style="cursor: pointer;">
                                    <a ng-click="detail(foo.employeeId);" bo-text="foo.username"></a>
                                </td>
                                <td bo-text="foo.employeeName"></td>
                                <td bo-text="foo.deptName"></td>
                                <td bo-text="foo.mobilePhone"></td>
                                <td bo-text="foo.createdDatetime | eccrmDatetime"></td>
                                <td>
                                    <a class="cp" title="通过审核" ng-click="approveOk(foo.id);">
                                        <i class="icons ok"></i>
                                    </a>
                                    <a class="cp" title="不通过" ng-click="approveDeny(foo.id);">
                                        <i class="icons fork"></i>
                                    </a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="list-pagination" eccrm-page="pager"></div>
</div>
</div >

</body>
<script type="text/javascript" src="<%=contextPath%>/app/base/user/user.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/base/user/list/user_approve.js"></script>
</html>