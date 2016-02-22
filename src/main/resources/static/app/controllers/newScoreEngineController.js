/**
 * Created by Jeroen on 22-2-2016.
 */

angular.module('projecttycoonControllers')
    .controller('newScoreEngineController', function($scope, $location ,KnowledgeAreaAdminResource, GameAdminResource, ScoreEngineAdminResource){
        $scope.knowledgeareas = KnowledgeAreaAdminResource.query();
        $scope.scoreengine = new ScoreEngineAdminResource();
        $scope.freeze = false;
        $scope.submit = function(){
            $scope.freeze = true;
            $scope.scoreengine.$save(
                function(data){
                    $location.path('/#/adminOverview').replace();
                },
                function(err){$scope.freeze = false;});
        };
    });