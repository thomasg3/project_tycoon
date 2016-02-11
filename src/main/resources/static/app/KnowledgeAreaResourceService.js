/**
 * Created by thomas on 11/02/16.
 */

angular.module('projecttycoon').factory('KnowledgeAreaResource', function($resource) {
    return $resource('/api/knowledgeareas', {}, {

    });

});