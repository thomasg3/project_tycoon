/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('navigation', function($rootScope, $scope, $http, $location, $window,MainUserResource, TeamResource) {
    $rootScope.MainUser = MainUserResource.getMainUser();
    $rootScope.authenticate = function(credentials, callback) {

        var headers = credentials ? {authorization : "Basic "
        + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('user', {headers : headers}).success(function(data) {
            if (data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback();
        }).error(function() {
            $rootScope.authenticated = false;
            callback && callback();
        });

    };
    $rootScope.authenticate();
    $scope.credentials = {};
    $scope.login = function() {
        $rootScope.authenticate($scope.credentials, function() {
            if ($rootScope.authenticated) {

                TeamResource.search({teamname: $scope.credentials.username},function(data){
                    //$window.sessionStorage.MainUser=JSON.stringify(data);
                    MainUserResource.saveMainUser(data);
                    $rootScope.MainUser=MainUserResource.getMainUser();
                })
                TeamResource.isRegistered({teamname: $scope.credentials.username}, function(data) {
                    if(data.registered){
                        $location.path("/");
                    }else{
                        $location.path("/registerTeam/" + $scope.credentials.username);
                    }
                    $scope.error = false;
                });

            } else {
                $location.path("/login");
                $scope.error = true;
            }
        });
    };
    $scope.logout = function() {
        $http.post('/logout', {}).success(function(){
            MainUserResource.saveMainUser({});
            $rootScope.authenticated = false;
            $location.path("/");
        }).error(function(){
            $rootScope.authenticated = false;
            $location.path("/");
        });
    }
});