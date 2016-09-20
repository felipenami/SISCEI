(function (angular) {
    'use strict';

    angular.module('siscei')
        .controller('DisciplinePopupController', function ( $scope, $state, $rootScope, $importService, $mdSidenav, $mdToast, $mdDialog, $log, $timeout, $q, $location, discipline, listDisciplines ) {

            /**
             * Serviços importados do DWR
             */
            $importService("courseService");

            /*-------------------------------------------------------------------
             * 		 				 	ATTRIBUTES
             *-------------------------------------------------------------------*/

            /**
             * Objeto retornado da Controler Training
             */
            $scope.discipline = discipline;

            $scope.model = {
                user: {},
                users : [],
                listUsers : [],
                listUsersSelected: listInternalUsers, //Lista passada como parametro da controller training
                department: {},


                filters: {
                    terms: []
                },
                showLoading : true,
                notFound    : false,
                internalUser : false,
                externalUser : false,

                page: {//PageImpl
                    content: null,
                    pageable: {//PageRequest
                        size: 5,
                        page: 0,
                        sort: {//Sort
                            orders: [

                            ]
                        }
                    }
                }
            };

            /*-------------------------------------------------------------------
             * 		 				  POST CONSTRUCT
             *-------------------------------------------------------------------*/

            /*-------------------------------------------------------------------
             * 		 			     HANDLERS - TRAINING
             *-------------------------------------------------------------------*/

            /*-------------------------------------------------------------------
             * 		 				 PRIVATE BEHAVIORS
             *-------------------------------------------------------------------*/

            $scope.changeToList = function( type ){

                //Limpamos a lista para um nova consulta
                $scope.model.users = [];
                $scope.model.employees= [];

                $scope.model.page.pageable = {
                    size: 5,
                    page: 0,
                    sort: {//Sort
                        orders: [

                        ]
                    }
                };

                $scope.listInternalUsersByFilters()
            };

            /**
             * Realiza a consulta de registros dos instrutores internos, consirando filtro, paginação e sorting.
             *
             * @see $scope.LIST_STATE
             * @see $scope.filters
             * @see $scope.page
             */
            $scope.listInternalUsersByFilters = function (users, pageRequest) {

                var departmentId = $scope.model.department != null ? $scope.model.department.id : null;

                $scope.model.showLoading = true;
                accountService.listUsersByFiltersWhitPage( $scope.model.filters.terms.toString(), departmentId, $scope.model.page.pageable, {
                    callback: function (result) {
                        result = JSON.parse(result);
                        $scope.model.page = result;
                        $scope.model.users = $scope.model.users.concat(result.content);
                        $scope.model.showLoading = false;
                        $scope.model.notFound = result.total == 0 ? true : false;
                        $scope.model.internalUser = true;
                        $scope.$apply();
                        if( typeof($scope.model.listUsersSelected ) != "undefined" ){
                            $scope.selectList();
                        }
                    },
                    errorHandler: function (message, exception) {
                        $mdToast.showSimple(message);
                        $scope.$apply();
                    }
                });
            };

            /**
             * Realiza a consulta de registros, consirando filtro, paginação e sorting.
             *
             * Por uma limitação do componente visual,
             * neste caso é necessário fazer uma consulta sincrona
             * ao invés de assincrona para renderizar os dados corretamente.
             *
             * @see $scope.LIST_STATE
             * @see $scope.filters
             * @see $scope.page
             */
            function querySearchDepartments(filter) {
                var deferred = $q.defer();
                accountService.listDepartmentsByFilters( filter, {
                    callback: function (result) {
                        deferred.resolve(result);
                    },
                    errorHandler: function (message, exception) {
                        $mdToast.showSimple(message);
                    }
                });
                return deferred.promise;
            }
            $scope.querySearchDepartments = querySearchDepartments;


            /**
             * Função resposável por adicionar ou remover uma User conforme ela seja marcada ou desmarcada
             */
            $scope.addUserList = function (user){

                if( !user.selected ){
                    $scope.model.listUsers.push(user);
                } else {
                    var index = $scope.model.listUsers.indexOf(user);

                    if (index > -1) {
                        $scope.model.listUsers.splice(index, 1);
                    }
                }
            };

            /**
             * Devemos procurar pelo objeto contido na lista de Uses para o mesmo seja marcado como 'Selected' que identifica qual User está sendo usado
             * @param object
             * @returns {number}
             */
            function searchIndexOf(object) {

                var listUsers   = $scope.model.users.length;
                for (var i = 0; i < listUsers; i++) {
                    if ($scope.model.users[i].id == object.id) {
                        return i;
                    }
                }
                return -1;
            }

            /**
             * Marcamos as Users que está sendo usado na lista de User vinda do Training
             */
            $scope.selectList = function(){

                var listUsers = $scope.model.listUsersSelected.length;
                for (var i = 0; i < listUsers ; i++) {

                    var index = searchIndexOf($scope.model.listUsersSelected[i]);
                    if( index >= 0 ){
                        $scope.model.users[index].selected = true;
                        $scope.model.listUsers[i] = $scope.model.users[index];
                    }
                }
            };

            /**
             * Por alguma restrição dos componentes do material não foi possivel adicionar um tamanho de 100% na pagina da lista para gerar o scroll
             * Sendo assim, foi necessário calcular o tamanho da página para pegar o valor e aplicar no elemento para gerar o scroll.
             */
            $scope.calculatePopupHeight = function(){

                $timeout(function(){
                    var sectionsWindow = angular.element(document.querySelector('.sectionsPopup'));
                    if (sectionsWindow != null) {
                        var windowHeight = angular.element(document.querySelector('.popup-participant'));
                        var height = windowHeight - sectionsWindow.offset().top + 'px';
                        sectionsWindow.css('height', sectionsWindow.innerHeight());
                    }
                },100);
            };

            /**
             * Configura o pageRequest conforme o componente visual pager
             * e chama o serviçoe de listagem, considerando o filtro corrente na tela.
             *
             * @see currentPage
             * @see data.filter
             */
            $scope.scrollPage = function() {
                $scope.model.showLoading = false;
                if( !$scope.model.page.lastPage ) {
                    $scope.model.page.pageable.page++;
                    $scope.listInternalUsersByFilters()
                }
            };

            /**
             * Realiza a consulta de registros, considerando filtro, paginação e sorting através dos eventos do "Enter" e "Backspace".
             *
             * @param event
             */
            $scope.listUsersByEvents = function ( event ) {

                var textDepartment = angular.element('.autocomplete-department .md-input-has-value .md-input').val();

                if( event.keyCode == 13 ||
                    (( event.keyCode == 8 && $scope.model.filters.terms == "" && event.currentTarget.localName == "input" ) ||
                    ( event.keyCode == 8 && textDepartment == null && event.currentTarget.localName == "md-autocomplete")) ){
                    $scope.changeToList($scope.model.type);
                }
            };

            /**
             *
             */
            $scope.close = function () {
                $mdDialog.cancel();
            };

            $scope.add = function () {
                for(var i= 0, count = $scope.model.listUsers.length; i<count; i++){
                    $scope.model.listUsers[i].role = null;
                }
                $mdDialog.hide($scope.model.listUsers);
            };

        });

}(window.angular));
