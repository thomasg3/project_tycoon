/**
 * Created by thomas on 15/02/16.
 */
angular.module('projecttycoonControllers')
    .controller('editLevelScoreController', function($scope, $routeParams, GameResource, PrestationResource) {
        GameResource.get({id : $routeParams.gameid}, function(data){
            $scope.game = data;
            for(i = 0; i < data.levels.length; i++){
                console.log(data.levels[i].name);
                if(data.levels[i].id == $routeParams.levelid){
                    $scope.level = data.levels[i];
                    break;
                }
            }
        });
    });