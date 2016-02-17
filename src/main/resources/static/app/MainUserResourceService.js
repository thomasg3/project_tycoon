/**
 * Created by michael on 17/02/16.
 */

angular.module('projecttycoon')
    .factory('MainUserResource', function ($window) {


        return {
            saveMainUser:function (data) {
                $window.sessionStorage.MainUser = JSON.stringify(data);
                console.log("saved MainUser: " +$window.sessionStorage.MainUser);
            },
            getMainUser:function () {
                var user = $window.sessionStorage.MainUser;
                if(user) {
                    var main = JSON.parse(user);
                }
                console.log(main);
                return main;
            }
        };
    });