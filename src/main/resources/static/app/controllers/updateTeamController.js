/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('updateTeam',function($rootScope, $scope, $http, $routeParams,$location,TeamResource, Upload){



    $scope.onFileSelect= function($files) {

        var formData=new FormData();
        formData.append("file",$files[0]);
        $http.post('/api/image/upload/'+$routeParams.teamname, formData, {
            transformRequest: function(data, headersGetterFunction) {
                return data;
            },
            headers: { 'Content-Type': undefined }
        }).success(function(data, status) {
            $scope.userPhoto=data;
        }).error(function(data, status) {
            console.log("Error " + data + " " + status)
            $scope.userPhoto=data;
    })};

    TeamResource.search({teamname : $routeParams.teamname},function(data){
        if(($rootScope.MainUser.admin&&data.id!=null)||$rootScope.MainUser.teamname ==  $routeParams.teamname){
            angular.element(document).ready(function () {
                $scope.team = TeamResource.search({teamname : $routeParams.teamname});

            });
            $scope.editTeam = function(){
                $scope.updateTeam = TeamResource.search({teamname : $routeParams.teamname},function(updateTeam){
                    if($scope.password==$scope.passwordRepeat){
                        updateTeam.teamname =$scope.teamname;
                        updateTeam.password = $scope.password;

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
});