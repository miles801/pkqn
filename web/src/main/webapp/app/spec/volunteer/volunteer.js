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

    app.service('VolunteerModal', function (CommonUtils, ModalFactory, $modal, AlertFactory, YouthService) {
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
