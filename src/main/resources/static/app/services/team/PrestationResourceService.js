/**
 * Created by thomas on 15/02/16.
 */
angular.module('projecttycoon').factory('PrestationResource', function($resource) {
    return $resource('/api/prestations/:id', {id: '@id'}, {
        update: {
            method: 'PUT'
        },
        saveAll : {
            method: 'POST',
            url: '/api/prestations/multiple',
            isArray: true
        }


    });

});