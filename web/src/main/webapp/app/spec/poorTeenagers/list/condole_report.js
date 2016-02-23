/**
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.condole.report', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'spec.poorTeenagers'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, CondoleService) {
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
                text: $scope.year + '年各县（市）区慰问次数统计',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left'
            },
            series: [
                {
                    name: '慰问次数(次)',
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

        var moneyPieOption = {
            title: {
                text: $scope.year + '年各县（市）区慰问金额统计',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left'
            },
            series: [
                {
                    name: '慰问金额(元)',
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
        var moneyChart = echarts.init(document.getElementById('moneyPie'));

        $scope.query = function () {
            var promise = CondoleService.analysis({year: $scope.year}, function (data) {
                moneyPieOption.title.text = $scope.year + '年各县（市）区慰问金额统计';
                var legendData = [];
                var moneySeries = [];
                var timesSeries = [];
                angular.forEach(data.data || [], function (o) {
                    legendData.push(o[1]);
                    moneySeries.push({name: o[1], value: o[3]});
                    timesSeries.push({name: o[1], value: o[2]});
                });
                moneyPieOption.legend.data = legendData;
                moneyPieOption.series[0].data = moneySeries;
                moneyChart.setOption(moneyPieOption);


                timesPieOption.title.text = $scope.year + '年各县（市）区慰问次数统计';
                timesPieOption.legend.data = legendData;
                timesPieOption.series[0].data = timesSeries;
                timesChart.setOption(timesPieOption);


            });
            CommonUtils.loading(promise);
        };

        $scope.query();

    });

})(window, angular, jQuery);