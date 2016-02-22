/**
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.poorTeenagers.list', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.angular.ztree',
        'base.org',
        'spec.poorTeenagers'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, PoorTeenagersService, Org) {
        $scope.condition = {};

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
                    var promise = PoorTeenagersService.pageQuery(param, function (data) {
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
                    var promise = PoorTeenagersService.deleteByIds({ids: id}, function () {
                        AlertFactory.success(null, '删除成功!');
                        $scope.query();
                    });
                    CommonUtils.loading(promise);
                }
            });
        };

        // 新增
        $scope.add = function () {
            CommonUtils.addTab({
                title: '新增',
                url: '/spec/poorTeenagers/add',
                onUpdate: $scope.query
            });
        };

        // 更新
        $scope.modify = function (id) {
            CommonUtils.addTab({
                title: '更新',
                url: '/spec/poorTeenagers/modify?id=' + id,
                onUpdate: $scope.query
            });
        };

        // 添加慰问记录
        $scope.moneyChange = function () {
            var money = $scope.money;
            var condition = $scope.condition;
            if (money == '') {
                condition.condoleTimes = null;
                condition.minMoney = null;
                condition.maxMoney = null;
            } else if (money == '1') {
                condition.condoleTimes = 0;
                condition.minMoney = null;
                condition.maxMoney = null;
            } else if (money == 2) {
                condition.condoleTimes = null;
                condition.minMoney = null;
                condition.maxMoney = 1000;
            } else if (money === 3) {
                condition.condoleTimes = null;
                condition.minMoney = 1000;
                condition.maxMoney = 2000;
            } else if (money == 4) {
                condition.condoleTimes = null;
                condition.minMoney = 2000;
                condition.maxMoney = null;
            }
        };
        // 查看
        $scope.view = function (id) {
            CommonUtils.addTab({
                title: '查看',
                url: '/spec/poorTeenagers/detail?id=' + id
            });
        };


        // 导出数据
        $scope.exportData = function () {
            var param = $.param(angular.extend({}, $scope.condition));
            param = encodeURI(encodeURI(param));
            window.open(CommonUtils.contextPathURL('/spec/poorTeenagers/export?' + param))
        };
    });

})(window, angular, jQuery);