
angular.module('store-front').controller('orderController', function ($scope, $window, $rootScope, $http, $localStorage, $location) {

    const gatewayPath = 'http://localhost:5000';
    var isCompleted = false;

    $scope.checkOut = function () {
        var data = {
            "cartName":$localStorage.cartName,
            "address":$scope.orderDetails.address,
            "phone":$scope.orderDetails.phone,
        }
        $http.post(gatewayPath + '/cart/api/v1/carts/createOrder/', data)
            .then(function (response) {
                $scope.payForm();
                $scope.loadCart();
                isCompleted = true;
            });
    }

    $scope.payForm = function () {
        $http.post(gatewayPath + '/order/api/v1/qiwi/payform', $localStorage.cartName)
            .then(function (response) {
                $window.open('//mail.ru');
                    });
    }

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.loadCart = function () {
        $http.post(gatewayPath + '/cart/api/v1/carts', $localStorage.cartName)
            .then(function (response) {
                $scope.Cart = response.data;
            });
    }

    $scope.loadCart();

});