/**
 * Created by Jeroen on 17-2-2016.
 */

angular.module('projecttycoon').factory('QuestionResource', function($resource) {
    return $resource('/api/questions/:id', {id: '@id'}, {
        update: {
            method: 'PUT'
        },
        updateAnswers: {
            method: 'PUT',
            url: '/api/questions/answers/:id'
        },
        saveAll : {
            method: 'POST',
            url: '/api/questions/:id'
        },
        getFormats : {
            method: 'GET',
            url: 'api/questions/enum',
            isArray: true
        }
    });

});