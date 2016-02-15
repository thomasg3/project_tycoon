/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('dashboard', function($rootScope, $scope, $http, GameResource, $routeParams){
    GameResource.get({id : $routeParams.id}, function(data){
        $scope.game = data;
    });
});


