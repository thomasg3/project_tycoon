/**
 * Created by michael on 18/02/16.
 */
angular.module('projecttycoonControllers')
    .directive('ngFiles', ['$parse', function ($parse) {

        function fn_link(scope, element, attrs) {
            var onChange = $parse(attrs.ngFiles);
            element.on('change', function (event) {
                onChange(scope, { $files: event.target.files });
            });
        };

        return {
            link: fn_link
        }
    } ])
    .controller('adminInfo', function($scope, $http,$routeParams,$window, $rootScope,$location,$sce,MainUserResource,InfoAdminResource) {
        InfoAdminResource.getTypes().$promise.then(function(types){
            $scope.types=types;
            $scope.type=types[0];
        });

        var formdata = new FormData();
        $scope.getTheFiles = function ($files) {
            formdata.append('file', $files[0]);
            $scope.filename=$files[0].name;

        };

        $scope.clear=function(){
            $scope.imgUrl="";
            $scope.videoUrl="";
            $scope.filename="";
        }

        $scope.postInfo = function(){
            $scope.send=$scope.getInfoFromForm();
            if($scope.send.type=='Document'){
                $scope.uploadFile();
            }

            InfoAdminResource.post($scope.send).$promise.then(function(){
                $location.path("/admin/info");
            });
        }

        $scope.createVideoUrl=function(){
            return "https://www.youtube.com/embed/"+$scope.videoUrl;
        }
        $scope.getIframeSrc=function(){
                return $sce.trustAsResourceUrl($scope.createVideoUrl());
        }
        $scope.getSafeSrc=function(url){
            return $sce.trustAsResourceUrl(url);
        }
        $scope.showInfo = function(info){
            $scope.infoArr = info;
            
        };

        $scope.deleteInfo = function(id){
            console.log("I am trying to delete");
            InfoAdminResource.delete({id:id}).$promise.then(function(data){
                $scope.showInfo(data);
            });
        }
        $window.deleteInfo = $scope.deleteInfo;

            InfoAdminResource.getAll().$promise.then(function (data) {
                $scope.showInfo(data);
            });

        if($routeParams.id){
            InfoAdminResource.get({id:$routeParams.id}).$promise.then(function(data){
                $scope.description=data.description;
                $scope.unlockedAt=data.unlockedAtLevel;
                $scope.type=data.type;
                var url = data.path.split('/');
                var parsed = url[url.length-1];
                if(data.type=="Video"){
                    $scope.videoUrl=parsed;
                }
                else if(data.type=="Document"){
                    $scope.filename=parsed;
                }
                else if(data.type=="Image"){
                    $scope.imgUrl=data.path;
                }
                var btn = document.getElementById("submit");
                btn.setAttribute("ng-click","updateInfo()");
            })
        }
        $scope.updateInfo = function(){

            //get info out form
            $scope.updateInfo = $scope.getInfoFromForm();
            console.log($scope.updateInfo);
            //check if file is selected if so upload it
            if($scope.filename){
                $scope.uploadFile();
            }
            //put info
            InfoAdminResource.update({id:$routeParams.id},$scope.updateInfo);
        }

        $scope.getInfoFromForm = function(){
            $scope.send={
                description:$scope.description,
                unlockedAtLevel:$scope.unlockedAt,
                type:$scope.type
            }

            if($scope.type=='Video'){
                $scope.send.path=$scope.createVideoUrl();
            }
            else if($scope.type=="Image"){
                $scope.send.path=$scope.imgUrl;
            }
            else {
                $scope.send.path = "/documents/" + $scope.filename;
            }
            return $scope.send;
        }
        $scope.checkWhatToDo = function(){
            if($routeParams.id){
                $scope.updateInfo();
            }
            else{
                $scope.postInfo();
            }
        }

        $scope.uploadFile=function(){
            var request = {
                method: 'POST',
                url: '/api/admin/info/upload',
                data: formdata,
                headers: {
                    'Content-Type': undefined
                }
            };
            $http(request)
                .success(function (d) {

                })
                .error(function () {
                });
        }
    });