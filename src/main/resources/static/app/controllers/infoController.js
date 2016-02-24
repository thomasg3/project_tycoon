/**
 * Created by michael on 18/02/16.
 */
angular.module('projecttycoonControllers')

    .controller('info', function($scope, $http, $rootScope,$location,$sce,$routeParams,MainUserResource,InfoResource) {
        $scope.showInfo = function(info){
            $scope.infoArr = info;
        };


        $scope.getSafeSrc=function(url){
            return $sce.trustAsResourceUrl(url);
        }

        //detail page
        if($routeParams.id){
            InfoResource.get({id:$routeParams.id}).$promise.then(function(info){
                $scope.info = info;
            })
        }
        //overview
        else{
            InfoResource.getForTeam({id:MainUserResource.getMainUser().id}).$promise.then(function(data){
                $scope.showInfo(data);
            })
        }



/*      my own search function however we can use angular filter
        if we want to write something better perhaps we can enhance this

        $scope.search=function(){
            var arr = [];
            console.log("search: " +$scope.searchText);
            console.log("arr: " + arr);
            angular.forEach($scope.info,function(info){
                angular.forEach(info.tags,function(tag){
                    console.log("tag: "+tag);
                    if(tag.indexOf($scope.searchText)>-1&&!$scope.contains(arr,info)){
                        console.log(info);
                        arr.push(info);
                        console.log(arr);
                    }
                })
            })
            $scope.showInfo(arr);
            if($scope.searchText.length==0){
                InfoResource.getForTeam({id:MainUserResource.getMainUser().id}).$promise.then(function(data){
                    $scope.showInfo(data);
                })
            }
        }

        $scope.contains = function(arr,obj){
            var found = false;
            for(var i = 0 ; i<arr.length&&!found;i++){
                if(arr[i]===obj){
                    found=true
                }
            }
            return found;
        }*/
    });