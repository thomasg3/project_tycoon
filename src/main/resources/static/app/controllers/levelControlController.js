/**
 * Created by thomas on 17/02/16.
 */
angular.module('projecttycoonControllers')
    .controller('levelControlController', function($scope, $routeParams,$sce, $http ,MainUserResource, GameAdminResource, LevelAdminResource,InfoAdminResource,StakeholderAdminResource){
        var updateList = function() {GameAdminResource.get({id: $routeParams.gameid}, function(data){
            $scope.game = data;
            $scope.levels = data.levels;
        })};
        $scope.errors = [];
        updateList();
        if($routeParams.levelid != 0){
            InfoAdminResource.getForLevel({level: $routeParams.levelid},function(infoArr){
                $scope.infoArr=infoArr;
            });
            LevelAdminResource.get({id: $routeParams.levelid}, function(level){
                $scope.currentLevel = level;
                $scope.time = level.latestStateChange;
                StakeholderAdminResource.getStakholdersOfLevel({level: level.round}, function(data){
                    $scope.stakeholders = data;
                })
            });
        } else {
            StakeholderAdminResource.getStakholdersOfLevel({level: $routeParams.levelid}, function(data){
                $scope.stakeholders = data;
            });
        }





        $scope.change = function(to){
            $http.get('/api/admin/levels/'+$scope.currentLevel.id+'/change/'+to)
                .success(function(data){
                    $scope.currentLevel = data;
                    $scope.time = data.latestStateChange;

                    updateList();
                })
                .error(function(err){
                    $scope.errors.push('Oops, something went wrong...');
                });
        };
         GameAdminResource.getTeamsForOverview({id: $routeParams.gameid},function(teams){
             $scope.teams=teams;
         });




        $scope.getSafeSrc=function(url){
            return $sce.trustAsResourceUrl(url);
        };

        $scope.isOnBlackList=function(id,info){
            return info.excludedTeams.indexOf(id)==-1 ? false :true;
        };

        $scope.sendChanges = function(id,info,checked){

            if(checked.target.checked){

                //remove id from blacklist
                InfoAdminResource.removeTeamFromBlackList({id:info.id ,team:id });
            }
            else{

                //put id in blacklist
                InfoAdminResource.addTeamToBlackList({id:info.id , team:id});
            }
        };

        $scope.changeStakeholderBlacklist = function(teamid, stakeholderid, checked){
            if(checked.target.checked){
                StakeholderAdminResource.removeTeamFromBlackList({id:stakeholderid ,team:teamid });
            }
            else{
                StakeholderAdminResource.addTeamToBlackList({id:stakeholderid , team:teamid});
            }
        };
        $scope.showInfo = function(info){
            info.show=true;
        }
    });