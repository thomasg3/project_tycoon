/**
 * Created by thomas on 09/02/16.
 */
angular.module('projecttycoonControllers', [])
    .controller('home', function($scope, $http) {
        $http.get('/resource/').success(function(data) {
            $scope.greeting = data;
        })
    })
    .controller('navigation', function($rootScope, $scope, $http, $location, TeamResource) {
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
                        TeamResource.isRegisterd({teamname: $scope.credentials.username}, function(data) {
                            console.log(data);
                            alert(JSON.stringify(data));
                            if(data.registerd){
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
                $http.post('logout', {}).success(function(){
                    $rootScope.authenticated = false;
                    $location.path("/");
                }).error(function(){
                    $rootScope.authenticated = false;
                    $location.path("/");
                });
            }
        })
    .controller('dashboard', function($rootScope, $scope, $http, $location, TeamResource){
        $scope.teams = TeamResource.getAll();
    })
    .controller('registration', function($rootScope, $scope, $http, $routeParams,$location, TeamResource) {
        $scope.oldUsername = $routeParams.username;
        $scope.initTeam = function(){

            var data = {
                "oldUsername": $scope.oldUsername,
                "oldPassword": $scope.credentials.oldPassword,
                "newUsername": $scope.credentials.username,
                "newPassword": $scope.credentials.password
            };

            $http.post('/initTeam', data).success(function(){
                $location.path('/');
            }).error(function(){
                alert("post error")
            });

        }
    });

