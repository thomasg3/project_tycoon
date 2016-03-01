/**
 * Created by thomas on 09/02/16.
 */
var app = angular.module('projecttycoon', [ 'ngRoute', 'projecttycoonControllers', 'ngResource', 'ngFileUpload']);

app.factory('httpInterceptor', ['$q', '$rootScope' ,'$location', '$log', function($q, $rootScope ,$location, $log){
    return {
        response: function(response){
            return response;
        },
        responseError: function(response){
            switch(response.status){
                case 401:
                    if(response.config.url != "user") {
                        window.location = '#/login';
                        $rootScope.$broadcast('http401', {response: response});
                    }
                    break;
                case 403:
                    window.location =  '#/forbidden';
                    break;
                case 404:
                    window.location =  '#/not_found';
                    break;
                case 500:
                    $rootScope.$broadcast('http500', {response: response});
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
            //##### ERROR ######
                .when('/not_found', {
                    templateUrl : 'views/error/404.html'
                })
                .when('/forbidden',{
                    templateUrl : 'views/error/403.html'
                })
            //##### PUBLIC #####
                .when('/', {
                    templateUrl : 'views/public/home.html',
                    controller : 'home'
                })
                .when('/login', {
                    templateUrl : 'views/public/login.html',
                    controller: 'navigation'
                })


            //##### TEAM #####
                .when('/registerTeam/:username', {
                    templateUrl : 'views/team/registration.html',
                    controller: 'registration'
                })
                .when('/editTeam/:teamname',{
                    templateUrl : 'views/team/update.html',
                    controller : 'updateTeam'
                })
                .when('/games/:gameid/teams/:id', {
                    templateUrl : 'views/team/details.html',
                    controller : 'detailTeamController'
                })
            //##### GAME #####
                .when('/dashboard/:id', {
                    templateUrl : 'views/game/dashboard.html',
                    controller: 'dashboard'
                })
                .when('/dashboard', {
                    templateUrl : 'views/game/dashboard.html',
                    controller: 'overviewDashboard'
                })
                .when('/games/:id/details',{
                    templateUrl : 'views/game/score/details.html',
                    controller : 'detailGameController'
                })
                //##### PLAY #####
                    .when('/games/:id/levels',{
                        templateUrl : 'views/game/play/levels.html',
                        controller : 'levelController'
                    })
                    .when('/games/:id/levels/:activelevel',{
                        templateUrl : 'views/game/play/levels.html',
                        controller : 'levelController'
                    })
                    .when('/play', {
                        templateUrl : 'views/game/play/play.html',
                        controller : 'playController'
                    })
            //##### ADMIN #####
                .when('/admin/overview', {
                    templateUrl : 'views/game/admin/overview.html',
                    controller: 'adminOverview'
                })
                //##### GAME CONTROLS #####
                    .when('/admin/createGame', {
                        templateUrl : 'views/game/admin/edit.html',
                        controller : 'newGameController'
                    })
                    .when('/admin/dashboard/:id', {
                        templateUrl : 'views/game/dashboard.admin.html',
                        controller: 'adminDashboard'
                    })
                    .when('/admin/games/:gameid/levels/:levelid', {
                        templateUrl : 'views/game/admin/controlcenter.html',
                        controller : 'levelControlController'
                    })
                    .when('/admin/games/:gameid/levels/:levelid/editscores', {
                        templateUrl : 'views/game/level/score_edit.html',
                        controller : 'editLevelScoreController'
                    })
                    .when('/admin/games/:id/details', {
                        templateUrl : 'views/game/score/details.admin.html',
                        controller : 'adminScoreDetails'
                    })
                    .when('/admin/games/:gameid/teams/:id', {
                        templateUrl : 'views/team/details.admin.html',
                        controller : 'adminTeamDetailsController'
                    })
                //##### MAIL #####
                    .when('/admin/:id/mail', {
                        templateUrl : 'views/mail/send.admin.html',
                        controller : 'mailController'
                    })

                //#### ANSWERS #####
                    .when('/admin/teams/:id/answers', {
                        templateUrl : 'views/game/play/answer.overview.admin.html',
                        controller : 'teamAnswersController'
                    })
            //##### STAKEHOLDERS #####
                .when('/admin/stakeholders', {
                    templateUrl : 'views/stakeholders/admin/controlcenter.html',
                    controller : 'adminStakeholderOverviewController'
                })
                .when('/admin/stakeholders/new', {
                    templateUrl: 'views/stakeholders/admin/edit.html',
                    controller : 'adminStakeholderNewController'
                })
                .when('/admin/stakeholders/:id', {
                    templateUrl : 'views/stakeholders/admin/details.html',
                    controller : 'adminStakeholderDetailController'
                })
                .when('/admin/stakeholders/:id/edit', {
                    templateUrl : 'views/stakeholders/admin/edit.html',
                    controller : 'adminStakeholderEditController'
                })
                .when('/admin/stakeholders/:id/delete', {
                    templateUrl : 'views/stakeholders/admin/delete.html',
                    controller : 'adminStakeholderDeleteController'
                })
                .when('/stakeholders', {
                    templateUrl : 'views/stakeholders/team/controlcenter.html',
                    controller : 'stakeholderOverviewController'
                })
                .when('/stakeholders/:id', {
                    templateUrl : 'views/stakeholders/team/details.html',
                    controller : 'stakeholderDetailController'
                })
            //##### SCORE ENGINE ######
                .when('/admin/scoreEngineOverview', {
                    templateUrl : 'views/scoreengine/overview.html',
                    controller : 'scoreEngineOverviewController'
                })
                .when('/admin/scoreEngineEdit/:id', {
                    templateUrl : 'views/scoreengine/edit.html',
                    controller : 'scoreEngineEditController'
                })
                .when('/admin/createScoreEngine', {
                    templateUrl : 'views/scoreengine/new.html',
                    controller : 'newScoreEngineController'
                })
            //#####  KNOWLEDGE AREAS #####
                .when('/admin/knowledgeareas/edit', {
                    templateUrl : 'views/knowledgearea/edit.html',
                    controller : 'editKnowledgeAreasController'
                })
            //##### INFO #####
                .when('/admin/info',{
                    templateUrl : 'views/info/overview.html',
                    controller:'adminInfo'
                })
                .when('/admin/info/create', {
                    templateUrl : 'views/info/edit.html',
                    controller : 'adminInfo'
                })
                .when('/admin/info/update/:id', {
                    templateUrl : 'views/info/edit.html',
                    controller : 'adminInfo'
                })
                .when('/info', {
                    templateUrl : 'views/info/overview.html',
                    controller : 'info'
                })
                .when('/info/:id',{
                    templateUrl : 'views/info/details.html',
                    controller: 'info'
                })
            .otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        $httpProvider.interceptors.push('httpInterceptor');
    });