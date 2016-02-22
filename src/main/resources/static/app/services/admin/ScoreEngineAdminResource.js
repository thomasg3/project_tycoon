/**
 * Created by Jeroen on 22-2-2016.
 */

angular.module('projecttycoon').factory('ScoreEngineAdminResource', function($resource) {
    return $resource('/api/admin/scoreengine/:id', {id: "@id"}, {
        getScoreEngineInfo:{
            url: '/api/admin/scoreengine/limit',
            isArray: true
        }
    });

});