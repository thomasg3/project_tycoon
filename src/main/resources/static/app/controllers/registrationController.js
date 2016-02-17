/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('registration', function($rootScope, $scope, $http, $routeParams,$location, TeamResource, GameResource,Upload,MainUserResource) {
    $scope.oldUsername = $routeParams.username;

    TeamResource.search({teamname: $routeParams.username},function(data){
        $scope.userPhoto=data.teamImage});



    $scope.onUrlSelect=function(){
        $http({
            url: '/api/image/uploadWeb/'+$routeParams.username,
            method: "POST",
            data: $scope.url
        })
            .then(function(response) {
                    $scope.userPhoto=$scope.url;
                })
    }

    $scope.onFileSelect= function($files) {

        var formData=new FormData();
        formData.append("file",$files[0]);
        $http.post('/api/image/upload/'+$routeParams.username, formData, {
            transformRequest: function(data, headersGetterFunction) {
                return data;
            },
            headers: { 'Content-Type': undefined }
        }).success(function(response){
                $scope.userPhoto=response.url;
        })}



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