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

    $scope.getLevels = function(id){
        $location.path('/admin/' + id + '/levels');
    }

});