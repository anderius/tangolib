var tangolib = angular.module('tangolib', [
    'ngRoute',
    'tangolib.services',
    'tangolib.controllers'
]);

tangolib.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/files', {
                templateUrl: 'partials/sound-file-list.html',
                controller: 'SoundFileListCtrl'
            }).
            when('/files/:id', {
                templateUrl: 'partials/sound-file-detail.html',
                controller: 'SoundFileDetailCtrl'
            }).
            when('/artists', {
                templateUrl: 'partials/artist-list.html',
                controller: 'ArtistListCtrl'
            }).
            when('/artists/:id', {
                templateUrl: 'partials/artist-detail.html',
                controller: 'ArtistDetailCtrl'
            }).
            when('/artists/:id/update', {
                templateUrl: 'partials/artist-update.html',
                controller: 'ArtistUpdateCtrl'
            }).
            when('/works', {
                templateUrl: 'partials/work-list.html',
                controller: 'WorkListCtrl'
            }).
            when('/works/:id', {
                templateUrl: 'partials/work-detail.html',
                controller: 'WorkDetailCtrl'
            }).
            otherwise({
                redirectTo: '/artists'
            });
    }]);