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
    .controller('info', function($scope, $http, $rootScope,$location,$sce,MainUserResource,InfoResource) {
        InfoResource.getTypes().$promise.then(function(types){
            $scope.types=types;
            $scope.type=types[0];
        });

        var formdata = new FormData();
        $scope.getTheFiles = function ($files) {
            console.log($files);
            formdata.append('file', $files[0]);
            $scope.filename=$files[0].name;

        };

        $scope.clear=function(){
            $scope.imgUrl="";
            $scope.videoUrl="";
        }

        $scope.postInfo = function(){
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
            else{
                $scope.send.path="/documents/"+$scope.filename;
                var request = {
                    method: 'POST',
                    url: '/api/info/upload',
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
            InfoResource.post($scope.send).$promise.then(function(){
                $location.path("/info");
            });
        }

        $scope.createVideoUrl=function(){
            return "https://www.youtube.com/embed/"+$scope.videoUrl;
        }
        $scope.getIframeSrc=function(){
            return $sce.trustAsResourceUrl($scope.createVideoUrl());
        }
        $scope.showInfo = function(info){
            var table = document.getElementById("infoTable");
            if(info.length==0){
                var row = document.createElement("tr");
                row.innerHTML="There are no documents available yet.";
                table.appendChild(row);

            }
            angular.forEach(info,function(value,key){
                var row = document.createElement("tr");
                table.appendChild(row);
                var newElement;
                if(value.type=="Image"){
                    newElement = document.createElement("img");
                    newElement.setAttribute("src", value.path);
                }
                else if(value.type=="Document"){
                    newElement=document.createElement("a");
                    newElement.setAttribute("href",value.path);
                    newElement.innerText=value.description;
                }
                else if(value.type=="Video"){
                    newElement=document.createElement("iframe");
                    newElement.setAttribute("width",560);
                    newElement.setAttribute("height",315);
                    newElement.setAttribute("src",value.path);

                }
                row.appendChild(newElement);
            })
        };
        if(MainUserResource.getMainUser().admin) {
            InfoResource.getAll().$promise.then(function (data) {
                $scope.showInfo(data);
            });
        }
        else{
            InfoResource.getForTeam({id:MainUserResource.getMainUser().id}).$promise.then(function(data){
                $scope.showInfo(data);
            })
        }
    });