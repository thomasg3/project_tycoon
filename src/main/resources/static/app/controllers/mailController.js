/**
 * Created by kiwi on 18/02/2016.
 */

angular.module('projecttycoonControllers')
    .controller('mailController', function($scope, $http,$routeParams, GameAdminResource, MailAdminResource, $rootScope, filterFilter) {
        $scope.data = $rootScope.MainUser;
        $scope.hide = 1;
        $scope.mail=new MailAdminResource();
        GameAdminResource.get({id : $routeParams.id}, function(data){
            $scope.game = data;
            var teams=$scope.game.teams;
            for(var i=0; i< teams.length;i++){
                teams[i].selected=true;
            }
            $scope.teams=teams;
        });



        $scope.sendMail = function(){
            var recipients =  filterFilter($scope.teams, {'selected': true});
            $scope.mail.recipients=recipients;
            $scope.mail.$mail({id : $routeParams.id},function(data){
                $scope.hide=0;
            });
        }
    });