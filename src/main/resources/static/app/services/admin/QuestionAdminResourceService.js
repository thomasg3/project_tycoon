/**
 * Created by thomas on 18/02/16.
 */

angular.module('projecttycoon').factory('QuestionAdminResource', function($resource) {
    return $resource('/api/admin/questions/:id', {id: '@id'}, {
        update: {
            method: 'PUT'
        },
        updateAnswers: {
            method: 'PUT',
            url: '/api/admin/questions/answers/:id'
        },
        saveAll : {
            method: 'POST',
            url: '/api/admin/questions/:id'
        }
    });

});