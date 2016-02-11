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

                        TeamResource.search({teamname: $scope.credentials.username},function(data){
                            $rootScope.MainUser = data;
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
    }).controller('updateTeam',function($rootScope, $scope, $http, $routeParams,$location,TeamResource){
            console.log($rootScope.MainUser.admin);

    TeamResource.search({teamname : $routeParams.teamname},function(data){
                if(($rootScope.MainUser.admin&&data.id!=null)||$rootScope.MainUser.teamname ==  $routeParams.teamname){
                    angular.element(document).ready(function () {
                        $scope.team = TeamResource.search({teamname : $routeParams.teamname});

                    })
                    $scope.editTeam = function(){
                        $scope.updateTeam = TeamResource.search({teamname : $routeParams.teamname},function(updateTeam){
                            if($scope.password==$scope.passwordRepeat){
                                updateTeam.teamname =$scope.teamname;
                                updateTeam.password = $scope.password;
                                if($rootScope.MainUser.state!="Admin") {
                                    updateTeam.state = "TEAM";
                                }
                                TeamResource.update({id:updateTeam.id},updateTeam).$promise.then(function(value){
                                    $location.path('/');
                                });
                                //if the user changed its team change the team in the rootScope
                                if($rootScope.MainUser.teamname ==  $routeParams.teamname)
                                    $rootScope.MainUser = updateTeam;
                            }
                            else{
                                $scope.error=true;
                            }

                        });

                    }
                }
                else{
                    alert("you cant do this. You will be redirected to your edit page.\nMake this nice pls error handling or some shit.");
                    $location.path('/editTeam/'+$rootScope.MainUser.teamname);
                }
            });


    }).controller('adminOverview', function($scope, $http,$location, GameResource) {
        GameResource.getAll().$promise.then(function(data){
            $scope.games = data;
            console.log($scope.games);
        });

        $scope.getGame = function(id){
            $location.path('/dashboard/' + id);
        }

    });

