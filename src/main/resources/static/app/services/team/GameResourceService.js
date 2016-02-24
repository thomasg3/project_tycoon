/**
 * Created by Jeroen on 11-2-2016.
 */

angular.module('projecttycoon').factory('GameResource', function($resource) {
    return $resource('/api/games/:id', {id: "@id"}, {
        getGameByUsername:{
            method: 'GET',
            url: '/api/games/game/:teamname'
        },
        deleteTeam: {
            method: 'DELETE',
            url: '/api/games/team/:id'
        },
        update:{
            method: 'PUT'
        },
        postQuestion:{
            method: 'POST',
            url: '/api/games/levelkn'
        },
        getAllPublicLevelsForGame: {
            method: 'GET',
            url: '/api/games/public/game/:id',
            isArray: true
        }
    });

});