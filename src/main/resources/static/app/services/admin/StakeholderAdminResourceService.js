/**
 * Created by kiwi on 22/02/2016.
 */


angular.module('projecttycoon').factory('StakeholderAdminResource', function($resource) {
    return $resource('/api/admin/stakeholders/:id', {id: '@id'}, {
        showAll: {
            method: 'GET',
            url: '/api/admin/stakeholders/'
        },
        update: {
            method: 'PUT'
        },
        delete : {
            method: 'POST',
            url: '/api/admin/stakeholders/:id'
        },
        create : {
            method: 'POST',
            url: '/api/admin/stakeholders/'
        }
    });

});