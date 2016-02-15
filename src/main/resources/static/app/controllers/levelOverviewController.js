/**
 * Created by Jeroen on 15-2-2016.
 */

angular.module('projecttycoonControllers')
    .controller('levelOverview', function($location, $rootScope, $scope, $http, GameResource, $routeParams){
        $scope.game = new GameResource();
        GameResource.get({id : $routeParams.id}, function(data){
            $scope.game = data;
        });


        $scope.addLevel = function(){
            $scope.game.levels.push($scope.level);
            $scope.game.$update({id : $scope.game.id},function(data){
                $location.reload();
            });
        }
    });


