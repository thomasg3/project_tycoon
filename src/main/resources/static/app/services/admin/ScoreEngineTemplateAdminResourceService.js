/**
 * Created by Jeroen on 23-2-2016.
 */


angular.module('projecttycoon').factory('ScoreEngineTemplateAdminResource', function($resource) {
    return $resource('/api/admin/scoreEngineTemplate/:id', {id: "@id"}, {
        delete:{
            method: 'DELETE',
            isArray: true
        }
    });

});