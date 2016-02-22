/**
 * Created by thomas on 18/02/16.
 */

angular.module('projecttycoon').factory('KnowledgeAreaAdminResource', function($resource) {
    return $resource('/api/admin/knowledgeareas/:id', {id: '@id'}, {
        update: {
            method: 'PUT'
        },
        saveAll : {
            method: 'POST',
            url: '/api/admin/knowledgeareas/multiple',
            isArray: true
        }
    });

});