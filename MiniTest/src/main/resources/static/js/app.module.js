(function() {
    'use strict';

    angular
        .module('interview', [
            'ngResource',
            'ngTable'
        ]).config(function($locationProvider) { 
                    $locationProvider.html5Mode({ enabled: true, requireBase: false }); 
                  });
})();
