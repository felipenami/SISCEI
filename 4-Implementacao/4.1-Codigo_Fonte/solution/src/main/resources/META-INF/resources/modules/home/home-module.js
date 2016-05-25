(function(window, angular, undefined) {
	'use strict';

	//Start the AngularJS
	var module = angular.module('home', ['ngMessages', 'ngSanitize', 'ngMaterial', 'ui.router', 'eits-md', 'eits-ng' ]);

	/**
	 * 
	 */
	module.config( function( $stateProvider, $urlRouterProvider, $importServiceProvider, $translateProvider, $mdThemingProvider ) {
		
        //-------
        //CONFIGURAÇÕES DE TEMA
        //-------
		$mdThemingProvider.definePalette('buttonsPalette', {
		    '50': '4CAF50',
		    '100': '01579B',
		    '200': 'FFC107',
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
		
		//-------
		//Broker configuration
		//-------
		$importServiceProvider.setBrokerURL("./broker/interface");
		//-------
		//Translate configuration
		//-------
		$translateProvider.useURL('./bundles');

		//-------
		//URL Router
		//-------
        $urlRouterProvider.otherwise("/");

        //HOME
        $stateProvider.state('home',{
        	url : "/",
        	templateUrl: './modules/home/views/home/home-index.html',
        });
	});

	/**
	 * 
	 */
	module.run( function( $rootScope, $window, $state, $stateParams ) {
		//$rootScope.$usuario 	= $window.usuario;
		$rootScope.$state 		= $state;
		$rootScope.$stateParams = $stateParams;
	});

	/**
	 * 
	 */
	angular.element(document).ready( function() {
		angular.bootstrap( document, ['home']);
	});

})(window, window.angular);