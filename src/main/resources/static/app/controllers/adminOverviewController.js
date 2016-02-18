/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('adminOverview', function($scope, $http, $location, GameAdminResource) {
    GameAdminResource.query(function(data){
        $scope.games = data;
    });
    $scope.deleteGame = function(id){
        GameAdminResource.delete({id : id}, function(){
            GameAdminResource.query(function(data){
                $scope.games = data;
            });
        });

    }
    $scope.getGame = function(id){
        $location.path('/dashboard/' + id);
    }
    $scope.getLevels = function(id){
        $location.path('/admin/' + id + '/levels');
    }

});