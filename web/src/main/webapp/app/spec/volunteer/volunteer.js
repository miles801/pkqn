/**
 * 志愿者
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (angular) {
    var app = angular.module('spec.volunteer', [
        'eccrm.base.param',
        'eccrm.angular',
        'eccrm.angularstrap'
    ]);


    // 志愿者
    app.service('VolunteerService', function (CommonUtils, $resource) {
        return $resource(CommonUtils.contextPathURL('/spec/volunteer/:method'), {}, {
            // 保存
            save: {method: 'POST', params: {method: 'save'}, isArray: false},

            // 更新
            update: {method: 'POST', params: {method: 'update'}, isArray: false},

            // 根据ID获取
            get: {method: 'GET', params: {method: 'get', id: '@id'}, isArray: false},

            // 分页查询
            pageQuery: {
                method: 'POST',
                params: {method: 'pageQuery', limit: '@limit', start: '@start'},
                isArray: false
            },

            // 根据id字符串（使用逗号分隔多个值）
            deleteByIds: {method: 'DELETE', params: {method: 'delete', ids: '@ids'}, isArray: false}
        })
    });

    app.service('VolunteerModal', function (CommonUtils, ModalFactory, $modal, AlertFactory, VolunteerService) {
        return {
            add: function (youthId, youthName, callback) {
                var modal = $modal({
                    backdrop: 'static',
                    template: CommonUtils.contextPathURL('/app/spec/youth/template/youthAudit-modal.html')
                });
                var $scope = modal.$scope;

                $scope.beans = {
                    id: youthId
                };
            },
            pick: function (callback) {
                var modal = $modal({
                    backdrop: 'static',
                    template: CommonUtils.contextPathURL('app/spec/volunteer/volunteer-multi.ftl.html')
                });

                var $scope = modal.$scope;

                $scope.condition = {
                    ownerId: CommonUtils.loginContext().id
                };
                $scope.query = function () {
                    $scope.pager.query();
                };
                $scope.pager = {
                    limit: 10,
                    fetch: function () {
                        return CommonUtils.promise(function (defer) {
                            var promise = VolunteerService.pageQuery($scope.condition, function (data) {
                                $scope.beans = data.data || {total: 0};
                                defer.resolve($scope.beans);
                            });
                            CommonUtils.loading(promise);
                        });
                    },
                    finishInit: function () {
                        this.query();
                    }
                };

                // 确认选择
                $scope.confirm = function () {
                    angular.isFunction(callback) && callback($scope.items || []);
                    modal.hide();
                };

                // 清空选择
                $scope.clear = function () {
                    ModalFactory.confirm({
                        scope: $scope,
                        content: '即将清空所有所选志愿者，并返回，请确认!',
                        callback: function () {
                            modal.hide();
                            angular.isFunction(callback) && callback([]);
                        }
                    });
                };
            }
        }
    });

    app.service('VolunteerParam', function (ParameterLoader) {
        return {
            /**
             * 性别
             */
            sex: function (callback) {
                ParameterLoader.loadBusinessParam('BP_SEX', callback);
            }
        };
    });

})(angular);
