/**
 * Created by Jeroen on 10-2-2016.
 */

angular.module('projecttycoon').factory('TeamResource', function($resource) {
    return $resource('/api/teams/:id', {id: "@id"}, {
        getAll: {
            method: 'GET',
            url: '/api/teams',
            isArray: true
        },
        search: {
            method: 'GET',
            url: '/api/teams/search/:teamname'
        },
        isRegistered: {
            method: 'GET',
            url: '/api/teams/search/:teamname/registered'
        },
        update: {
            method: 'PUT'
        }
    });
});

