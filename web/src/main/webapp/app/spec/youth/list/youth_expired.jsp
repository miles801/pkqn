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
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/zTree/css/ztree.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-ztree-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/org/org.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/employee/employee-modal.js"></script>
    <script>
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
    <style>
        td > .btn.btn-icon {
            color: #fff;
            border-radius: 3px;
            margin: 3px;
            border: 0;
        }

        .btn-gray {
            background-color: #a2aeae;
        }

        .btn-yellow {
            background-color: #fcf61b;
            color: #1A0202 !important;
        }

        .btn-audit {
            background-color: #9afc1a;
            color: #1A0202 !important;
        }
    </style>
</head>
<body>
<div class="main condition-row-2" ng-app="spec.youth.list" ng-controller="Ctrl">
    <div class="list-condition">
        <div class="block">
            <div class="block-header">
                <span class="header-text">
                    <span class="glyphicons search"></span>
                </span>
                <span class="header-button">
                    <a type="button" class="btn btn-min" ng-click="query()" style="position: relative;top:3px;">
                        <span class="glyphicons search"></span> 查询
                    </a>
                </span>
            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>名称:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="condition.name"/>
                        <div class="form-label col-1-half">
                            <label validate-error="form.mz">民族:</label>
                        </div>
                        <select ng-model="condition.nation" class="col-2-half" name="mz"
                                ng-options="foo.value as foo.name for foo in nation">
                        </select>
                        <div class="form-label col-1-half">
                            <label>年龄:</label>
                        </div>
                        <select ng-model="age" class="col-2-half" ng-change="ageChange();">
                            <option value="">全部</option>
                            <option value="1">6-10</option>
                            <option value="2">11-15</option>
                            <option value="3">16-20</option>
                            <option value="4">21-25</option>
                        </select>
                    </div>
                    <div class="row">

                        <div class="form-label col-1-half">
                            <label>县（市）区:</label>
                        </div>
                        <div class="col-2-half" id="orgId">
                            <input class="col-12" type="text" ng-model="orgName" readonly
                                   ztree-single="orgOptions"/>
                            <span class="add-on">
                                <i class="icons icon search" ng-click="clearOrg();"></i>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="list-result ">
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
                                <td>姓名</td>
                                <td style="width: 40px;">性别</td>
                                <td style="width: 40px;">出生日期</td>
                                <td>年龄</td>
                                <td>文化程度</td>
                                <td>民族</td>
                                <td>住址</td>
                                <td>手机号</td>
                                <td>所属县区</td>
                                <td>团干部</td>
                                <td>基本情况</td>
                                <td style="width: 100px;">帮扶次数</td>
                                <td style="width: 120px;">最近一次帮扶时间</td>
                                <td style="width: 80px;">操作</td>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <tr ng-show="!beans || !beans.total">
                                <td colspan="14" class="text-center">没有查询到数据！</td>
                            </tr>
                            <tr bindonce ng-repeat="foo in beans.data" ng-cloak>
                                <td>
                                    <a ng-click="view(foo.id,foo.name)" class="cp" bo-text="foo.name"></a>
                                </td>
                                <td bo-text="foo.sexName"></td>
                                <td bo-text="foo.birthday|eccrmDate"></td>
                                <td bo-text="foo.age"></td>
                                <td bo-text="foo.educationName"></td>
                                <td bo-text="foo.nationName"></td>
                                <td bo-text="foo.address|substr:20"></td>
                                <td bo-text="foo.mobile"></td>
                                <td bo-text="foo.orgName"></td>
                                <td bo-text="foo.ownerName"></td>
                                <td bo-text="foo.content|substr:20"></td>
                                <td bo-text="foo.helpTimes||0"></td>
                                <td bo-text="foo.lastHelpDate|eccrmDate"></td>
                                <td>
                                    <a style="cursor:pointer" title="导出" ng-click="exportInfo(foo.id)">
                                        <i class="icons download"></i>
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
<script type="text/javascript" src="<%=contextPath%>/app/spec/youth/youth.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/spec/youth/list/youth_expired.js"></script>
</html>