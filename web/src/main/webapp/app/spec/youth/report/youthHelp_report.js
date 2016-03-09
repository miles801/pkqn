/**
 * 个人的帮扶列表
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.youth.help.report', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'spec.youth'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, YouthService, YouthParam, YouthHelpService) {
        var year = new Date().getUTCFullYear();
        $scope.condition = {
            year: year,
            month: new Date().getUTCMonth() + 1
        };

        $scope.years = [year];
        for (var i = 9; i > 0; i--) {
            $scope.years.push(year - i);
        }

        $scope.month = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];

        $scope.query = function () {
            var promise = YouthHelpService.workReport($scope.condition, function (data) {
                $scope.beans = data.data || [];
            });
            CommonUtils.loading(promise, 'Loading...');
        };


        $scope.beans = [];
        // 导出数据
        $scope.exportData = function () {
            if ($scope.beans.length == 0) {
                AlertFactory.warning('没有需要导出的数据!', '警告!');
                return;
            } else if ($scope.beans.length > 500) {
                AlertFactory.warning('导出的数据量过大，请进行过滤!', '警告!');
                return;
            }
            var param = encodeURI(encodeURI($.param($scope.condition)));
            window.open(CommonUtils.contextPathURL('/spec/youth/help/exportInfo?' + param))
        };

    });

})(window, angular, jQuery);