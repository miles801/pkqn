<%--
Created by IntelliJ IDEA.
User: chenl
Date: 2014-10-15 14:47:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <base href="<%=request.getContextPath()%>/">
    <title>团员列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/angular-motion-v0.3.2/angular-motion.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/zTree/css/ztree.css">

    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-ztree-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/moment/moment.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/org/org.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/employee/employee.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/spec/member/member_list.js"></script>
    <script type="text/javascript">
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
</head>
<body id="ng-app">
<div class="main condition-row-2 " ng-app="spec.member.list" ng-controller="Ctrl">
    <input type="hidden" id="contextPath" value="<%=request.getContextPath()%>"/>

    <div class="list-condition">
        <div class="block">
            <div class="block-header">
                <span class="header-button">
                    <button type="button" class="btn btn-green btn-min" ng-click="query()">
                        <span class="glyphicons binoculars"></span>查询
                    </button>
                </span>
            </div>
            <div class="block-content">
                <div class="content-wrap" style="padding-top: 0px">
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>年龄:</label>
                        </div>
                        <select ng-model="age" class="col-2-half"
                                ng-options="foo.value as foo.name for foo in ages" ng-change="changeAge()">
                        </select>

                        <div class="form-label col-1-half">
                            <label validate-error="form.orgName">所属县区:</label>
                        </div>
                        <div class="col-2-half">
                            <input type="text" class="col-12" ztree-single="OrgztreeOptions" name="orgName"
                                   ng-model="condition.orgName" ng-disabled="!orgPermission">
                                <span class="add-on" ng-cloak ng-show="orgPermission">
                                   <i class="icons circle_fork icon" title="清空"
                                      ng-click="clearOrg();"></i>
                                </span>
                        </div>

                        <div class="form-label col-1-half">
                            <label>荣誉称号:</label>
                        </div>
                        <select ng-model="condition.honor" class="col-2-half"
                                ng-options="foo.value as foo.name for foo in honor">
                        </select>

                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>所在领域:</label>
                        </div>
                        <select class="col-2-half" name="ly" ng-model="condition.ly" ng-change="lyChange();"
                                ng-options="foo.value as foo.name for foo in ly"
                                ng-cloak ng-disabled="!lyPermission"></select>
                        <div class="form-label col-1-half">
                            <label>子领域:</label>
                        </div>
                        <select class="col-2-half" ng-model="condition.ly2"
                                ng-options="foo.value as foo.name for foo in ly2" ng-disabled="!lyPermission"></select>
                        <div class="form-label col-1-half">
                            <label>入团年份:</label>
                        </div>
                        <select class="col-2-half" ng-model="year" ng-change="yearChange()"
                                ng-options="foo.value as foo.name for foo in years"></select>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="list-result ">
        <div class="block">
            <div class="block-header">
                <div class="header-text">
                    <span class="glyphicons list"></span>
                    <span>团员列表</span>
                </div>
                <div class="header-button">
                    <button type="button" class="btn btn-sm btn-green btn-min" ng-click="clear()">清理</button>
                    <button type="button" class="btn btn-sm btn-green btn-min" ng-click="exportData()">导出</button>
                </div>
            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <div class="table-responsive panel panel-table  first-min">
                        <table class="table table-striped table-hover">
                            <thead class="table-header">
                            <tr>
                                <td>姓名</td>
                                <td>年龄</td>
                                <td>身份证号</td>
                                <td>所属县区</td>
                                <td>民族</td>
                                <td>政治面貌</td>
                                <td>入团年月</td>
                                <td>移动电话</td>
                                <td>所在领域</td>
                                <td style="white-space: nowrap;text-align:center; width: 10px;">操作</td>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <tr ng-show="!employees || !employees.total || employees.data.length==0">
                                <td colspan="10" class="text-center">没有查询到数据！</td>
                            </tr>
                            <tr bindonce ng-repeat="foo in employees.data">
                                <td title="点击查询明细！" style="cursor: pointer;">
                                    <a ng-click="detail(foo.id)"
                                       bo-text="foo.employeeName"></a>
                                </td>
                                <td bo-text="foo.age"></td>
                                <td bo-text="foo.idNo"></td>
                                <td bo-text="foo.orgName"></td>
                                <td bo-text="foo.nationName"></td>
                                <td bo-text="foo.zzmmName"></td>
                                <td bo-text="foo.beginWorkDate|date:'yyyy-MM'"></td>
                                <td bo-text="foo.mobile"></td>
                                <td bo-text="foo.lyName + (foo.ly2Name?('-'+foo.ly2Name):'')"></td>
                                <td>
                                    <a class="cp" ng-click="modify(foo.id)" title="编辑">
                                        <i class="icons edit"></i>
                                    </a>
                                    <a class="cp" ng-click="remove(foo.id)" title="删除！">
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
</body>
</html>