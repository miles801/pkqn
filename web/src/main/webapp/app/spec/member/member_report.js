/**
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.member.report', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.base.employee',
        'eccrm.base.param'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, EmployeeService, ParameterLoader) {

        var years = [];
        var now = new Date().getFullYear();
        for (i = 0; i < 20; i++) {
            years.push(now - i);
        }
        $scope.years = years;
        $scope.year = now;

        var timesChart = echarts.init(document.getElementById('timesPie'));
        var ldtyChart = echarts.init(document.getElementById('ldtyChart'));
        var sexChart = echarts.init(document.getElementById('sexChart'));
        var ageChart = echarts.init(document.getElementById('ageChart'));
        var lyChart = echarts.init(document.getElementById('lyChart'));
        var timesPieOption = {
            title: {
                text: '各县（市）区团员统计',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                x: 'right',
                y: 'bottom',
                orient: 'vertical',
                show: false
            },
            series: [
                {
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        var ldtyOption = $.extend(true, {}, timesPieOption);
        ldtyOption.title.text = '各县（市）区流动团员统计';
        var sexOption = $.extend(true, {}, timesPieOption);
        sexOption.title.text = '团员男女比例统计';
        var ageOption = $.extend(true, {}, timesPieOption);
        ageOption.title.text = '团员年龄段比例统计';
        var lyOption = $.extend(true, {}, timesPieOption);
        lyOption.title.text = '各领域团员比例统计';

        var lyDefer = CommonUtils.defer();
        ParameterLoader.loadBusinessParam('SPEC_LY', function (data) {
            lyDefer.resolve(data);
        });
        $scope.query = function () {
            var promise = EmployeeService.memberAnalysis(function (data) {
                var legendData = [];
                var timesSeries = [];
                var total = 0;
                angular.forEach(data.data || [], function (o) {
                    legendData.push(o[1]);
                    total += (o[2]);
                    timesSeries.push({name: o[1], value: o[2]});
                });
                timesPieOption.legend.data = legendData;
                timesPieOption.series[0].data = timesSeries;
                timesPieOption.title.subtext = '共计' + total + '个团员';
                timesChart.setOption(timesPieOption);

            });
            CommonUtils.loading(promise);

            var promise2 = EmployeeService.memberAnalysis2(function (data) {
                var legendData = [];
                var series = [];
                var total = 0;
                angular.forEach(data.data || [], function (o) {
                    legendData.push(o[1]);
                    total += (o[2]);
                    series.push({name: o[1], value: o[2]});
                });
                ldtyOption.legend.data = legendData;
                ldtyOption.series[0].data = series;
                ldtyOption.title.subtext = '共计' + total + '个团员';
                ldtyChart.setOption(ldtyOption);
            });
            CommonUtils.loading(promise2);

            var promise3 = EmployeeService.memberAnalysisSex(function (data) {
                var legendData = [];
                var series = [];
                var total = 0;
                angular.forEach(data.data || [], function (o) {
                    var sex = o[0];
                    var name;
                    if (!sex) {
                        name = '未设置';
                    } else if (sex == 'BP_MAN') {
                        name = '男';
                    } else {
                        name = '女';
                    }
                    legendData.push(name);
                    total += (o[1]);
                    series.push({name: name, value: o[1]});
                });
                sexOption.legend.data = legendData;
                sexOption.series[0].data = series;
                sexOption.title.subtext = '共计' + total + '个团员';
                sexChart.setOption(sexOption);
            });
            CommonUtils.loading(promise3);


            var promise4 = EmployeeService.memberAnalysisAge(function (data) {
                var legendData = ['14-18岁', '19-23岁', '24-28岁', '28岁以上'];
                var series = [];
                var total = 0;
                angular.forEach(data.data || [], function (o) {
                    total += (o[0]);
                    total += (o[1]);
                    total += (o[2]);
                    total += (o[3]);
                    series.push({name: legendData[0], value: o[0]});
                    series.push({name: legendData[1], value: o[1]});
                    series.push({name: legendData[2], value: o[2]});
                    series.push({name: legendData[3], value: o[3]});
                });
                ageOption.legend.data = legendData;
                ageOption.series[0].data = series;
                ageOption.title.subtext = '共计' + total + '个团员';
                ageChart.setOption(ageOption);
            });
            CommonUtils.loading(promise4);


            var promise5 = EmployeeService.memberAnalysisLY(function (data) {
                var legendData = [];
                var series = [];
                var total = 0;
                lyDefer.promise.then(function (ly) {
                    angular.forEach(data.data || [], function (o) {
                        total += (o[1]);
                        for (var i = 0; i < ly.length; i++) {
                            if (ly[i].value == o[0]) {
                                legendData.push(ly[i].name);
                                series.push({name: ly[i].name, value: o[1]});
                                break;
                            }
                        }
                    });
                    lyOption.legend.data = legendData;
                    lyOption.series[0].data = series;
                    lyOption.title.subtext = '共计' + total + '个团员';
                    lyChart.setOption(lyOption);
                });
            });
            CommonUtils.loading(promise5);

        };


        $scope.query();
    });

})(window, angular, jQuery);