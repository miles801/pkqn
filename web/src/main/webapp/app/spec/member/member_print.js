/**
 */
(function (window, angular, $) {
    var app = angular.module('spec.member.print', [
        'eccrm.base.employee',
        'eccrm.angular',
        'eccrm.angularstrap'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, EmployeeService, AlertFactory) {


        $scope.beans = {};

        var load = function (id, callback) {
            var promise = EmployeeService.get({id: id}, function (data) {
                var foo = data.data || {};
                if (foo.positionCode != 'TY') {
                    AlertFactory.error('您不是团员，无法打印!');
                    return false;
                }

                $scope.beans = foo;

            });
            CommonUtils.loading(promise);
        };

        $scope.printInfo = function () {
            window.open(CommonUtils.contextPathURL('/app/spec/member/member_print.jsp?show=true'));
        };

        load(CommonUtils.loginContext().id);
    });
})(window, angular, jQuery);