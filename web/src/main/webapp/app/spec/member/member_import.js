(function (window, angular, $) {
    var app = angular.module('spec.member.import', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.base.employee',
        'eccrm.angular.ztree',
        'eccrm.base.param',
        'base.org'
    ]);

    //
    app.controller('Ctrl', function ($scope, ModalFactory, CommonUtils, AlertFactory, EmployeeService, Org, ParameterLoader, Parameter) {
        $scope.beans = {};

        $scope.extra = {};

        // 组织机构
        $scope.orgTree = Org.pick(function (o) {
            $scope.extra.orgId = o.id;
            $scope.extra.orgName = o.name;
        });

        // 领域
        ParameterLoader.loadBusinessParam('SPEC_LY', function (data) {
            $scope.ly = data || [];
            $scope.ly.unshift({name: '请选择...'});
        });

        // 领域改变时，加载子领域
        $scope.lyChange = function (ly2) {
            $scope.ly2 = [];
            $scope.extra.ly2 = ly2 || '';
            if (!$scope.extra.ly) {
                $scope.ly2.push({name: '请选择..', value: ''});
                return;
            }
            Parameter.fetchBusinessCascade('SPEC_LY', $scope.extra.ly, function (data) {
                $scope.ly2 = data.data || [];
                $scope.ly2.unshift({name: '请选择..', value: ''});
            });
        };
        // 团组织
        ParameterLoader.loadBusinessParam('SPEC_TZZ', function (data) {
            $scope.tzz = data || [];
            $scope.tzz.unshift({name: '请选择...'});
        });


        EmployeeService.get({id: CommonUtils.loginContext().id}, function (emp) {
            emp = emp.data || {};
            $scope.emp = emp;
            if (emp.positionCode == 'NORMAL_MANAGER') {
                $scope.extra.orgId = emp.orgId;
                $scope.extra.orgName = emp.orgName;
                $scope.extra.tzz = emp.tzz;
                $scope.extra.tzzName = emp.tzzName;
                $scope.extra.ly = emp.ly;
                $scope.extra.ly2 = emp.ly2;
                $scope.lyChange(emp.ly2);
            } else if (emp.positionCode == 'EJGLY') {
                $scope.extra.orgId = emp.orgId;
                $scope.extra.orgName = emp.orgName;
                $scope.extra.ly = emp.ly;
                $scope.extra.ly2 = emp.ly2;
                $scope.lyChange(emp.ly2);
            }


        });

        $scope.importData = function () {
            var ids = $scope.fileUpload.getAttachment() || [];
            if (ids && ids.length < 1) {
                AlertFactory.error(null, '请上传数据文件!');
                return false;
            }
            $scope.extra.attachmentIds = ids.join(',');
            var promise = EmployeeService.importData($scope.extra, function () {
                AlertFactory.success('导入成功!');
            });
            CommonUtils.loading(promise, '导入中,请稍后...');
        };

        // 关闭当前页签
        $scope.back = CommonUtils.back;


        // 附件
        $scope.fileUpload = {
            maxFile: 3,
            canDownload: true,
            onSuccess: function () {
                $scope.$apply(function () {
                    $scope.canImport = true;
                });
            },
            swfOption: {
                fileTypeExts: '*.xls;*.xlsx'
            }
        };

    });
})(window, angular, jQuery);