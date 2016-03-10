/**
 * 团干部
 */
(function (window, angular, $) {
    var app = angular.module('eccrm.base.employee.list', [
        'eccrm.base.employee',
        'eccrm.angular',
        'base.org',
        'eccrm.directive.ztree',
        'eccrm.angularstrap'
    ]);
    app.controller('EmployeeListController', function ($scope, EmployeeConstant, OrgTree, CommonUtils, ModalFactory, AlertFactory, $window, Debounce, EmployeeService) {
        $scope.condition = {
            permission: true,
            orderBy: 'createdDatetime',
            reverse: 'false'
        };

        $scope.modify = function (id) {
            CommonUtils.addTab({
                title: '更新团干部',
                url: '/base/employee/modify/' + id,
                onUpdate: $scope.pager.load
            });
        };

        $scope.detail = function (id) {
            CommonUtils.addTab({
                title: '查看团干部',
                url: '/base/employee/detail/' + id
            });
        };

        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, $scope.condition, {start: this.start, limit: this.limit});
                return CommonUtils.promise(function (defer) {
                    var promise = EmployeeService.permissionPageQuery(param);
                    CommonUtils.loading(promise, '加载员工列表...', function (data) {
                        data = data.data || {total: 0, data: []};
                        $scope.employees = data;
                        defer.resolve(data);
                    });
                });
            },
            finishInit: function () {
                this.load();
            }
        };
        $scope.query = function () {
            $scope.pager.query();
        };

    });
})(window, angular, jQuery);