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
    .controller('home', function($scope, $http, $rootScope) {
        $scope.data = $rootScope.MainUser;
    })
    .controller('navigation', function($rootScope, $scope, $http, $location, TeamResource) {
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
                        TeamResource.search({teamname : $scope.credentials.username}, function(data){
                            $rootScope.MainUser = data;
                            TeamResource.isRegisterd({teamname: $scope.credentials.username}, function(data) {
                                if(data.registerd){
                                    $location.path("/");
                                }else{
                                    $location.path("/registerTeam/" + $scope.credentials.username);
                                }
                                $scope.error = false;
                            });
                        });
                    } else {
                        $location.path("/login");
                        $scope.error = true;
                    }
                });
            };
            $scope.logout = function() {
                $http.post('/logout', {}).success(function(){
                    $rootScope.authenticated = false;
                    $location.path("/");
                }).error(function(){
                    $rootScope.authenticated = false;
                    $location.path("/");
                });
            }
        })
    .controller('dashboard', function($rootScope, $scope, $http, GameResource, $routeParams){
        if($routeParams.id !== undefined){
            GameResource.get({id : $routeParams.id}, function(data){
                $scope.game = data;
            });
        }else{
            GameResource.getGameByUsername({teamname : $rootScope.MainUser.teamname}, function(data){
                $scope.game = data;
            });
        }
    })
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
    }).controller('adminOverview', function($scope, $http,$location, GameResource) {
        GameResource.getAll().$promise.then(function(data){
            $scope.games = data;
            console.log($scope.games);
        });

        $scope.getGame = function(id){
            $location.path('/dashboard/' + id);
        }

    });

