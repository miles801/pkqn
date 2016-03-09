<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>志愿者</title>
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
<div class="main condition-row-1" ng-app="spec.volunteer.list" ng-controller="Ctrl">
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
                            <label>所属县区:</label>
                        </div>
                        <div class="col-2-half">
                            <input class="col-12" type="text" ng-model="orgName" readonly ztree-single="orgTree"/>
                            <span class="add-on">
                                <i class="icons icon fork" title="清除" ng-click="clearOrg();"></i>
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
                    <a type="button" class="btn btn-min" ng-click="add()" style="position: relative;top:3px;">
                        <span class="glyphicons search"></span> 新增
                    </a>
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
                                <td>身份证号</td>
                                <td>所属县区</td>
                                <td>专业</td>
                                <td>联系电话</td>
                                <td>添加人</td>
                                <td style="width: 120px;">录入时间</td>
                                <td style="width: 80px;">操作</td>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <tr ng-show="!beans || !beans.total">
                                <td colspan="9" class="text-center">没有查询到数据！</td>
                            </tr>
                            <tr bindonce ng-repeat="foo in beans.data" ng-cloak>
                                <td>
                                    <a ng-click="view(foo.id)" class="cp" bo-text="foo.name"></a>
                                </td>
                                <td bo-text="foo.sexName"></td>
                                <td bo-text="foo.idCard"></td>
                                <td bo-text="foo.orgName"></td>
                                <td bo-text="foo.duty"></td>
                                <td bo-text="foo.phone"></td>
                                <td bo-text="foo.ownerName"></td>
                                <td bo-text="foo.createdDatetime|eccrmDatetime"></td>
                                <td>
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
<script type="text/javascript" src="<%=contextPath%>/app/spec/volunteer/volunteer.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/spec/volunteer/volunteer_list.js"></script>
</html>