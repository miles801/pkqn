<%--
  Created by IntelliJ IDEA.
  User: miles
  Date: 13-11-22
  Time: 下午3:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<c:set var="isAdd" value="${pageType eq null or pageType eq 'add'}"></c:set>
<c:set var="isModify" value="${pageType ne null and pageType eq 'modify'}"></c:set>
<c:set var="isDetail" value="${pageType ne null and pageType eq 'detail'}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>编辑菜单</title>
    <meta content="text/html" charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/angular-motion-v0.3.2/angular-motion.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/zTree/css/ztree.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-ztree-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-upload.js"></script>

    <script>
        window.angular.contextPathURL = "<%=contextPath%>";
    </script>
</head>
<body>
<div class="main" ng-app="eccrm.base.menu.edit" ng-controller="MenuEditController">
    <div class="row-10 block">
        <div class="block-header">
                <span class="header-text">
                    <span class="glyphicons circle_info"></span>
                    <span>
                        <c:if test="${isAdd}">新增功能</c:if>
                        <c:if test="${isModify}">更新功能</c:if>
                        <c:if test="${isDetail}">查看功能</c:if>
                    </span>
                </span>
                <span class="header-button">
                    <c:if test="${isAdd}">
                        <button type="button" class="btn btn-green btn-min"
                                ng-click="save()" ng-disabled="form.$invalid" ng-cloak>
                            <span class="glyphicons floppy_saved"></span> 保存
                        </button>
                    </c:if>
                    <c:if test="${isModify}">
                        <button type="button" class="btn btn-green btn-min"
                                ng-click="update()" ng-disabled="form.$invalid" ng-cloak>
                            <span class="glyphicons claw_hammer"></span> 更新
                        </button>
                    </c:if>
                    <a type="button" class="btn btn-green btn-min"
                       ng-click="back();">
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
                        <input type="hidden" id="attachmentId"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>功能名称:</label>
                        </div>
                        <input type="text" name="name" class="col-2-half" placeholder="请输入功能名称" ng-model="menu.name"
                               validate validate-required validate-min-length="2" validate-max-length="20"
                               maxlength="20" data-placement="right" validate-options="hasName"/>

                        <div class="form-label col-1-half">
                            <label>功能编号:</label>
                        </div>
                        <input type="text" name="code" class="col-2-half" ng-model="menu.code"
                               validate validate-required validate-max-length="40" maxlength="40"
                               validate-options="hasCode"/>

                        <div class="form-label col-1-half">
                            <label>功能类型:</label>
                        </div>
                        <select id="type" ng-model="menu.type" ng-options="foo.value as foo.name for foo in types"
                                class="col-2"> </select>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>显示顺序:</label>
                        </div>
                        <input type="number" name="sequenceNo" class="col-2-half" placeholder="请输入一个正整数..."
                               ng-model="menu.sequenceNo"
                               validate validate-int validate-min-value="0" validate-max-value="999"/>

                        <div class="form-label col-1-half">
                            <label for="authorization">需要授权:</label>
                        </div>
                        <input type="checkbox" class="col" id="authorization" ng-model="menu.authorization"
                               value="true"/>

                        <div class="form-label col-1-half">
                            <label for="show">显示:</label>
                        </div>
                        <input class="col" type="checkbox" id="show" ng-model="menu.show" value="true"/>
                        <div class="form-label col-1-half">
                            <label for="fullScreen">全屏:</label>
                        </div>
                        <input class="col" type="checkbox" id="fullScreen" ng-model="menu.fullScreen" value="true"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>所属资源:</label>
                        </div>
                        <div class="col-2-half" style="position: relative;">
                            <input type="text" class="col-12" ztree-single="ztreeResource"
                                   ng-model="menu.resource.name"/>
                            <span class="add-on">
                                <i class="icons circle_fork icon" ng-click="menu.resource=null" title="清除"></i>
                            </span>
                        </div>

                    </div>
                    <div class="row" ng-show="menu.type=='SYS_CDLX'" ng-cloak>
                        <div eccrm-upload="uploadOptions"></div>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>上级功能:</label>
                        </div>
                        <div class="col-2-half" style="position: relative;">
                            <input type="text" class="col-12" ztree-single="ztreeOptions" ng-model="menu.parent.name"/>
                            <span class="add-on" style="position:absolute;right: 0;top: 0">
                                <i class="icons circle_fork icon" ng-click="menu.parent=null" title="清除"></i>
                            </span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>备注:</label>
                        </div>
                        <textarea rows="6" class="col-10" ng-model="menu.description" maxlength="2000"></textarea>
                    </div>
                    <div class="row" style="margin-top: 5px;">
                        <div class="form-label col-1-half">
                            <label>采集人:</label>
                        </div>
                        <span class="col-2-half" ng-bind-template="{{menu.creatorName}}"></span>

                        <div class="form-label col-1-half">
                            <label>采集时间:</label>
                        </div>
                        <span class="col-2-half" ng-bind-template="{{menu.createdDatetime | eccrmDatetime}}"></span>

                        <div class="form-label col-1-half">
                            <label>状态:</label>
                        </div>
                        <div class="col-2">
                            <select ng-model="menu.status"
                                    ng-options="foo.value as foo.name for foo in status"> </select>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="<%=contextPath%>/app/base/resource/menu/menu.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/base/resource/resource.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/base/resource/menu/menu_edit.js"></script>
</html>