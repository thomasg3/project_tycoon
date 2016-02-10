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
    .controller('registration', function($rootScope, $scope, $http, $routeParams,$location) {
        $scope.oldUsername = $routeParams.username;

        $scope.initTeam = function(){

            var data = {
                "oldUsername": $scope.oldUsername,
                "oldPassword": $scope.credentials.oldPassword,
                "newUsername": $scope.credentials.username,
                "newPassword": $scope.credentials.password
            };

            alert(JSON.stringify(data));


            $http.post('/initTeam', data).success(function(){
                $location.path('/');
            }).error(function(){
                alert("post error")
            });

        }
    });

