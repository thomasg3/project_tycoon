/**
 * Created by michael on 12/02/16.
 */
angular.module('projecttycoonControllers')
    .controller('newGameController', function($scope, $location , GameAdminResource, ScoreEngineTemplateAdminResource){


        $scope.game = new GameAdminResource();

        $scope.scoreenginestemplates = ScoreEngineTemplateAdminResource.query(function(data){
            $scope.selectedItem = data[0];
            $scope.game.scoreengineid = data[0].id;
        });

        $scope.notifyChange= function(){
            while($scope.game.levels > $scope.game.allLevels.length){
                $scope.game.allLevels.push({});
            }
            while($scope.game.levels < $scope.game.allLevels.length){
                $scope.game.allLevels.pop();
            }
        };
        $scope.game.allLevels = [];
        $scope.freeze = false;

        $scope.dropboxitemselected = function (scoreengine) {
            $scope.game.scoreengineid = scoreengine.id;
            $scope.selectedItem = scoreengine;
        };

        $scope.submit = function(){
            $scope.freeze = true;
            $scope.game.$save(
                function(data){
                    $location.path('/#/adminOverview').replace();
                },
                function(err){$scope.freeze = false;});
        };
    });