/**
 * Created by Jeroen on 23-2-2016.
 */

angular.module('projecttycoonControllers')
    .controller('scoreEngineEditController', function($scope, ScoreEngineTemplateAdminResource, $routeParams){
        ScoreEngineTemplateAdminResource.get({id: $routeParams.id}, function(data){
            $scope.scoreEngineTemplate = data;
        });
    }).directive('myLevel', function($http, LevelTemplateResource, LevelAdminResource) {
        return {
            restrict: 'E',
            scope: {
                my_level: '=level'
            },
            templateUrl: "views/game/level/level-iso.html",
            link: function ($scope) {

                $scope.changeLevelName = function(id) {
                    LevelTemplateResource.get({id : id}, function(data){
                        data.name = $scope.my_level.name;
                        data.$update({id : id}, function(data){
                            $scope.my_level = data;
                        });
                    });

                };

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
                if($scope.my_levelkn.question.question){
                    $scope.saved = true;
                }
                $scope.addQuestion = function(lk) {
                    if(lk.question.question && $scope.selected){
                        QuestionAdminResource.get({id : lk.question.id}, function(question){
                            question.question = lk.question.question;
                            question.format = $scope.selected;
                            question.$update({id : question.id}, function(data){
                                $scope.saved = true;
                            });
                        });
                    }
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
                $scope.elements = $scope.levelkn.question.answers.length -1;

                $scope.addAnswer = function(answer) {
                    QuestionAdminResource.get({id : $scope.levelkn.question.id}, function(question){
                        if($scope.levelkn.question.answers[$scope.levelkn.question.answers.length -1].answer == ""){
                            $scope.levelkn.question.answers.pop();
                        }

                        question.answers = $scope.levelkn.question.answers;
                        question.$updateAnswers({id : question.id}, function(data){
                            $scope.levelkn.question.answers = data.answers;
                            $scope.levelkn.question.answers.push({answer: "", score:""});
                            $scope.elements = $scope.levelkn.question.answers.length-1;
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
                            $scope.elements = $scope.levelkn.question.answers.length -1;
                        });
                    });
                }
            }
        };
    });