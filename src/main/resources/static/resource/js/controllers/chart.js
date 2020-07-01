'use strict';
app.controller('DataBaseInstanceList', ['$scope', '$http', '$state', function($scope, $http, $state) {
    $http.post('/api/user', {name: "name", password: "password"})
        .then(function(response) {
            if (response.data.code === 200) {
                console.log(response.data);
            }else{
                $scope.authError = response.data.message;
            }
        }, function(x) {
            $scope.authError = 'Server Error';
        });
}]);