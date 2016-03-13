<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>编辑志愿者</title>
    <meta content="text/html" charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/zTree/css/ztree.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-ztree-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/org/org.js"></script>

    <script type="text/javascript">
        window.angular.contextPathURL = "<%=contextPath%>";
    </script>

</head>
<body>
<div class="main" ng-app="spec.volunteer.edit" ng-controller="Ctrl">
    <div class="block">
        <div class="block-header">
                <span class="header-text">
                    <span class="glyphicons info-sign"></span>
                </span>
                <span class="header-button">
                    <c:if test="${pageType eq 'add'}">
                        <button type="button" class="btn btn-green btn-min" ng-click="save()"
                                ng-disabled="!form.$valid">
                            <span class="glyphicons disk_save"></span> 保存
                        </button>
                        <button type="button" class="btn btn-green btn-min" ng-click="save(true)"
                                ng-disabled="!form.$valid">
                            <span class="glyphicons disk_open"></span> 保存并新建
                        </button>
                    </c:if>
                    <c:if test="${pageType eq 'modify'}">
                        <button type="button" class="btn btn-green btn-min" ng-click="update()"
                                ng-disabled="!form.$valid">
                            <span class="glyphicons claw_hammer"></span> 更新
                        </button>
                    </c:if>
                    <a type="button" class="btn btn-green btn-min" ng-click="back();">
                        <span class="glyphicons message_forward"></span> 返回
                    </a>
                </span>
        </div>
        <div class="block-content">
            <div class="content-wrap" style="padding: 5px 0 15px 0;">
                <form name="form" class="form-horizontal" role="form" style="position: relative;">
                    <div style="display: none;">
                        <input type="hidden" id="pageType" value="${pageType}"/>
                        <input type="hidden" id="id" value="${id}"/>
                    </div>

                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="form.name">姓名:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.name" name="name"
                               validate validate-required validate-max-length="20"/>
                        <div class="form-label col-1-half">
                            <label validate-error="form.sex">性别:</label>
                        </div>
                        <select ng-model="beans.sex" class="col-2-half" name="sex"
                                ng-options="foo.value as foo.name for foo in sex" validate validate-required>
                        </select>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>身份证号:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.idCard"
                               validate validate-max-length="20"/>
                        <div class="form-label col-1-half">
                            <label>熟悉领域:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.duty"
                               validate validate-max-length="20"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>联系电话:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.phone"
                               validate validate-max-length="40" validate-int/>

                        <div class="form-label col-1-half">
                            <label>所属县区:</label>
                        </div>
                        <div class="col-2-half">
                            <input class="col-12" type="text" ng-model="beans.orgName" readonly ztree-single="orgTree"/>
                            <span class="add-on">
                                <i class="icons icon fork" title="清除" ng-click="clearOrg();"></i>
                            </span>
                        </div>
                        <div class="form-label col-1-half">
                            <label>录入人:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.ownerName"
                               readonly/>

                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>备注:</label>
                        </div>
                        <textarea class="col-10-half" rows="5" ng-model="beans.description"></textarea>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/spec/volunteer/volunteer.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/spec/volunteer/volunteer_edit.js"></script>
</html>