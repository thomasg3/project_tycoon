/**
 * Created by kiwi on 22/02/2016.
 */


angular.module('projecttycoon').factory('StakeholderResource', function($resource) {
    return $resource('/api/stakeholders/:id', {id: '@id'}, {

    });

});