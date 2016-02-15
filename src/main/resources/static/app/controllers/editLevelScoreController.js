/**
 * Created by thomas on 15/02/16.
 */
angular.module('projecttycoonControllers')
    .controller('editLevelScoreController', function($scope, $routeParams, $location,GameResource, LevelResource ,PrestationResource) {
        $scope.saving = true;
        $scope.teamLevelPres = [];
        GameResource.get({id : $routeParams.gameid}, function(data){
            $scope.game = data;
            for(i = 0; i < data.levels.length; i++){
                if(data.levels[i].id == $routeParams.levelid)
                    $scope.levelindex = i;

            }
            for(i = 0; i < data.teams.length; i++){
                $scope.teamLevelPres.push(data.teams[i].teamLevelPrestations);
            }
            $scope.saving = false;
        });
        LevelResource.get({id : $routeParams.levelid}, function(data){
            $scope.level = data;
        })
        $scope.save = function(){
            $scope.saving = true;

            PrestationResource.saveAll( [].concat.apply([], $scope.teamLevelPres),
                function(data){
                    $location.path('/games/'+$scope.game.id+'/details').replace();
                },
                function(err){
                    saving = false;
                }
            )
        }
    });