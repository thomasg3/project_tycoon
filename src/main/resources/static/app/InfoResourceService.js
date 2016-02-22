/**
 * Created by michael on 18/02/16.
 */

angular.module('projecttycoon').factory('InfoResource', function($resource) {
    return $resource('/api/info/:id', {id: "@id"}, {

        getAll: {
            method: 'GET',
            url: '/api/info',
            isArray: true
        },
        //carefull the id is used for the team here and not for the Info itself
        getForTeam:{
            method: 'GET',
            url: '/api/info/team/:id',
          isArray:true
        },
        post: {
            method:'POST',
            url: 'api/info'
        },
        getTypes : {
            method : 'GET',
            url: 'api/info/types'
        }
    });

});