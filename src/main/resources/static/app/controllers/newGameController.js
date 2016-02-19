/**
 * Created by michael on 12/02/16.
 */
angular.module('projecttycoonControllers')
    .controller('newGameController', function($scope, $location ,KnowledgeAreaAdminResource, GameAdminResource){
        $scope.knowledgeareas = KnowledgeAreaAdminResource.query();
        $scope.game = new GameAdminResource();
        $scope.notifyChange= function(){
            while($scope.game.levels > $scope.game.allLevels.length){
                $scope.game.allLevels.push({});
            }
            while($scope.game.levels < $scope.game.allLevels.length){
                $scope.game.allLevels.pop();
            }
        }
        $scope.game.allLevels = [];
        $scope.freeze = false;
        $scope.submit = function(){
            $scope.freeze = true;
            $scope.game.$save(
                function(data){
                    $location.path('/dashboard/'+data.id).replace();
                },
                function(err){$scope.freeze = false;});
        };
    });