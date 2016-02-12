/**
 * Created by Jeroen on 12-2-2016.
 */

var GAMENAME_REGEXP = /^[A-Za-z0-9\s]*$/;
function GameNameValidator(){
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function($scope, $elm, $attrs, ctrl) {
            $scope.$watch($attrs.ngModel, function(data) {
                if(data.length  > 4){
                    ctrl.$setValidity("gamenamelength", true);
                }else{
                    ctrl.$setValidity("gamenamelength", false);
                }
            });
        }
    };
}

angular
    .module('projecttycoon')
    .directive('gameNameValidator', GameNameValidator);

/*
var GAMENAME_REGEXP = /^[A-Za-z0-9\s]*$/;
angular.module('projecttycoon').directive('gameNameValidator', function(){
    return {
                    restrict: 'A',
                    require: 'ngModel',
                    scope: {
                        gameNameValidator: '='
                    },
                    link: function(scope, elm, attrs, ctrl) {

                        var gameNameValidation = function(modelValue){
                            if(!modelValue || modelValue.length > 4){
                                ctrl.$setValidity('validgamenamelength', true);
                            }
                            else{
                                ctrl.$setValidity('validgamenamelength', false);
                            }

                            if(GAMENAME_REGEXP.test(modelValue)){
                                ctrl.$setValidity('validgamename', true);
                            }
                            else{
                    ctrl.$setValidity('validgamename', false);
                }
            };

            ctrl.$parsers.unshift(gameNameValidation);


        }
    };
});
*/
var INTEGER_REGEXP = /^[0-9]+$/;
angular.module('projecttycoon').directive('integer', function(){
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(modelValue){
                if(!modelValue){
                    ctrl.$setValidity('validint', false);
                } else if (INTEGER_REGEXP.test(modelValue)) {
                    ctrl.$setValidity('validint', true);
                } else {
                    ctrl.$setValidity('validint', false);
                }
            });
        }
    };
});
