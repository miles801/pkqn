<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%
    String contextPath = request.getContextPath();
%>
<head>
    <base href="<%=request.getContextPath()%>/">
    <title>团员印章</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/employee/employee.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/spec/member/member_print.js"></script>

    <script type="text/javascript">
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
    <style>
        td.td-label {
            width: 120px;
            font-weight: 500;
        }

        td.td-value {
            width: 280px;
        }

        .table-striped > tbody > tr:nth-child(odd) > td, .table-striped > tbody > tr:nth-child(odd) > th {
            background: none;
        }
    </style>
</head>
<body id="ng-app">
<div class="main" ng-app="spec.member.print" ng-controller="Ctrl" style="text-align: center;position: relative;">
    <div class="row" ng-cloak ng-if="'${param.show}'!=='true'" style="margin-top:20px;">
        <button class="btn btn-blue" ng-click="printInfo();" ng-if="beans.status=='2'" style="height: 40px;width: 60px;">
            打印
        </button>
        <button class="btn btn-blue" ng-click="applyMember();" ng-if="beans.status=='0'||beans.status=='4'"
                style="height: 40px;width: 100px;">
            申请团员证
        </button>
    </div>
    <h4 ng-if="beans.status=='1'" ng-cloak>正在申请中，请耐心等待审核!</h4>
    <h4 ng-if="beans.status=='3'" ng-cloak>当前登录用户已被注销，无法使用该功能!</h4>
    <h4 ng-if="beans.status=='4'" ng-cloak>审核未通过，无法使用该功能!请修改个人信息后重新申请！</h4>
    <div class="table-responsive panel panel-table" style="width: 700px;margin:0 auto;position: relative;">
        <table class="table table-striped table-hover" ng-cloak>
            <tbody>
            <tr>
                <td class="td-label">姓名</td>
                <td class="td-value">{{beans.employeeName}}</td>
                <td class="td-label">身份证号</td>
                <td class="td-value">{{beans.idNo}}</td>
            </tr>
            <tr>
                <td class="td-label">民族</td>
                <td class="td-value">{{beans.nationName}}</td>
                <td class="td-label">政治面貌</td>
                <td class="td-value">{{beans.zzmmName}}</td>
            </tr>
            <tr>
                <td class="td-label">入党年月</td>
                <td class="td-value"></td>
                <td class="td-label">入团年月</td>
                <td class="td-value">{{beans.beginWorkDate|date:'yyyy-MM'}}</td>
            </tr>
            <tr>
                <td class="td-label">全日制教育学历</td>
                <td class="td-value">{{beans.xueliName}}</td>
                <td class="td-label">团员所在领域</td>
                <td class="td-value">{{beans.lyName}}</td>
            </tr>
            <tr>
                <td class="td-label">是否在本县区从业</td>
                <td class="td-value">{{beans.isWorking?'是':'否'}}</td>
                <td class="td-label">荣誉称号</td>
                <td class="td-value">{{beans.honorName}}</td>
            </tr>
            <tr>
                <td class="td-label">移动电话</td>
                <td class="td-value">{{beans.mobile}}</td>
                <td class="td-label">邮箱</td>
                <td class="td-value">{{beans.email}}</td>
            </tr>
            <tr>
                <td class="td-label">备注</td>
                <td class="td-value" colspan="3" style="height: 80px;">{{beans.description}}</td>
            </tr>
            </tbody>
        </table>
        <div ng-cloak ng-if="'${param.show}'=='true'"
             style="position: absolute;width: 200px;height: 200px;top: 100px;right: 30px;">
            <img src="<%=contextPath%>/app/spec/member/zhang.png" alt="印章" width="200" height="200"/>
        </div>
    </div>
</div>
</body>
</html>