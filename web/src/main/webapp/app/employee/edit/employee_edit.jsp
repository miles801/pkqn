<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%
    String contextPath = request.getContextPath();
%>
<head>
    <base href="<%=request.getContextPath()%>/">
    <title>编辑员工</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
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
    <script type="text/javascript" src="<%=contextPath%>/app/employee/employee.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/employee/edit/employee_edit.js"></script>

    <script type="text/javascript">
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
</head>
<body id="ng-app">
<div class="main tab" ng-app="eccrm.base.employee.edit" ng-controller="Ctrl">
    <div class="row-10">
        <div class="block">
            <div class="block-header">
                    <span class="header-text">
                        <span class="glyphicons info-sign"></span>
                        <span>员工档案</span>
                    </span>
                    <span class="header-button">
                        <button type="button" class="btn btn-sm btn-green btn-min"
                                ng-show="!page.type || page.type=='add'" ng-click="save()" ng-disabled="!form.$valid">
                            <span class="glyphicons disk_save"></span> 保存
                        </button>
                        <button type="button" class="btn btn-sm btn-green btn-min" ng-show="page.type=='modify'"
                                ng-click="update()" ng-disabled="!form.$valid && !canUpdate">
                            <span class="glyphicons claw_hammer"></span> 更新
                        </button>
                        <a type="button" class="btn btn-sm btn-green btn-min" ng-click="back();">
                            <span class="glyphicons message_forward"></span> 返回
                        </a>
                    </span>
            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <form name="form" class="form-horizontal" role="form">
                        <div style="display: none;">
                            <input type="hidden" ng-model="page.type" ng-init="page.type='${pageType}'"/>
                            <input type="hidden" id="contextPath" value="<%=request.getContextPath()%>"/>
                            <input type="hidden" id="id" value="${id}"/>
                            <input type="hidden" id="pageType" value="${pageType}"/>
                        </div>
                        <div class="row">
                            <div class="form-label col-1-half">
                                <label validate-error="form.employeeName">姓名:</label>
                            </div>
                            <input class="col-2-half" type="text" name="employeeName" validate validate-required
                                   validate-max-length="15" ng-model="employee.employeeName"/>

                            <div class="form-label col-1-half">
                                <label validate-error="form.employeeCode">工号:</label>
                            </div>
                            <input class="col-2-half" type="text" name="employeeCode" validate validate-required validate-max-length="15"
                                   ng-model="employee.employeeCode"/>

                            <div class="form-label col-1-half">
                                <label>性别:</label>
                            </div>
                            <div class="col-2-half">
                                <select ng-model="employee.gender" ng-options="foo.value as foo.name for foo in sex"
                                        class="col-12">
                                </select>
                            </div>
                        </div>


                        <div class="row">
                            <div class="form-label col-1-half">
                                <label>所属机构:</label>
                            </div>
                            <div class="col-2-half">
                                <input type="text" class="col-12" ztree-single="OrgztreeOptions"
                                       ng-model="employee.organization.name"/>
                                <input type="hidden" ng-model="employee.organization.id"/>
                                    <span class="add-on">
                                       <i class="icons circle_fork icon" title="清空" ng-click="employee..organization.id=null;employee.organization.name=null;"></i>
                                    </span>
                            </div>

                            <div class="form-label col-1-half">
                                <label>移动电话:</label>
                            </div>
                            <input class="col-2-half" type="text" validate validate-int validate-max-length="20"
                                   ng-model="employee.mobile"/>

                            <div class="form-label col-1-half">
                                <label>电子邮箱:</label>
                            </div>
                            <input class="col-2-half" type="text" validate validate-email validate-max-length="40"
                                   ng-model="employee.email"/>
                        </div>
                        <div class="row">
                            <div class="form-label col-1-half">
                                <label>职务:</label>
                            </div>
                            <input class="col-2-half" type="text" validate validate-max-length="40"
                                   ng-model="employee.duty"/>

                            <div class="form-label col-1-half">
                                <label>职位:</label>
                            </div>
                            <input class="col-2-half" type="text" validate validate-max-length="40"
                                   ng-model="employee.post"/>
                        </div>
                        <div class="row">
                            <div class="form-label col-1-half">
                                <label>备注:</label>
                            </div>
                            <textarea rows="6" class="col-10-half" ng-model="employee.description"
                                      validate validate-max-length="500"></textarea>
                        </div>
                        <div class="row">
                            <div eccrm-upload="uploadOptions">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-label col-1-half">&nbsp; </div>
                            <div id="imageId" class="col" ng-show="employee.imageId"
                                 style="border: 1px dashed #DAF3F5;padding: 5px 10px;"></div>
                        </div>
                        <div class="row">
                            <div class="form-label col-1-half">
                                <label validate-error="form.status">状态:</label>
                            </div>
                            <select ng-model="employee.status" name="status" ng-options="foo.value as foo.name for foo in EmpStatus" validate validate-required
                                    class="col-2-half">
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>