(function(angular) {
  'use strict';
  /**
   * 
   */
  angular.module('siscei')
	.controller('UserController', function ($scope, $rootScope,$state, $importService, $mdDialog, $mdSidenav, $mdToast, $timeout, $window, $location, $locale , $q, $http) {
		 /**
         * Serviços importados do DWR
         */
		$importService("accountService");
		/**
		 * 
		 */
		 //----STATES
        /**
         * Representa o estado de listagem de registros.
         */
        $scope.LIST_STATE = "user.list";
        /**
         * Representa o estado para a criação de registros.
         */
        $scope.ADD_STATE = "user.add";
        /**
         * Representa o estado para a edição de registros.
         */
        $scope.EDIT_STATE = "user.edit";
        /**
         * Representa o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "user.detail";		
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
                user : new User(),
                query :{
            	    order: 'name',
            	    limit: 5,
            	    page: 1
            	  },
            	limitOptions : [5, 10, 15],
            	totalElements : 0,
                filters: {
                    terms: [],
                },
                states : [],
                cities : [],
                state : {},
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
          $state.current.breadCrumbs = [{'state': $scope.LIST_STATE, 'name': 'Usuários'}];

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
        	
        	accountService.findUserById(id,{
        		callback: function (result) {
        			$scope.model.user = result;
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
            	size: 100,
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
                $scope.model.user = new User();
                $scope.model.users = [];
                $scope.listUsersByFilters( $scope.model.filters,  $scope.model.page.pageable );
        };
        /**
         * 
         */
        $scope.changeToAdd = function(){
        	console.debug("changeToAdd");
        	
        	$scope.model.user = new User();
        	
        }
        /**
         * 
         */
    	$scope.changeToDetail = function (id) {
        	console.debug("Detail");
        	
        	accountService.findUserById(id,{
        		callback: function (result) {
        			$scope.model.user = result;
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
        $scope.inactivetedUser = function (event, entity) {
            console.debug("Inativando", entity);

            var confirm = $mdDialog.confirm()
                .title('Tem certeza que deseja alterar a situação desse usuário?')
//                .content('Não será possível recuperar este registro se for excluído.')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(event);

            $mdDialog.show(confirm).then(function (result) {
            	
            	accountService.inactivetedUser(entity.id, {
                    callback: function (result) {
                        if( $state.current.name == $scope.LIST_STATE){
                            $scope.changeToList();
                        } else {
                            $state.go( $scope.LIST_STATE );
                        }
                        $mdToast.showSimple("Usuário inativado");
                        $scope.$apply();
                    },
                    errorHandler: function (message, exception) {
                        $mdToast.showSimple("Você não tem permissão para realizar essa ação!");
                        $state.go($scope.LIST_STATE);
                        $scope.$apply();
                    }
                });
            });
        };
        /**
         * 
         */
        $scope.activetedUser = function (event, entity) {
            console.debug("Inativando", entity);

            var confirm = $mdDialog.confirm()
                .title('Tem certeza que deseja alterar a situação desse usuário?')
//                .content('Não será possível recuperar este registro se for excluído.')
                .ok('Sim')
                .cancel('Cancelar')
                .targetEvent(event);

            $mdDialog.show(confirm).then(function (result) {
            	
            	accountService.activetedUser(entity.id, {
                    callback: function (result) {
                        if( $state.current.name == $scope.LIST_STATE){
                            $scope.changeToList();
                        } else {
                            $state.go( $scope.LIST_STATE );
                        }
                        $mdToast.showSimple("Usuário ativado");
                        $scope.$apply();
                    },
                    errorHandler: function (message, exception) {
                        $mdToast.showSimple("Você não tem permissão para realizar essa ação!");
                        $state.go($scope.LIST_STATE);
                        $scope.$apply();
                    }
                });
            });
        };
    	/**
    	 * 
    	 */
        $scope.listUsersByFilters = function(filters, pageRequest){
        	console.debug("test");
        	accountService.listUsersByFilters( filters.terms.toString(), pageRequest, {
                callback: function (result) {
                    $scope.model.users = $scope.model.users.concat(result.content);
                    $scope.model.totalElements = result.totalElements;
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
        $scope.insertUserHandler = function (user){
        	if($scope.validateForm()){
        		accountService.insertUser( user, {
	        		callback: function(result){
	        			$mdToast.showSimple("Usuário salvo com sucesso!");
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
        $scope.updateSupplierHandler = function(supplier){
        	if($scope.validateForm()){
        		supplierService.updateSupplier(supplier, {
        			callback: function(result){
	        			$mdToast.showSimple("Fornecedor	alterado com sucesso!");
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
        	if($scope.model.user.name == null){
        		$mdToast.showSimple("O nome do usuário deve ser informado.");
                return false;
        	}
        	if($scope.model.user.email == null){
        		$mdToast.showSimple("O email do usuário deve ser informado.");
        		return false;
        	}
        	if($scope.model.user.role == null){
        		$mdToast.showSimple("O perfil do usuário deve ser informado.");
        		return false;
        	}
        	if($scope.model.user.password != $scope.model.user.passwordConfirmation){
        		$mdToast.showSimple("As senhas não conferem..");
        		return false;
        	}
        	return true;
        }
        /**
         * 
         */
        $scope.listUsersByEvents = function ( event ) {
        	
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