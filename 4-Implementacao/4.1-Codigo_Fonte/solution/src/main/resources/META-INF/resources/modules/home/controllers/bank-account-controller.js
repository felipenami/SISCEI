(function(angular) {
  'use strict';
  /**
   * 
   */	
	
  angular.module('siscei')
	.controller('BankAccountController', function ($scope, $rootScope,$state, $importService, $mdDialog, $mdSidenav, $mdToast, $timeout, $window, $location, $locale , $q) {
		
		 //----STATES
        /**
         * Representa o estado de listagem de registros.
         */
        $scope.LIST_STATE = "bankAccount.list";
        /**
         * Representa o estado para a criação de registros.
         */
        $scope.ADD_STATE = "bankAccount.add";
        /**
         * Representa o estado para a edição de registros.
         */
        $scope.EDIT_STATE = "bankAccount.edit";
        /**
         * Representa o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "bankAccount.detail";		
        /**
         * 
         */
        
        /*-------------------------------------------------------------------
         * 		 				  POST CONSTRUCT
         *-------------------------------------------------------------------*/ 
        /**
         * 
         */
        $scope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
          $state.current.breadCrumbs = [{'state': $scope.LIST_STATE, 'name': 'BankAccount'}];

            //Controle para mudar o botão do menu para o botão voltar da página
            $state.current.currentState = {
                state: $scope.LIST_STATE,
                nameState: $state.current.name == $scope.LIST_STATE ? 'LIST_STATE' : null
            };

            switch ( $state.current.name ) {
                case $scope.LIST_STATE: {
                    $scope.changeToList();
                    break;
                }
                case $scope.DETAIL_STATE: {
//                    $scope.changeToDetail( $state.params.id );
                    break;
                }
                case $scope.ADD_STATE: {
                    $scope.changeToAdd();
                    break;
                }
                case $scope.EDIT_STATE: {
                	 $scope.changeToEdit($state.params.id);
                }
                    
            }
        });
        
        /*-------------------------------------------------------------------
         * 		 				  		HANDLERS
         *-------------------------------------------------------------------*/
        /**
         * 
         */
        $scope.changeToEdit = function () {
        	console.debug("edit");
        }
        /**
         * 
         */
        $scope.changeToAdd = function () {
        	console.debug("Add");
        }
        /**
         * 
         */
        $scope.changeToList = function () {
        	console.debug("List");
        }
        /**
         * 
         */
        $scope.changeToDetail = function () {
        	console.debug("Detail");
        }
        
        
	});
	
	
})(window.angular);