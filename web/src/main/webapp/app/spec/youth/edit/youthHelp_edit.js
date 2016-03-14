/**
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.youth.help.edit', [
        'spec.youth',
        'eccrm.angular',
        'eccrm.angularstrap',
        'spec.volunteer',   // 志愿者
        'eccrm.angular.ztree'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, YouthHelpService, VolunteerModal) {

        var pageType = $scope.pageType = $('#pageType').val();
        var id = $('#id').val();
        var youthId = $('#youthId').val();

        var attachmentIds = [];
        // 附件上传
        $scope.uploadOptions = {
            bid: id,
            showUrl: true,
            onSuccess: function (att) {
                attachmentIds.push(att.id);
            }
        };

        var editor = KindEditor.create('#description', {
            uploadJson: CommonUtils.contextPathURL('/attachment/upload2?dataType=jsp'),
            afterUpload: function (url, obj) {
                $scope.$apply(function () {
                    attachmentIds.push(obj.id)
                });
            }
        });

        // 选择志愿者
        $scope.pickVolunteer = function () {
            VolunteerModal.pick(function (data) {
                var volunteerIds = [];
                var volunteerNames = [];
                angular.forEach(data || [], function (o) {
                    volunteerIds.push(o.id);
                    volunteerNames.push(o.name);
                });
                $scope.beans.volunteeIds = volunteerIds.join(',');
                $scope.beans.volunteerNames = volunteerNames.join(' , ');
            });
        };

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
            $scope.beans.attachmentIds = attachmentIds.join(',');
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
            $scope.beans.attachmentIds = attachmentIds.join(',');
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