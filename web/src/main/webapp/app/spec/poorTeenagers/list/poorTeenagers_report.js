/**
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.poorTeenagers.report', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'spec.poorTeenagers'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, PoorTeenagersService) {
        var years = [];
        var now = new Date().getFullYear();
        for (i = 0; i < 20; i++) {
            years.push(now - i);
        }
        $scope.years = years;
        $scope.year = now;

        var pieOptions = {
            title: {
                text: $scope.year + '年各区县贫困青年统计',
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
                    name: '贫困青年数量(个)',
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
        var pie = echarts.init(document.getElementById('pie'));

        $scope.query = function () {
            var promise = PoorTeenagersService.analysis({year: $scope.year}, function (data) {
                pieOptions.title.text = $scope.year + '年各区县贫困青年统计';
                var legendData = [];
                var moneySeries = [];
                angular.forEach(data.data || [], function (o) {
                    legendData.push(o[1]);
                    moneySeries.push({name: o[1], value: o[2]});
                });
                pieOptions.legend.data = legendData;
                pieOptions.series[0].data = moneySeries;
                pie.setOption(pieOptions);

            });
            CommonUtils.loading(promise);
        };

        $scope.query();
    });

})(window, angular, jQuery);