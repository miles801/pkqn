/**
 * Created by chenl on 2014-10-15 14:47:37.
 */
(function (window, angular, $) {
    var app = angular.module('spec.member.list', [
        'eccrm.base.employee',
        'eccrm.angular',
        'base.org',
        'eccrm.angularstrap',
        'eccrm.angular.ztree'
    ]);
    app.controller('Ctrl', function ($scope, EmployeeConstant, OrgTree, CommonUtils, ModalFactory, AlertFactory, $window, Debounce, EmployeeService) {
        $scope.OrgztreeOptions = OrgTree.dynamicTree(function (node) {
            $scope.condition.organization = {id: node.id, name: node.name};
        });
        // 职务
        EmployeeConstant.duty(function (data) {
            $scope.dutys = data;
        });
        // 状态
        EmployeeConstant.status(function (data) {
            $scope.EmpStatus = data;
        });
        $scope.condition = {
            orderBy: 'createdDatetime',
            reverse: 'false',
            status:'1',
            positionCode: 'LDTY'
        };


        $scope.modify = function (id) {
            CommonUtils.addTab({
                title: '更新团员',
                url: '/app/spec/member/member_edit.jsp?pageType=modify&id=' + id,
                onUpdate: $scope.pager.load
            });

        };
        $scope.detail = function (id) {
            CommonUtils.addTab({
                title: '查看团员',
                url: '/app/spec/member/member_edit.jsp?pageType=detail&id=' + id
            });
        };
        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, $scope.condition, {start: this.start, limit: this.limit});
                return CommonUtils.promise(function (defer) {
                    var promise = EmployeeService.permissionPageQuery(param, function (data) {
                        data = data.data || {total: 0, data: []};
                        $scope.employees = data;
                        defer.resolve(data);
                    });
                    CommonUtils.loading(promise);
                });
            },
            finishInit: function () {
                this.load();
            }
        };
        $scope.query = function () {
            $scope.pager.query();
        };

        $scope.accept = function (id) {
            ModalFactory.confirm({
                scope: $scope,
                content: '<span style="color: #08B708;font-size: 14px;">审核通过，请确认该操作!</span>',
                callback: function () {
                    var promise = EmployeeService.applyAccept({id: id}, function () {
                        AlertFactory.success('审核成功!');
                        $scope.query();
                    });
                    CommonUtils.loading(promise);
                }
            });

        }

        $scope.deny = function (id) {
            ModalFactory.confirm({
                scope: $scope,
                content: '<span style="color: #ff0000;font-size: 14px;">审核不通过，请确认该操作!</span>',
                callback: function () {
                    var promise = EmployeeService.applyDeny({id:id},function (data) {
                        AlertFactory.success('审核成功!');
                        $scope.query();
                    });
                    CommonUtils.loading(promise);
                }
            })
        };

    });
})(window, angular, jQuery);