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

                $scope.addQuestion = function(){
                    GameResource.get({id : $scope.my_gameid}, function(game){
                        var index;
                        for (index = 0; index < game.levels.length; ++index) {
                            if(game.levels[index].id === $scope.my_level.id){
                                if(!game.levels[index].questions){
                                    game.levels[index].questions = [];
                                }
                                if(!$scope.my_level.questions){
                                    $scope.my_level.questions = [];
                                }
                                game.levels[index].questions.push($scope.newquestion);
                                $scope.my_level.questions.push($scope.newquestion);
                                $scope.newquestion = null;
                            }
                        }
                        game.$update({id: game.id}, function(data){
                        });
                    });
                    //return $scope.my_level;
                };
            }
        };
    });


