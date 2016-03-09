/**
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.youth.edit', [
        'spec.youth',
        'base.org',
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.angular.ztree'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, YouthService,
                                     YouthParam, Org) {

        var pageType = $scope.pageType = $('#pageType').val();
        var id = $('#id').val();

        // 加载性别
        YouthParam.sex(function (data) {
            $scope.sex = data;
            $scope.sex.unshift({name: '请选择'});
        });
        // 加载教育程度
        YouthParam.education(function (data) {
            $scope.education = data;
            $scope.education.unshift({name: '请选择'});
        });
        // 加载民族
        YouthParam.nation(function (data) {
            $scope.nation = data;
            $scope.nation.unshift({name: '请选择'});
        });
        // 加载政治面貌
        YouthParam.zzmm(function (data) {
            $scope.zzmm = data;
            $scope.zzmm.unshift({name: '请选择'});
        });

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
                    $scope.beans.attachmentIds = id;
                    $scope.beans.picture = id;
                });
            },

            onDelete: function () {
                $('#imageId').html('');
                $scope.beans.attachmentIds = null;
                $scope.beans.picture = null;
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
            $scope.beans.picture = null;
        };

        $scope.beans = {};
        // 如果不是根节点，则只能查询自己的机构的数据
        var orgId = CommonUtils.loginContext().orgId;
        if (orgId != 1) {
            $scope.beans.orgId = orgId;
            $scope.beans.orgName = CommonUtils.loginContext().orgName;
            $('#orgId').children().attr('disabled', 'disabled');
        }
        $scope.orgOptions = Org.pick(function (o) {
            $scope.beans.orgId = o.id;
            $scope.beans.orgName = o.name;
        });

        $scope.back = CommonUtils.back;

        // 保存
        $scope.save = function (createNew) {
            $scope.form.$setValidity('committed', false);
            var promise = YouthService.save($scope.beans, function (data) {
                AlertFactory.success('保存成功!');
                CommonUtils.addTab('update');
                if (createNew === true) {
                    CommonUtils.delay(function () {
                        window.location.reload();
                    }, 2000);
                }
            });
            CommonUtils.loading(promise, '保存中...');
        };

        // 更新
        $scope.update = function () {
            var promise = YouthService.update($scope.beans, function (data) {
                AlertFactory.success(null, '更新成功!');
                $scope.form.$setValidity('committed', false);
                CommonUtils.addTab('update');
                CommonUtils.delay(CommonUtils.back, 3000);
            });
            CommonUtils.loading(promise, '更新中...');
        };

        // 加载数据
        $scope.load = function (id, callback) {
            // 明细
            var promise = YouthService.get({id: id}, function (data) {
                $scope.beans = data.data || {};
                // 头像
                var imageId = $scope.beans.picture;
                if (imageId) {
                    $('#imageId').html('<img style="height: 140px;width: 120px;" src="' + CommonUtils.contextPathURL('/attachment/view?id=' + imageId) + '"/>');
                }
                // 回调
                if (angular.isFunction(callback)) {
                    callback();
                }
            });
            CommonUtils.loading(promise, 'Loading...');

        };

        // 添加家庭关系
        $scope.addRelation = function () {
            $scope.beans.relations = $scope.beans.relations || [];
            $scope.beans.relations.push({});
        };

        // 删除家庭关系
        $scope.removeRelation = function (index) {
            $scope.beans.relations.splice(index, 1);
        };

        if (pageType == 'add') {
            $scope.beans.sex = 'BP_MAN';
            $scope.beans.orgId = CommonUtils.loginContext().orgId;
            $scope.beans.orgName = CommonUtils.loginContext().orgName;
        } else if (pageType == 'modify') {
            $scope.load(id);
        } else if (pageType == 'detail') {
            $scope.uploadOptions.readonly = true;
            $scope.load(id, function () {
                $('input,textarea,select').attr('disabled', 'disabled');
            });
        } else {
            AlertFactory.error($scope, '错误的页面类型');
        }

    });
})(window, angular, jQuery);