/**
 */
(function (window, angular, $) {
    var app = angular.module('eccrm.base.employee.edit', [
        'eccrm.base.employee',
        'eccrm.angular',
        'base.org',
        'eccrm.directive.ztree',
        'eccrm.angularstrap'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, EmployeeConstant, OrgTree, EmployeeService, AlertFactory) {
        // 性别
        EmployeeConstant.sex(function (data) {
            $scope.sex = data;
        });
        // 职务
        EmployeeConstant.duty(function (data) {
            $scope.dutys = data;
        });
        // 状态
        EmployeeConstant.status(function (data) {
            $scope.EmpStatus = data;
        });

        $scope.employee = {
            status: "0",
            gender: "BP_MAN"
        };

        $scope.OrgztreeOptions = OrgTree.dynamicTree(function (node) {
            $scope.employee.organization = {id: node.id, name: node.name};
            $scope.employee.orgId = node.id;
        });
        //回到上一个页面
        $scope.back = CommonUtils.back;

        var type = $('#pageType').val();
        var id = $('#id').val();

        // 头像
        $scope.uploadOptions = {
            labelText: '头像',
            maxFile: 1,
            onSuccess: function (o) {
                var id = o.id;
                $('#imageId').html('<img style="height: 140px;width: 120px;" src="' + CommonUtils.contextPathURL('/attachment/temp/view?id=' + id) + '"/>');
                $scope.$apply(function () {
                    $scope.employee.imageId = id;
                });
            },

            onDelete: function () {
                $('#imageId').html('');
                $scope.employee.imageId = null;
            },
            bid: id,
            swfOption: {
                fileSizeLimit: 1024,
                fileTypeExts: "*.png;*.jpg"
            }
        };

        //保存
        $scope.save = function () {
            EmployeeService.save($scope.employee, function (data) {
                data = data || {};
                if (data && data.success) {
                    if (self.frameElement.tagName == "IFRAME" && self.frameElement.id && self.frameElement.id.indexOf("iframe_") == 0) {
                        CommonUtils.addTab('update');
                    }
                    CommonUtils.back();
                } else {
                    AlertFactory.error($scope, '[' + (data['fail'] || data['error'] || '') + ']');
                }
            });
        };


        //更新
        $scope.update = function () {
            EmployeeService.update($scope.employee, function (data) {
                data = data || {};
                if (data && data.success) {
                    if (self.frameElement.tagName == "IFRAME" && self.frameElement.id && self.frameElement.id.indexOf("iframe_") == 0) {
                        CommonUtils.addTab('update');
                    }
                    CommonUtils.back();
                } else {
                    AlertFactory.error($scope, '[' + (data['fail'] || data['error'] || '') + ']');
                }
            });
        };
        var originalName;
        var load = function (id, callback) {
            var promise = EmployeeService.get({id: id}, function (data) {
                $scope.employee = data.data || [];
                originalName = $scope.employee.extensionNumber;
                $scope.employee.organization = {
                    id: $scope.employee.organizationId,
                    name: $scope.employee.organizationName
                };

                var imageId = $scope.employee.imageId;
                if (imageId) {
                    $('#imageId').html('<img style="height: 140px;width: 120px;" src="' + CommonUtils.contextPathURL('/attachment/view?id=' + imageId) + '"/>');
                }
                angular.isFunction(callback) && callback();
            });
            CommonUtils.loading(promise);
        };
        if (type == 'add') {

        } else if (type == 'modify') {
            load(id);
        } else if (type == 'detail') {
            load(id, function () {
                $('input,textarea,select').attr('disabled', 'disabled');
            });
        }
    });
})(window, angular, jQuery);