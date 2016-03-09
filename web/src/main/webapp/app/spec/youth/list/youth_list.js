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
        $scope.condition = {};

        // 拥有配对的权限
        $scope.hasMatchRight = false;
        $http.get(CommonUtils.contextPathURL('/auth/accreditFunc/hasPermission?code=YOUTH_MATCH'))
            .success(function (data) {
                $scope.hasMatchRight = !!data.data;
            });
        // 民族
        YouthParam.nation(function (data) {
            $scope.nation = data;
            $scope.nation.unshift({name: '全部'});
        });

        // 如果不是根节点，则只能查询自己的机构的数据
        var orgId = CommonUtils.loginContext().orgId;
        $scope.isRootOrg = true;
        if (orgId != 1) {
            $scope.condition.orgId = orgId;
            $scope.isRootOrg = false;
        } else {
            $scope.orgOptions = Org.pick(function (o) {
                $scope.condition.orgId = o.id;
                $scope.orgName = o.name;
            });
        }

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

        // 删除或批量删除
        $scope.remove = function (id) {
            ModalFactory.confirm({
                scope: $scope,
                content: '确定要执行【删除】操作吗?',
                callback: function () {
                    var promise = YouthService.deleteByIds({ids: id}, function (data) {
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
                title: '新增闲散青年',
                url: '/spec/youth/add',
                onUpdate: $scope.query
            });
        };

        // 更新
        $scope.modify = function (id) {
            CommonUtils.addTab({
                title: '更新',
                url: '/spec/youth/modify?id=' + id,
                onUpdate: $scope.pager.load
            });
        };

        // 年龄改变
        $scope.ageChange = function () {
            var age = $scope.age;
            var condition = $scope.condition;
            if (age == '') {
                condition.age1 = null;
                condition.age2 = null;
            } else if (age == '1') {
                condition.age1 = 6;
                condition.age2 = 10;
            } else if (age == 2) {
                condition.age1 = 11;
                condition.age2 = 15;
            } else if (age === 3) {
                condition.age1 = 16;
                condition.age2 = 20;
            } else if (age == 4) {
                condition.age1 = 21;
                condition.age2 = 25;
            }
        };
        // 查看
        $scope.view = function (id) {
            CommonUtils.addTab({
                title: '查看',
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

        $scope.matchEmp = function (id, state) {
            // 配对
            if (state == 'RED') {
                EmployeeModal.pickEmployee({}, function (emp) {
                    // 判断是否已经匹配过了
                    var promise = YouthService.matchOwner({
                        id: id,
                        ownerId: emp.id,
                        ownerName: emp.employeeName
                    }, function () {
                        AlertFactory.success('配对成功!');
                        $scope.pager.load();
                    });
                    CommonUtils.loading(promise);
                });
                // 取消配对
            } else if (state == 'YELLOW') {
                ModalFactory.confirm({
                    scope: $scope,
                    content: '一旦取消配对，数据不可恢复，请确认?',
                    callback: function () {
                        var promise = YouthService.clearOwner({id: id}, function () {
                            AlertFactory.success('取消配对成功!');
                            $scope.pager.load();
                        });
                        CommonUtils.loading(promise);
                    }
                });
            } else if (state == 'BLUE') {
                AlertFactory.warning('已经"帮扶成功"的数据不允许修改!');
            } else if (state == 'GRAY') {
                AlertFactory.warning('已经"解除帮扶"的数据不允许修改!');
            }
        };
    });

})(window, angular, jQuery);