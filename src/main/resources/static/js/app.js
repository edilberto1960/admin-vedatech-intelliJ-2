/**
 * Created by z00382545 on 10/01/18.
 */

var app = angular.module('validationApp', []).controller('mainController', function($scope) {
			console.log("hello world");
			$scope.bankName = '';
			$scope.account = '';
			console.log($scope.bankName);
			
			// function to submit the form after all validation has occurred			
			$scope.submitForm = function() {

				// check to make sure the form is completely valid
				if ($scope.userForm.$valid) {
					alert('our form is amazing');
				}

			};

		});
		