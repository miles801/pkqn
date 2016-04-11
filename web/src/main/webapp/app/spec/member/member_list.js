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
    app.controller('Ctrl', function ($scope, EmployeeConstant, OrgTree, CommonUtils, ModalFactory, AlertFactory,
                                     EmployeeService, ParameterLoader, Parameter) {
        $scope.OrgztreeOptions = OrgTree.dynamicTree(function (node) {
            $scope.condition.orgId = node.id;
            $scope.condition.orgName = node.name;
        });

        $scope.years = [{name: '不限制'}];
        var year = new Date().getFullYear();
        for (var i = 0; i < 28; i++) {
            $scope.years.push({name: (year - i) + '年', value: year - i})
        }

        $scope.yearChange = function () {
            $scope.condition.beginWorkDate1 = $scope.year + '-01-01 00:00:00';
            $scope.condition.beginWorkDate2 = ($scope.year + 1) + '-01-01 00:00:00';

        };
        $scope.ages = [
            {name: '所有'},
            {name: '14-18岁', value: '1'},
            {name: '19-23岁', value: '2'},
            {name: '24-28岁', value: '3'},
            {name: '28岁以上', value: '4'}
        ];
        $scope.changeAge = function () {
            var age = $scope.age;
            var condition = $scope.condition;
            if (!age) {
                condition.age1 = null;
                condition.age2 = null;
            } else if (age == '1') {
                condition.age1 = 14;
                condition.age2 = 18;
            } else if (age == '2') {
                condition.age1 = 19;
                condition.age2 = 23;
            } else if (age == '3') {
                condition.age1 = 24;
                condition.age2 = 28;
            } else if (age == '4') {
                condition.age1 = 28;
            }

        };
        $scope.ly2 = [{name: '全部'}];

        $scope.condition = {
            orderBy: 'createdDatetime',
            reverse: 'false',
            positionCode: 'TY'
        };

        // 领域
        ParameterLoader.loadBusinessParam('SPEC_LY', function (data) {
            $scope.ly = data || [];
            $scope.ly.unshift({name: '全部'});
        });

        // 荣誉称号
        ParameterLoader.loadBusinessParam('SPEC_HONOR', function (data) {
            $scope.honor = data || [];
            $scope.honor.unshift({name: '全部'});
        });

        // 领域改变时，加载子领域
        $scope.lyChange = function (ly2) {
            $scope.ly2.length = 1;
            $scope.condition.ly2 = ly2 || '';
            if ($scope.condition.ly) {
                Parameter.fetchBusinessCascade('SPEC_LY', $scope.condition.ly, function (data) {
                    $scope.ly2 = data.data || [];
                    $scope.ly2.unshift({name: '全部', value: ''});
                });
            }
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

        $scope.remove = function (id) {
            ModalFactory.confirm({
                scope: $scope,
                content: '团员信息一旦删除后，将不可恢复，请确认!',
                callback: function () {
                    var promise = EmployeeService.deleteByIds({ids: id}, function () {
                        AlertFactory.success('删除成功!');
                        $scope.query();
                    });
                    CommonUtils.loading(promise);
                }
            });

        };

        $scope.clear = function () {
            ModalFactory.confirm({
                scope: $scope,
                content: '<span style="color: #ff0000;font-size: 14px;">重置年龄并清理数据!</span><br/>清理后年龄不在团员范围内的团员将会被移到“非团员青年”中，请确认!',
                callback: function () {
                    var promise = EmployeeService.clear(function (data) {
                        AlertFactory.success('清理成功，一共清理了' + (data.data || 0) + '条信息!');
                        $scope.query();
                    });
                    CommonUtils.loading(promise);
                }
            })
        };


        // 导出信息
        $scope.exportData = function () {
            if (!$scope.pager.total) {
                AlertFactory.error('没有匹配的数据，请重新设置查询条件并进行查询操作!');
                return;
            }
            var param = $.param(angular.extend({}, $scope.condition));
            param = encodeURI(encodeURI(param));
            window.open(CommonUtils.contextPathURL('/base/employee/export-ty?' + param))
        };

    });
})(window, angular, jQuery);