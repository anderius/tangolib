var services = angular.module('tangolib.services', ['ngResource']);

services.factory('SoundFilesFactory', function ($resource) {
    return $resource('/files', {}, {
        query: {method: 'GET', isArray: true },
        create: { method: 'POST' }
    })
});

services.factory('SoundFileFactory', function ($resource) {
    return $resource('/files/:id', {}, {
        show: { method: 'GET' },
        update: { method: 'PUT', params: {id: '@id'} },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

services.factory('ArtistsFactory', function ($resource) {
    return $resource('/artists', {}, {
        query: {method: 'GET', isArray: true },
        create: { method: 'POST' }
    })
});

services.factory('ArtistFactory', function ($resource) {
    return $resource('/artists/:id', {}, {
        show: { method: 'GET' },
        update: { method: 'PUT', params: {id: '@id'} },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});

services.factory('ArtistUpdateFactory', function ($resource) {
    return $resource('/artists/:id/fetch', {}, {
        show: { method: 'GET', isArray: true }
    })
});

services.factory('WorksFactory', function ($resource) {
    return $resource('/works', {}, {
        query: {method: 'GET', isArray: true },
        create: { method: 'POST' }
    })
});

services.factory('WorkFactory', function ($resource) {
    return $resource('/works/:id', {}, {
        show: { method: 'GET' },
        update: { method: 'PUT', params: {id: '@id'} },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});
