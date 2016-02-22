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

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, PoorTeenagersService, PoorTeenagersParam, Org) {

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

        // 加载数据
        $scope.load = function (id) {
            var promise = PoorTeenagersService.get({id: id}, function (data) {
                $scope.beans = data.data || {};
            });
            CommonUtils.loading(promise, 'Loading...');
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