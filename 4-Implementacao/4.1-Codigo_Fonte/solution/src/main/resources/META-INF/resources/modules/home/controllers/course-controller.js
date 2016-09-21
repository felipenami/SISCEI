(function(angular) {
  'use strict';
  /**
   * 
   */	
	
  angular.module('siscei')
	.controller('CourseController', function ($scope, $rootScope,$state, $importService, $mdDialog, $mdSidenav, $mdToast, $timeout, $window, $location, $locale , $q) {
		/**
         * Serviços importados do DWR
         */
		$importService("courseService");
		/**
		 * 
		 */
		 //----STATES
        /**
         * Representa o estado de listagem de registros.
         */
        $scope.LIST_STATE = "course.list";
        /**
         * Representa o estado para a criação de registros.
         */
        $scope.ADD_STATE = "course.add";
        /**
         * Representa o estado para a edição de registros.
         */
        $scope.EDIT_STATE = "course.edit";
        /**
         * Representa o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "course.detail";		
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
                course : new Course(),
                listDisciplines : [],
                filters: {
                    terms: [],
                },
                courses : [],
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
          $state.current.breadCrumbs = [{'state': $scope.LIST_STATE, 'name': 'Cursos'}];

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
        	
        	courseService.findCourseById(id,{
        		callback: function (result) {
        			$scope.model.course = result;
        			$scope.model.listDisciplines = $scope.model.course.discipline;
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
                $scope.model.course = new Course();
                $scope.model.courses = [];
                $scope.listCoursesByFilters( $scope.model.filters,  $scope.model.page.pageable );
        };
        /**
         * 
         */
        $scope.changeToAdd = function () {
        	console.debug("Add");
        	$scope.model.course = new Course();
        	$scope.model.listDisciplines = [];
        }

        /**
         * 
         */
        $scope.changeToDetail = function (id) {
        	console.debug("Detail");
        	courseService.findCourseById(id,{
        		callback: function (result) {
        			$scope.model.course = result;
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
        $scope.listCoursesByFilters = function(filters, pageRequest){
        	
        	courseService.listCoursesByFilters( filters.terms.toString(), pageRequest, {
                callback: function (result) {
                    $scope.model.courses = $scope.model.courses.concat(result.content);
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
        $scope.insertCourseHandler= function(course){
        	course.discipline = $scope.model.listDisciplines;
        	if($scope.validateForm()){
        		courseService.insertCourse( course, {
	        		callback: function(result){
	        			$mdToast.showSimple("Curso salvo com sucesso!");
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
            	
            	courseService.removeCourse(entity.id, {
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
        	if($scope.model.course.name == null){
        		$mdToast.showSimple("Informe o nome do curso.");
                return false;
        	}
        	if($scope.model.course.description == null){
        		$mdToast.showSimple("Informe uma descrição do curso.");
        		return false;
        	}
        	if($scope.model.course.discipline == "" ){
        		$mdToast.showSimple("O curso dever ter ao menos uma disciplina.");
        		return false;
        	}
        	return true;
        }
        /**
         * 
         */
        $scope.listCoursesByEvents = function ( event ) {
        	
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
        
        $scope.downloadCourseReport = function( courseId ){
        	courseService.downloadCourseReport( courseId, {
        		callback: function (result) {
        				dwr.engine.openInDownload( result );
        				$scope.$apply();
        		},
        		errorHandler: function (message, exception) {
    				$eitsToast.error(message);
    				$scope.$apply();
        		}
       		});
       };
        /**
         * 
         */
        $scope.openPopupDiscipline = function(event, discipline){

            $mdDialog.show({
                templateUrl: 'modules/home/views/course/popup/discipline-popup.html',
                targetEvent: event,
                scope: $scope.$new(),
                controller: "DisciplinePopupController",
                resolve:{
                	discipline: function(){
                        return discipline;
                    }
                }
            }).then(function(result){
                $scope.model.listDisciplines.push( result );
            });
        };
        /**
         * 
         */
        $scope.removeDiscipline = function (discipline){

            var index = $scope.model.listDisciplines.indexOf(discipline);
            $scope.model.listDisciplines.splice(index, 1);
        };

        
        
	});
	
})(window.angular);