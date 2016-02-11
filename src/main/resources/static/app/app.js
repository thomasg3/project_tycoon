/**
 * Created by thomas on 09/02/16.
 */
angular.module('projecttycoon', [ 'ngRoute', 'projecttycoonControllers', 'ngResource' ])
    .config(function($routeProvider, $httpProvider) {

        $routeProvider.when('/', {
            templateUrl : 'views/public/home.html',
            controller : 'home'
        }).when('/login', {
            templateUrl : 'views/public/login.html',
            controller: 'navigation'
        }).when('/createGame', {
            templateUrl : 'views/game/edit.html',
            controller : 'newGameController'
        }).when('/:gamename', {
            templateUrl: 'views/public/gamedetails.html',
            controller: 'detailGameController'

        }).when('/dashboard/:id', {
            templateUrl : 'views/dashboard.html',
            controller: 'dashboard'
        }).when('/registerTeam/:username', {
            templateUrl : 'views/teamRegistration.html',
            controller: 'registration'
        }).when('/adminOverview', {
            templateUrl : 'views/adminOverview.html',
            controller: 'adminOverview'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    });