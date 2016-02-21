(function (window, angular, $) {
    //获取模块
    var app = angular.module("eccrm.panel.base.list", [
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.base.employee',
        'eccrm.base.user'
    ]);


    //
    app.controller('BaseCtrl', function (User, EmployeeService, $scope, $modal, CommonUtils, AlertFactory) {
        $scope.beans = {};
        $scope.isShow = false;
        var id = $('#userId').val();
        EmployeeService.get({id: id}, function (data) {
            data = data.data || {};
            $scope.beans = data;
            var imageId = data.imageId;
            if (imageId) {
                $('#imageId').html('<img src="' + CommonUtils.contextPathURL('/attachment/view?id=' + imageId) + '" width="150" height="180">');
            } else {
                $('#imageId').html('<img src="' + CommonUtils.contextPathURL('/style/standard/images/default_tx.png') + '" width="150" height="180">');
            }
        });

        var modal;
        // 修改密码
        $scope.updatePwd = function () {
            modal = $modal({
                scope: $scope,
                template: CommonUtils.contextPathURL('/app/case/panel/update_pwd.html')
            });

        };

        $scope.checkPassword = function () {
            User.checkPassword({password: $.md5($scope.beans.password)}, function (data) {
                if (data.data == false) {
                    AlertFactory.error($scope, null, '原始密码输入错误，请重新输入！');
                } else {
                    $scope.updatePassword();
                }
            });
        };

        //
        $scope.updatePassword = function () {
            if ($scope.beans.newPassword !== $scope.beans.redoPassword) {
                AlertFactory.error($scope, null, '两次密码不一致，请重新输入！');
            } else {
                var promise = User.updatePwd({password: $.md5($scope.beans.redoPassword)}, function (data) {
                    if (data.success) {
                        $scope.isShow = false;
                        $scope.beans = {};
                        AlertFactory.success($scope, null, '密码修改成功，下次登录生效');
                        modal.hide();
                    } else {
                        AlertFactory.error($scope, data.error || data.fail || '', '密码修改失败!');
                    }
                });
                CommonUtils.loading(promise);
            }
        };
    });


})(window, angular, jQuery);
