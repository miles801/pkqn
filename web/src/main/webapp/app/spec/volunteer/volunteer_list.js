/**
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.volunteer.list', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'spec.volunteer'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, VolunteerService) {
        $scope.condition = {};

        //查询数据
        $scope.query = function () {
            $scope.pager.query();
        };

        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, {start: this.start, limit: this.limit}, $scope.condition);
                $scope.beans = [];
                return CommonUtils.promise(function (defer) {
                    var promise = VolunteerService.pageQuery(param, function (data) {
                        param = null;
                        $scope.beans = data.data || {total: 0};
                        defer.resolve($scope.beans);
                    });
                    CommonUtils.loading(promise, 'Loading...');
                });
            }
        };

        // 删除或批量删除
        $scope.remove = function (id) {
            ModalFactory.confirm({
                scope: $scope,
                content: '确定要执行【删除】操作吗?',
                callback: function () {
                    var promise = VolunteerService.deleteByIds({ids: id}, function (data) {
                        if (data.success) {
                            AlertFactory.success(null, '删除成功!');
                            $scope.query();
                        }
                    });
                    CommonUtils.loading(promise);
                }
            });
        };

        // 新增
        $scope.add = function () {
            CommonUtils.addTab({
                title: '新增志愿者',
                url: '/spec/volunteer/add',
                onUpdate: $scope.query
            });
        };

        // 更新
        $scope.modify = function (id) {
            CommonUtils.addTab({
                title: '更新志愿者',
                url: '/spec/volunteer/modify?id=' + id,
                onUpdate: $scope.pager.load
            });
        };

        // 查看
        $scope.view = function (id) {
            CommonUtils.addTab({
                title: '查看志愿者',
                url: '/spec/volunteer/detail?id=' + id
            });
        };


    });

})(window, angular, jQuery);