<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>闲散青少年</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script>
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
</head>
<body>
<div class="main condition-row-1" ng-app="spec.youth.help.report" ng-controller="Ctrl" style="overflow: auto;">
    <div class="list-condition">
        <div class="block">
            <div class="block-header">
                <span class="header-text">
                    <span class="glyphicons search"></span>
                </span>
                <span class="header-button">
                    <a type="button" class="btn btn-min" ng-click="query()" style="position: relative;top:3px;"
                       ng-disabled="!condition.year || !condition.month">
                        <span class="glyphicons search"></span> 查询
                    </a>
                </span>
            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="year">年份:</label>
                        </div>
                        <select ng-model="condition.year" ng-options="foo as foo for foo in years" class="col-2-half"
                                validate validate-required name="year">
                        </select>
                        <div class="form-label col-1-half">
                            <label validate-error="month">月份:</label>
                        </div>
                        <select ng-model="condition.month" ng-options="foo as foo for foo in month" class="col-2-half"
                                validate validate-required name="month">
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="list-result no-pagination">
        <div class="block">
            <div class="block-header">
                <div class="header-button">
                    <button type="button" class="btn btn-green btn-min" ng-click="exportData()"
                            style="position: relative;top:3px;">
                        <span class="glyphicons disk_save"></span> 导出Excel
                    </button>
                </div>
            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <div class="table-responsive panel panel-table">
                        <table class="table table-striped table-hover text-center">
                            <thead class="table-header">
                            <tr>
                                <td></td>
                                <td>团干部姓名</td>
                                <td>团干部职务</td>
                                <td>姓名</td>
                                <td>性别</td>
                                <td>年龄</td>
                                <td>联系电话</td>
                                <td>本月工作情况</td>
                                <td>是否继续帮扶</td>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <tr ng-show="!beans.length">
                                <td colspan="9" class="text-center">没有查询到数据！</td>
                            </tr>
                            <tr bindonce ng-repeat="foo in beans" ng-cloak>
                                <td bo-text="$index+1"></td>
                                <td bo-text="foo[0]"></td>
                                <td bo-text="foo[1]||''"></td>
                                <td bo-text="foo[2]"></td>
                                <td bo-text="foo[3]"></td>
                                <td bo-text="foo[4]"></td>
                                <td bo-text="foo[5]"></td>
                                <td bo-text="foo[6]"></td>
                                <td bo-text="foo[7]"></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/spec/youth/youth.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/spec/youth/report/youthHelp_report.js"></script>
</html>