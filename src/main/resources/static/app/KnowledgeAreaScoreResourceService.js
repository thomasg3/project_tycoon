/**
 * Created by Jeroen on 18-2-2016.
 */


angular.module('projecttycoon').factory('KnowledgeAreaScoreResource', function($resource) {
    return $resource('/api/knowledgeareascores/:id', {id: '@id'}, {
        update: {
            method: 'PUT'
        }
    });

});