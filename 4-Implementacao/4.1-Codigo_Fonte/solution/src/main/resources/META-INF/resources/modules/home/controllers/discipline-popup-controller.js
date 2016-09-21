(function (angular) {
    'use strict';

    angular.module('siscei')
        .controller('DisciplinePopupController', function ( $scope, $state, $rootScope, $importService, $mdSidenav, $mdToast, $mdDialog, $log, $timeout, $q, $location, discipline ) {

            /**
             * Serviços importados do DWR
             */
            $importService("courseService");

            /*-------------------------------------------------------------------
             * 		 				 	ATTRIBUTES
             *-------------------------------------------------------------------*/

            $scope.discipline = discipline;

            $scope.model = {

            	discipline: {},
                showLoading : true            }
            
            /*-------------------------------------------------------------------
             * 		 				  POST CONSTRUCT
             *-------------------------------------------------------------------*/

            /*-------------------------------------------------------------------
             * 		 			     HANDLERS - TRAINING
             *-------------------------------------------------------------------*/

            /*-------------------------------------------------------------------
             * 		 				 PRIVATE BEHAVIORS
             *-------------------------------------------------------------------*/
            /**
             * 
             */
            $scope.close = function () {
                $mdDialog.cancel();
            };
        	/**
        	 * 
        	 */
            $scope.add = function (discipline) {
            	if($scope.validateForm()){
            		$mdDialog.hide($scope.model.discipline);
            	}
            };
            /**
             * 
             */
            $scope.validateForm = function (){
            	if($scope.model.discipline.name == null){
            		$mdToast.showSimple("Informe o nome da disciplina.");
                    return false;
            	}
            	if($scope.model.discipline.description == null){
            		$mdToast.showSimple("Informe uma descrição da disciplina.");
            		return false;
            	}
            	return true;
            }

        });

}(window.angular));
