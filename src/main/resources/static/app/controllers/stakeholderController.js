/**
 * Created by kiwi on 22/02/2016.
 */


angular.module('projecttycoonControllers')
    .controller('stakeholderOverviewController', function($scope, StakeholderResource) {
        StakeholderResource.query(function(data){
            $scope.stakeholders = data;
        })
    })
    .controller('stakeholderDetailController', function($scope, $routeParams, StakeholderResource) {
        StakeholderResource.get({id: $routeParams.id}, function(data){
            $scope.stakeholder = data;
        })
    });