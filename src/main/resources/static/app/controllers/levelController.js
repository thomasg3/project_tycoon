/**
 * Created by Jeroen on 17-2-2016.
 */

angular.module('projecttycoonControllers')
    .controller('levelController', function($rootScope, $scope, GameResource, $routeParams){
        //avtive moet meegegevenworden on routeparam
        $scope.gameid = $routeParams.id;
        $scope.activelevel = 1;
        if($routeParams.activelevel){
            $scope.activelevel = $routeParams.activelevel;
        }

        GameResource.get({id : $routeParams.id}, function(game){
            $scope.levels = game.levels;
        });
    }).directive('levelinput', function($http, LevelResource) {
        return {
            restrict: 'E',
            scope: {
                my_levelid: '=levelid'
            },
            templateUrl: "views/game/level/gameinput-iso.html",
            link: function ($scope) {
                LevelResource.getPublicLevel({id: $scope.my_levelid}, function(level){
                    $scope.level = level;
                });
            }
        };
    }).directive('questioninput', function($rootScope, KnowledgeAreaScoreResource, $http) {
        return {
            restrict: 'E',
            scope: {
                my_levelkn: '=levelknowledgearea',
                my_levelid: '=levelid'
            },
            templateUrl: "views/game/level/questioninput-iso.html",
            link: function ($scope) {
                $scope.submitAnswer = function() {
                    for(var i = 0; i < $rootScope.MainUser.teamLevelPrestations.length; i++){
                        if($rootScope.MainUser.teamLevelPrestations[i].level.id === $scope.my_levelid){
                            for(var j = 0; j < $rootScope.MainUser.teamLevelPrestations[i].knowledgeAreaScores.length; j++){
                                if($rootScope.MainUser.teamLevelPrestations[i].knowledgeAreaScores[j].knowledgeArea.id === $scope.my_levelkn.knowledgeArea.id){
                                    KnowledgeAreaScoreResource.get({id : $rootScope.MainUser.teamLevelPrestations[i].knowledgeAreaScores[j].id}, function(data){
                                        data.answer = $scope.answer;
                                        data.$update({id: data.id}, function(data){
                                            alert(JSON.stringify(data));
                                        });
                                    });
                                }
                            }
                        }
                    }
                }
             }
        }

    });
