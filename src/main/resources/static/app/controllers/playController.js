/**
 * Created by thomas on 24/02/16.
 */
angular.module('projecttycoonControllers')
    .controller('playController', function($location, MainUserResource, GameResource){
        var user = MainUserResource.getMainUser();
        GameResource.getGameByUsername({teamname: user.teamname}, function(game){
            $location.path('/games/'+game.id+'/levels').replace();
        });
    });