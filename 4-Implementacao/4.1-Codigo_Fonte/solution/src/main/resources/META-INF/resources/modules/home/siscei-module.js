(function(window, angular, undefined) {
	'use strict';

	//Start the AngularJS
	var module = angular.module('siscei', ['ngMessages', 'ngSanitize', 'ngMaterial', 'ui.router', 'eits-md', 'eits-ng', 'md.data.table', 'ngMask', 'ng-currency' ]);

	/**
	 * 
	 */
	module.config( function( $stateProvider, $urlRouterProvider, $importServiceProvider, $translateProvider, $mdThemingProvider, $mdDateLocaleProvider ) {
		
		
    	/*-------------------------------------------------------------------
    	 *				 		   CONFIGURAÇÕES FORMATO DE DATA
    	 *-------------------------------------------------------------------*/
		/**
		 * 
		 */
		$mdDateLocaleProvider.formatDate = function(date) {
		      var m = moment(date);
		      return m.isValid() ? m.format('L') : '';
		    };
		 /**
		  * 
		  */   
    	/*-------------------------------------------------------------------
    	 *				 		   CONFIGURAÇÕES DE TEMA
    	 *-------------------------------------------------------------------*/
		    
		$mdThemingProvider.definePalette('buttonsPalette', {
		    '50': '4CAF50',
		    '100': '01579B',
		    '200': '01579B',
		    '300': 'e57373',
		    '400': 'ef5350',
		    '500': 'f44336',
		    '600': 'e53935',
		    '700': 'd32f2f',
		    '800': 'c62828',
		    '900': 'b71c1c',
		    'A100': 'ff8a80',
		    'A200': 'ff5252',
		    'A400': 'ff1744',
		    'A700': 'd50000'
		  });
		/**
		 * Paleta de cores padrão do sistema
		 */
        $mdThemingProvider.theme('default')
            .primaryPalette('light-blue', {
                'default': '900', 
                'hue-1': '600', 
                'hue-2': '400', 
                'hue-3': 'A100' 
            })
			/**
			 * Paleta de cores para os botões
			 */
            .accentPalette('buttonsPalette', {
                'hue-1': '50',
                'hue-2': '100',
                'hue-3': '200'
            });
		
        
    	/*-------------------------------------------------------------------
    	 *				 		   Broker configuration
    	 *-------------------------------------------------------------------*/
        /**
         * 
         */
		$importServiceProvider.setBrokerURL("./broker/interface");
		/**
		 * 
		 */
    	/*-------------------------------------------------------------------
    	 *				 		   Translate configuration
    	 *-------------------------------------------------------------------*/
		/**
		 * 
		 */
		$translateProvider.useURL('./bundles');
		/**
		 * 
		 */
    	/*-------------------------------------------------------------------
    	 *				 		     URL Router
    	 *-------------------------------------------------------------------*/
		/**
		 * 
		 */
        $urlRouterProvider.otherwise("/accountPayable/list");
        /**
         * 
         */
    	/*-------------------------------------------------------------------
    	 *				 		     SUPPLIER
    	 *-------------------------------------------------------------------*/
        
		$stateProvider.state('supplier',{
			abstract: true,
			url : "/supplier",
			template: '<div ui-view/>',
			controller : 'SupplierController as supplierController'
		})
		.state('supplier.list',{
			url : "/list",
			templateUrl : "./modules/home/views/supplier/supplier-list.html"
		})
		.state('supplier.add',{
			url : "/add",
			templateUrl : "./modules/home/views/supplier/supplier-form.html"
		})
		.state('supplier.edit',{
			url : "/edit/{id:[0-9]{1,10}}",
			templateUrl : "./modules/home/views/supplier/supplier-form.html"
		})
		.state('supplier.detail',{
			url : "/detail/{id:[0-9]{1,10}}",
			templateUrl : "./modules/home/views/supplier/supplier-detail.html"
		});
		
    	/*-------------------------------------------------------------------
    	 *				 		     BANKACCOUNT
    	 *-------------------------------------------------------------------*/
		
		$stateProvider.state('bankAccount',{
			abstract: true,
			url : "/bankAccount",
			template: '<div ui-view/>',
			controller : 'BankAccountController as bankAccountController'
		})
		.state('bankAccount.list',{
			url : "/list",
			templateUrl : "./modules/home/views/bank-account/bank-account-list.html"
		})
		.state('bankAccount.add',{
			url : "/add",
			templateUrl : "./modules/home/views/bank-account/bank-account-form.html"
		})
		.state('bankAccount.edit',{
			url : "/edit/{id:[0-9]{1,10}}",
			templateUrl : "./modules/home/views/bank-account/bank-account-form.html"
		})
		.state('bankAccount.detail',{
			url : "/detail/{id:[0-9]{1,10}}",
			templateUrl : "./modules/home/views/bank-account/bank-account-detail.html"
		});
		
    	/*-------------------------------------------------------------------
    	 *				 		     ACCOUNTPAYABLE
    	 *-------------------------------------------------------------------*/
		
		$stateProvider.state('accountPayable',{
			abstract: true,
			url : "/accountPayable",
			template: '<div ui-view/>',
			controller : 'AccountPayableController as accountPayableController'
		})
		.state('accountPayable.list',{
			url : "/list",
			templateUrl : "./modules/home/views/account-payable/account-payable-list.html"
		})
		.state('accountPayable.add',{
			url : "/add",
			templateUrl : "./modules/home/views/account-payable/account-payable-form.html"
		})
		.state('accountPayable.edit',{
			url : "/edit/{id:[0-9]{1,10}}",
			templateUrl : "./modules/home/views/account-payable/account-payable-form.html"
		})
		.state('accountPayable.detail',{
			url : "/detail/{id:[0-9]{1,10}}",
			templateUrl : "./modules/home/views/account-payable/account-payable-detail.html"
		});
		
    	/*-------------------------------------------------------------------
    	 *				 		     ACCOUNTRECEIVABLE
    	 *-------------------------------------------------------------------*/
		
		$stateProvider.state('accountReceivable',{
			abstract: true,
			url : "/accountReceivable",
			template: '<div ui-view/>',
			controller : 'AccountReceivableController as accountReceivableController'
		})
		.state('accountReceivable.list',{
			url : "/list",
			templateUrl : "./modules/home/views/account-receivable/account-receivable-list.html"
		})
		.state('accountReceivable.add',{
			url : "/add",
			templateUrl : "./modules/home/views/account-receivable/account-receivable-form.html"
		})
		.state('accountReceivable.edit',{
			url : "/edit/{id:[0-9]{1,10}}",
			templateUrl : "./modules/home/views/account-receivable/account-receivable-form.html"
		})
		.state('accountReceivable.detail',{
			url : "/detail/{id:[0-9]{1,10}}",
			templateUrl : "./modules/home/views/account-receivable/account-receivable-detail.html"
		});
		
		
    	/*-------------------------------------------------------------------
    	 *				 		     COURSE
    	 *-------------------------------------------------------------------*/
		$stateProvider.state('course',{
			abstract: true,
			url : "/course",
			template: '<div ui-view/>',
			controller : 'CourseController as courseController'
		})
		.state('course.list',{
			url : "/list",
			templateUrl : "./modules/home/views/course/course-list.html"
		})
		.state('course.add',{
			url : "/add",
			templateUrl : "./modules/home/views/course/course-form.html"
		})
		.state('course.edit',{
			url : "/edit/{id:[0-9]{1,10}}",
			templateUrl : "./modules/home/views/course/course-form.html"
		})
		.state('course.detail',{
			url : "/detail/{id:[0-9]{1,10}}",
			templateUrl : "./modules/home/views/course/course-detail.html"
		});
		
    	/*-------------------------------------------------------------------
    	 *				 		     CLASSROOM
    	 *-------------------------------------------------------------------*/
		$stateProvider.state('classroom',{
			abstract: true,
			url : "/classroom",
			template: '<div ui-view/>',
			controller : 'ClassroomController as classroomController'
		})
		.state('classroom.list',{
			url : "/list",
			templateUrl : "./modules/home/views/classroom/classroom-list.html"
		})
		.state('classroom.add',{
			url : "/add",
			templateUrl : "./modules/home/views/classroom/classroom-form.html"
		})
		.state('classroom.edit',{
			url : "/edit/{id:[0-9]{1,10}}",
			templateUrl : "./modules/home/views/classroom/classroom-form.html"
		})
		.state('classroom.detail',{
			url : "/detail/{id:[0-9]{1,10}}",
			templateUrl : "./modules/home/views/classroom/classroom-detail.html"
		});
		
		
		
	});
	/**
	 * 
	 */
	module.run( function( $rootScope, $window, $state, $stateParams ) {
		$rootScope.$usuario 	= $window.usuario;
		$rootScope.$state 		= $state;
		$rootScope.$stateParams = $stateParams;
	});

	/**
	 * 
	 */
	angular.element(document).ready( function() {
		angular.bootstrap( document, ['siscei']);
	});

})(window, window.angular);