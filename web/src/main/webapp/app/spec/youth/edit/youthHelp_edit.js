/**
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.youth.help.edit', [
        'spec.youth',
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.angular.ztree'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, YouthHelpService) {

        var pageType = $scope.pageType = $('#pageType').val();
        var id = $('#id').val();
        var youthId = $('#youthId').val();

        // 附件上传
        $scope.uploadOptions = {
            bid: id,
            showUrl: true
        };

        var editor = KindEditor.create('#description')

        var load = function (callback) {
            var promise = YouthHelpService.get({id: id}, function (data) {
                $scope.beans = data.data || {};
                editor.html($scope.beans.description);
                if (angular.isFunction(callback)) {
                    callback();
                }
            });
            CommonUtils.loading(promise);
        };

        $scope.beans = {};
        $scope.back = CommonUtils.back;

        // 保存
        $scope.save = function () {
            $scope.form.$setValidity('committed', false);
            var ids = $scope.uploadOptions.getAttachment();
            $scope.beans.attachmentIds = ids.join(',');
            $scope.beans.content = editor.html();
            var promise = YouthHelpService.save($scope.beans, function (data) {
                AlertFactory.success('保存成功!');
                CommonUtils.addTab('update');
                CommonUtils.delay(function () {
                    CommonUtils.back();
                }, 2000);
            });
            CommonUtils.loading(promise, '保存中...');
        };

        // 更新
        $scope.update = function () {
            var ids = $scope.uploadOptions.getAttachment();
            $scope.beans.attachmentIds = ids.join(',');
            $scope.beans.content = editor.html();
            var promise = YouthHelpService.update($scope.beans, function (data) {
                AlertFactory.success(null, '更新成功!');
                $scope.form.$setValidity('committed', false);
                CommonUtils.addTab('update');
                CommonUtils.delay(CommonUtils.back, 3000);
            });
            CommonUtils.loading(promise, '更新中...');
        };


        if (pageType == 'add') {
            $scope.beans.youthId = youthId;
            $scope.beans.occurDate = new Date().getTime();
        } else if (pageType == 'modify') {
            load();
        } else if (pageType == 'detail') {
            $scope.uploadOptions.readonly = true;
            load(function () {
                $('input,textarea,select').attr('disabled', 'disabled');
            });
        } else {
            AlertFactory.error($scope, '错误的页面类型');
        }
    });
})(window, angular, jQuery);