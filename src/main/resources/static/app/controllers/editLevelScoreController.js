/**
 * Created by thomas on 15/02/16.
 */
angular.module('projecttycoonControllers')
    .controller('editLevelScoreController', function($scope, $routeParams, $location,GameResource, LevelResource ,PrestationResource) {
        $scope.saving = true;
        GameResource.get({id : $routeParams.gameid}, function(data){
            $scope.game = data;
            for(i = 0; i < data.levels.length; i++){
                if(data.levels[i].id == $routeParams.levelid)
                    $scope.levelindex = i;

            }
            $scope.saving = false;
        });
        LevelResource.get({id : $routeParams.levelid}, function(data){
            $scope.level = data;
        })
        $scope.save = function(){
            $scope.saving = true;
            $scope.game.$save(
                function(data){
                    $location.path('/games/'+data.id+'/details').replace();
                },
                function(err){
                    saving = false;
                }
            )
        }
    });