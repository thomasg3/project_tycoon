/**
 * Created by thomas on 18/02/16.
 */

angular.module('projecttycoon').factory('TeamAdminResource', function($resource) {
    return $resource('/api/admin/teams/:id', {id: "@id"}, {
        search: {
            method: 'GET',
            url: '/api/admin/teams/search/:teamname',
            isArray: false
        },
        isRegistered: {
            method: 'GET',
            url: '/api/admin/teams/search/:teamname/registered'
        },
        update: {
            method: 'PUT'
        }
    });
});
