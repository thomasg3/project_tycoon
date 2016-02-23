/**
 * Created by kiwi on 22/02/2016.
 */


angular.module('projecttycoonControllers')
    .controller('adminStakeholderOverviewController', function($scope, StakeholderAdminResource) {

        StakeholderAdminResource.query(function(data){
            $scope.stakeholders = data;
        })
    })
    .controller('adminStakeholderDetailController', function($scope, $routeParams, StakeholderAdminResource) {
        StakeholderAdminResource.get({id: $routeParams.id}, function(data){
            $scope.stakeholder = data;
        })
    })
    .controller('adminStakeholderEditController', function($scope, $location ,$http,$routeParams ,StakeholderAdminResource) {
        $scope.title = "Edit Stakeholder";
        $scope.freeze = false;
        StakeholderAdminResource.get({id: $routeParams.id}, function(data){
            $scope.stakeholder = data;
        });
        $scope.save = function(){
            $scope.freeze = true;
            $scope.stakeholder.$update(
                function(data){
                    $location.path('/admin/stakeholders').replace();
                },
                function(err){
                    $scope.freeze = false;
                });
        };
        $scope.onFileSelect= function($files) {
            $scope.freeze = true;
            var formData=new FormData();
            formData.append("file",$files[0]);
            $http.post('/api/admin/stakeholders/upload', formData, {
                transformRequest: function(data, headersGetterFunction) {
                    return data;
                },
                headers: { 'Content-Type': undefined }
            }).success(function(response){
                $scope.stakeholder.imagePath=response.url;
                $scope.freeze = false;
            }).error(function(err){$scope.freeze = false;})};

        $scope.removeUrl = function(index){
            $scope.stakeholder.links.splice(index, 1);
        };
        $scope.addUrl = function(){
            $scope.stakeholder.links.push($scope.url);
            $scope.url = "";
        };

    })
    .controller('adminStakeholderNewController', function($scope, $location, $http,StakeholderAdminResource) {
        $scope.title = "New Stakeholder";
        $scope.freeze = false;
        $scope.stakeholder = new StakeholderAdminResource();
        $scope.stakeholder.links = [];
        $scope.save = function(){
            $scope.freeze = true;
            $scope.stakeholder.$save(
                function(data){
                    $location.path('/admin/stakeholders').replace();
                },
                function(err){
                    $scope.freeze = false;
                });
        };
        $scope.onFileSelect= function($files) {
            $scope.freeze = true;
            var formData=new FormData();
            formData.append("file",$files[0]);
            $http.post('/api/admin/stakeholders/upload', formData, {
                transformRequest: function(data, headersGetterFunction) {
                    return data;
                },
                headers: { 'Content-Type': undefined }
            }).success(function(response){
                $scope.stakeholder.imagePath=response.url;
                $scope.freeze = false;
            }).error(function(err){$scope.freeze = false;})};

        $scope.removeUrl = function(index){
            $scope.stakeholder.links.splice(index, 1)
        };
        $scope.addUrl = function(){
            $scope.stakeholder.links.push($scope.url);
            $scope.url = "";
        };
    })
    .controller('adminStakeholderDeleteController', function($scope, $routeParams,$location, StakeholderAdminResource) {
        StakeholderAdminResource.delete({id: $routeParams.id}, function(){
            $location.path('/admin/stakeholders').replace();
        });
    });