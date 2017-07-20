angular.module('de.fraunhofer.abm').controller("cartController", 
['$rootScope', '$scope', '$http', '$location', 'ngCart', 'searchResultService',
function cartController($rootScope, $scope, $http, $location, ngCart, searchResultService) {
	var self = this;
	self.cart = ngCart;
	
	self.emptyCollection = function() {
		ngCart.empty();
	};
	
	self.selectAll = function() {
		for (var i = 0; i < searchResultService.results.length; i++) {
			var repo = searchResultService.results[i];
			ngCart.addItem(repo.id.toString(), repo.name, 1, 1, repo);
		}
	};
	
	self.createJSON = function(){
		repoList = [];
		items = self.cart.getCart().items;
		for (i=0; i<self.cart.getTotalItems(); i++) {
			repoList.push({"id": items[i].getData().name, "cp": '?'});
		}
	}
}]);