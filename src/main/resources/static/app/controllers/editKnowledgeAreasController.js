/**
 * Created by michael on 12/02/16.
 */

angular.module('projecttycoonControllers')
.controller('editKnowledgeAreasController', function($scope, $location, KnowledgeAreaResource){
    $scope.knowledgeareas = KnowledgeAreaResource.query();
    $scope.newarea = new KnowledgeAreaResource();
    $scope.toadds = [];
    $scope.todeletes = [];
    $scope.add = function(){
        toadd = new KnowledgeAreaResource();
        toadd.name = $scope.newarea.name;
        toadd.elementNumber = $scope.knowledgeareas.length + $scope.toadds.length;
        $scope.toadds.push(toadd);
        $scope.newarea.name = "";
    }
    $scope.save = function(){
        KnowledgeAreaResource.saveAll($scope.toadds, function(){
            $scope.toadds = [];
            $scope.knowledgeareas = KnowledgeAreaResource.query();
        })
    }
});