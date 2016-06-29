(function(angular) {
  'use strict';
  /**
   * 
   */	
	
  angular.module('siscei')
	.controller('AccountReceivableController', function ($scope, $rootScope,$state, $importService, $mdDialog, $mdSidenav, $mdToast, $timeout, $window, $location, $locale , $q) {
		/**
         * Serviços importados do DWR
         */
		$importService("accountService");
		/**
		 * 
		 */
		$importService("accountReceivableService");
		/**
		 * 
		 */
		$importService("supplierService");
		/**
		 * 
		 */
		$importService("bankAccountService");
		/**
		 * 
		 */
		$importService("categoryService");
		/**
		 * 
		 */
		 //----STATES
        /**
         * Representa o estado de listagem de registros.
         */
        $scope.LIST_STATE = "accountReceivable.list";
        /**
         * Representa o estado para a criação de registros.
         */
        $scope.ADD_STATE = "accountReceivable.add";
        /**
         * Representa o estado para a edição de registros.
         */
        $scope.EDIT_STATE = "accountReceivable.edit";
        /**
         * Representa o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "accountReceivable.detail";		
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
        $scope.lateColor = {};
        $scope.model = {
                form    : {},
                accountReceivable : new AccountReceivable(),
                filters: {
                    terms: [],
                },
                accountsReceivable : [],
                totalAccountReceivable : 0,
                totalReceived : 0,
                totalNotReceived : 0,
                totalLate: 0,
                categories : [],
                bankAccounts : [],
                users : [],
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
          $state.current.breadCrumbs = [{'state': $scope.LIST_STATE, 'name': 'AccountPayable'}];

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
        	
           	$scope.model.accountReceivable = new AccountReceivable();
        	$scope.model.categories = [];
        	$scope.model.bankAccounts = [];
        	$scope.model.users = [];
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
        	$scope.listBankAccountsByFilters($scope.model.filters, $scope.model.page.pageable);
        	$scope.listCategoriesByFilters($scope.model.filters, $scope.model.page.pageable);
        
        	$scope.listUsersByFilters( $scope.model.filters, $scope.model.page.pageable );
        	
        	accountReceivableService.findAccountReceivableById(id,{
        		callback: function (result) {
        			$scope.model.accountReceivable = result;
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
            			property: 'dueDate',
            			nullHandlingHint: null
            		}]
                }
            };
                
                //Limpamos a lista para um nova consulta
                $scope.model.accountReceivable = new AccountReceivable();
                $scope.model.accountsReceivable = [];
                $scope.model.totalAccountReceivable = 0;
                $scope.model.totalReceived = 0;
                $scope.model.totalNotReceived = 0;
                $scope.model.totalOverdue = 0;
                $scope.listAccountReceivableByFilters( $scope.model.filters,  $scope.model.page.pageable );
        };
        /**
         * 
         */
        $scope.changeToAdd = function () {
        	console.debug("Add");
            
        	$scope.model.accountReceivable = new AccountReceivable();
        	$scope.model.categories = [];
        	$scope.model.bankAccounts = [];
        	$scope.model.users = [];
        	$scope.model.accountReceivable.status = 'NOT_RECEIVED';
        	$scope.model.accountReceivable.dueDate = new Date();
        	$scope.model.accountReceivable.value = 0;
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
        	$scope.listBankAccountsByFilters($scope.model.filters, $scope.model.page.pageable);
        	$scope.listCategoriesByFilters($scope.model.filters, $scope.model.page.pageable);
        	$scope.listUsersByFilters( $scope.model.filters, $scope.model.page.pageable );
        	
        	$scope.model.accountReceivable.entryDate = new Date();
        }

        /**
         * 
         */
        $scope.changeToDetail = function (id) {
        	console.debug("Detail");
        	
        	accountReceivableService.findAccountReceivableById(id,{
        		callback: function (result) {
        			$scope.model.accountReceivable = result;
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
        /**
         * 
         */
        $scope.insertAccountReceivableHandler= function(accountReceivable){
        	if($scope.validateForm()){
        		if($scope.model.accountReceivable.status == null){
            		$scope.model.accountReceivable.status = 'NOT_RECEIVED';
            	}
        		accountReceivableService.insertAccountReceivable( accountReceivable, {
	        		callback: function(result){
	                	
	        			$mdToast.showSimple("Conta salva com sucesso!");
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
        $scope.updateAccountReceivableHandler= function(accountReceivable){
        	if($scope.validateForm()){
        		
        		accountReceivableService.insertAccountReceivable( accountReceivable, {
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
            	
            	accountReceivableService.removeAccountReceivable(entity.id, {
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
        	
        	var today = new Date();
        	today.setHours(23,59,59);
        	
        	if($scope.model.accountReceivable.description == null){
        		$mdToast.showSimple("Informe uma descrição para a conta.");
                return false;
        	}
        	if($scope.model.accountReceivable.dueDate == null){
        		$mdToast.showSimple("Informe a data de vencimento para a conta.");
        		return false;
        	}
        	if($scope.model.accountReceivable.value == null){
        		$mdToast.showSimple("Informe valor da conta.");
        		return false;
        	}
        	if($scope.model.accountReceivable.bankAccount == null){
        		$mdToast.showSimple("Selecione uma conta bancária.");
        		return false;
        	}
        	if($scope.model.accountReceivable.category == null){
        		$mdToast.showSimple("Selecione uma categoria.");
        		return false;
        	}
        	if($scope.model.accountReceivable.receivementDate > today ){
        		$mdToast.showSimple("Para baixar o recebimento é preciso definir uma data igual ou anterior a hoje.");
        		return false;
        	}
        	return true;
        }
        /**
         * 
         */
        $scope.getAccountsReceivableTotal = function(accountsReceivable){
        	console.log(accountsReceivable);
        	angular.forEach(accountsReceivable, function( obj, key ) {
        		var today = new Date();
        		today.setHours(0,0,0,0);
        		obj.dueDate.setHours(0,0,0,0);
        		
        		if(obj.status == 'RECEIVED'){
        			$scope.model.totalReceived += obj.value;
        		}
        		if(((obj.status == 'NOT_RECEIVED') && (obj.dueDate >= today )) ){
        			$scope.model.totalNotReceived+= obj.value;
        		}
        		if(((obj.status == 'NOT_RECEIVED') && (obj.dueDate < today )) ){
        			$scope.model.totalOverdue += obj.value;
        		}
        		$scope.model.totalAccountReceivable = $scope.model.totalReceived + $scope.model.totalNotReceived + $scope.model.totalOverdue;
            });
        }
        /**
         * 
         */
        $scope.listCategoriesByFilters = function(filters, pageRequest){
        	
        	categoryService.listCategoriesByFilters( filters.terms.toString(), null, {
                callback: function (result) {
                    $scope.model.categories = $scope.model.categories.concat(result.content);
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
        $scope.listBankAccountsByFilters = function(filters, pageRequest){
        	
        	bankAccountService.listBankAccountsByFilters( filters.terms.toString(), null, {
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
        $scope.listUsersByFilters = function(filters, pageRequest){
        	console.debug("test");
        	accountService.listUsersByFilters( filters.terms.toString(), null, {
                callback: function (result) {
                    $scope.model.users = $scope.model.users.concat(result.content);
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
        
        $scope.accountReceivableLate = function(accountReceivable){
        	
        	var date = new Date();
        	date.setHours(0,0,0,0);
        	accountReceivable.dueDate.setHours(0,0,0,0);
        	
        	if( ((accountReceivable.dueDate < date ) && (accountReceivable.status == 'NOT_RECEIVED')) ){
        		$scope.lateColor = {'background-color' : '#FFEBEE'};
        	}
    		if( ((accountReceivable.dueDate >= date ) || (accountReceivable.status == 'RECEIVED')) ){
        		$scope.lateColor = {};
        	}
        	
        	return $scope.lateColor;
        }
        /**
         * 
         */
        $scope.listAccountsReceivableByEvents = function ( event ) {

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
        
	});
})(window.angular);