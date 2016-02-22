<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>编辑贫困青年</title>
    <meta content="text/html" charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/zTree/css/ztree.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-ztree-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/org/org.js"></script>
    <script type="text/javascript">
        window.angular.contextPathURL = "<%=contextPath%>";
    </script>
</head>
<body>
<div class="main" ng-app="spec.poorTeenagers.edit" ng-controller="Ctrl">
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
            <div class="content-wrap">
                <form name="form" class="form-horizontal" role="form">
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
                        <select ng-model="beans.sex" class="col-2-half" name="sex" validate validate-required>
                            <option value="0">男</option>
                            <option value="1">女</option>
                        </select>
                        <div class="form-label col-1-half">
                            <label validate-error="form.contact">联系方式:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.contact" name="contact"
                               validate validate-required validate-max-length="20"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>所在学校:</label>
                        </div>
                        <input class="col-6-half" type="text" ng-model="beans.school"
                               validate validate-max-length="100"/>
                        <div class="form-label col-1-half">
                            <label>所在班级:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.classes"
                               validate validate-max-length="10"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>出生年月:</label>
                        </div>
                        <div class="col-2-half">
                            <input class="col-12" type="text" ng-model="beans.birthday" readonly
                                   eccrm-my97="{}"/>
                            <span class="add-on">
                                <i class="icons icon clock"></i>
                            </span>
                        </div>
                        <div class="form-label col-1-half">
                            <label validate-error="form.parentName">家长姓名:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.parentName" name="parentName"
                               validate validate-max-length="20"/>
                        <div class="form-label col-1-half">
                            <label validate-error="form.parentContact">家长联系方式:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.parentContact" name="parentContact"
                               validate validate-max-length="20"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="form.orgName">区县:</label>
                        </div>
                        <div class="col-2-half">
                            <input class="col-12" type="text" ng-model="beans.orgName" name="orgName" readonly
                                   ztree-single="orgOptions"
                                   validate validate-required/>
                            <span class="add-on">
                                <i class="icons icon search"></i>
                            </span>
                        </div>
                        <div class="form-label col-1-half">
                            <label>地址:</label>
                        </div>
                        <input class="col-6-half" type="text" ng-model="beans.address"
                               validate validate-max-length="100"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>基本情况描述:</label>
                        </div>
                        <textarea class="col-10-half" rows="10" ng-model="beans.content"></textarea>
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
<script type="text/javascript" src="<%=contextPath%>/app/spec/poorTeenagers/poorTeenagers.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/spec/poorTeenagers/edit/poorTeenagers_edit.js"></script>
</html>