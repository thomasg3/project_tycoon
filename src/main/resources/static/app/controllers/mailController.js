/**
 * Created by kiwi on 18/02/2016.
 */

angular.module('projecttycoonControllers')
    .controller('mailController', function($scope, $http,$routeParams, GameResource, MailResource, $rootScope) {
        $scope.data = $rootScope.MainUser;
        $scope.hide = 1;
        $scope.mail=new MailResource();
        GameResource.get({id : $routeParams.id}, function(data){
            $scope.game = data;
        });

        $scope.sendMail = function(){
            $scope.mail.$mail({id : $routeParams.id},function(data){
                $scope.hide=0;
            });
        }
    });