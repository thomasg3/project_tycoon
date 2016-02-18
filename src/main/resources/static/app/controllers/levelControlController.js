/**
 * Created by thomas on 17/02/16.
 */
angular.module('projecttycoonControllers')
    .controller('levelControlController', function($scope, $routeParams, $http ,MainUserResource, GameResource, LevelResource){
        var updateList = function() {GameResource.get({id: $routeParams.gameid}, function(data){
            $scope.game = data;
            $scope.levels = data.levels;
        })};
        $scope.errors = [];
        updateList();
        if($routeParams.levelid != 0){
            LevelResource.get({id: $routeParams.levelid}, function(level){
                $scope.currentLevel = level;

            })
        }

        $scope.change = function(to){
            $http.get('/api/levels/'+$scope.currentLevel.id+'/change/'+to)
                .success(function(data){
                    $scope.currentLevel = data;
                    updateList();
                })
                .error(function(err){
                    $scope.errors.push('Oops, something went wrong...');
                });
        }

    });