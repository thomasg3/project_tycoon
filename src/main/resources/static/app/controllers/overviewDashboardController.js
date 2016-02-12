/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('overviewDashboard', function($scope,$rootScope, $http, TeamResource, GameResource) {
    TeamResource.get({id : $rootScope.MainUser.id}, function(data){
        GameResource.getGameByUsername({teamname: $rootScope.MainUser.teamname}, function(game){
            $scope.game = game;
        })
        $scope.team = data;
    });
});