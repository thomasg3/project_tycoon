/**
 * Created by kiwi on 18/02/2016.
 */

angular.module('projecttycoon').factory('MailResource', function($resource) {
    return $resource('/api/mail/:id',{id: "@id"}, {
        mail: {
            method: 'POST',
            url: '/api/mail/:id'}});
});