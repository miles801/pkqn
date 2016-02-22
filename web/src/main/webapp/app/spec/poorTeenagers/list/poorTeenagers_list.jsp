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
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/zTree/css/ztree.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-ztree-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/org/org.js"></script>
    <script>
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
</head>
<body>
<div class="main condition-row-2" ng-app="spec.poorTeenagers.list" ng-controller="Ctrl">
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
                            <label>电话</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="condition.phone"/>
                        <div class="form-label col-1-half">
                            <label>身份证号:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="condition.idCard"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>慰问金额:</label>
                        </div>
                        <select ng-model="money" class="col-2-half" ng-change="moneyChange();">
                            <option value="">全部</option>
                            <option value="1">未慰问</option>
                            <option value="2">1000元以下</option>
                            <option value="3">1000-2000元</option>
                            <option value="4">2000以上</option>
                        </select>
                        <div class="form-label col-1-half" ng-cloak ng-if="isRootOrg">
                            <label>区县:</label>
                        </div>
                        <div class="col-2-half" id="orgId" ng-if="isRootOrg">
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
                                <td>住址</td>
                                <td>电话</td>
                                <td>QQ</td>
                                <td>家长姓名</td>
                                <td>家庭年收入</td>
                                <td>基本情况</td>
                                <td>备注</td>
                                <td style="width: 120px;">录入时间</td>
                                <td style="width: 120px;">慰问次数</td>
                                <td style="width: 120px;">慰问金额</td>
                                <td style="width: 80px;">操作</td>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <tr ng-show="!beans || !beans.total">
                                <td colspan="15" class="text-center">没有查询到数据！</td>
                            </tr>
                            <tr bindonce ng-repeat="foo in beans.data" ng-cloak>
                                <td>
                                    <a ng-click="view(foo.id)" class="cp" bo-text="foo.name"></a>
                                </td>
                                <td bo-text="foo.sexName"></td>
                                <td bo-text="foo.birthday|eccrmDate"></td>
                                <td bo-text="foo.age"></td>
                                <td bo-text="foo.address"></td>
                                <td bo-text="foo.phone"></td>
                                <td bo-text="foo.qq"></td>
                                <td bo-text="foo.parentName"></td>
                                <td bo-text="foo.incomeName"></td>
                                <td bo-text="foo.content|substr:20"></td>
                                <td bo-text="foo.description|substr:10"></td>
                                <td bo-text="foo.createdDatetime|eccrmDatetime"></td>
                                <td bo-text="foo.condoleTimes"></td>
                                <td bo-text="foo.condoleMoney"></td>
                                <td>
                                    <a style="cursor:pointer" title="导出" ng-click="exportInfo(foo.id)">
                                        <i class="icons download"></i>
                                    </a>
                                    <a style="cursor:pointer" title="修改" ng-click="modify(foo.id)">
                                        <i class="icons edit"></i>
                                    </a>
                                    <a style="cursor:pointer" title="删除" ng-click="remove(foo.id)">
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
<script type="text/javascript" src="<%=contextPath%>/app/spec/poorTeenagers/poorTeenagers.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/spec/poorTeenagers/list/poorTeenagers_list.js"></script>
</html>