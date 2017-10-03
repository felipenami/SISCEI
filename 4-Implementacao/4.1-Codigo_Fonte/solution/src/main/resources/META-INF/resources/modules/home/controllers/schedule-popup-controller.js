(function (angular) {
    'use strict';

    angular.module('siscei')
        .controller('SchedulePopupController', function ( $scope, $state, $rootScope, $importService, $mdSidenav, $mdToast, $mdDialog, $log, $timeout, $q, $location, schedule ) {

            /**
             * Serviços importados do DWR
             */
            $importService("courseService");

            /*-------------------------------------------------------------------
             * 		 				 	ATTRIBUTES
             *-------------------------------------------------------------------*/

            $scope.schedule = schedule;

            $scope.model = {

            	schedule: {},
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
            $scope.add = function (schedule) {
            	if($scope.validateForm()){
            		$mdDialog.hide($scope.model.schedule);
            	}
            };
            /**
             * 
             */
            $scope.validateForm = function (){
            	if($scope.model.schedule.beginHour == null){
            		$mdToast.showSimple("Informe a hora inicio.");
                    return false;
            	}
            	if($scope.model.schedule.endHour == null){
            		$mdToast.showSimple("Informe a hora final.");
            		return false;
            	}
            	return true;
            }

        });

}(window.angular));
