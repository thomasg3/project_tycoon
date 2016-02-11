/**
 * Created by Jeroen on 11-2-2016.
 */

angular.module('projecttycoon').factory('GameResource', function($resource) {
    return $resource('/api/games/:id', {id: "@id"}, {
        getAll: {
            method: 'GET',
            url: '/api/games',
            isArray: true
        },
        save: {
            method: 'POST'
        }
    });

});