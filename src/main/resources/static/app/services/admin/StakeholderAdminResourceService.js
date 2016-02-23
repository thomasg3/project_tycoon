/**
 * Created by kiwi on 22/02/2016.
 */


angular.module('projecttycoon').factory('StakeholderAdminResource', function($resource) {
    return $resource('/api/admin/stakeholders/:id', {id: '@id'}, {
        update: {
            method: 'PUT'
        },
        getStakholdersOfLevel: {
            method: 'GET',
            url: '/api/admin/stakeholders/from_level/:level',
            isArray : true
        },
        addTeamToBlackList : {
            method : 'GET',
            url: 'api/admin/stakeholders/:id/blacklist/:team'
        },
        removeTeamFromBlackList : {
            method : 'DELETE',
            url: 'api/admin/stakeholders/:id/blacklist/:team'
        }

    });

});