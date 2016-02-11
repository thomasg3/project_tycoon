/**
 * Created by thomas on 09/02/16.
 */
angular.module('projecttycoon', [ 'ngRoute', 'projecttycoonControllers', 'ngResource' ])
    .config(function($routeProvider, $httpProvider) {

        $routeProvider.when('/', {
            templateUrl : 'home.html',
            controller : 'home'
        }).when('/login', {
            templateUrl : 'login.html',
            controller: 'navigation'
        }).when('/dashboard', {
            templateUrl : 'views/dashboard.html',
            controller: 'dashboard'
        }).when('/registerTeam/:username', {
            templateUrl : 'teamRegistration.html',
            controller: 'registration'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    });
