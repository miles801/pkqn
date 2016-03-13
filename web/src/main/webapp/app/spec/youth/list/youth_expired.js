/**
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.youth.list', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'base.org',
        'eccrm.base.employee.modal',
        'spec.youth'
    ]);
    app.controller('Ctrl', function ($scope, $http, CommonUtils, AlertFactory, ModalFactory, YouthService, YouthParam, Org, EmployeeModal) {
        $scope.condition = {
            expired: true
        };

        // 民族
        YouthParam.nation(function (data) {
            $scope.nation = data;
            $scope.nation.unshift({name: '全部'});
        });

        $scope.orgOptions = Org.pick(function (o) {
            $scope.condition.orgId = o.id;
            $scope.orgName = o.name;
        });

        $scope.clearOrg = function () {
            $scope.condition.orgId = null;
            $scope.orgName = null;
        };
        //查询数据
        $scope.query = function () {
            $scope.pager.query();
        };

        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, {start: this.start, limit: this.limit}, $scope.condition);
                $scope.beans = [];
                return CommonUtils.promise(function (defer) {
                    var promise = YouthService.pageQuery(param, function (data) {
                        param = null;
                        $scope.beans = data.data || {total: 0};
                        defer.resolve($scope.beans);
                    });
                    CommonUtils.loading(promise, 'Loading...');
                });
            }
        };


        // 查看
        $scope.view = function (id, name) {
            CommonUtils.addTab({
                title: '查看-' + name,
                url: '/spec/youth/detail?id=' + id
            });
        };


        // 导出数据
        $scope.exportData = function () {
            if ($scope.pager.total == 0) {
                AlertFactory.warning('没有需要导出的数据!', '警告!');
                return;
            } else if ($scope.pager.total > 500) {
                AlertFactory.warning('导出的数据量过大，请进行过滤!', '警告!');
                return;
            }
            var param = $.param(angular.extend({}, $scope.condition));
            param = encodeURI(encodeURI(param));
            window.open(CommonUtils.contextPathURL('/spec/youth/export?' + param))
        };
        // 导出信息
        $scope.exportInfo = function (id) {
            window.open(CommonUtils.contextPathURL('/spec/youth/exportInfo?id=' + id))
        };

    });

})(window, angular, jQuery);