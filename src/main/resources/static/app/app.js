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
                    break;
            }
            return $q.reject(response);

        }
    };
}]);

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
            .when('/games/:id/levels',{
                templateUrl : 'views/game/levels.html',
                controller : 'levelController'
            })
            .when('/games/:id/levels/:activelevel',{
                templateUrl : 'views/game/levels.html',
                controller : 'levelController'
            })
            .when('/admin/games/:gameid/levels/:levelid', {
                templateUrl : 'views/level/overview.html',
                controller : 'levelControlController'
            })
            //##### admin pages #####
            .when('/admin/dashboard/:id', {
                templateUrl : 'views/adminDashboard.html',
                controller: 'adminDashboard'
            })
            .when('/admin/games/:gameid/levels/:levelid/editscores', {
                templateUrl : 'views/game/level/score_edit.html',
                controller : 'editLevelScoreController'
            })
            .when('/admin/overview', {
                templateUrl : 'views/adminOverview.html',
                controller: 'adminOverview'
            })
            .when('/admin/games/:id/details', {
                templateUrl : 'views/game/adminDetails.html',
                controller : 'adminScoreDetails'
            })
            .when('/admin/games/:gameid/teams/:id', {
                templateUrl : 'views/team/details.admin.html',
                controller : 'adminTeamDetailsController'
            })
            .when('/admin/createGame', {
                templateUrl : 'views/game/edit.html',
                controller : 'newGameController'
            })
            .when('/admin/createScoreEngine', {
                templateUrl : 'views/scoreengine/edit.html',
                controller : 'newScoreEngineController'
            })
            .when('/admin/knowledgeareas/edit', {
                templateUrl : 'views/knowledgearea/edit.html',
                controller : 'editKnowledgeAreasController'
            })
            .when('/admin/:id/levels', {
                templateUrl : 'views/levelOverview.html',
                controller : 'levelOverview'
            })
            .when('/admin/:id/mail', {
                templateUrl : 'views/sendMail.html',
                controller : 'mailController'
            })
            .when('/info', {
                templateUrl : 'views/game/allInfo.html',
                controller : 'info'
            })
            .when('/admin/info/create', {
                templateUrl : 'views/createInfo.html',
                controller : 'adminInfo'
            }).when('/admin/info',{
                templateUrl : 'views/game/allInfo.html',
                controller:'adminInfo'
            }).when('/admin/info/update/:id', {
                templateUrl : 'views/createInfo.html',
                controller : 'adminInfo'
            })
            .when('/admin/teams/:id', {
                templateUrl : 'views/teamOverview.html',
                controller : 'teamOverviewController'
            })
            .when('/admin/teams/:id/answers', {
                templateUrl : 'views/teamAnswerOverview.html',
                controller : 'teamAnswersController'
            })
            //##### stakeholder pages #####
            .when('/admin/stakeholders', {
                templateUrl : 'views/stakeholders/overview.html',
                controller : 'adminStakeholderOverviewController'
            })
            .when('/admin/stakeholders/new', {
                templateUrl: 'views/stakeholders/edit.html',
                controller : 'adminStakeholderNewController'
            })
            .when('/admin/scoreEngineOverview', {
                templateUrl : 'views/scoreEngineOverview.html',
                controller : 'scoreEngineOverviewController'
            })
            .when('/admin/scoreEngineEdit/:id', {
                templateUrl : 'views/scoreEngineEdit.html',
                controller : 'scoreEngineEditController'
            })
            .when('/admin/stakeholders/:id', {
                templateUrl : 'views/stakeholders/details.html',
                controller : 'adminStakeholderDetailController'
            })
            .when('/admin/stakeholders/:id/edit', {
                templateUrl : 'views/stakeholders/edit.html',
                controller : 'adminStakeholderEditController'
            })
            .when('/admin/stakeholders/:id/delete', {
                templateUrl : 'views/stakeholders/delete.html',
                controller : 'adminStakeholderDeleteController'
            })
            .when('/stakeholders', {
                templateUrl : 'views/stakeholders/overview.public.html',
                controller : 'stakeholderOverviewController'
            })
            .when('/stakeholders/:id', {
                templateUrl : 'views/stakeholders/details.public.html',
                controller : 'stakeholderDetailController'
            })
            .otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        $httpProvider.interceptors.push('httpInterceptor');
    });