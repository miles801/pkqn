/**
 * 闲散青年
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (angular) {
    var app = angular.module('spec.youth', [
        'eccrm.base.param',
        'eccrm.angular',
        'eccrm.angularstrap'
    ]);

    app.service('YouthService', function (CommonUtils, $resource) {
        return $resource(CommonUtils.contextPathURL('/spec/youth/:method'), {}, {
            // 保存
            save: {method: 'POST', params: {method: 'save', attachmentIds: '@attachmentIds'}, isArray: false},

            // 更新
            update: {method: 'POST', params: {method: 'update', attachmentIds: '@attachmentIds'}, isArray: false},

            // 根据id查询信息
            get: {method: 'GET', params: {method: 'get', id: '@id'}, isArray: false},

            // 查询指定的员工是否已经匹配过了
            hasMatched: {method: 'GET', params: {method: 'hasMatched', ownerId: '@ownerId'}, isArray: false},

            // 给指定的闲散青年匹配负责人
            matchOwner: {method: 'POST', params: {method: 'matchOwner'}, isArray: false},

            // 取消配对
            clearOwner: {method: 'POST', params: {method: 'clearOwner', id: '@id'}, isArray: false},

            // 统计各个状态的闲散青年个数
            analysis: {method: 'GET', params: {method: 'analysis', year: '@year'}, isArray: false},

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

    app.service('YouthParam', function (ParameterLoader) {
        return {
            /**
             * 性别
             */
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
             * 教育程度/学历
             */
            education: function (callback) {
                ParameterLoader.loadBusinessParam('BP_EDU', callback);
            },
            /**
             * 政治面貌
             */
            zzmm: function (callback) {
                ParameterLoader.loadBusinessParam('BP_ZZMM', callback);
            }
        };
    });

})(angular);
