/**
 * Created by Jeroen on 15-2-2016.
 */

angular.module('projecttycoon').factory('LevelResource', function($resource) {
    return $resource('/api/levels/:id', {id: "@id"}, {
        save: {
            method: 'POST'
        },
        update: {
            method: 'PUT'
        },
        getPublicLevel: {
            method: 'GET',
            url: '/api/levels/public/:id'
        }
    });
});