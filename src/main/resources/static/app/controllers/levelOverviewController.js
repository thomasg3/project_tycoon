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
            $scope.game.levels.push($scope.level);
            $scope.game.$update({id : $scope.game.id},function(data){
                $location.path("/admin/" + $scope.game.id + "/levels");

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
                                game.levels[index].questions.push($scope.newquestion);
                            }
                        }
                        game.$update({id: game.id}, function(data){

                            location.reload();
                        });
                    });
                    return $scope.my_level;
                };
            }
        };
    });


