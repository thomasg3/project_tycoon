/**
 * Created by Jeroen on 15-2-2016.
 */

angular.module('projecttycoonControllers')
    .controller('levelOverview', function($rootScope, $scope, $http, LevelResource, GameResource, $routeParams){
        $scope.game = new GameResource();
        GameResource.get({id : $routeParams.id}, function(data){
            $scope.game = data;
        });
        $scope.level = new LevelResource();

        /*/
        $scope.addQuestion = function(id){
            $scope.question.$save(function(data){
                alert(JSON.stringify(data));
            });
        };*/

        $scope.addLevel = function(){
            $scope.game.

            $scope.level.$save(function(data){
                alert(JSON.stringify(data));
            });
        }
    });


