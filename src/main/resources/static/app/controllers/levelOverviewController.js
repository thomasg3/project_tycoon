/**
 * Created by Jeroen on 15-2-2016.
 */

angular.module('projecttycoonControllers')
    .controller('levelOverview', function($location, $rootScope, $scope, $http, GameResource, $routeParams){
        $scope.game = new GameResource();
        GameResource.get({id : $routeParams.id}, function(data){
            $scope.game = data;
            $scope.newQuestions = new Array($scope.game.levels);
        });


        $scope.addLevel = function(){
            GameResource.get({id : $scope.game.id}, function(game){
                game.levels.push($scope.level);
                game.$update({id : $scope.game.id},function(){
                    $scope.game = game;
                    $scope.newQuestions = new Array($scope.game.levels);
                });
            });
        }
    }).directive('myLevel', function($http, GameResource) {
        return {
            restrict: 'E',
            scope: {
                my_level: '=level',
                my_gameid: '=gameid'
            },
            templateUrl: "views/game/level/level-iso.html",
            link: function ($scope) {

            }
        };
    }).directive('levelkn', function(GameResource) {
    return {
        restrict: 'E',
        scope: {
            my_levelkn: '=levelknowledgearea',
            my_gameid: '=gameid'
        },
        templateUrl: "views/game/level/levelKnowledgeArea-iso.html",
        link: function ($scope, $attr) {
            $scope.button = "Add";
            $scope.saved = false;

            $scope.addQuestion = function(lk) {
                $scope.button = "Remove";
                $scope.saved = true;

                //lk..
                GameResource.get({id: $scope.my_gameid}, function(game){
                    for(var i=0; i< game.scoreEngine.levels.length; i++) {
                        for(var j=0; j< game.scoreEngine.levels[i].levelKnowledgeAreas.length; j++) {
                            if($scope.my_levelkn.id === game.scoreEngine.levels[i].levelKnowledgeAreas[j].id){
                                game.scoreEngine.levels[i].levelKnowledgeAreas[j].question = $scope.my_levelkn.question;
                                game.$update({id : game.id}, function(data){
                                    $scope.my_levelkn.question.saved = true;
                                })
                            }
                        }
                    }
                });
            };

            $scope.deleteQuestion = function(lk) {
                $scope.button = "Add";
                delete $scope.levelkn.question;
                for(var i=0; i< game.scoreEngine.levels.length; i++) {
                    for(var j=0; j< game.scoreEngine.levels[i].levelKnowledgeAreas.length; j++) {
                        if($scope.levelkn.id === game.scoreEngine.levels[i].levelKnowledgeAreas[j].id){
                            delete game.scoreEngine.levels[i].levelKnowledgeAreas[j].question;
                            game.$update({id : game.id}, function(data){

                            })
                        }
                    }
                }

            };

        }
    };
    }).directive('answer', function(GameResource) {
        return {
            restrict: 'E',
            scope: {
                levelkn: '=levelknowledgearea',
                my_gameid: '=gameid'
            },
            templateUrl: "views/game/level/answer-iso.html",
            link: function ($scope, $attr) {
                if(!$scope.levelkn.question.answers){
                    $scope.levelkn.question.answers =[];
                }
                $scope.levelkn.question.answers.push({answer: "", score:""})
                $scope.addAnswer = function(answer) {
                    $scope.levelkn.question.answers.push({answer: "", score:""})
                }

                $scope.deleteAnswer = function(answer) {
                    for(var i=0; i<$scope.levelkn.question.answers.length; i++) {
                        if($scope.levelkn.question.answers[i] === answer) {
                            $scope.levelkn.question.answers.splice(i, 1);
                            break;
                        }
                    }
                }
            }
        };
    });


