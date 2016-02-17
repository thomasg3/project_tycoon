/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('updateTeam',function($rootScope, $scope, $http, $routeParams,$location,$window,TeamResource,MainUserResource){


    TeamResource.search({teamname : $routeParams.teamname},function(data){
        $scope.MainUser = MainUserResource.getMainUser();
        if(($scope.MainUser.admin&&data.id!=null)||$scope.MainUser.teamname ==  $routeParams.teamname){
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
                        if($scope.MainUser.teamname ==  $routeParams.teamname)
                            $scope.MainUser = updateTeam;
                    }
                    else{
                        $scope.error=true;
                    }

                });

            }
        }
        else{
            alert("you cant do this. You will be redirected to your edit page.\nMake this nice pls error handling or some shit.");
            $location.path('/editTeam/'+$scope.MainUser.teamname);
        }
    });
});