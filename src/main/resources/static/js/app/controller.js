/**
 * Created by thomas on 09/02/16.
 */
angular.module('projecttycoonControllers', [])
    .controller('gameController', function($scope, $http,$location) {

    $scope.SendData=function() {

        $http({
            method: "post",
            url:"/createGame?gameName="+$scope.gameName+"&teamAmounts="+$scope.teamAmounts,
            'headers': {'Content-Type' : 'application/json'},
            data : {
            "gameName": $scope.gameName,
            "teamAmounts" : $scope.teamAmounts
            } })
            .success(function (response) {
                //navigate to the game?
                $location.path ( "/game/"+response.id);
            });
    }
})
    .controller('home', function($scope, $http) {
        $http.get('/resource/').success(function(data) {
            $scope.greeting = data;
        })
    })
    .controller('navigation', function($rootScope, $scope, $http, $location) {
            var authenticate = function(credentials, callback) {

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

            authenticate();
            $scope.credentials = {};
            $scope.login = function() {
                authenticate($scope.credentials, function() {
                    if ($rootScope.authenticated) {
                        $location.path("/");
                        $scope.error = false;
                    } else {
                        $location.path("/login");
                        $scope.error = true;
                    }
                });
            };
            $scope.logout = function() {
                $http.post('logout', {}).success(function(){
                    $rootScope.authenticated = false;
                    $location.path("/");
                }).error(function(){
                    $rootScope.authenticated = false;
                    $location.path("/");
                });
            }
        });