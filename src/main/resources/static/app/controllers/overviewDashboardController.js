/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('overviewDashboard', function($scope,$rootScope, $http,$window, TeamResource, GameResource,MainUserResource) {
   // TeamResource.get({id : $window.sessionStorage.MainUser.id}, function(data){
        GameResource.getGameByUsername({teamname: MainUserResource.getMainUser().teamname}, function(game){
            $scope.game = game;
        })
        //$scope.team = data;
    });
//});