(function() {
    'use strict';

    angular
        .module('interview')
        .controller('LocationAllController', LocationAllController);

    LocationAllController.$inject = ['$scope', '$http'];

    function LocationAllController ($scope, $http) {
        var vm = this;
        console.log("LocationAllController");

        $scope.vm = vm;
        vm.areas = [];
        vm.isSearching = false;
        vm.search = search;
//        vm.loadPage = loadPage;
//        vm.predicate = pagingParams.predicate;
//        vm.reverse = pagingParams.ascending;
//        vm.transition = transition;
//        vm.itemsPerPage = paginationConstants.itemsPerPage;
//
        loadAllAreas();
        function loadAllAreas() {
            $http({
                method: 'GET',
                url: '/location/areas/',
            }).then(
              function successCallback(response) {
                  vm.areas = [{id:0, name:'All'}];
                  vm.areas = vm.areas.concat(response.data);
              }, function errorCallback(response) {
                alert("Can not fetch areas!!!");
              });
        }
        
        function search() {
            vm.locations = [];
            //$("#btn_search").prop('disabled', true);
            vm.isSearching = true;
            //$("#txt_search").text(' In Progess');
            //$("#fa_spinner").removeClass("hidden");
            $http({
                method: 'GET',
                url: "/location/ajax?area=" + vm.selectedArea.id
            }).then(
              function successCallback(response) {
                  vm.locations=response.data;
                  vm.isSearching = false;
              }, function errorCallback(response) {
                alert("Can not fetch locations!!!");
                vm.isSearching = false;
              });
        }

//        function loadPage(page) {
//            vm.page = page;
//            vm.transition();
//        }
//
//        function transition() {
//            $state.transitionTo($state.$current, {
//                page: vm.page,
//                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
//                search: vm.currentSearch
//            });
//        }
    }
})();
