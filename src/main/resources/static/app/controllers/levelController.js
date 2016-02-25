/**
 * Created by Jeroen on 17-2-2016.
 */

angular.module('projecttycoonControllers')
    .controller('levelController', function($rootScope, $scope, GameResource, $routeParams, LevelResource){
        $scope.id = $routeParams.id;

        var findFirstOpenLevel = function(){
            GameResource.get({id : $routeParams.id}, function(game){
                $scope.levels = game.levels;
                for(var i = 0; i<= $scope.levels.length -1; i++){
                    if($scope.levels[i].state === "Open"){
                        $scope.activelevel = $scope.levels[i].id;
                        $scope.level = $scope.levels[i];
                        break;
                    }
                }

                /*
                LevelResource.get({id: $scope.activelevel}, function(level){
                    $scope.level = level;
                });
                */
            });
        };

        var isRouteParmIdOpen = function(){
            GameResource.get({id : $routeParams.id}, function(game){
                $scope.levels = game.levels;
                for(var i = 0; i<= $scope.levels.length -1; i++){
                    if($routeParams.activelevel == $scope.levels[i].id){
                        if($scope.levels[i].state === "Open"){
                            $scope.activelevel = $scope.levels[i].id;
                            $scope.level = $scope.levels[i];
                            break;
                        }
                    }
                }

                /*
                LevelResource.get({id: $scope.activelevel}, function(level){
                    $scope.level = level;
                });
                */
            });
        };

        if($routeParams.activelevel){
            isRouteParmIdOpen();
        }else{
            findFirstOpenLevel();
        }


    }).directive('questioninput', function($rootScope, KnowledgeAreaScoreResource, $http) {
        return {
            restrict: 'E',
            scope: {
                my_levelkn: '=levelknowledgearea',
                my_levelid: '@myLevelid'
            },
            templateUrl: "views/game/level/gameinput-iso.html",
            link: function ($scope) {
                $scope.answerd = false;
                for(var i = 0; i < $rootScope.MainUser.teamLevelPrestations.length; i++){
                    if($rootScope.MainUser.teamLevelPrestations[i].level.id == $scope.my_levelid){
                        for(var j = 0; j < $rootScope.MainUser.teamLevelPrestations[i].knowledgeAreaScores.length; j++){
                            if($rootScope.MainUser.teamLevelPrestations[i].knowledgeAreaScores[j].knowledgeArea.id == $scope.my_levelkn.knowledgeArea.id){
                                KnowledgeAreaScoreResource.get({id : $rootScope.MainUser.teamLevelPrestations[i].knowledgeAreaScores[j].id}, function(data){
                                    $scope.knowledgearea = new KnowledgeAreaScoreResource();
                                    $scope.knowledgearea = data;
                                    if($scope.knowledgearea.answer){
                                        $scope.answerd = true;
                                    }
                                });
                                break;
                            }
                        }
                    }
                }

                $scope.submitAnswer = function() {
                    $scope.knowledgearea.$update({id: $scope.knowledgearea.id}, function(data){
                        $scope.answerd = true;
                    });
                }

             }
        }

    });
