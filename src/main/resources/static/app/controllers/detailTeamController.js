/**
 * Created by thomas on 16/02/16.
 */
angular.module('projecttycoonControllers')
    .controller('detailTeamController', function($scope, $routeParams,TeamResource){
        TeamResource.get({id : $routeParams.id}, function(data){
            $scope.team = data;
        });
    });