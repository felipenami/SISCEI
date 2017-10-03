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
		$importService("bankAccountService");
		/**
		 * 
		 */
		$importService("accountPayableService");
		/**
		 * 
		 */
		$importService("accountReceivableService");
		
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
        $scope.filter = {
        		show : false
        }
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
                accountsPayable : [],
                accountsReceivable : [],
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
          $state.current.breadCrumbs = [{'state': $scope.LIST_STATE, 'name': 'Contas Bancárias'}];

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
        	
        	bankAccountService.findBankAccountById(id,{
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
        	$scope.model.bankAccount.balance = 0;
        }

        /**
         * 
         */
        $scope.changeToDetail = function (id) {
        	console.debug("Detail");
        	$scope.model.page.pageable = {
                	size: 9,
                	page: 0,
                	sort: {
                		orders: [{ 
                			direction: 'ASC',
                			property: 'dueDate',
                			nullHandlingHint: null
                		}]
                    }
                };
//        	$scope.model.accountsPayable = [];
//        	$scope.model.accountsReceivable = [];
//        	$scope.listAccountsPayableByFilters( $scope.model.filters,  $scope.model.page.pageable );
//        	$scope.listAccountReceivableByFilters( $scope.model.filters,  $scope.model.page.pageable );
        	
        	bankAccountService.findBankAccountById(id,{
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
        	
        	bankAccountService.listBankAccountsByFilters( filters.terms.toString(), pageRequest, {
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
        		bankAccountService.insertBankAccount( bankAccount, {
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
        		bankAccountService.updateBankAccount( bankAccount, {
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
            	
            	bankAccountService.removeBankAccount(entity.id, {
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
        		$mdToast.showSimple("O nome do banco deve ser informado.");
                return false;
        	}
        	if($scope.model.bankAccount.description == null){
        		$mdToast.showSimple("Informe uma descrição da conta.");
        		return false;
        	}
        	if($scope.model.bankAccount.balance == null){
        		$mdToast.showSimple("Informe o saldo inicial da conta.");
        		return false;
        	}
        	return true;
        }
        /**
         * 
         */
        $scope.listBankAccountsByEvents = function ( event ) {
        	
	    	if( event.keyCode == 13 || $scope.model.filters.terms == "" ){
	          $scope.changeToList();
	    	}
	    };
	    /**
	     * 
	     */
	    $scope.clearFilters = function(){
	    	$scope.filter.show = false;
	    	$scope.model.filters.terms = "";
	    	$scope.changeToList();
	    };
	    /**
	     * 
	     */
	    $scope.listAccountsPayableByFilters = function(filters, pageRequest){
        	
        	accountPayableService.listAccountsPayableByFilters( filters.terms.toString() , pageRequest, {
                callback: function (result) {
                    $scope.model.accountsPayable = $scope.model.accountsPayable.concat(result.content);
                    $scope.getAccountsPayableTotal($scope.model.accountsPayable);
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
        $scope.listAccountReceivableByFilters = function(filters, pageRequest){
        				   
        	accountReceivableService.listAccountsReceivableByFilters( filters.terms.toString() , pageRequest, {
                callback: function (result) {
                    $scope.model.accountsReceivable = $scope.model.accountsReceivable.concat(result.content);
                    $scope.getAccountsReceivableTotal($scope.model.accountsReceivable);
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
	
        
	});
	
})(window.angular);