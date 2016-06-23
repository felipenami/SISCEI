(function(angular) {
  'use strict';
  /**
   * 
   */	
	
  angular.module('siscei')
	.controller('BankAccountController', function ($scope, $rootScope,$state, $importService, $mdDialog, $mdSidenav, $mdToast, $timeout, $window, $location, $locale , $q) {
		/**
         * Serviços importados do DWR
         */
		$importService("accountService");
		/**
		 * 
		 */
		$importService("financeService");
		/**
		 * 
		 */
		
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
        $scope.showMoreInformation = function(bol, name){
            $scope.moreInformation[name] = bol;
            $scope.$apply();

        };
        /**
         * Responsável por abrir os paper conforme o ID
         */
        $scope.openPaper = function( namePaper ){
            $timeout(function(){
                angular.element('#'+namePaper)[0].open();
            },100);
        };
        
        /**
         * 
         */
        $scope.model = {
                form    : {},
                bankAccount : new BankAccount(),
                filters: {
                    terms: [],
                },
                bankAccounts : [],
                page: {//PageImpl
                    content: null,
                    pageable: {//PageRequest
                        size: 9,
                        page: 0,
                        sort: {//Sort
                            orders: [
                                { direction: 'ASC' }
                            ]
                        }
                    }
                }
        	};
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
                    $scope.changeToDetail( $state.params.id );
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
        $scope.changeToEdit = function (id) {
        	console.debug("edit");
        	
        	financeService.findBankAccountById(id,{
        		callback: function (result) {
        			$scope.model.bankAccount = result;
        			$scope.$apply();
        		},
                errorHandler: function (message, exception) {
		        	$mdToast.showSimple(message);
		            $state.go($scope.LIST_STATE);
		            $scope.$apply();
                }
        	})
        	
        }
        /**
         * 
         */
        $scope.changeToList = function () {
            console.debug("changeToList");
            
            $scope.model.page.pageable = {
            	size: 9,
            	page: 0,
            	sort: {
            		orders: [{ 
            			direction: 'ASC',
            			property: 'name',
            			nullHandlingHint: null
            		}]
                }
            };
                
                //Limpamos a lista para um nova consulta
                $scope.model.bankAccount = new BankAccount();
                $scope.model.bankAccounts = [];
                $scope.listBankAccountsByFilters( $scope.model.filters,  $scope.model.page.pageable );
        };
        /**
         * 
         */
        $scope.changeToAdd = function () {
        	console.debug("Add");
        	$scope.model.bankAccount = new BankAccount();
        }

        /**
         * 
         */
        $scope.changeToDetail = function (id) {
        	console.debug("Detail");
        	
        	financeService.findBankAccountById(id,{
        		callback: function (result) {
        			$scope.model.bankAccount = result;
        			$scope.$apply();
        		},
                errorHandler: function (message, exception) {
		        	$mdToast.showSimple(message);
		            $state.go($scope.LIST_STATE);
		            $scope.$apply();
                }
        	})
        }
        /**
         * 
         */
        $scope.listBankAccountsByFilters = function(filters, pageRequest){
        	
        	financeService.listBankAccountsByFilters( filters.terms.toString(), pageRequest, {
                callback: function (result) {
                    $scope.model.bankAccounts = $scope.model.bankAccounts.concat(result.content);
                    $scope.model.showLoading = false;
                    $scope.model.notFound = result.totalElements == 0 ? true : false;
                    $scope.$apply();
                },
                errorHandler: function (message, exception) {
                    $mdToast.showSimple(message);
                    $scope.$apply();
                }
            });
        }
        /**
         * 
         */
        $scope.insertBankAccountHandler= function(bankAccount){
        	if($scope.validateForm()){
	        	financeService.insertBankAccount( bankAccount, {
	        		callback: function(result){
	        			$mdToast.showSimple("Conta bancária salva com sucesso!");
	                    $state.go($scope.LIST_STATE);
	                    $scope.$apply();
	        		},
	        		 errorHandler: function (message, exception) {
	                     $mdToast.showSimple(message);
	                     $scope.$apply();
	                 }
	        	})
        	}
        }
        /**
         * 
         */
        $scope.updateBankAccountHandler = function( bankAccount ){
        	if($scope.validateForm()){
	        	financeService.updateBankAccount( bankAccount, {
	        		callback: function(result){
	        			$mdToast.showSimple("Registro alterado com sucesso!");
	                    $state.go($scope.LIST_STATE);
	                    $scope.$apply();
	        		},
	        		 errorHandler: function (message, exception) {
	                     $mdToast.showSimple(message);
	                     $scope.$apply();
	                 }
	        	})        		
        		
        	}
        }
        /**
         * 
         */
        $scope.changeToRemove = function (event, entity) {
            console.debug("changeToRemove", entity);

            var confirm = $mdDialog.confirm()
                .title('Tem certeza que deseja excluir este registro?')
                .content('Não será possível recuperar este registro se for excluído.')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(event);

            $mdDialog.show(confirm).then(function (result) {
            	
            	financeService.removeBankAccount(entity.id, {
                    callback: function (result) {
                        if( $state.current.name == $scope.LIST_STATE){
                            $scope.changeToList();
                        } else {
                            $state.go( $scope.LIST_STATE );
                        }
                        $mdToast.showSimple("O registro foi excluído com sucesso!");
                        $scope.$apply();
                    },
                    errorHandler: function (message, exception) {
                        $mdToast.showSimple(message);
                        $state.go($scope.LIST_STATE);
                        $scope.$apply();
                    }
                });
            });
        };
        
        /**
         * 
         */
        $scope.validateForm = function (){
        	if($scope.model.bankAccount.name == null){
        		$mdToast.showSimple("O nome fantasia deve ser informado.");
                return false;
        	}
        	if($scope.model.bankAccount.description == null){
        		$mdToast.showSimple("O telefone do fornecedor deve ser informado.");
        		return false;
        	}
        	if($scope.model.bankAccount.balance == null){
        		$mdToast.showSimple("O endereço do fornecedor deve ser informado.");
        		return false;
        	}
        	return true;
        }       
        
	});
	
	
})(window.angular);