/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('home', function($scope, $http, $rootScope) {
    $scope.data = $rootScope.MainUser;

    $scope.onFileSelect== function($files) {
        //$files: an array of files selected, each file has name, size, and type.
        for (var i = 0; i < $files.length; i++) {
            var $file = $files[i];
            Upload.upload({
                url: 'api/image/upload',
                file: $file,
                progress: function(e){}
            })
        }}

});