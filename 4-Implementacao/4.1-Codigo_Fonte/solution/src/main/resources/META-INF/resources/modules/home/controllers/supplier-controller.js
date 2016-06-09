(function(angular) {
  'use strict';
  /**
   * 
   */
  angular.module('siscei')
	.controller('SupplierController', function ($scope, $rootScope,$state, $importService, $mdDialog, $mdSidenav, $mdToast, $timeout, $window, $location, $locale , $q) {
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
		$importService("addressService");
		
		 //----STATES
        /**
         * Representa o estado de listagem de registros.
         */
        $scope.LIST_STATE = "supplier.list";
        /**
         * Representa o estado para a criação de registros.
         */
        $scope.ADD_STATE = "supplier.add";
        /**
         * Representa o estado para a edição de registros.
         */
        $scope.EDIT_STATE = "supplier.edit";
        /**
         * Representa o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "supplier.detail";		
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
                supplier : new Supplier(),
                filters: {
                    terms: [],
                },
                states : [],
                cities : [],
                state : {},
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
          $state.current.breadCrumbs = [{'state': $scope.LIST_STATE, 'name': 'Supplier'}];

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
        $scope.calculateWindowHeight = function(){

            var sectionsWindow = angular.element(document.querySelector('#sectionsWindow'));
            if (sectionsWindow != null) {
                var windowHeight = window.innerHeight;
                var height = windowHeight - sectionsWindow.offset().top + 'px';
                sectionsWindow.css('height', height);
            }
        };
        /**
         *  
         */
        $scope.changeToEdit = function (id) {
        	
        	financeService.findSupplierById(id,{
        		callback: function (result) {
        			$scope.model.supplier = result;
        			$scope.listCitiesByState($scope.model.supplier.address.city.state.id);
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
            			property: 'tradeName',
            			nullHandlingHint: null
            		}]
                }
            };
                
                //Limpamos a lista para um nova consulta
                $scope.model.supplier = new Supplier();
                $scope.model.suppliers = [];
                $scope.listSuppliersByFilters( $scope.model.filters,  $scope.model.page.pageable );
        };
        /**
         * 
         */
        $scope.changeToAdd = function(){
        	console.debug("changeToAdd");
        	
        	$scope.model.states = [];
        	$scope.model.cities = [];
        	$scope.model.state = {};
        	$scope.model.supplier = new Supplier();
        }
        /**
         * 
         */
        $scope.changeToDetail = function(){
        	console.debug("changeToDetail");
        }
        /**
         * 
         */
        $scope.listSuppliersByFilters = function(filters, pageRequest){
        	console.debug("test");
        	financeService.listSuppliersByFilters( filters.terms.toString(), pageRequest, {
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
        /**
         * 
         */
        $scope.insertSupplierHandler = function (supplier){
        	if($scope.validateForm()){
	        	financeService.insertSupplier( supplier, {
	        		callback: function(result){
	        			$mdToast.showSimple("Fornecedor	 salvo com sucesso!");
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
        $scope.listAllStates = function(){
        	
        	addressService.listAllStates({
        		callback: function (result) {
                    $scope.model.states = result;
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
        $scope.listCitiesByState = function( state ){
	        	addressService.listCitiesByState( state, {
	        		callback: function (result) {
	                    $scope.model.cities = result;
	                    $scope.$apply();
	                },
	                errorHandler: function (message, exception) {
	                    $mdToast.showSimple(message);
	                    $scope.$apply();
	                }
	        	});
        }
        
        $scope.validateForm = function (){
        	if($scope.model.supplier.tradeName == null){
        		$mdToast.showSimple("O nome fantasia deve ser informado.");
                return false;
        	}
        	if($scope.model.supplier.phone == null){
        		$mdToast.showSimple("O telefone do fornecedor deve ser informado.");
        		return false;
        	}
        	if($scope.model.supplier.address.street == null){
        		$mdToast.showSimple("O endereço do fornecedor deve ser informado.");
        		return false;
        	}
        	if($scope.model.supplier.address.neighborhood == null){
        		$mdToast.showSimple("Informe o bairro.");
        		return false;
        	}
        	if($scope.model.state == null){
        		$mdToast.showSimple("Informe o estado.");
        		return false;
        	}
        	if($scope.model.supplier.address.city == null){
        		$mdToast.showSimple("A cidade deve ser informada.");
        		return false;
        	}
        	return true;
        }
		
	});
  })(window.angular);