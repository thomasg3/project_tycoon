/**
 * Created by thomas on 09/02/16.
 */
angular.module('projecttycoonControllers', [])
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

            var isRegisterd = function(credentials){
                $http.get('/isRegisterdTeam/' + credentials.username).success(function(data) {
                    return data;
                }).error(function(){
                    return false;
                });
            };

            authenticate();
            $scope.credentials = {};
            $scope.login = function() {
                authenticate($scope.credentials, function() {
                    if ($rootScope.authenticated) {
                        alert(isRegisterd($scope.credentials));
                        if(isRegisterd($scope.credentials)){
                            $location.path("/");
                        }else{
                            $location.path("/registerTeam/" + $scope.credentials.username);
                        }
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
    })
    .controller('registration', function($rootScope, $scope, $http, $routeParams,$location) {
        $scope.oldUsername = $routeParams.username;

        $scope.initTeam = function(){

            var data = {
                "oldUsername": $scope.oldUsername,
                "oldPassword": $scope.credentials.oldPassword,
                "newUsername": $scope.credentials.username,
                "newPassword": $scope.credentials.password
            };


            $http.post('/initTeam', data).success(function(){
                alert("Post succes");
                $location.path('/');
            }).error(function(){
                alert("post error")
            });

        }
    });

