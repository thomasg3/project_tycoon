/**
 * Created by Jeroen on 11-2-2016.
 */

angular.module('projecttycoon').factory('GameResource', function($resource) {
    return $resource('/api/games/:id', {id: "@id"}, {
        update: {
            method: 'PUT'
        },
        getAll: {
            method: 'GET',
            url: '/api/games',
            isArray: true
        },
        getGameByUsername:{
            method: 'GET',
            url: '/api/games/game/:teamname'
        },
        update:{
            method: 'PUT'
        }
    });

});