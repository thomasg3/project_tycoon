/**
 * Created by michael on 12/02/16.
 */
angular.module('projecttycoonControllers')
    .controller('newGameController', function($scope, $location ,KnowledgeAreaResource, GameResource){
        $scope.knowledgeareas = KnowledgeAreaResource.query();
        $scope.game = new GameResource();
        $scope.freeze = false;
        $scope.submit = function(){
            $scope.freeze = true;
            $scope.game.$save(function(data){
                $location.path('/dashboard/'+data.id).replace();

            }, function(err){$scope.freeze = false;});
        };
    });