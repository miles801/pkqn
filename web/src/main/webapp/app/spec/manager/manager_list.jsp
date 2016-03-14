<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <base href="<%=request.getContextPath()%>/">
    <title>团干部列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/artDialog/artDialog.css"/>
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
    <script type="text/javascript" src="<%=contextPath%>/app/spec/manager/manager_list.js"></script>
    <script type="text/javascript">
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
</head>
<body id="ng-app">
<div class="main condition-row-1" ng-app="eccrm.base.employee.list" ng-controller="Ctrl">
<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>"/>

<div class="list-condition">
    <div class="block">
        <div class="block-header">
            <div class="header-text">
                <span class="glyphicons list"></span>
            </div>
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
                        <label>姓名:</label>
                    </div>
                    <input class="col-2-half" type="text" ng-model="condition.employeeName"/>


                    <div class="form-label col-1-half" ng-if="showOrg" ng-cloak>
                        <label>所属县区:</label>
                    </div>
                    <div class="col-2-half" ng-if="showOrg" ng-cloak>
                        <input class="col-12" type="text" ng-model="orgName" readonly ztree-single="orgTree"/>
                            <span class="add-on">
                                <i class="icons icon fork" title="清除" ng-click="clearOrg();"></i>
                            </span>
                    </div>
                    <div class="form-label col-1-half">
                        <label>手机号码:</label>
                    </div>
                    <input class="col-2-half" type="text" ng-model="condition.mobile"/>
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
                <span>团干部管理</span>
            </div>
        </div>
        <div class="block-content">
            <div class="content-wrap">
                <div class="table-responsive panel panel-table">
                    <table class="table table-striped table-hover text-center">
                        <thead class="table-header">
                        <tr>
                            <td>姓名</td>
                            <td>性别</td>
                            <td>所属县区</td>
                            <td>工作单位</td>
                            <td>移动电话</td>
                            <td>职务</td>
                            <td>民族</td>
                            <td style="min-width: 200px;">地址</td>
                        </tr>
                        </thead>
                        <tbody class="table-body">
                        <tr ng-show="!employees || !employees.total || employees.data.length==0">
                            <td colspan="8" class="text-center">没有查询到数据！</td>
                        </tr>
                        <tr bindonce ng-repeat="foo in employees.data">
                            <td title="点击查询明细！" style="cursor: pointer;">
                                <a ng-click="detail(foo.id)"
                                   bo-text="foo.employeeName"></a>
                            </td>
                            <td bo-text="foo.genderName"></td>
                            <td bo-text="foo.orgName"></td>
                            <td bo-text="foo.company"></td>
                            <td bo-text="foo.mobile"></td>
                            <td bo-text="foo.duty"></td>
                            <td bo-text="foo.nationName"></td>
                            <td bo-text="foo.address|substr:20"></td>
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
</html>