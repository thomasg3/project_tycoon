/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
    .controller('adminDashboard', function($rootScope, $scope, $http, GameAdminResource, $routeParams,TeamAdminResource){
        var update = function(data){
            $scope.game = data;
            var ctx = $("#teamEvolutionGraph").get(0).getContext("2d");
            var labels = ["Start"].concat($scope.game.levels.map(function(level){return level.name;}));
            var datasets = [];
            var unit = 360/$scope.game.teams.length;
            for(i = 0; i<$scope.game.teams.length; i++){
                var raw_data = $scope.game.teams[i].teamLevelPrestations.map(function(pres){return pres.levelScore;});
                for(j = 1; j<raw_data.length; j++){
                    raw_data[j] += raw_data[j-1];
                }
                datasets.push({
                    label: $scope.game.teams[i],
                    fillColor: "hsla("+unit*i+",100%, 50%,0.2)",
                    strokeColor: "hsla("+unit*i+",100%, 50%,1)",
                    pointColor: "hsla("+unit*i+",100%, 50%,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "hsla("+unit*i+",100%, 50%,1)",
                    data: [0].concat(raw_data)
                });
            }
            var data = {
                labels: labels,
                datasets: datasets
            };
            var chart = new Chart(ctx).Line(data, {
                //scaleBeginAtZero : true
            });
        };

        if($routeParams.id){
            GameAdminResource.get({id : $routeParams.id}, function(data){
                update(data);
            });
        }


        $scope.deleteTeam = function (id) {
            GameAdminResource.deleteTeam({id : id}, function(data){
                update(data);
            })
        };

        $scope.recalculate = function(id){
            GameAdminResource.recalculateGame({id : id}, function(data){
                $scope.game = data;
                update(data);
            })
        };


    });
