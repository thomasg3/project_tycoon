/**
 * Created by michael on 18/02/16.
 */
angular.module('projecttycoonControllers')
    .controller('info', function($scope, $http, $rootScope,MainUserResource,InfoResource) {
            InfoResource.getAll().$promise.then(function(data){
                angular.forEach(data,function(value,key){
                    var table = document.getElementById("infoTable");
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
            });
    });