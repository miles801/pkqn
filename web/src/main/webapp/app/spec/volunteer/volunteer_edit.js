/**
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.volunteer.edit', [
        'spec.volunteer',
        'eccrm.angular',
        'eccrm.angular.ztree',
        'base.org',
        'eccrm.angularstrap'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory,
                                     VolunteerService, VolunteerParam, Org) {

        var pageType = $scope.pageType = $('#pageType').val();
        var id = $('#id').val();

        $scope.orgTree = Org.pick(function (o) {
            $scope.beans.orgId = o.id;
            $scope.beans.orgName = o.name;
        });

        $scope.clearOrg = function () {
            $scope.beans.orgId = null;
            $scope.beans.orgName = null;
        };

        // 加载性别
        VolunteerParam.sex(function (data) {
            $scope.sex = data;
            $scope.sex.unshift({name: '请选择'});
        });


        $scope.beans = {};
        // 如果不是根节点，则只能查询自己的机构的数据

        $scope.back = CommonUtils.back;

        // 保存
        $scope.save = function (createNew) {
            $scope.form.$setValidity('committed', false);
            var promise = VolunteerService.save($scope.beans, function (data) {
                AlertFactory.success('保存成功!');
                if (createNew === true) {
                    $scope.beans = {};
                    $scope.beans.sex = 'BP_MAN';
                    var loginContext = CommonUtils.loginContext();
                    $scope.beans.ownerId = loginContext.id;
                    $scope.beans.ownerName = loginContext.employeeName;
                    $scope.beans.orgId = loginContext.orgId;
                    $scope.beans.orgName = loginContext.orgName;
                    $scope.form.$setValidity('committed', true);
                }
                CommonUtils.addTab('update');
            });
            CommonUtils.loading(promise, '保存中...');
        };

        // 更新
        $scope.update = function () {
            var promise = VolunteerService.update($scope.beans, function (data) {
                AlertFactory.success(null, '更新成功!');
                $scope.form.$setValidity('committed', false);
                CommonUtils.addTab('update');
                CommonUtils.delay(CommonUtils.back, 3000);
            });
            CommonUtils.loading(promise, '更新中...');
        };

        // 加载数据
        $scope.load = function (id, callback) {
            // 明细
            var promise = VolunteerService.get({id: id}, function (data) {
                $scope.beans = data.data || {};
                // 回调
                if (angular.isFunction(callback)) {
                    callback();
                }
            });
            CommonUtils.loading(promise, 'Loading...');

        };


        if (pageType == 'add') {
            $scope.beans.sex = 'BP_MAN';
            var loginContext = CommonUtils.loginContext();
            $scope.beans.ownerId = loginContext.id;
            $scope.beans.ownerName = loginContext.employeeName;
            $scope.beans.orgId = loginContext.orgId;
            $scope.beans.orgName = loginContext.orgName;
        } else if (pageType == 'modify') {
            $scope.load(id);
        } else if (pageType == 'detail') {
            $scope.load(id, function () {
                $('input,textarea,select').attr('disabled', 'disabled');
            });
        } else {
            AlertFactory.error($scope, '错误的页面类型');
        }

    });
})(window, angular, jQuery);