/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('editKnowledgeAreasController', function($scope, $location, KnowledgeAreaAdminResource){
    $scope.knowledgeareas = KnowledgeAreaAdminResource.query();
    $scope.newarea = new KnowledgeAreaAdminResource();
    $scope.toadds = [];
    $scope.todeletes = [];
    $scope.add = function(){
        toadd = new KnowledgeAreaAdminResource();
        toadd.name = $scope.newarea.name;
        toadd.elementNumber = $scope.knowledgeareas.length + $scope.toadds.length;
        $scope.toadds.push(toadd);
        $scope.newarea.name = "";
    };
    $scope.save = function(){
        KnowledgeAreaAdminResource.saveAll($scope.toadds, function(){
            $scope.toadds = [];
            $scope.knowledgeareas = KnowledgeAreaAdminResource.query();
        })
    }
});