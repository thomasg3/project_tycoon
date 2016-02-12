/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('registration', function($rootScope, $scope, $http, $routeParams,$location, TeamResource) {
    $scope.oldUsername = $routeParams.username;

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
                                $rootScope.MainUser = data;
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