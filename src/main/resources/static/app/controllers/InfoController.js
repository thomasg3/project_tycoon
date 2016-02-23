/**
 * Created by michael on 18/02/16.
 */
angular.module('projecttycoonControllers')

    .controller('info', function($scope, $http, $rootScope,$location,$sce,MainUserResource,InfoResource) {
        $scope.showInfo = function(info){
            $scope.infoArr = info;
        };


            InfoResource.getForTeam({id:MainUserResource.getMainUser().id}).$promise.then(function(data){
                $scope.showInfo(data);
            })
        $scope.getSafeSrc=function(url){
            return $sce.trustAsResourceUrl(url);
        }
    });