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

    app.service('CondoleService', function (CommonUtils, $resource) {
        return $resource(CommonUtils.contextPathURL('/spec/condole/:method'), {}, {
            // 保存
            save: {method: 'POST', params: {method: 'save'}, isArray: false},

            // 更新
            update: {method: 'POST', params: {method: 'update'}, isArray: false},

            // 根据id查询信息
            get: {method: 'GET', params: {method: 'get', id: '@id'}, isArray: false},

            // 根据青年ID查询
            queryByTeenager: {
                method: 'GET',
                params: {method: 'queryByTeenager', teenagerId: '@teenagerId'},
                isArray: false
            },

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

    app.service('CondoleModal', function (CommonUtils, ModalFactory, $modal, AlertFactory, CondoleService) {
        var pageTypes = ['add', 'modify', 'view'];
        var common = function (options, callback) {
            var pageType = options.type;
            if ($.inArray(pageType, pageTypes) == -1) {
                AlertFactory.error(null, '打开弹出层失败：不合法的页面类型，仅支持[' + pageTypes.join(',') + ']类型!');
                return;
            }
            var teenagerId = options.teenagerId;
            if (pageType === 'add' && !teenagerId) {
                AlertFactory.error(null, '打开弹出层失败：没有获得青少年ID!');
                return;
            }

            var modal = $modal({
                template: CommonUtils.contextPathURL('/app/spec/poorTeenagers/template/condole-modal-edit.html')
            });

            var $scope = modal.$scope;

            $scope.type = pageType;
            $scope.save = function () {
                var promise = CondoleService.save($scope.beans, function (data) {
                    if (data.success) {
                        if (angular.isFunction(callback)) {
                            callback();
                        }
                        $scope.$hide();
                    } else {
                        AlertFactory.saveError($scope, data);
                    }
                });
                CommonUtils.loading(promise);
            };

            $scope.update = function () {
                var promise = CondoleService.update($scope.beans, function (data) {
                    if (data.success) {
                        if (callback && angular.isFunction(callback)) {
                            callback();
                        }
                        $scope.$hide();
                    } else {
                        AlertFactory.updateError($scope, data);
                    }
                });
                CommonUtils.loading(promise);
            };

            var load = function (callback) {
                var promise = CondoleService.get({id: options.id}, function (data) {
                    data = data.data || {};
                    $scope.beans = data;
                    if (angular.isFunction(callback)) {
                        callback();
                    }
                });
                CommonUtils.loading(promise);
            };


            if (pageType == 'add') {
                $scope.beans = {
                    poorTeenagerId: teenagerId,
                    poorTeenagerName: options.teenagerName,
                    occurDate: new Date().getTime()
                };
            } else if (pageType == 'modify') {
                load();
            } else if (pageType == 'view') {
                load(function () {
                    $('.modal').find('input,select,textarea').attr('disabled', 'disabled');
                });
            }
        };
        return {
            add: function (teenagerId, teenagerName, callback) {
                common({type: 'add', teenagerId: teenagerId, teenagerName: teenagerName}, callback);
            },
            modify: function (id, callback) {
                if (!id) {
                    AlertFactory.error(null, '打开弹出层失败!没有获得ID!')
                    return;
                }
                common({type: 'modify', id: id}, callback);
            },
            view: function (id, callback) {
                if (!id) {
                    AlertFactory.error(null, '打开弹出层失败!没有获得ID!')
                    return;
                }
                common({type: 'view', id: id}, callback);
            }
        }
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
