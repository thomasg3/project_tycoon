/**
 * Created by kiwi on 22/02/2016.
 */


angular.module('projecttycoon').factory('StakeholderAdminResource', function($resource) {
    return $resource('/api/admin/stakeholders/:id', {id: '@id'}, {
        update: {
            method: 'PUT'
        }
    });

});