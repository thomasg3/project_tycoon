/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('home', function($scope, $rootScope,MainUserResource) {
    $rootScope.MainUser = MainUserResource.getMainUser();
    $scope.MainUser = MainUserResource.getMainUser();
});