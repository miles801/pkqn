<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>编辑闲散青年</title>
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
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/jcrop/css/Jcrop.min.css">
    <script type="text/javascript" src="<%=contextPath%>/vendor/jcrop/js/Jcrop.min.js"></script>

    <script type="text/javascript">
        window.angular.contextPathURL = "<%=contextPath%>";
    </script>

    <style>
        #picture .col-1-half {
            width: 50px !important;
            text-align: left;
        }

        td > input {
            width: 90%;
            margin: 5px;
        }
    </style>
</head>
<body>
<div class="main" ng-app="spec.youth.edit" ng-controller="Ctrl">
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

                    <div id="picture" style="position: absolute;width: 200px;height: 100px;left: 70%;">
                        <%-- 头像 --%>
                        <div eccrm-upload="uploadOptions">
                        </div>
                        <div style="position:absolute;top:5px;left: 45px;width: 180px;height: 80px;">
                            <div id="imageId" class="col" ng-show="beans.picture"
                                 style="border: 1px dashed #DAF3F5;padding: 5px 10px;"></div>
                            <i class="icons icon fork cp col" ng-show="beans.picture" ng-click="removePicture()"
                               ng-cloak ng-if="pageType!=='detail'" style="margin-left: 8px;"></i>
                        </div>
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
                            <label validate-error="form.education">文化程度:</label>
                        </div>
                        <select ng-model="beans.education" class="col-2-half"
                                ng-options="foo.value as foo.name for foo in education">
                        </select>
                        <div class="form-label col-1-half">
                            <label validate-error="form.nation">民族:</label>
                        </div>
                        <select ng-model="beans.nation" class="col-2-half" name="nation"
                                ng-options="foo.value as foo.name for foo in nation">
                        </select>


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
                            <label>年龄(周岁):</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.age" readonly/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>手机号码:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.mobile"
                               validate validate-max-length="20" validate-int/>
                        <div class="form-label col-1-half">
                            <label>身份证号码:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.tel"
                               validate validate-max-length="20"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>邮箱:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.email"
                               validate validate-max-length="100"/>
                        <div class="form-label col-1-half">
                            <label>QQ:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.qq"
                               validate validate-max-length="20" validate-int/>
                    </div>
                    <div class="row">

                        <div class="form-label col-1-half">
                            <label validate-error="form.orgName">县（市）区:</label>
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
                            <label>兴趣爱好:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="beans.interest"
                               validate validate-max-length="20"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>地址:</label>
                        </div>
                        <input class="col-6-half" type="text" ng-model="beans.address"
                               validate validate-max-length="100"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>基本情况:</label>
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
                        <div class="form-label col-1-half">&nbsp;</div>
                        <div class="col-10-half">
                            <h3 class="text-center">家庭成员及主要社会关系</h3>
                            <div class="block">
                                <div class="block-header">
                                    <div class="header-button" ng-if="pageType!=='detail'" ng-cloak>
                                        <button type="button" class="btn btn-green btn-min" ng-click="addRelation()">
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
                                                <td>姓名</td>
                                                <td style="width: 80px;">关系</td>
                                                <td>年龄</td>
                                                <td>文化程度</td>
                                                <td>职业</td>
                                                <td>联系电话</td>
                                                <td style="width: 80px;">操作</td>
                                            </tr>
                                            </thead>
                                            <tbody class="table-body">
                                            <tr ng-show="!beans.relations.length">
                                                <td colspan="8" class="text-center" style="border:0;">无家庭关系！</td>
                                            </tr>
                                            <tr ng-repeat="foo in beans.relations" ng-cloak>
                                                <td>{{$index+1}}</td>
                                                <td style="width: 80px;">
                                                    <input type="text" ng-model="foo.name" validate validate-required
                                                           validate-max-length="20">
                                                </td>
                                                <td>
                                                    <input type="text" ng-model="foo.relation" validate
                                                           validate-max-length="20">
                                                </td>
                                                <td style="width: 60px;">
                                                    <input type="number" ng-model="foo.age" validate validate-int
                                                           validate-max-value="150" validate-min-value="0">
                                                </td>
                                                <td style="width: 120px;">
                                                    <input type="text" ng-model="foo.education" validate
                                                           validate-max-length="20">
                                                </td>
                                                <td style="width: 80px;">
                                                    <input type="text" ng-model="foo.duty" validate
                                                           validate-max-length="20">
                                                </td>
                                                <td style="width: 80px;">
                                                    <input type="text" ng-model="foo.phone" validate
                                                           validate-int validate-max-length="20">
                                                </td>
                                                <td style="width: 80px;">
                                                    <a style="cursor:pointer" title="删除" ng-if="$parent.pageType!=='detail'" ng-cloak
                                                       ng-click="removeRelation($index)">
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


                    <div class="row" ng-cloak ng-if="'${pageType}'==='detail'">
                        <div class="form-label col-1-half">&nbsp;</div>
                        <div class="col-10-half">
                            <h3 class="text-center">帮扶记录</h3>
                            <div class="block">
                                <div class="block-header">
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
                                            </tr>
                                            </thead>
                                            <tbody class="table-body">
                                            <tr ng-show="!helpLogs.total">
                                                <td colspan="4" class="text-center" style="border:0;">无帮扶历史！</td>
                                            </tr>
                                            <tr bindonce ng-repeat="foo in helpLogs.data" ng-cloak>
                                                <td>{{$index+1}}</td>
                                                <td>
                                                    <a ng-click="viewHelpHistory(foo.id)" class="cp" bo-text="foo.title"></a>
                                                </td>
                                                <td bo-text="foo.occurDate|eccrmDate"></td>
                                                <td bo-text="foo.creatorName"></td>
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
<script type="text/javascript" src="<%=contextPath%>/app/spec/youth/youth.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/spec/youth/edit/youth_edit.js"></script>
</html>