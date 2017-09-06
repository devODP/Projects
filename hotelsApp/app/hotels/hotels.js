'use strict';

var app=angular.module('hotels', ['ngRoute', 'ngMessages']);

app.controller('HotelsCtrl', ['$scope', '$timeout', "$location", "searchFactory", function($scope, $timeout, $location, searchFactory){
	//DEFINE A SPINNER HERE
	$scope.loaded = false;

	angular.element('.overlay').toggle();

	$timeout(function(){
		$scope.loaded = true;
		angular.element('.overlay').toggle();
	}, 1000);

	//DEFINE OUR MODEL HERE
	this.search={};

	//GET ONLY VALID KEYS
	this.orderBySelection = function(){
		//debugger;
		if(typeof this.rooms === "object" && this.rooms.length > 0){
			var nestedObject = this.rooms[0];
			var keys = Object.keys(nestedObject);

			//Get Rid of Data That Doesn't Belong In UI
			var filteredKeySet = angular.element.map(keys, function(key, val){
				if(key !== "isBooked"){
					return key;
				}
			});

			this.orderByOptions = filteredKeySet;
		}
	};

	this.orderBySelection();

	this.isPromoted = function(room){
		if(room.promotion !== null && room.promotion !== undefined){
			return "promote";
		}else{
			return "regular";
		}
	}

	//DEFINE BOOKING
	this.booking = function(room){
		alert(room.size + " is booked!!");
	};

	this.searchAvailability = function(){
		this.searcharrival = $scope.arrival;
		this.search.checkout = $scope.checkout;

		this.displayAnimation = true;
		this.rooms = searchFactory.search();
		//angular.element('#transition')[0].click();
		var anchorTag = angular.element('#searchFormContainer');
		angular.element('html body').animate({
				scrollTop: anchorTag.offset().top
		}, 1000);

	};

	//THEME
	$scope.theme = "flatly";

	this.changeTheme = function(element){
		switch(element){
			case 1:
				$scope.theme = "flatly";
				break;
			case 2:
				$scope.theme = "united";
				break;
			case 3:
				$scope.theme = "cerulean";
				break;
		}
	}

	//hotels.js: FILTER STRING
	this.filterString = "";
}]);


//Use camelCase when defining a directives!!
app.directive('hotelsResult', function(){
	return {
		restrict: 'E',
		templateUrl: 'app/common/hotels-result.html'
	};
});

app.directive('hotelsGoogleMaps', function(){
	return {
		restrict : 'E',
		templateUrl : 'app/common/google-maps.html'
	};
});

app.directive('adsContainer', function(){
	return {
		restrict : 'E',
		templateUrl : 'app/common/ads-container.html'
	};
});

app.directive("bootstrapCalendar", function(){
	return {
		"restrict": "E",
		"templateUrl": "app/common/bootstrap-calendar.html",
		"transclude": true,
		"controller": "HotelsCtrl",
		"controllerAs": "hotels",
		"link": function(scope, element, attrs, hotels){

			var today = new Date();
			var tomorrow = new Date(today.getTime() + 24 * 60 * 60 * 1000);

			var startDt = (element.attr('id') === 'arrival') ? today : tomorrow;

			element.find("label").text(attrs.label);
			var selector = element.find('.date');
			selector.datetimepicker({
				language: 'en',
				startDate: startDt
			});

			selector.on('changeDate', function(e){
				console.log(e.date.toString());
				if(element.attr('id') === 'arrival'){
					scope.arrival= e.date.toString();
				}else{
					scope.checkout=e.date.toString();
				}

			});
		}
	};
});

app.factory('searchFactory', function($http, $log, $q){
	var service = {};

	service.search = function(){
		return [{"size" 			: "studio",
			  "beds" 			: 1,
			  "kitchen" 		: true,
			  "price" 			: "99.99",
			  "promotion" 		: {
			  	"discount"  	: "30%",
			  	"message"		: "Reserve it today!",
			  	"promoPrice"	: "79.99",
			  	"onSale"		: "promote"
			  },
			  "roomNumber"		: 201,
			  "isBooked" 		: false},
			  {"size" 			: "studio",
			  "beds" 			: 1,
			  "kitchen" 		: true,
			  "price" 			: "99.99",
			  "roomNumer"		: 203,
			  "isBooked" 		: false},
			  {"size" 			: "queen",
			  "beds" 			: 2,
			  "kitchen" 		: true,
			  "price" 			: "127.99",
			  "roomNumber"		: 201,
			  "isBooked" 		: false}
			  ];
	};
	return service;
});

//var mockRooms