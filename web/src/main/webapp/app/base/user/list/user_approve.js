/**
 * Created by shenbb on 2014-02-01 16:45:10.
 */
(function (window, angular, $) {
    var app = angular.module('eccrm.base.user.list', [
        'eccrm.base.user',
        'eccrm.angular',
        'eccrm.angularstrap'
    ]);
    app.controller('UserListController', function ($window, $scope, User, CommonUtils, UserConstant, AlertFactory, ModalFactory) {
        //定义变量
        UserConstant.status(function (data) {
            $scope.userStatus = data;
            $scope.userStatus.unshift({value: undefined, name: '全部'});
        });
        UserConstant.type(function (data) {
            $scope.userType = data;
            $scope.userType.unshift({value: undefined, name: '全部'});
        });

        var defaults = {
            orderBy: 'code',
            reverse: false,
            positionCode:'NORMAL_MANAGER',
            status: 'PAUSE'
        };

        // 重置查询条件
        $scope.reset = function () {
            $scope.condition = angular.extend({}, defaults);
        };
        $scope.reset();
        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, $scope.condition, {start: this.start, limit: this.limit});
                return CommonUtils.promise(function (defer) {
                    var result = User.query(param, function (data) {
                        data = data.data || {total: 0};
                        $scope.users = data;
                        defer.resolve(data);
                    });
                    CommonUtils.loading(result, '加载用户列表...');
                });
            },
            finishInit: function () {
                this.query();
            }
        };


        // 查询
        $scope.query = function () {
            $scope.pager.query();
        };


        /**
         * 审核通过
         */
        $scope.approveOk = function (id) {//删除
            ModalFactory.confirm({
                scope: $scope,
                content: '请仔细核查信息，【审批通过】后，该用户将可以登录当前系统!，请确认!',
                callback: function () {
                    var promise = User.approveOk({ids: id}, function () {
                        AlertFactory.success('审批成功!');
                        $scope.query();
                    });
                    CommonUtils.loading(promise);
                }
            });
        };
        /**
         * 审核不通过
         */
        $scope.approveDeny = function (id) {//删除
            ModalFactory.confirm({
                scope: $scope,
                content: '请仔细核查信息，【审批拒绝】后，该用户将将无法登录系统!，请确认!',
                callback: function () {
                    var promise = User.approveDeny({ids: id}, function () {
                        AlertFactory.success('审批成功!');
                        $scope.query();
                    });
                    CommonUtils.loading(promise);
                }
            });
        };


        /**
         * 用户明细
         * @param id
         */
        $scope.detail = function (empId) {
            CommonUtils.addTab({
                title: '用户明细',
                url: '/base/employee/detail/' + empId
            });
        };
    });
})(window, angular, jQuery);
