(function(){
    var App = angular.module('movieCollection', ['ui.router', 'js-data']);

    App.factory('Movie', ['DS',function(DS){
        return DS.defineResource('movie');
    }])

    App.config(['$stateProvider', 'DSProvider',function($stateProvider, DSProvider){

        DSProvider.defaults.basePath = '/api';

        var add_new_config = {
            url: '/add_movie',
            templateUrl: 'assets/templates/add_new.html',
            controller: ['$scope', 'Movie', function($scope, Movie){
                $scope.movie = Movie.createInstance();
                $scope.success = false;

                $scope.save = function() {
                    $scope.movie.DSCreate().then(function(){
                        $scope.movie = Movie.createInstance();
                        $scope.success = true;
                    })
                }
            }]
        };

        var show_all_config = {
            url: '/show_all',
            templateUrl: 'assets/templates/all_movies.html',
            resolve: {
                movies: ['Movie', function(Movie){ return Movie.findAll();}]
            },
            controller: ['$scope', 'movies', '$state', function($scope, movies,$state){
                $scope.movies = movies;

                $scope.showDetails = function(movie) {
                    $state.go('show_all.details', {movieId : movie.id})
                }
            }]
        };

        var movie_detail_config = {
            url: '/details/{movieId}',
            templateUrl: 'assets/templates/movie_details.html',
            resolve: {
                movie: ['Movie', '$stateParams', function(Movie, $stateParams){

                    return Movie.find($stateParams.movieId);
                }]
            },
            controller: ['$scope', 'movie', '$state', function($scope, movie, $state){
                $scope.movie = movie;

                $scope.update = function() {
                    movie.DSSave();
                }
                $scope.delete = function() {
                    movie.DSDestroy().then(function(){
                        $state.go('show_all');
                    });
                }
            }]

        }
        $stateProvider.state('add_movie', add_new_config);
        $stateProvider.state('show_all', show_all_config);
        $stateProvider.state('show_all.details', movie_detail_config);
    }]);

    App.filter('boolHuman', function(){
        return function(input) {
            return input ? 'Yes':'No';
        }
    });

    App.directive('editText', function(){

        return {
            restrict: 'E',
            scope: {
                value : '='
            },
            require: 'value',
            controller: ['$scope', function($scope){
                $scope.editing = false;
                $scope.toggleEdit = function(save) {
                    if (!$scope.editing) {
                        $scope.editing = true;
                    } else {
                        if (save) {
                            $scope.$parent.update();
                        }
                        $scope.editing = false;
                    }
                }
            }],
            templateUrl: 'assets/templates/edit_text.html'
        };
    })
})()