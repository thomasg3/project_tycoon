/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('dashboard', function($rootScope, $scope, $http, GameResource, $routeParams,TeamResource){
    GameResource.get({id : $routeParams.id}, function(data){
        $scope.game = data;
    });
    
    $scope.deleteTeam = function (id) {
        GameResource.deleteTeam({id : id}).$promise.then(function(){
            GameResource.get({id : $routeParams.id}, function(data){
                $scope.game = data;
            });
        })
    }
});