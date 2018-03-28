(function ( angular ) {
    'use strict';
    
/**
 * 
 * @param $scope
 * @param $state
 */
angular.module('siscei')
	   .controller('HomeController', function( $rootScope, $scope, $state, $importService, $mdToast, $mdDialog, $mdSidenav ) {

    /*-------------------------------------------------------------------
     * 		 				 	ATTRIBUTES
     *-------------------------------------------------------------------*/
    //----STATES
    
	//-----
	/**
	 * 
	 */
	$scope.menuSideNavId = "menuSideNav";
	$scope.user = user;
	/*-------------------------------------------------------------------
     * 		 				  	POST CONSTRUCT
     *-------------------------------------------------------------------*/
	
    /*-------------------------------------------------------------------
     * 		 				 	  HANDLERS
     *-------------------------------------------------------------------*/
    /**
     * 
     */
    $scope.toggleMenuSideNavHandler = function() {
    	console.log("toggleMenuSideNavHandler");
    	$mdSidenav($scope.menuSideNavId).toggle();
    };
    
    /**
     * 
     */
    $scope.hasPermission = function(role) {
    	var roles = (typeof role == "string") ? [role] : role;
    	var authorities = $scope.user.principal.authorities;
    	
    	for(var role in roles) {
    		for(var i in authorities) {
    			switch(authorities[i].$name) {
    				case roles[role]:
    					return true;
    					break;
    			}
    		}
    	}
    	
    	return false;
    }
});

}(window.angular));