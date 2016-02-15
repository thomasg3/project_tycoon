/**
 * Created by thomas on 09/02/16.
 */
angular.module('projecttycoon', [ 'ngRoute', 'projecttycoonControllers', 'ngResource' ])
    .config(function($routeProvider, $httpProvider) {

        $routeProvider
            //##### public pages #####
            .when('/', {
                templateUrl : 'views/public/home.html',
                controller : 'home'
            })
            .when('/login', {
                templateUrl : 'views/public/login.html',
                controller: 'navigation'
            })
            .when('/dashboard/:id', {
                templateUrl : 'views/dashboard.html',
                controller: 'dashboard'
            })
            .when('/dashboard', {
                templateUrl : 'views/dashboard.html',
                controller: 'overviewDashboard'
            })
            //##### team pages #####
            .when('/registerTeam/:username', {
                templateUrl : 'views/teamRegistration.html',
                controller: 'registration'
            })
            .when('/editTeam/:teamname',{
                templateUrl : 'views/updateTeam.html',
                controller : 'updateTeam'
            })
            //##### game pages #####
            .when('/games/:id/details',{
                templateUrl : 'views/game/details.html',
                controller : 'detailGameController'
            })
            .when('/games/:gameid/levels/:levelid/editscores', {
                templateUrl : 'views/game/level/score_edit.html',
                controller : 'editLevelScoreController'
            })
            //##### admin pages #####
            .when('/adminOverview', {
                templateUrl : 'views/adminOverview.html',
                controller: 'adminOverview'
            })
            .when('/createGame', {
                templateUrl : 'views/game/edit.html',
                controller : 'newGameController'
            })
            .when('/admin/knowledgeareas/edit', {
                templateUrl : 'views/knowledgearea/edit.html',
                controller : 'editKnowledgeAreasController'
            })
            .otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    });