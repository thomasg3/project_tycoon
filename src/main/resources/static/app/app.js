/**
 * Created by thomas on 09/02/16.
 */
var app = angular.module('projecttycoon', [ 'ngRoute', 'projecttycoonControllers', 'ngResource', 'ngFileUpload']);

app.factory('httpInterceptor', ['$q', '$location', '$log', function($q, $location, $log){
    return {
        response: function(response){
            return response;
        },
        responseError: function(response){
            $log.debug("HTTP_ERR "+response.status);
            switch(response.status){
                case 401:
                    if(response.config.url != "user")
                        window.location =  '#/login';
                    break;
                case 403:
                    window.location =  '#/forbidden';
                    break;
                case 404:
                    window.location =  '#/not_found';
                    break;
                default:
                    return $q.reject(response);
            }

        }
    };
}])

app.config(function($routeProvider, $httpProvider) {

        $routeProvider
            //##### error pages ######
            .when('/not_found', {
                templateUrl : 'views/error/404.html'
            })
            .when('/forbidden',{
                templateUrl : 'views/error/403.html'
            })
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
            .when('/games/:gameid/teams/:id', {
                templateUrl : 'views/team/details.html',
                controller : 'detailTeamController'
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
            .when('/admin/:id/levels', {
                templateUrl : 'views/levelOverview.html',
                controller : 'levelOverview'
            })
            .when('/info', {
                templateUrl : 'views/game/allInfo.html',
                controller : 'info'
            })
            .otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        $httpProvider.interceptors.push('httpInterceptor');
    });