/**
 * Created by thomas on 24/02/16.
 */
angular.module('projecttycoonControllers')
    .controller('messageController', function($scope, $rootScope, $log){
        var reset = function(){
            $scope.messages = [];
        }
        reset();
        $scope.$on('http401', function(event, args){
            $scope.messages.push({
                content: "You need to be logged in to see this",
                warning: true
            });
        });
        $scope.$on('http500', function(event, args){
            if(args.response.data.exception === 'javax.validation.ConstraintViolationException') {
                $scope.messages.push({
                    content: "There is something wrong with the form you sent.",
                    warning: true
                });
            } else {
                $scope.messages.push({
                    content: "Something went wrong on the server",
                    error: true
                });
            }
        });

        $scope.$on('loggedIn', function(event, args){
            reset();
        });

        $scope.$on('loggedOut', function(event, args){
            reset();
        })

        $scope.close = function(index){
            $scope.messages.splice(index, 1);
        };

    });