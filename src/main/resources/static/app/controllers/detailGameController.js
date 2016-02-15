/**
 * Created by thomas on 12/02/16.
 */
angular.module('projecttycoonControllers')
    .controller('detailGameController', function($scope, GameResource, $routeParams){
        GameResource.get({id : $routeParams.id}, function(data){
            $scope.game = data;
        });
    });