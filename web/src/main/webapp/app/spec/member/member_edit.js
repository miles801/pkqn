/**
 */
(function (window, angular, $) {
    var app = angular.module('spec.member.edit', [
        'eccrm.base.employee',
        'eccrm.angular',
        'base.org',
        'eccrm.base.param',
        'eccrm.directive.ztree',
        'eccrm.angularstrap'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, EmployeeConstant, OrgTree, EmployeeService, AlertFactory, ParameterLoader, Parameter) {
        // 是否具有编辑权限
        $scope.hasEditPermission = false;


        // 领域
        ParameterLoader.loadBusinessParam('SPEC_LY', function (data) {
            $scope.ly = data || [];
            $scope.ly.unshift({name: '请选择...'});
        });

        // 领域改变时，加载子领域
        $scope.lyChange = function (ly2) {
            $scope.ly2 = [];
            $scope.employee.ly2 = ly2 || '';
            Parameter.fetchBusinessCascade('SPEC_LY', $scope.employee.ly, function (data) {
                $scope.ly2 = data.data || [];
                $scope.ly2.unshift({name: '请选择..', value: ''});
            });
        };

        // 学历
        ParameterLoader.loadBusinessParam('BP_EDU', function (data) {
            $scope.education = data || [];
            $scope.education.unshift({name: '请选择...'});
        });
        // 荣誉称号
        ParameterLoader.loadBusinessParam('SPEC_HONOR', function (data) {
            $scope.honor = data || [];
            $scope.honor.unshift({name: '请选择...'});
        });
        // 性别
        EmployeeConstant.sex(function (data) {
            $scope.sex = data;
            $scope.sex.unshift({name: '请选择...'});
        });
        // 加载民族
        EmployeeConstant.nation(function (data) {
            $scope.nation = data;
            $scope.nation.unshift({name: '请选择'});
        });
        // 加载政治面貌
        EmployeeConstant.zzmm(function (data) {
            $scope.zzmm = data;
            $scope.zzmm.unshift({name: '请选择'});
        });

        $scope.employee = {
            positionCode: 'TY',
            status: "2",
            gender: "BP_MAN",
            honor: '1',
            beginWorkDate: new Date().getTime()
        };

        $scope.OrgztreeOptions = OrgTree.dynamicTree(function (node) {
            $scope.employee.orgId = node.id;
            $scope.employee.orgName = node.name;
        });

        $scope.clearOrg = function () {
            $scope.employee.orgId = null;
            $scope.employee.orgName = null;
        };

        //回到上一个页面
        $scope.back = CommonUtils.back;

        var type = $scope.pageType = $('#pageType').val();
        var id = $('#id').val();

        // 头像
        $scope.uploadOptions = {
            labelText: '头像',
            maxFile: 1,
            thumb: true,
            thumbWidth: 120,
            thumbHeight: 140,
            showTable: false,
            onSuccess: function (o) {
                var id = o.id;
                $('#imageId').html('<img style="height: 140px;width: 120px;" src="' + CommonUtils.contextPathURL('/attachment/temp/view?id=' + id) + '"/>');
                $scope.$apply(function () {
                    $scope.employee.attachmentIds = id;
                    $scope.employee.picture = id;
                });
            },

            onDelete: function () {
                $('#imageId').html('');
                $scope.employee.attachmentIds = null;
                $scope.employee.picture = null;
            },
            bid: id,
            swfOption: {
                fileSizeLimit: 10 * 1000 * 1000,
                fileTypeExts: "*.png;*.jpg"
            }
        };

        // 移除头像
        $scope.removePicture = function () {
            $scope.uploadOptions.removeAll();
            $scope.employee.picture = null;
        };

        //保存
        $scope.save = function () {
            $scope.employee.beginWorkDate += '-01';
            var promise = EmployeeService.save($scope.employee, function (data) {
                $scope.form.$setValidity('committed', false);
                AlertFactory.success('保存成功!');
            });
            CommonUtils.loading(promise);
        };


        //更新
        $scope.update = function () {
            $scope.employee.beginWorkDate += '-01';
            var promise = EmployeeService.update($scope.employee, function (data) {
                $scope.form.$setValidity('committed', false);
                AlertFactory.success('更新成功!');
                CommonUtils.addTab('update');
                CommonUtils.delay($scope.back, 2000);
            });
            CommonUtils.loading(promise);
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

                // 手动触发领域的二级事件
                if ($scope.employee.ly) {
                    $scope.lyChange($scope.employee.ly2);
                }
                // 头像
                var imageId = $scope.employee.picture;
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