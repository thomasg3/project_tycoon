/**
 * Created by michael on 18/02/16.
 */

angular.module('projecttycoon').factory('InfoAdminResource', function($resource) {
    return $resource('/api/admin/info/:id', {id: "@id"}, {

        getAll: {
            method: 'GET',
            url: '/api/admin/info',
            isArray: true
        },
        //carefull the id is used for the team here and not for the Info itself
        getForTeam:{
            method: 'GET',
            url: '/api/admin/info/team/:id',
            isArray:true
        },
        post: {
            method:'POST',
            url: 'api/admin/info'
        },
        getTypes : {
            method : 'GET',
            url: 'api/admin/info/types'
        },
        delete:{
            method : 'DELETE',
            isArray:true
        },
        update : {
            method : 'PUT',
            url: 'api/admin/info/:id'
        }
    });

});