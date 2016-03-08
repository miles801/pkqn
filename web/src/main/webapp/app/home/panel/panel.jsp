<%@ page import="com.ycrl.core.context.SecurityContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%
        String contextPath = request.getContextPath();
        String userId = SecurityContext.getUserId();
    %>
    <title>仪表盘</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/angular-motion-v0.3.2/angular-motion.css">

    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/jquery-v1.8.3/jquery.md5.js"></script>

    <script type="text/javascript">
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
            font-size: 14px;
        }

        #clockbox {
            height: 100px;
            width: 610px;
        <!-- border: 1 px solid #ccc -->
        }

        .clean {
            clear: both
        }

        #timebox {
            height: 100%;
            width: 300px;
            padding-right: 20px;
            float: left;

        }

        #datebox {
            height: 100%;
            width: 300px;
            float: left;
            border-left: 2px solid #ccc;
            padding-left: 20px;
        }

        #time_part {
            font-size: 75px;
            font-weight: bolder;
            float: right;
            margin-left: -10px;;
        }

        #second_part {
            font-size: 45px;
            font-weight: bolder;
        }

        #week_part, #date_part {
            font-size: 32px;
            font-weight: bolder;
        }

        .mybtn .btn-blue {
            height: 35px;
            width: 100px;
            line-height: 32px;
            font-size: 14px;
            font-weight: 500;
            font-family: "微软雅黑", "宋体"
        }
    </style>
</head>

<body id="ng-app">
<div class="main" ng-app="eccrm.panel.base.list" ng-controller="BaseCtrl" style="overflow: auto;">
    <input type="hidden" id="userId" value="<%=userId%>"/>

    <div class="row" style="height: 200px;padding: 5px 20px;">
        <table style="height: 100%;width: 100%;">
            <tbody>
            <tr>
                <td style="width: 180px;" id="imageId">
                </td>
                <td>
                    <p>姓名：{{beans.employeeName}}</p>

                    <p>性别：{{beans.genderName}}</p>

                    <p>职务：{{beans.duty}}</p>

                    <p>民族：{{beans.nationName}}</p>

                    <p>所属县区：{{beans.orgName}}</p>

                </td>
                <td style="width: 150px;">
                    <div id="clockbox" style="padding: 5px;">
                        <div id="timebox">
                            <div id="time_part" style="float:right;"></div>
                        </div>
                        <div id="datebox">
                            <div id="week_part"></div>
                            <div id="date_part"></div>
                            <div style="margin-top: 20px;">
                                <div class="row mybtn">
                                    <a type="button" class="btn btn-blue"
                                       href="<%=contextPath%>/base/employee/modify/<%=userId%>"
                                       style="width: 110px;">
                                        <span class="glyphicons plus"></span> 完善个人信息
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/home/panel/panel.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/employee/employee.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/base/user/user.js"></script>
</html>



