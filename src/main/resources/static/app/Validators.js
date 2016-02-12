/**
 * Created by Jeroen on 12-2-2016.
 */

var INTEGER_REGEXP = /^\-?\d+$/;
angular.module('projecttycoon').directive('integer', function() {
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {
            ctrl.$validators.integer = function(modelValue, viewValue) {
                if (ctrl.$isEmpty(modelValue)) {
                    // consider empty models to be valid
                    return true;
                }
                if (INTEGER_REGEXP.test(viewValue)) {
                    // it is valid
                    return true;
                }
                // it is invalid
                return false;
            };
        }
    };
});