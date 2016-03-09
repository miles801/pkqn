/**
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.youth.help.view2', [
        'spec.youth',
        'base.org',
        'eccrm.angular',
        'eccrm.angularstrap'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, YouthHelpService) {

        var id = $('#id').val();
        if (!id) {
            AlertFactory.error('错误的访问!没有获得ID!');
            CommonUtils.delay(function () {
                CommonUtils.back();
            }, 3000);
            return;
        }


        // 查询历史帮扶记录
        var load = function () {
            var promise = YouthHelpService.get({id: id}, function (data) {
                $scope.beans = data.data;
                $('#content').html($scope.beans.content);
            });
            CommonUtils.loading(promise);
        };

        load();

    });
})(window, angular, jQuery);