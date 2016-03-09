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
<div class="main" ng-app="spec.youth.help.view" ng-controller="Ctrl">
    <div style="display: none;">
        <input type="hidden" id="pageType" value="${pageType}"/>
        <input type="hidden" id="youthId" value="${param.youthId}"/>
    </div>
    <div class="row">
        <div class="form-label col-1-half">&nbsp;</div>
        <div class="col-10-half">
            <h3 class="text-center">帮扶记录</h3>
            <div class="block">
                <div class="block-header">
                    <div class="header-button" ng-if="pageType!=='detail'" ng-cloak>
                        <button type="button" class="btn btn-green btn-min" ng-click="query()">
                            <span class="glyphicons disk_save"></span> 刷新
                        </button>
                        <button type="button" class="btn btn-green btn-min" ng-click="add()">
                            <span class="glyphicons disk_save"></span> 添加
                        </button>
                    </div>
                </div>
                <div class="block-content">
                    <div class="table-responsive panel panel-table">
                        <table class="table table-striped table-hover text-center">
                            <thead class="table-header">
                            <tr>
                                <td style="width: 20px;">序号</td>
                                <td>主题</td>
                                <td>帮扶时间</td>
                                <td>录入人</td>
                                <td style="width: 80px;">操作</td>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <tr ng-show="!beans.total">
                                <td colspan="5" class="text-center" style="border:0;">无帮扶历史！</td>
                            </tr>
                            <tr bindonce ng-repeat="foo in beans.data" ng-cloak>
                                <td>{{$index+1}}</td>
                                <td>
                                    <a ng-click="view(foo.id)" class="cp" bo-text="foo.title"></a>
                                </td>
                                <td bo-text="foo.occurDate|eccrmDate"></td>
                                <td bo-text="foo.creatorName"></td>
                                <td>
                                    <a style="cursor:pointer" title="修改" ng-click="modify(foo.id)"> <i
                                            class="icons edit"></i> </a>
                                    <a style="cursor:pointer" title="删除" ng-click="remove(foo.id)"> <i
                                            class="icons fork"></i> </a>
                                </td>
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
<script type="text/javascript" src="<%=contextPath%>/app/spec/youth/edit/youthHelp_view.js"></script>
</html>