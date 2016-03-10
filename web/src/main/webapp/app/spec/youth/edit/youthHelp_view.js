/**
 * Created by Michael on 2016-02-21 11:17:58.
 */
(function (window, angular, $) {
    var app = angular.module('spec.youth.help.view', [
        'spec.youth',
        'base.org',
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.angular.ztree'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, YouthHelpService) {

        $scope.pageType = $('#pageType').val();
        var youthId = $('#youthId').val();
        if (!youthId) {
            AlertFactory.error('错误的访问!没有获得闲散青年ID!');
            CommonUtils.delay(function () {
                CommonUtils.back();
            }, 2000);
            return;
        }

        // 新增帮扶记录
        $scope.add = function () {
            CommonUtils.addTab({
                title: '添加帮扶记录',
                url: '/spec/youth/help/add?youthId=' + youthId,
                onUpload: loadHistory
            })
        };

        // 更新帮扶记录
        $scope.modify = function (id) {
            CommonUtils.addTab({
                title: '更新帮扶记录',
                url: '/spec/youth/help/modify?id=' + id,
                onUpload: loadHistory
            })
        };
        // 移除帮扶记录
        $scope.remove = function (id) {
            ModalFactory.confirm({
                scope: $scope,
                content: '删除后数据不可恢复，请确认该操作!',
                callback: function () {
                    var promise = YouthHelpService.deleteByIds({ids: id}, function () {
                        AlertFactory.success('删除成功!');
                        loadHistory();
                    });
                    CommonUtils.loading(promise);
                }

            });
        };
        // 查询历史帮扶记录
        var loadHistory = $scope.query = function () {
            var promise = YouthHelpService.queryByYouth({youthId: youthId}, function (data) {
                $scope.beans = data.data || {total: 0, data: []};
            });
            CommonUtils.loading(promise);
        };

        // 查看帮扶记录
        $scope.view = function (id) {
            CommonUtils.addTab({
                title: '浏览帮扶记录',
                url: CommonUtils.contextPathURL('/app/spec/youth/edit/youthHelp_view2.jsp?id=' + id)
            })
        };

        loadHistory();
    });
})(window, angular, jQuery);