/**
 * Created by thomas on 09/02/16.
 */
angular.module('projecttycoon', [ 'ngRoute', 'projecttycoonControllers' ])
    .config(function($routeProvider, $httpProvider) {

        $routeProvider.when('/', {
            templateUrl : 'home.html',
            controller : 'home'
        }).when('/login', {
            templateUrl : 'login.html',
            controller: 'navigation'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    });