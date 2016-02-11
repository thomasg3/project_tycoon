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
            url: '/api/teams/search/:teamname',
            isArray: false
        },
        isRegistered: {
            method: 'GET',
            url: '/api/teams/search/:teamname/registered',
            isArray: false,
            responseType: 'json'
        },
        update: {
            method: 'PUT',
            URL: '/api/teams/:id'
        }
    });

});

