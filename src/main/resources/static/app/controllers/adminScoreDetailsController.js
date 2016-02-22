/**
 * Created by Jeroen on 22-2-2016.
 */
angular.module('projecttycoonControllers')
    .controller('adminScoreDetails', function($scope, GameAdminResource, $routeParams){
        GameAdminResource.get({id : $routeParams.id}, function(data){
            $scope.game = data;
        });
    });