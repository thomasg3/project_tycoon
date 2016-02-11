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
                $location.path ( "/dashboard/"+response.id);
            });
    }
})
    .controller('home', function($scope, $http) {
        $http.get('/resource/').success(function(data) {
            $scope.greeting = data;
        })
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
    .controller('dashboard', function($rootScope, $scope, $http, $location){

        $http.get("/game/1").success(function(data){
            $scope.game = data;
        })
    })
    .controller('registration', function($rootScope, $scope, $http, $routeParams,$location, TeamResource) {
        $scope.oldUsername = $routeParams.username;

        $scope.initTeam = function(){

            $http.post('logout', {}).success(function(){
                $rootScope.authenticated = false;
            }).error(function(){
                $rootScope.authenticated = false;
            });

            var oldCredentials = {username : $scope.oldUsername, password : $scope.credentials.oldPassword};
            $rootScope.authenticate(oldCredentials, function(){
                if ($rootScope.authenticated) {
                    TeamResource.search({teamname : $scope.oldUsername}, function(updateTeam){
                        updateTeam.teamname = $scope.credentials.username;
                        updateTeam.password = $scope.credentials.password;
                        updateTeam.$update({id : updateTeam.id});
                        $location.path('/');
                    });
                    $scope.error = false;
                } else {
                    $scope.error = true;
                    $location.path('/registerTeam/' + $scope.oldUsername);
                }
            });
        }
    }).controller('updateTeam',function($rootScope, $scope, $http, $routeParams,$location,TeamResource){

        //if the user is an admin or if the user is the same user as he wants to edit he can proceed
        if($rootScope.MainUser.admin || $rootScope.MainUser.teamname ==  $routeParams.teamname){
            //onload get the selected team
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

