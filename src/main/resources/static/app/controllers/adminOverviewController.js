/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('adminOverview', function($scope, $http,$location, GameResource) {
    GameResource.getAll().$promise.then(function(data){
        $scope.games = data;
    });

    $scope.getGame = function(id){
        $location.path('/dashboard/' + id);
    }
    $scope.deleteGame = function(id){
        GameResource.delete({id : id}).$promise.then(function(){
            //refresh the view
            GameResource.getAll().$promise.then(function(data){
                $scope.games = data;
            });
            // $location.path('/adminOverview'); seems logical but doesnt work for some reason
        });

    }
});