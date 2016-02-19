/**
 * Created by Jeroen on 12-2-2016.
 */

function RegexValidator(){
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function($scope, $elm, $attrs, ctrl) {
            $scope.$watch($attrs.ngModel, function(data) {
                if(ctrl.$dirty || ctrl.$touched){
                    if($attrs.regex && data){
                        var regex = new RegExp($attrs.regex);
                        if(regex.test(data)){
                            ctrl.$setValidity($attrs.name + "validregex", true);
                        }else{
                            ctrl.$setValidity($attrs.name + "validregex", false);
                        }
                    }
                }
            });
        }
    };
}

function PasswordConfirm() {
    return {
        restrict: 'A',
        require: "ngModel",
        link: function ($scope, $element, $attrs, ctrl) {
            $scope.$watch(function () {
                if(ctrl.$dirty || ctrl.$touched) {
                    if ($attrs.password === ctrl.$viewValue) {
                        ctrl.$setValidity($attrs.name + "confirmPassword", true);
                    } else {
                        ctrl.$setValidity($attrs.name + "confirmPassword", false);
                    }
                }
            });
        }
    };
}

angular.module('projecttycoon').directive('passwordConfirm', PasswordConfirm);
angular.module('projecttycoon').directive('regexValidator', RegexValidator);

