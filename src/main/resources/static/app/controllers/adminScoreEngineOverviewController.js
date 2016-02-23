/**
 * Created by Jeroen on 23-2-2016.
 */

angular.module('projecttycoonControllers')
    .controller('scoreEngineOverviewController', function($scope, ScoreEngineTemplateAdminResource, $routeParams){
        ScoreEngineTemplateAdminResource.query(function(data){
            $scope.scoreEnginesTemplates = data;
        });

        $scope.deleteScoreEngine = function(id){
            ScoreEngineAdminResource.delete({id : id}, function(data){
                $scope.scoreEngines = data;
            });
        }
    });