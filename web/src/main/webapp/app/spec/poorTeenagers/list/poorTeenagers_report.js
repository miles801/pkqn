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
                text: $scope.year + '年各县（市）区贫困青少年统计',
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
                    name: '贫困青少年数量(个)',
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
                pieOptions.title.text = $scope.year + '年各县（市）区贫困青少年统计';
                var legendData = [];
                var moneySeries = [];
                var totalPersons = 0;
                angular.forEach(data.data || [], function (o) {
                    legendData.push(o[1]);
                    totalPersons += (o[2] || 0);
                    moneySeries.push({name: o[1], value: o[2]});
                });
                pieOptions.title.subtext = '总计-' + totalPersons + '个';
                pieOptions.legend.data = legendData;
                pieOptions.series[0].data = moneySeries;
                pie.setOption(pieOptions);

            });
            CommonUtils.loading(promise);
        };

        $scope.query();
    });

})(window, angular, jQuery);