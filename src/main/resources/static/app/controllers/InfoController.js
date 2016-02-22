/**
 * Created by michael on 18/02/16.
 */
angular.module('projecttycoonControllers')

    .controller('info', function($scope, $http, $rootScope,$location,$sce,MainUserResource,InfoResource) {

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


            InfoResource.getForTeam({id:MainUserResource.getMainUser().id}).$promise.then(function(data){
                $scope.showInfo(data);
            })

    });