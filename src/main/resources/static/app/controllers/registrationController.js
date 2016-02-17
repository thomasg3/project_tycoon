/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('registration', function($rootScope, $scope, $http, $routeParams,$location, TeamResource, GameResource,MainUserResource) {
    $scope.oldUsername = $routeParams.username;

    /*
    ----------------Onregistered user mag geen getGameByUsername doen----------------------
    GameResource.getGameByUsername({teamname:$scope.oldUsername}, function(game){
        alert("test");
        var index;
        var teamnames = [];
        for (index = 0; index < game.teams.length; ++index) {
            teamnames.push(game.teams[index].teamname);
            alert(JSON.stringify(teamnames));
        }
    });
    */


    $scope.initTeam = function(){

        var oldCredentials = {username : $scope.oldUsername, password : $scope.credentials.oldPassword};

        $http.post('logout', {}).success(function(){
            $rootScope.authenticated = false;
            $rootScope.authenticate(oldCredentials, function(){
                if ($rootScope.authenticated) {
                    TeamResource.search({teamname : $scope.oldUsername}, function(updateTeam){
                        updateTeam.teamname = $scope.credentials.username;
                        updateTeam.password = $scope.credentials.password;
                        updateTeam.$update({id : updateTeam.id}, function(){
                            TeamResource.get({id: updateTeam.id}, function(data){
                                MainUserResource.saveMainUser(data);
                                $location.path('/');
                            });
                        });
                    });
                    $scope.error = false;
                } else {
                    $scope.error = true;
                    $location.path('/registerTeam/' + $scope.oldUsername);
                }
            });
        }).error(function(){
            $rootScope.authenticated = false;
        });
    }
});