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
        }).when('/dashboard/:id', {
            templateUrl : 'views/dashboard.html',
            controller: 'dashboard'
        }).when('/dashboard', {
            templateUrl : 'views/dashboard.html',
            controller: 'overviewDashboard'
        }).when('/registerTeam/:username', {
            templateUrl : 'views/teamRegistration.html',
            controller: 'registration'
        }).when('/editTeam/:teamname',{
            templateUrl : 'views/updateTeam.html',
            controller : 'updateTeam'
        }).when('/adminOverview', {
            templateUrl : 'views/adminOverview.html',
            controller: 'adminOverview'
        }).when('/admin/knowledgeareas/edit', {
            templateUrl : 'views/knowledgearea/edit.html',
            controller : 'editKnowledgeAreasController'
        }
        ).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    });