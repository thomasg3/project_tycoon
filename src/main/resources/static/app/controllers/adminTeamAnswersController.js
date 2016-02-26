/**
 * Created by Jeroen on 23-2-2016.
 */

angular.module('projecttycoonControllers')
    .controller('teamAnswersController', function($scope, TeamAdminResource, $routeParams){
        TeamAdminResource.get({id : $routeParams.id}, function(data){
            $scope.team = data;
        });
    })
    .directive('teamanswers', function($rootScope, KnowledgeAreaScoreResource, $http) {
    return {
        restrict: 'E',
        scope: {
            my_tlp: '=teamlevelprestation',
            my_team: '=team'
        },
        templateUrl: "views/team/answers-iso.html",
        link: function ($scope) {

            $scope.getGivenAnswer = function(levelkn){
                for(var i =0; i< $scope.my_tlp.knowledgeAreaScores.length; i++){
                    if($scope.my_tlp.knowledgeAreaScores[i].knowledgeArea.id == levelkn.knowledgeArea.id){
                        return $scope.my_tlp.knowledgeAreaScores[i].answer;
                    }
                }
            }

        }
    }

});
