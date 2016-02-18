/**
 * Created by thomas on 18/02/16.
 */
angular.module('projecttycoon').factory('PrestationAdminResource', function($resource) {
    return $resource('/api/admin/prestations/:id', {id: '@id'}, {
        update: {
            method: 'PUT'
        },
        saveAll : {
            method: 'POST',
            url: '/api/admin/prestations/multiple',
            isArray: true
        }


    });

});