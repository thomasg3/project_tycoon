/**
 * Created by thomas on 17/02/16.
 */
angular.module('projecttycoonControllers')
    .controller('levelControlController', function($scope, $routeParams,$sce, $http ,MainUserResource, GameAdminResource, LevelAdminResource,InfoAdminResource){
        var updateList = function() {GameAdminResource.get({id: $routeParams.gameid}, function(data){
            $scope.game = data;
            $scope.levels = data.levels;
        })};
        $scope.errors = [];
        updateList();
        if($routeParams.levelid != 0){
            LevelAdminResource.get({id: $routeParams.levelid}, function(level){
                $scope.currentLevel = level;

            })
        }



        $scope.change = function(to){
            $http.get('/api/admin/levels/'+$scope.currentLevel.id+'/change/'+to)
                .success(function(data){
                    $scope.currentLevel = data;
                    updateList();
                })
                .error(function(err){
                    $scope.errors.push('Oops, something went wrong...');
                });
        }
         GameAdminResource.getTeamsForOverview({id: $routeParams.gameid},function(teams){
             $scope.teams=teams;
         })

        InfoAdminResource.getForLevel({level: $routeParams.levelid},function(infoArr){
            $scope.infoArr=infoArr;
        })


        $scope.getSafeSrc=function(url){
            return $sce.trustAsResourceUrl(url);
        }

        $scope.isOnBlackList=function(id,info){
            return info.excludedTeams.indexOf(id)==-1 ? false :true;
        }

        $scope.sendChanges = function(id,info,checked){
            console.log(id);
            if(checked.target.checked){
                console.log("checked");
                //remove id from blacklist
                InfoAdminResource.removeTeamFromBlackList({id:info.id ,team:id });
            }
            else{
                console.log("not checked");
                //put id in blacklist
                InfoAdminResource.addTeamToBlackList({id:info.id , team:id});
            }
        }
    });