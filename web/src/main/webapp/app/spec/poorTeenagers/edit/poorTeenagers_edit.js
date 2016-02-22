/**
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.poorTeenagers.edit', [
        'spec.poorTeenagers',
        'base.org',
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.angular.ztree'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, PoorTeenagersService,
                                     PoorTeenagersParam, Org, CondoleService, CondoleModal) {

        var pageType = $('#pageType').val();
        var id = $('#id').val();

        // 加载性别
        PoorTeenagersParam.sex(function (data) {
            $scope.sex = data;
            $scope.sex.unshift({name: '请选择'});
        });
        // 加载民族
        PoorTeenagersParam.nation(function (data) {
            $scope.nation = data;
            $scope.nation.unshift({name: '请选择'});
        });
        // 加载政治面貌
        PoorTeenagersParam.zzmm(function (data) {
            $scope.zzmm = data;
            $scope.zzmm.unshift({name: '请选择'});
        });
        // 加载健康状况
        PoorTeenagersParam.health(function (data) {
            $scope.health = data;
            $scope.health.unshift({name: '请选择'});
        });
        // 加载家庭年收入
        PoorTeenagersParam.income(function (data) {
            $scope.income = data;
            $scope.income.unshift({name: '请选择'});
        });

        $scope.beans = {};
        // 如果不是根节点，则只能查询自己的机构的数据
        var orgId = CommonUtils.loginContext().orgId;
        if (orgId != 1) {
            $scope.beans.orgId = orgId;
            $scope.beans.orgName = CommonUtils.loginContext().orgName;
            $('#orgId').children().attr('disabled', 'disabled');
        }
        $scope.orgOptions = Org.pick(function (o) {
            $scope.beans.orgId = o.id;
            $scope.beans.orgName = o.name;
        });

        $scope.back = CommonUtils.back;

        // 保存
        $scope.save = function (createNew) {
            var promise = PoorTeenagersService.save($scope.beans, function (data) {
                if (data && data['success'] == true) { //保存成功
                    AlertFactory.success(null, '保存成功!');
                    if (createNew === true) {
                        $scope.beans = {};
                    } else {
                        $scope.form.$setValidity('committed', false);
                    }
                } else {
                    AlertFactory.saveError($scope, data);
                }
            });
            CommonUtils.loading(promise, '保存中...');
        };

        // 更新
        $scope.update = function () {
            var promise = PoorTeenagersService.update($scope.beans, function (data) {
                if (data && data['success'] == true) { // 更新成功
                    AlertFactory.success(null, '更新成功!');
                    $scope.form.$setValidity('committed', false);
                    CommonUtils.back();
                } else {
                    AlertFactory.updateError($scope, data);
                }
            });
            CommonUtils.loading(promise, '更新中...');
        };

        // 慰问记录
        var loadCondole = function () {
            var promise = CondoleService.queryByTeenager({teenagerId: id}, function (data) {
                $scope.condoles = data.data || [];
            });
            CommonUtils.loading(promise);
        };

        // 加载数据
        $scope.load = function (id) {
            // 明细
            var promise = PoorTeenagersService.get({id: id}, function (data) {
                $scope.beans = data.data || {};
            });
            CommonUtils.loading(promise, 'Loading...');

            // 慰问记录
            loadCondole();

        };


        $scope.addCondole = function () {
            CondoleModal.add(id, $scope.beans.name, function () {
                AlertFactory.success(null, '添加成功!');
                loadCondole();
            })
        };

        $scope.modifyCondole = function (condoleId) {
            CondoleModal.modify(condoleId, function () {
                AlertFactory.success(null, '更新成功!');
                loadCondole();
            })
        };

        $scope.viewCondole = function (condoleId) {
            CondoleModal.view(condoleId)
        };

        // 删除慰问记录
        $scope.removeCondole = function (condoleId) {
            ModalFactory.confirm({
                scope: $scope,
                content: '数据删除后将不可恢复，确定删除？',
                callback: function () {
                    var promise = CondoleService.deleteByIds({ids: condoleId}, function () {
                        AlertFactory.success(null, '删除成功!');
                        loadCondole();
                    });
                    CommonUtils.loading(promise);
                }
            });
        };

        if (pageType == 'add') {
            $scope.beans.sex = 'BP_MAN';
        } else if (pageType == 'modify') {
            $scope.load(id);
        } else if (pageType == 'detail') {
            $scope.load(id);
            $('input,textarea,select').attr('disabled', 'disabled');
        } else {
            AlertFactory.error($scope, '错误的页面类型');
        }

    });
})(window, angular, jQuery);