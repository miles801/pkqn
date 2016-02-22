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
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-upload.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/org/org.js"></script>

    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/jcrop/css/Jcrop.min.css">
    <script type="text/javascript" src="<%=contextPath%>/vendor/jcrop/js/Jcrop.min.js"></script>

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
            <div class="content-wrap" style="padding: 5px 0 15px 0;">
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
                        <select ng-model="beans.sex" class="col-2-half" name="sex"
                                ng-options="foo.value as foo.name for foo in sex" validate validate-required>
                        </select>
                        <div class="form-label col-1-half">
                            <label validate-error="form.mz">民族:</label>
                        </div>
                        <select ng-model="beans.mz" class="col-2-half" name="mz"
                                ng-options="foo.value as foo.name for foo in nation" validate validate-required>
                        </select>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="form.zzmm">政治面貌:</label>
                        </div>
                        <select ng-model="beans.zzmm" class="col-2-half" name="zzmm"
                                ng-options="foo.value as foo.name for foo in zzmm" validate validate-required>
                        </select>

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
                            <label>年龄(周岁):</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.age" readonly/>

                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>手机号码:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.phone"
                               validate validate-max-length="20" validate-int/>
                        <div class="form-label col-1-half">
                            <label>QQ:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.qq"
                               validate validate-max-length="20" validate-int/>
                        <div class="form-label col-1-half">
                            <label validate-error="form.health">健康状况:</label>
                        </div>
                        <select ng-model="beans.health" class="col-2-half"
                                ng-options="foo.value as foo.name for foo in health">
                        </select>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="form.idCard">身份证号:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.idCard" name="idCard"
                               validate validate-required validate-min-length="18" validate-max-length="18"
                               validate-naming/>

                        <div class="form-label col-1-half">
                            <label>所在学校:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.school"
                               validate validate-max-length="100"/>
                        <div class="form-label col-1-half">
                            <label>所在班级:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.classes"
                               validate validate-max-length="10"/>
                    </div>
                    <div class="row">

                        <div class="form-label col-1-half">
                            <label validate-error="form.parentName">家长姓名:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.parentName" name="parentName"
                               validate validate-max-length="20"/>
                        <div class="form-label col-1-half">
                            <label validate-error="form.reason">贫困原因:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.reason" name="reason"
                               validate validate-max-length="20"/>
                        <div class="form-label col-1-half">
                            <label validate-error="form.income">家庭年收入:</label>
                        </div>
                        <select ng-model="beans.income" class="col-2-half"
                                ng-options="foo.value as foo.name for foo in income">
                        </select>

                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label validate-error="form.orgName">区县:</label>
                        </div>
                        <div class="col-2-half" id="orgId">
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

                    <div class="row">
                        <div eccrm-upload="uploadOptions">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">&nbsp; </div>
                        <div id="imageId" class="col" ng-show="beans.picture"
                             style="border: 1px dashed #DAF3F5;padding: 5px 10px;"></div>
                        <i class="icons icon fork cp" ng-show="beans.picture" ng-click="removePicture()"
                           ng-cloak ng-if="pageType!=='detail'" style="margin-left: 8px;"></i>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">&nbsp;</div>
                        <div class="col-10-half">
                            <h3 class="text-center">慰问记录</h3>
                            <div class="block">
                                <div class="block-header">
                                    <div class="header-button">
                                        <button type="button" class="btn btn-green btn-min" ng-click="addCondole()"
                                                ng-disabled="!beans.id">
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
                                                <td>标题</td>
                                                <td>慰问金额</td>
                                                <td>详细情况</td>
                                                <td style="width: 80px;">发生日期</td>
                                                <td style="width: 80px;">操作</td>
                                            </tr>
                                            </thead>
                                            <tbody class="table-body">
                                            <tr ng-show="!condoles.length">
                                                <td colspan="6" class="text-center" style="border:0;">无慰问记录！</td>
                                            </tr>
                                            <tr bindonce ng-repeat="foo in condoles" ng-cloak>
                                                <td bo-text="$index+1"></td>
                                                <td>
                                                    <a ng-click="viewCondole(foo.id);" bo-text="foo.title"
                                                       class="cp"></a>
                                                </td>
                                                <td bo-text="foo.money"></td>
                                                <td bo-text="foo.description"></td>
                                                <td bo-text="foo.occurDate|eccrmDate"></td>
                                                <td>
                                                    <a style="cursor:pointer" title="编辑"
                                                       ng-click="modifyCondole(foo.id)">
                                                        <i class="icons edit"></i>
                                                    </a>
                                                    <a style="cursor:pointer" title="删除"
                                                       ng-click="removeCondole(foo.id)">
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
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/spec/poorTeenagers/poorTeenagers.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/spec/poorTeenagers/edit/poorTeenagers_edit.js"></script>
</html>