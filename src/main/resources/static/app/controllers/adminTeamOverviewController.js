/**
 * Created by Jeroen on 23-2-2016.
 */

angular.module('projecttycoonControllers')
    .controller('teamOverviewController', function($scope, GameAdminResource, $routeParams){
        GameAdminResource.getTeamsForOverview({id : $routeParams.id}, function(data){
            $scope.teams = data;
        });
        GameAdminResource.get({id : $routeParams.id}, function(data){
            $scope.game = data;
        })
    });