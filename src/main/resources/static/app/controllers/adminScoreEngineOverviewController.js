/**
 * Created by Jeroen on 23-2-2016.
 */

angular.module('projecttycoonControllers')
    .controller('scoreEngineOverviewController', function($scope, ScoreEngineAdminResource, $routeParams){
        ScoreEngineAdminResource.getScoreEngineInfo(function(data){
            $scope.scoreEngines = data;
        });
    });