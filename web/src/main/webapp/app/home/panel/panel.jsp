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
    <script type="text/javascript" src="<%=contextPath%>/vendor/jquery-v1.8.3/jquery.md5.js" ></script >

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

                    <p>工号：{{beans.employeeCode}}</p>

                    <p>职位：{{beans.post}}</p>

                    <p>职务：{{beans.duty}}</p>

                    <p>上次登录时间：{{beans.lastLoginTime | eccrmDatetime}}</p>
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
                                    <a type="button" class="btn btn-blue" ng-click="updatePwd()">
                                        <span class="glyphicons plus"></span> 更改密码
                                    </a>
                                    <a type="button" class="btn btn-blue"
                                       href="<%=contextPath%>/base/employee/modify/<%=userId%>">
                                        <span class="glyphicons plus"></span> 档案信息
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
    <%-- 待办案件 --%>
    <div class="row" style="margin-top:15px;border: none;">
    </div>
    <%-- 待跟踪案件 --%>
    <div class="row" style="margin-top:15px;">
        <%--<iframe style="height: 250px;width: 100%;padding: 0;margin: 0;border: 0;overflow: hidden;" frameborder="no"
                src="<%=contextPath%>/app/case/panel/waitTrace_list.jsp"></iframe>--%>
    </div>
</div>
</body>
<script type="text/javascript">

    function formatMD(val, length) {
        length = length < 0 ? 0 : length;
        var pre = '';
        for (var i = 0; i < length; i++) {
            pre += '0';
        }
        val = pre + val;
        return val.substr(val.length - length);
    }

    var t = null;
    t = setTimeout(time, 1000);
    function time() {
        clearTimeout(t);
        dt = new Date();
        var h = dt.getHours();
        var m = dt.getMinutes();
        var s = dt.getSeconds();
        if(parseInt(h)<10){
            h = '0'+h;
        }
        if(parseInt(m)<10){
            m = '0'+m;
        }
        if(parseInt(s)<10){
            s = '0'+s;
        }
        document.getElementById("time_part").innerHTML = +formatMD(h, 2) + ':' + formatMD(m, 2) + ' ' + '<span id="second_part">' + formatMD(s, 2) + '</span>';
        t = setTimeout(time, 1000);
    }


    var myDate = new Date();
    var Week = ['日', '一', '二', '三', '四', '五', '六'];
    var weekdate = '';
    var year = myDate.getFullYear();
    var month = myDate.getMonth() + 1;
    var day = myDate.getDate();
    weekdate += ' 星期' + Week[myDate.getDay()];

    document.getElementById("week_part").innerHTML = weekdate;
    document.getElementById("date_part").innerHTML = year + "年" + formatMD(month, 2) + "月" + formatMD(day, 2) + "日";
</script>
<script type="text/javascript" src="<%=contextPath%>/app/home/panel/panel.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/employee/employee.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/base/user/user.js" ></script >
</html>



