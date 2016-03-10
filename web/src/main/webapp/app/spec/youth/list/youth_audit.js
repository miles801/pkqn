/**
 * 个人的帮扶列表
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.youth.audit', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'spec.youth'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, YouthService, YouthParam, YouthModal) {
        $scope.condition = {
            noPermission: true
        };

        // 民族
        YouthParam.nation(function (data) {
            $scope.nation = data;
            $scope.nation.unshift({name: '全部'});
        });

        //查询数据
        $scope.query = function () {
            $scope.pager.query();
        };

        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, {start: this.start, limit: this.limit}, $scope.condition);
                if (!param.state) {
                    param.states = ['BLUE_WAIT', 'GRAY_WAIT'];
                }
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


        // 新增
        $scope.add = function () {
            CommonUtils.addTab({
                title: '新增闲散青年',
                url: '/spec/youth/add',
                onUpdate: $scope.query
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

        // 帮扶成功
        $scope.helpSuccess = function (youthId, state) {
            ModalFactory.confirm({
                scope: $scope,
                content: '一旦执行该操作，数据将不可恢复，请确认?',
                callback: function () {
                    var promise = YouthService.success({youthId: youthId}, function () {
                        AlertFactory.success('操作成功!');
                        $scope.pager.load();
                    });
                    CommonUtils.loading(promise);
                }
            });
        };

        // 接触帮扶
        $scope.helpFail = function (youthId, state) {
            ModalFactory.confirm({
                scope: $scope,
                content: '一旦执行该操作，数据将不可恢复，请确认?',
                callback: function () {
                    var promise = YouthService.fail({youthId: youthId}, function () {
                        AlertFactory.success('操作成功!');
                        $scope.pager.load();
                    });
                    CommonUtils.loading(promise);
                }
            });
        };

        $scope.helpBack = function (id, name) {
            YouthModal.back(id, name, $scope.pager.load);
        };

        $scope.viewHelpLog = function (youthId, name) {
            CommonUtils.addTab({
                title: '帮扶记录-' + name,
                url: 'app/spec/youth/edit/youthHelp_view.jsp?pageType=detail&youthId=' + youthId
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
    });

})(window, angular, jQuery);