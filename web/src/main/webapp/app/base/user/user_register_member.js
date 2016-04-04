(function (window, angular, $) {
    var app = angular.module('eccrm.base.user.register', [
        'eccrm.base.user',
        'eccrm.angular',
        'eccrm.base.param',
        'eccrm.angularstrap',
        'eccrm.angular.ztree',
        'base.org'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, User, AlertFactory, Org, ParameterLoader) {

        // 领域
        ParameterLoader.loadBusinessParam('SPEC_LY', function (data) {
            $scope.ly = data || [];
            $scope.ly.unshift({name: '请选择...'});
        });
        // 团组织
        ParameterLoader.loadBusinessParam('SPEC_TZZ', function (data) {
            $scope.tzz = data || [];
            $scope.tzz.unshift({name: '请选择...'});
        });
        $scope.user = {
            position: 'LDTY'   // 流动团员
        };

        // 组织机构
        $scope.orgTree = Org.pick(function (o) {
            $scope.user.deptId = o.id;
            $scope.user.deptName = o.name;
        });


        $scope.ok = function () {
            // 验证密码是否一致
            if ($scope.user.password !== $scope.user.password2) {
                AlertFactory.error(null, '两次输入的密码不一致!请重新输入!');
                return;
            }
            $scope.form.$setValidity('committed', false);
            var beans = angular.extend({}, $scope.user);
            delete beans.password2;
            beans.password = $.md5(beans.password);
            var promise = User.register(beans, function () {
                AlertFactory.success('注册成功!');
            }, function () {
                $scope.form.$setValidity('committed', true);
            });

            CommonUtils.loading(promise);

        };
    });
})(window, angular, jQuery);