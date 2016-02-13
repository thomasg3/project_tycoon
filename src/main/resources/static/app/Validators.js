/**
 * Created by Jeroen on 12-2-2016.
 */
//var GAMENAME_REGEXP = /^[A-Za-z0-9\s]*$/;

function MinLengthValidator(){
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function($scope, $elm, $attrs, ctrl) {
            $scope.$watch($attrs.ngModel, function(data) {
                if(data && $attrs.min){
                    if(data.length  >= $attrs.min){
                        ctrl.$setValidity($attrs.name + "length", true);
                    }else{
                        ctrl.$setValidity($attrs.name + "length", false);
                    }
                }
            });
        }
    };
}

function RegexValidator(){
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function($scope, $elm, $attrs, ctrl) {
            $scope.$watch($attrs.ngModel, function(data) {
                if($attrs.regex && data){
                    var regex = new RegExp($attrs.regex);
                    if(regex.test(data)){
                        ctrl.$setValidity($attrs.name + "validregex", true);
                    }else{
                        ctrl.$setValidity($attrs.name + "validregex", false);
                    }
                }
            });
        }
    };
}

angular.module('projecttycoon').directive('minLengthValidator', MinLengthValidator);
angular.module('projecttycoon').directive('regexValidator', RegexValidator);

