/**
 * Created by thomas on 18/02/16.
 */
angular.module('projecttycoon').factory('GameAdminResource', function($resource) {
    return $resource('/api/admin/games/:id', {id: "@id"}, {
        getGameByUsername:{
            method: 'GET',
            url: '/api/games/admin/game/:teamname'
        },
        deleteTeam: {
            method: 'DELETE',
            url: '/api/admin/games/team/:id'
        },
        update:{
            method: 'PUT'
        },
        postQuestion:{
            method: 'POST',
            url: '/api/admin/games/levelkn'
        },
        getTeamsForOverview:{
            method: 'GET',
            url: '/api/admin/games/:id/lightteams',
            isArray: true
        }
    });

});

