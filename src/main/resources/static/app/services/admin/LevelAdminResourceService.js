/**
 * Created by thomas on 18/02/16.
 */


angular.module('projecttycoon').factory('LevelAdminResource', function($resource) {
    return $resource('/api/admin/levels/:id', {id: "@id"}, {
        save: {
            method: 'POST'
        },
        update: {
            method: 'PUT'
        }
    });
});