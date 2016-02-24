/**
 * Created by Jeroen on 22-2-2016.
 */
angular.module('projecttycoonControllers')
    .controller('adminScoreDetails', function($scope, GameAdminResource, $routeParams){
        GameAdminResource.get({id : $routeParams.id}, function(data){
            $scope.game = data;
        });

        $scope.recalculateLevel = function(levelid) {
            GameAdminResource.recalculateLevel({id: $scope.game.id, levelid: levelid}, function (data) {
                $scope.game = data;
            });
        }
    });