/**
 * 贫困青年
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (angular) {
    var app = angular.module('spec.poorTeenagers', [
        'eccrm.base.param',
        'eccrm.angular',
        'eccrm.angularstrap'
    ]);

    app.service('PoorTeenagersService', function (CommonUtils, $resource) {
        return $resource(CommonUtils.contextPathURL('/spec/poorTeenagers/:method'), {}, {
            // 保存
            save: {method: 'POST', params: {method: 'save'}, isArray: false},

            // 更新
            update: {method: 'POST', params: {method: 'update'}, isArray: false},

            // 根据id查询信息
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

    app.service('PoorTeenagersParam', function (ParameterLoader) {
        return {
            sex: function (callback) {
                ParameterLoader.loadBusinessParam('BP_SEX', callback);
            },
            /**
             * 民族
             */
            nation: function (callback) {
                ParameterLoader.loadBusinessParam('BP_NATION', callback);
            },
            /**
             * 政治面貌
             */
            zzmm: function (callback) {
                ParameterLoader.loadBusinessParam('BP_ZZMM', callback);
            },
            /**
             * 健康状况
             */
            health: function (callback) {
                ParameterLoader.loadBusinessParam('BP_HEALTH', callback);
            },
            /**
             * 家庭年收入
             */
            income: function (callback) {
                ParameterLoader.loadBusinessParam('FAMARY_INCOME', callback);
            }
        };
    });

})(angular);
