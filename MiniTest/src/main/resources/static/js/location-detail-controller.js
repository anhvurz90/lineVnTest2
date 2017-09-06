(function() {
    'use strict';

    angular
        .module('interview')
        .controller('LocationDetailController', LocationDetailController);

    LocationDetailController.$inject = ['$scope', '$location', '$http', '$resource', '$window', 'NgTableParams'];

    function LocationDetailController ($scope, $location, $http, $resource, $window, NgTableParams) {
        var vm = this;
        console.log("LocationDetailController");
        
        $scope.vm = vm;
        vm.locationId = $location.search().id;
        vm.users = [];
        
        vm.search = search;

        initUserTable();
        
        function search() {
            vm.tableParams.reload();
        }
        function initUserTable() {
            vm.users = [];
            
            var Api = $resource("/location/users?id=" + vm.locationId);
            vm.tableParams = new NgTableParams({
              page: 1, // show first page
              count: 10 // count per page
            }, {
              filterDelay: 300,
              getData: function(params) {
                // ajax request to api
                var jsonParams = params.url();
                jsonParams['searchPattern'] = vm.searchPattern;
                return Api.get(jsonParams).$promise.then(function(data) {
                  params.total(data.count);
                  return data.results;
                });
              }
            });
        }

    }
})();
