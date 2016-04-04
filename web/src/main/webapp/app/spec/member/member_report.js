/**
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.member.report', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.base.employee'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, EmployeeService) {

        var years = [];
        var now = new Date().getFullYear();
        for (i = 0; i < 20; i++) {
            years.push(now - i);
        }
        $scope.years = years;
        $scope.year = now;

        var timesChart = echarts.init(document.getElementById('timesPie'));
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
        };

        $scope.query();
    });

})(window, angular, jQuery);