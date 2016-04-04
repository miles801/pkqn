/**
 */
(function (window, angular, $) {
    var app = angular.module('spec.member.print', [
        'eccrm.base.employee',
        'eccrm.angular',
        'eccrm.angularstrap'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, EmployeeService, AlertFactory, ModalFactory) {


        var id=CommonUtils.loginContext().id;
        $scope.beans = {};

        var load = function (id, callback) {
            var promise = EmployeeService.get({id: id}, function (data) {
                var foo = data.data || {};
                if (foo.positionCode != 'LDTY') {
                    AlertFactory.error('您不是流动团员，无法打印!');
                    return false;
                }

                $scope.beans = foo;

            });
            CommonUtils.loading(promise);
        };

        $scope.printInfo = function () {
            window.open(CommonUtils.contextPathURL('/app/spec/member/member_print.jsp?show=true'));
        };


        $scope.applyMember = function () {
            ModalFactory.confirm({
                scope: $scope,
                content: '在申请期间无法修改个人信息，请确认!',
                callback: function () {
                    var promise = EmployeeService.applyMember({id: id}, function () {
                        AlertFactory.success('申请成功!');
                        load(id);
                    });
                    CommonUtils.loading(promise);
                }

            });
        };
        load(id);
    });
})(window, angular, jQuery);