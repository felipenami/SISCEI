(function(angular) {
  'use strict';
  /**
   * 
   */
  angular.module('siscei')
	.controller('MatriculationController', function ($scope, $rootScope,$state, $importService, $mdDialog, $mdSidenav, $mdToast, $timeout, $window, $location, $locale , $q, $http) {
		 /**
         * Serviços importados do DWR
         */
		$importService("accountService");
		/**
		 * 
		 */
		$importService("addressService");
		/**
		 * 
		 */
		$importService("matriculationService");
		/**
		 * 
		 */
		
		
		 //----STATES
        /**
         * Representa o estado de listagem de registros.
         */
        $scope.LIST_STATE = "matriculation.list";
        /**
         * Representa o estado para a criação de registros.
         */
        $scope.ADD_STATE = "matriculation.add";
        /**
         * Representa o estado para a edição de registros.
         */
        $scope.EDIT_STATE = "matriculation.edit";
        /**
         * Representa o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "matriculation.detail";		
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
                user : new Matriculation(),
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
                listMatriculations : [],
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
          $state.current.breadCrumbs = [{'state': $scope.LIST_STATE, 'name': 'Alunos'}];

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
                $scope.model.matriculation = new Matriculation();
                $scope.listAllMatriculations();
        };
        /**
         * 
         */
        $scope.changeToAdd = function(){
        	console.debug("changeToAdd");
        	
        	$scope.model.states = [];
        	$scope.model.cities = [];
        	$scope.model.state = {};       	
        	$scope.model.user = new User();
        	$scope.model.user.responsible = {
        			name : null,
        			rg   : null,
        			cpf  : null,
        			phone: null,
        			birthDate: null
        			
        	}
        	$scope.model.user.role = 'STUDENT';
        	
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
        $scope.listAllMatriculations = function(filters, pageRequest){
        	console.debug("test");
        	matriculationService.listAllMatriculations( {
                callback: function (result) {
                	$scope.model.listMatriculations = result;
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
        $scope.insertStudentHandler = function (user){
        	user.password = 'siscei123321';
        	if($scope.validateForm()){
        		accountService.insertUser( user, {
	        		callback: function(result){
	        			$mdToast.showSimple("Aluno salvo com sucesso!");
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
        		$mdToast.showSimple("O nome do aluno deve ser informado.");
                return false;
        	}
        	if($scope.model.user.email == null){
        		$mdToast.showSimple("O email do aluno deve ser informado.");
        		return false;
        	}
        	if($scope.model.user.motherName == null){
        		$mdToast.showSimple("O nome da Mãe deve ser informado.");
        		return false;
        	}
        	if($scope.model.user.phone == null){
        		$mdToast.showSimple("Informe um telefone.");
        		return false;
        	}
        	if($scope.model.user.birthDate == null){
        		$mdToast.showSimple("A data de nascimento deve ser informado.");
        		return false;
        	}
        	if($scope.model.minor){
        		if($scope.model.user.responsible == null){
            		$mdToast.showSimple("Informe um responsável.");
                    return false;
            	}	
        		if($scope.model.user.responsible.name == null){
            		$mdToast.showSimple("O nome do responsável deve ser informado.");
                    return false;
            	}	
        		if($scope.model.user.responsible.cpf == null){
            		$mdToast.showSimple("O CPF do responsável deve ser informado.");
                    return false;
            	}
        		if($scope.checkIfIsMinor($scope.model.user.responsible.birthDate)){
            		$mdToast.showSimple("O responsável não pode ser menor de idade.");
                    return false;
            	}
        		
        		
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
	    /**
	     * 
	     */
		$scope.validarCPF = function ( cpf ) {
			 
			 var modelForm = angular.element( document.querySelector('#studentForm') ).scope()['studentForm'];
			 if ( cpf ) {
				 cpf = cpf.replace(/[^\d]+/g,'');    
				 if(cpf == '') {
					 modelForm.cpfInput.$setValidity( "cpfValido", true );
					 return true;
				 }
				 // Elimina CPFs invalidos conhecidos    
				 if (cpf.length != 11 || 
						 cpf == "00000000000" || 
						 cpf == "11111111111" || 
						 cpf == "22222222222" || 
						 cpf == "33333333333" || 
						 cpf == "44444444444" || 
						 cpf == "55555555555" || 
						 cpf == "66666666666" || 
						 cpf == "77777777777" || 
						 cpf == "88888888888" || 
						 cpf == "99999999999") {
					 modelForm.cpfInput.$setValidity( "cpfValido", false );       
					 return false;
				 }
				 // Valida 1o digito 
				 var add = 0;    
				 for (var i=0; i < 9; i ++)       
					 add += parseInt(cpf.charAt(i)) * (10 - i);  
				 var rev = 11 - (add % 11);  
				 if (rev == 10 || rev == 11)     
					 rev = 0;    
				 if (rev != parseInt(cpf.charAt(9))) {
					 modelForm.cpfInput.$setValidity( "cpfValido", false );      
					 return false;
				 }     
				 // Valida 2o digito 
				 add = 0;    
				 for (var i = 0; i < 10; i ++)        
					 add += parseInt(cpf.charAt(i)) * (11 - i);  
				 var rev = 11 - (add % 11);  
				 if (rev == 10 || rev == 11) 
					 rev = 0;    
				 if (rev != parseInt(cpf.charAt(10))) {
					 modelForm.cpfInput.$setValidity( "cpfValido", false ); 
					 return false;
				 }
				 
				 modelForm.cpfInput.$setValidity( "cpfValido", true ); 
			 }else {
				 modelForm.cpfInput.$setValidity( "cpfValido", true ); 
			 } 
		}
		/**
		 * 
		 */
		$scope.validarCPFResponsible = function ( cpf ) {
			 
			 var modelForm = angular.element( document.querySelector('#studentForm') ).scope()['studentForm'];
			 if ( cpf ) {
				 cpf = cpf.replace(/[^\d]+/g,'');    
				 if(cpf == '') {
					 modelForm.cpfInputResponsible.$setValidity( "cpfValido", true );
					 return true;
				 }
				 // Elimina CPFs invalidos conhecidos    
				 if (cpf.length != 11 || 
						 cpf == "00000000000" || 
						 cpf == "11111111111" || 
						 cpf == "22222222222" || 
						 cpf == "33333333333" || 
						 cpf == "44444444444" || 
						 cpf == "55555555555" || 
						 cpf == "66666666666" || 
						 cpf == "77777777777" || 
						 cpf == "88888888888" || 
						 cpf == "99999999999") {
					 modelForm.cpfInputResponsible.$setValidity( "cpfValido", false );       
					 return false;
				 }
				 // Valida 1o digito 
				 var add = 0;    
				 for (var i=0; i < 9; i ++)       
					 add += parseInt(cpf.charAt(i)) * (10 - i);  
				 var rev = 11 - (add % 11);  
				 if (rev == 10 || rev == 11)     
					 rev = 0;    
				 if (rev != parseInt(cpf.charAt(9))) {
					 modelForm.cpfInputResponsible.$setValidity( "cpfValido", false );      
					 return false;
				 }     
				 // Valida 2o digito 
				 add = 0;    
				 for (var i = 0; i < 10; i ++)        
					 add += parseInt(cpf.charAt(i)) * (11 - i);  
				 var rev = 11 - (add % 11);  
				 if (rev == 10 || rev == 11) 
					 rev = 0;    
				 if (rev != parseInt(cpf.charAt(10))) {
					 modelForm.cpfInputResponsible.$setValidity( "cpfValido", false ); 
					 return false;
				 }
				 
				 modelForm.cpfInputResponsible.$setValidity( "cpfValido", true ); 
			 }else {
				 modelForm.cpfInputResponsible.$setValidity( "cpfValido", true ); 
			 } 
		}
		/**
		 * 
		 */
		
		$scope.checkIfIsMinor = function ( dateOfBirth ) {
			
			
			if( dateOfBirth != null ){
				
				var dateToCalculate = new Date();	
			  	var calculateYear = dateToCalculate.getFullYear();
			    var calculateMonth = dateToCalculate.getMonth();
			    var calculateDay = dateToCalculate.getDate();

			    var birthYear = dateOfBirth.getFullYear();
			    var birthMonth = dateOfBirth.getMonth();
			    var birthDay = dateOfBirth.getDate();

			    var age = calculateYear - birthYear;
			    var ageMonth = calculateMonth - birthMonth;
			    var ageDay = calculateDay - birthDay;

			    if (ageMonth < 0 || (ageMonth == 0 && ageDay < 0)) {
			        age = parseInt(age) - 1;
			    }
			    if(age > 18)
			    	{
			    		return $scope.model.minor = false;
			    	}
			    
			    return $scope.model.minor = true;
			} 
			return $scope.model.minor = false;
		}
	     
	});
  
  })(window.angular);