/**
 * Created by thomas on 16/02/16.
 */
angular.module('projecttycoonControllers')
    .controller('detailTeamController', function($scope, $routeParams,TeamResource){
        $scope.gameid = $routeParams.gameid;
        TeamResource.get({id : $routeParams.id}, function(data){
            $scope.team = data;
            var ctx = $("#knowledgeAreaRader").get(0).getContext("2d");
            var labels = $scope.team.teamLevelPrestations[0].knowledgeAreaScores.map(function(kas){return kas.knowledgeArea.name;});

            var datas = labels.map(function(label){return 0;});
            for(l = 0; l < $scope.team.teamLevelPrestations.length; l++) {
                for (i = 0; i < labels.length; i++) {
                    datas[i] += $scope.team.teamLevelPrestations[l].knowledgeAreaScores[i].score;
                }
            }


            var chart = new Chart(ctx).Radar({
                labels: labels,
                datasets: [
                    {
                        label: $scope.team.teamname,
                        fillColor: "rgba(255, 0, 0, .2)",
                        strokeColor: "rgba(255, 0, 0, 1)",
                        pointColor: "rgba(255, 0, 0, 1)",
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(255, 0, 0, 1)",
                        data: datas
                    }
                ]
            }, {
                //scaleBeginAtZero : true
            });
            $scope.knowledgeareaTotals = datas;
        });

    });