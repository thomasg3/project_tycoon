/**
 * Created by Jeroen on 24-2-2016.
 */

angular.module('projecttycoon').factory('LevelTemplateResource', function($resource) {
    return $resource('/api/admin/levelTemplates/:id',{id: "@id"}, {
        update: {
            method: 'PUT',
        }});
});