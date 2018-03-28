(function(angular) {
  'use strict';
  /**
   * 
   */	
	
  angular.module('siscei')
	.controller('ClassroomController', function ($scope, $rootScope,$state, $importService, $mdDialog, $mdSidenav, $mdToast, $timeout, $window, $location, $locale , $q) {
		/**
         * Serviços importados do DWR
         */
		$importService("classRoomService");
		/**
		 * 
		 */
		$importService("courseService");
		/**
		 * 
		 */
		 //----STATES
        /**
         * Representa o estado de listagem de registros.
         */
        $scope.LIST_STATE = "classroom.list";
        /**
         * Representa o estado para a criação de registros.
         */
        $scope.ADD_STATE = "classroom.add";
        /**
         * Representa o estado para a edição de registros.
         */
        $scope.EDIT_STATE = "classroom.edit";
        /**
         * Representa o estado de detalhe de um registro.
         */
        $scope.DETAIL_STATE = "classroom.detail";		
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
                classroom : new Classroom(),
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
                listSchedules : {},
                courses :[],
                classrooms : [],
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
          $state.current.breadCrumbs = [{'state': $scope.LIST_STATE, 'name': 'Turmas'}];

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
        	
        	classRoomService.findClassroomById(id,{
        		callback: function (result) {
        			$scope.model.classroom = result;
        			$scope.model.listSchedules = $scope.model.classroom.schedule;
        			$scope.$apply();
        		},
                errorHandler: function (message, exception) {
		        	$mdToast.showSimple(message);
		            $state.go($scope.LIST_STATE);
		            $scope.$apply();
                }
        	});
        	
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
        	$scope.listCoursesByFilters( $scope.model.filters,  $scope.model.page.pageable );
        	
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
                $scope.model.classroom = new Classroom();
                $scope.model.classrooms = [];
                $scope.listClassroomsByFilters( $scope.model.filters,  $scope.model.page.pageable );
        };
        /**
         * 
         */
        $scope.changeToAdd = function () {
        	console.debug("Add");
        	$scope.model.classroom = new Classroom();
        	$scope.model.listSchedules = [];
        	$scope.model.courses = [];
        	$scope.model.classroom.status = "OPEN";
        	
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
        	$scope.listCoursesByFilters( $scope.model.filters,  $scope.model.page.pageable );
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
        $scope.listClassroomsByFilters = function(filters, pageRequest){
        	
        	classRoomService.listClassroomsByFilters( filters.terms.toString(), null, pageRequest, {
                callback: function (result) {
                    $scope.model.classrooms = $scope.model.classrooms.concat(result.content);
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
        /**
         * 
         */
        $scope.insertClassRoomHandler = function(classroom){
        	classroom.schedule =  $scope.model.listSchedules;
//        	$scope.prepareHour($scope.model.listSchedules);
//        	if($scope.validateForm()){
        		classRoomService.insertClassroom( classroom, {
	        		callback: function(result){
	        			$mdToast.showSimple("Turma salva com sucesso!");
	                    $state.go($scope.LIST_STATE);
	                    $scope.$apply();
	        		},
	        		 errorHandler: function (message, exception) {
	                     $mdToast.showSimple(message);
	                     $scope.$apply();
	                 }
	        	})
//        	}
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
        $scope.prepareHour = function(listSchedules){
        	var beginHour, beginMinute, endHour, endMinute;
        	
        	angular.forEach(listSchedules, function( obj, key ) {
        		beginHour   = parseFloat(obj.beginHour.split(":")[0]),
	            beginMinute = parseFloat(obj.beginHour.split(":")[1]);
        		
        		endHour   = parseFloat(obj.endHour.split(":")[0]),
	            endMinute = parseFloat(obj.endHour.split(":")[1]);
        		
        		$scope.model.classroom.schedule.beginHour = new Date();
                $scope.model.classroom.schedule.endHour = new Date();
                
                $scope.model.classroom.schedule.beginHour.setHours(beginHour, beginMinute);
                $scope.model.classroom.schedule.endHour.setHours(endHour, endMinute);
        		
            });
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
        	if($scope.model.classroom.name == null){
        		$mdToast.showSimple("Informe o nome do curso.");
                return false;
        	}
        	if($scope.model.classroom.schedule.beginHour == null){
        		$mdToast.showSimple("Informe a hora inicial.");
        		return false;
        	}
        	if($scope.model.classroom.schedule.endHour == null ){
        		$mdToast.showSimple("Informe a hora final.");
        		return false;
        	}
        	if($scope.model.classroom.course == null ){
        		$mdToast.showSimple("Informe o curso desta turma.");
        		return false;
        	}
        	return true;
        }
        /**
         * 
         */
        $scope.listClassroomsByEvent = function ( event ) {
        	
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
        $scope.openPopupSchedule = function(event, schedule){

            $mdDialog.show({
                templateUrl: 'modules/home/views/classroom/popup/schedule-popup.html',
                targetEvent: event,
                scope: $scope.$new(),
                controller: "SchedulePopupController",
                resolve:{
                	schedule: function(){
                        return schedule;
                    }
                }
            }).then(function(result){
                $scope.model.listSchedules.push( result );
            });
        };
        /**
         * 
         */
        $scope.removeSchedule = function (schedule){

            var index = $scope.model.listSchedules.indexOf(schedule);
            $scope.model.listSchedules.splice(index, 1);
        };

        
        
	});
	
})(window.angular);