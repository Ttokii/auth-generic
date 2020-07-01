'use strict';

app.controller('SigninFormController', ['$scope', '$http', '$state', function($scope, $http, $state) {
    $scope.user = {};
    $scope.authError = null;
    $scope.login = function() {
      $scope.authError = null;
      $http.post('/api/login', {username: $scope.user.name, password: $scope.user.password})
      .then(function(response) {
        if (response.data.code === 200) {
            $state.go('app.dashboard-v1');
        }else{
            $scope.authError = response.data.message;
        }
      }, function(x) {
        $scope.authError = 'Server Error';
      });
    };
  }])
;