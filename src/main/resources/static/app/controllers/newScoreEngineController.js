/**
 * Created by Jeroen on 22-2-2016.
 */

angular.module('projecttycoonControllers')
    .controller('newScoreEngineController', function($scope, $location ,KnowledgeAreaAdminResource, GameAdminResource, ScoreEngineTemplateAdminResource){
        $scope.knowledgeareas = KnowledgeAreaAdminResource.query();
        $scope.scoreenginetemplate = new ScoreEngineTemplateAdminResource();
        $scope.freeze = false;
        $scope.submit = function(){
            $scope.freeze = true;
            $scope.scoreenginetemplate.$save(
                function(data){
                    $location.path('/admin/scoreEngineOverview').replace();
                },
                function(err){$scope.freeze = false;});
        };
    });