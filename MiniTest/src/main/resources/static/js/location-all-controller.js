(function() {
    'use strict';

    angular
        .module('interview')
        .controller('LocationAllController', LocationAllController);

    LocationAllController.$inject = ['$scope', '$http', '$resource', '$window', 'NgTableParams'];

    function LocationAllController ($scope, $http, $resource, $window, NgTableParams) {
        var vm = this;
        console.log("LocationAllController");

        $scope.vm = vm;
        vm.areas = [];
        vm.isSearching = false;
        vm.search = search;
        vm.showLocation = showLocation;

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
            vm.isSearching = true;
            
            var Api = $resource("/location/ajax?area=" + vm.selectedArea.id);
            this.tableParams = new NgTableParams({
              page: 1, // show first page
              count: 10 // count per page
            }, {
              filterDelay: 300,
              getData: function(params) {
                // ajax request to api
                return Api.get(params.url()).$promise.then(function(data) {
                  params.total(data.count);
                  vm.isSearching = false;
                  return data.results;
                });
              }
            });
            
//            $http({
//                method: 'GET',
//                url: "/location/ajax?area=" + vm.selectedArea.id
//            }).then(
//              function successCallback(response) {
//                  vm.locations=response.data;
//                  vm.isSearching = false;
//              }, function errorCallback(response) {
//                alert("Can not fetch locations!!!");
//                vm.isSearching = false;
//              });
        }

        function showLocation(locationId) {
            $window.location.href = '/location/detail?id=' + locationId;
        }
    }
})();
