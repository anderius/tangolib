var app = angular.module('tangolib.controllers', []);

app.controller('SoundFileListCtrl', ['$scope', 'SoundFilesFactory', 'SoundFileFactory', '$location',
    function ($scope, SoundFilesFactory, SoundFileFactory, $location) {

        $scope.editSoundFile = function (id) {
            $location.path('files/' + id);
        };

        $scope.deleteSoundFile = function (soundFileId) {
            SoundFileFactory.delete({ id: soundFileId });
            $scope.files = SoundFilesFactory.query();
        };

        $scope.editArtist = function (id) {
            $location.path('artists/' + id);
        };

        $scope.files = SoundFilesFactory.query();
    }
]);

app.controller('SoundFileDetailCtrl', ['$scope', '$routeParams', 'SoundFileFactory', '$location',
    function ($scope, $routeParams, SoundFileFactory, $location) {

        $scope.cancel = function () {
            $location.path('files');
        };

        $scope.updateSoundFile = function () {
            SoundFileFactory.update($scope.file);
            $location.path('files');
        };

        $scope.file = SoundFileFactory.show({id: $routeParams.id});
    }
]);

app.controller('ArtistListCtrl', ['$scope', 'ArtistsFactory', 'ArtistFactory', '$location',
    function ($scope, ArtistsFactory, ArtistFactory, $location) {

        $scope.editArtist = function (id) {
            $location.path('artists/' + id);
        };

        $scope.deleteArtist = function (id) {
            ArtistFactory.delete({ id: id });
            $location.path('artists');
        };

        $scope.artists = ArtistsFactory.query();
    }
]);

app.controller('ArtistDetailCtrl', ['$scope', '$routeParams', 'ArtistFactory', '$location',
    function ($scope, $routeParams, ArtistFactory, $location) {

        $scope.cancel = function () {
            $location.path('artists');
        };

        $scope.save = function () {
            ArtistFactory.update($scope.artist);
            $location.path('artists');
        };

        $scope.update = function () {
            $location.path('artists/' + $routeParams.id + '/update');
        };

        $scope.artist = ArtistFactory.show({id: $routeParams.id});
    }
]);
app.controller('ArtistUpdateCtrl', ['$scope', '$routeParams', 'ArtistFactory', 'ArtistUpdateFactory', '$location',
    function ($scope, $routeParams, ArtistFactory, ArtistUpdateFactory, $location) {

        $scope.cancel = function () {
            $location.path('artists/' + $routeParams.id);
        };

        $scope.save = function () {
            ArtistFactory.update($scope.artist);
            $location.path('artists');
        };

        $scope.updateField = function(value, targetField) {
            $scope.artist[targetField] = value;
        };

        $scope.dateFormat = 'dd-MM-yyyy';
        $scope.artist = ArtistFactory.show({id: $routeParams.id});
        $scope.updates = ArtistUpdateFactory.show({id: $routeParams.id});
    }
]);
app.controller('WorkListCtrl', ['$scope', 'WorksFactory', 'WorkFactory', '$location',
    function ($scope, WorksFactory, WorkFactory, $location) {

        $scope.editWork = function (id) {
            $location.path('works/' + id);
        };

        $scope.deleteWork = function (id) {
            WorkFactory.delete({ id: id });
            $scope.works = WorksFactory.query();
        };

        $scope.works = WorksFactory.query();
    }
]);

app.controller('WorkDetailCtrl', ['$scope', '$routeParams', 'WorkFactory', '$location',
    function ($scope, $routeParams, WorkFactory, $location) {

        $scope.cancel = function () {
            $location.path('works');
        };

        $scope.updateWork = function () {
            WorkFactory.update($scope.work);
            $location.path('works');
        };

        $scope.deleteWork = function () {
            WorkFactory.delete({ id: $scope.work.id });
            $location.path('works');
        };

        $scope.work = WorkFactory.show({id: $routeParams.id});
    }
]);

app.controller('navCtrl', ['$scope', '$location', function ($scope, $location) {
    $scope.navClass = function (page) {
        var currentRoute = $location.path().substr(0, page.length) || 'artists';
        return page === currentRoute ? 'active' : '';
    };
}]);