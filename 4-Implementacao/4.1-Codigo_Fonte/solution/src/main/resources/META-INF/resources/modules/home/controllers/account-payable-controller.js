(function(angular) {
  'use strict';
  /**
   * 
   */	
	
  angular.module('siscei')
	.controller('AccountPayableController', function ($scope, $rootScope,$state, $importService, $mdDialog, $mdSidenav, $mdToast, $timeout, $window, $location, $locale , $q) {
		/**
         * Serviços importados do DWR
         */
		$importService("accountService");
		/**
		 * 
		 */
		$importService("accountPayableService");
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
        $scope.LIST_STATE = "accountPayable.list";
        /**
         * Representa o estado para a criação de registros.
         */
        $scope.ADD_STATE = "accountPayable.add";
        /**
         * Representa o estado para a edição de registros.
         */
        $scope.EDIT_STATE = "accountPayable.edit";
        /**
         * Representa o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "accountPayable.detail";		
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
                accountPayable : new AccountPayable(),
                filters: {
                    terms: [],
                },
                accountsPayable : [],
                totalAccountsPayable : 0,
                totalPaid : 0,
                totalNotPaid : 0,
                totalDue: 0,
                categories : [],
                bankAccounts : [],
                suppliers : [],
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
          $state.current.breadCrumbs = [{'state': $scope.LIST_STATE, 'name': 'Contas a Pagar'}];

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
        	
           	$scope.model.accountPayable = new AccountPayable();
        	$scope.model.categories = [];
        	$scope.model.bankAccounts = [];
        	$scope.model.suppliers = [];
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
        	$scope.model.page.pageable = {
                	size: 9,
                	page: 0,
                	sort: {
                		orders: [{ 
                			direction: 'ASC',
                			property: 'tradeName',
                			nullHandlingHint: null
                		}]
                    }
                };
        	$scope.listSuppliersByFilters( $scope.model.filters, $scope.model.page.pageable );
        	
        	accountPayableService.findAccountPayableById(id,{
        		callback: function (result) {
        			$scope.model.accountPayable = result;
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
                $scope.model.accountPayable = new AccountPayable();
                $scope.model.accountsPayable = [];
                $scope.model.totalAccountsPayable = 0;
                $scope.model.totalPaid = 0;
                $scope.model.totalNotPaid = 0;
                $scope.model.totalDue = 0;
                $scope.listAccountsPayableByFilters( $scope.model.filters,  $scope.model.page.pageable );
        };
        /**
         * 
         */
        $scope.changeToAdd = function () {
        	console.debug("Add");
            
        	$scope.model.accountPayable = new AccountPayable();
        	$scope.model.categories = [];
        	$scope.model.bankAccounts = [];
        	$scope.model.suppliers = [];
        	$scope.model.accountPayable.status = 'NOT_PAID';
        	$scope.model.accountPayable.dueDate = new Date();
        	$scope.model.accountPayable.value = 0;
        	
        	$scope.listBankAccountsByFilters($scope.model.filters, null);
        	$scope.listCategoriesByFilters($scope.model.filters, null);
        	$scope.listSuppliersByFilters( $scope.model.filters, null );
        	$scope.model.accountPayable.entryDate = new Date();
        }

        /**
         * 
         */
        $scope.changeToDetail = function (id) {
        	console.debug("Detail");
        	
        	accountPayableService.findAccountPayableById(id,{
        		callback: function (result) {
        			$scope.model.accountPayable = result;
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
        $scope.listAccountsPayableByFilters = function(filters, pageRequest){
        	
        	accountPayableService.listAccountsPayableByFilters( filters.terms.toString() , null,null, pageRequest, {
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
        $scope.insertAccountPayableHandler= function(accountPayable){
        	if($scope.validateForm()){
        		if($scope.model.accountPayable.status == null){
            		$scope.model.accountPayable.status = 'NOT_PAID';
            	}
        		if($scope.model.accountPayable.status == 'NOT_PAID'){
            		$scope.model.accountPayable.paymentDate = null;
            	}
        		accountPayableService.insertAccountPayable( accountPayable, {
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
        $scope.updateAccountPayableHandler= function(accountPayable){
        	if($scope.validateForm()){
        		if($scope.model.accountPayable.status == null){
            		$scope.model.accountPayable.status = 'NOT_PAID';
            	}
        		if($scope.model.accountPayable.status == 'NOT_PAID'){
            		$scope.model.accountPayable.paymentDate = null;
            	}
        		accountPayableService.insertAccountPayable( accountPayable, {
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
            	
            	accountPayableService.removeAccountPayable(entity.id, {
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
        	
        	if($scope.model.accountPayable.description == null){
        		$mdToast.showSimple("Informe uma descrição para a conta.");
                return false;
        	}
        	if($scope.model.accountPayable.dueDate == null){
        		$mdToast.showSimple("Informe a data de vencimento para a conta.");
        		return false;
        	}
        	if($scope.model.accountPayable.value == 0 ){
        		$mdToast.showSimple("Informe o valor da conta.");
        		return false;
        	}
        	if($scope.model.accountPayable.bankAccount == null){
        		$mdToast.showSimple("Selecione uma conta bancária.");
        		return false;
        	}
        	if($scope.model.accountPayable.category == null){
        		$mdToast.showSimple("Selecione uma categoria.");
        		return false;
        	}
        	if($scope.model.accountPayable.paymentDate > today ){
        		$mdToast.showSimple("Para baixar o pagamento é preciso definir uma data igual ou anterior a hoje.");
        		return false;
        	}
        	return true;
        }
        /**
         * 
         */
        $scope.getAccountsPayableTotal = function(accountsPayable){
        	
        	angular.forEach(accountsPayable, function( obj, key ) {
        		var today = new Date();
        		today.setHours(0,0,0,0);
        		obj.dueDate.setHours(0,0,0,0);
        		if(obj.status == 'PAID'){
        			$scope.model.totalPaid += obj.value;
        		}
        		if(((obj.status == 'NOT_PAID') && (obj.dueDate >= today )) ){
        			$scope.model.totalNotPaid+= obj.value;
        		}
        		if(((obj.status == 'NOT_PAID') && (obj.dueDate < today )) ){
        			$scope.model.totalDue += obj.value;
        		}
        		$scope.model.totalAccountsPayable = $scope.model.totalPaid + $scope.model.totalNotPaid + $scope.model.totalDue;
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
        $scope.listSuppliersByFilters = function(filters, pageRequest){
        	console.debug("test");
        	supplierService.listSuppliersByFilters( filters.terms.toString(), null, {
                callback: function (result) {
                    $scope.model.suppliers = $scope.model.suppliers.concat(result.content);
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
        
        $scope.accountPayableLate = function(accountPayable){
        	
        	var date = new Date();
        	date.setHours(0,0,0,0);
        	accountPayable.dueDate.setHours(0,0,0,0);
        	
        	if( ((accountPayable.dueDate < date ) && (accountPayable.status == 'NOT_PAID')) ){
        		$scope.lateColor = {'background-color' : '#FFEBEE'};
        	}
    		if( ((accountPayable.dueDate >= date ) || (accountPayable.status == 'PAID')) ){
        		$scope.lateColor = {};
        	}
        	
        	return $scope.lateColor;
        }
        /**
         * 
         */
        $scope.listAccountsPayableByEvents = function ( event ) {

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