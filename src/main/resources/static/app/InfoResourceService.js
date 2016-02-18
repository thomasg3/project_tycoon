/**
 * Created by michael on 18/02/16.
 */

angular.module('projecttycoon').factory('InfoResource', function($resource) {
    return $resource('/api/info/:id', {id: "@id"}, {
        getAll: {
            method: 'GET',
            url: '/api/info',
            isArray: true
        }
    });

});