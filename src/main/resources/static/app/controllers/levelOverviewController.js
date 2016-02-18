/**
 * Created by Jeroen on 15-2-2016.
 */

angular.module('projecttycoonControllers')
    .controller('levelOverview', function($location, $rootScope, $scope, $http, GameAdminResource, $routeParams, QuestionAdminResource){
        $scope.game = new GameAdminResource();
        GameAdminResource.get({id : $routeParams.id}, function(data){
            $scope.game = data;
            $scope.newQuestions = new Array($scope.game.levels);
        });


        $scope.addLevel = function(){
            GameAdminResource.get({id : $scope.game.id}, function(game){
                game.levels.push($scope.level);
                game.$update({id : $scope.game.id},function(){
                    $scope.game = game;
                    $scope.newQuestions = new Array($scope.game.levels);
                });
            });
        }
    }).directive('myLevel', function($http, GameAdminResource) {
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
    }).directive('levelkn', function(QuestionAdminResource) {
    return {
        restrict: 'E',
        scope: {
            my_levelkn: '=levelknowledgearea',
            my_gameid: '=gameid'
        },
        templateUrl: "views/game/level/levelKnowledgeArea-iso.html",
        link: function ($scope, $attr) {
            $scope.saved = false;
            if($scope.question){
                $scope.saved = true;
            }
            $scope.addQuestion = function(lk) {
                QuestionAdminResource.get({id : lk.question.id}, function(question){
                    question.question = lk.question.question;
                    question.format = lk.question.format;
                    question.$update({id : question.id}, function(data){
                        $scope.saved = true;
                    });
                });
            };

            $scope.deleteQuestion = function(lk) {
                $scope.saved = false;
                QuestionResourceService.get({id : lk.question.id}, function(question){
                    question.question = null;
                    question.format = null;
                    question.$update({id : question.id}, function(data){

                    });
                });
            };

        }
    };
    }).directive('answer', function(QuestionAdminResource) {
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
                $scope.levelkn.question.answers.push({answer: "", score:""});
                $scope.addAnswer = function(answer) {
                    QuestionAdminResource.get({id : $scope.levelkn.question.id}, function(question){
                        question.answers = $scope.levelkn.question.answers;
                        question.$updateAnswers({id : question.id}, function(data){
                            $scope.levelkn.question.answers.push({answer: "", score:""});
                        });
                    });
                };

                $scope.deleteAnswer = function(answer) {
                    for(var i=0; i< $scope.levelkn.question.answers.length; i++) {
                        if($scope.levelkn.question.answers[i] === answer) {
                            $scope.levelkn.question.answers.splice(i, 1);
                            break;
                        }
                    }

                    QuestionAdminResource.get({id : $scope.levelkn.question.id}, function(question){
                        $scope.levelkn.question.answers.pop();
                        question.answers = $scope.levelkn.question.answers;
                        question.$updateAnswers({id : question.id}, function(data){
                            $scope.levelkn.question.answers.push({answer: "", score:""});
                        });
                    });
                }
            }
        };
    });


