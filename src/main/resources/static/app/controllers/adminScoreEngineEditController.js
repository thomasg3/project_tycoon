/**
 * Created by Jeroen on 23-2-2016.
 */

angular.module('projecttycoonControllers')
    .controller('scoreEngineEditController', function($scope, ScoreEngineAdminResource, $routeParams){
        ScoreEngineAdminResource.get({id: $routeParams.id}, function(data){
            $scope.scoreEngine = data;
        });
    }).directive('myLevel', function($http, QuestionAdminResource) {
        return {
            restrict: 'E',
            scope: {
                my_level: '=level'
            },
            templateUrl: "views/game/level/level-iso.html",
            link: function ($scope) {

            }
        };
    }).directive('levelkn', function(QuestionAdminResource) {
        return {
            restrict: 'E',
            scope: {
                my_levelkn: '=levelknowledgearea'
            },
            templateUrl: "views/game/level/levelKnowledgeArea-iso.html",
            link: function ($scope, $attr) {

                QuestionAdminResource.getFormats(function(data){
                    $scope.formats = data;
                    for(var i = 0; i<$scope.formats.length; i++){
                        if($scope.my_levelkn.question.format === $scope.formats[i].format){
                            $scope.selected = $scope.formats[i];
                        }
                    }
                });

                $scope.saved = false;
                if($scope.question){
                    $scope.saved = true;
                }
                $scope.addQuestion = function(lk) {
                    QuestionAdminResource.get({id : lk.question.id}, function(question){
                        question.question = lk.question.question;
                        question.format = $scope.selected;
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
                levelkn: '=levelknowledgearea'
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