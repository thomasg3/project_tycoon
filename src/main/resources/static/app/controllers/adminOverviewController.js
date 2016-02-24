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

    };
    $scope.getGame = function(id){
        $location.path('/admin/dashboard/' + id);
    };
    $scope.getLevels = function(id){
        $location.path('/admin/' + id + '/levels');
    };

    $scope.mail = function(id){
        $location.path('/admin/' + id + '/mail');
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