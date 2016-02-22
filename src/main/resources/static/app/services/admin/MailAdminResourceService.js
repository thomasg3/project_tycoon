/**
 * Created by kiwi on 18/02/2016.
 */

angular.module('projecttycoon').factory('MailAdminResource', function($resource) {
    return $resource('/api/admin/mail/:id',{id: "@id"}, {
        mail: {
            method: 'POST',
            url: '/api/admin/mail/:id'}});
});