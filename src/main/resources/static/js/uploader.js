/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function($) {
	
	$.fn.uploader = function(baseurl) {
		var uploader = this;
		uploader.empty();
		baseurl = window.location.origin;
		 
/*	 alert(baseurl);
	 uploader.append("BASE URL : " + baseurl);*/
	 
		$.ajax({
			url : baseurl + "api/clean",
			method : 'delete',
			async : false,
			success : function() {
				uploader.append("<li>Cleaned up Database</li>");
			}
		});

		$.ajax({
			url : baseurl + "/api/serviceclean",
			method : 'delete',
			async : false,
			success : function() {
				uploader.append("<li>Cleaned up BankingDatabase</li>");
			}
		});

		$.ajax({
			url : "data/categories.json",
			contentType : 'json',
			dataType : 'json',
			success : function(categories) {
				categories.forEach(function(category) {
					uploader.uploadCategory(category);
				});
			}
		});

		$.ajax({
			url : "data/products.json",
			contentType : 'json',
			dataType : 'json',
			success : function(products) {
				products.forEach(function(product) {
					uploader.uploadProduct(product);
				});
			}
		});
		
		
		
		$.ajax({
			url : "data/retailstores.json",
			contentType : 'json',
			dataType : 'json',
			success : function(retailstores) {
				retailstores.forEach(function(retailstores) {
					uploader.uploadRetailStores(retailstores);
				});
			}
		});

		$.ajax({
			url : "data/coupons.json",
			contentType : 'json',
			dataType : 'json',
			success : function(coupons) {
				coupons.forEach(function(coupon) {
					uploader.uploadCoupon(coupon);
				});
			}
		});

		$.ajax({
			url : "data/weeklyAds.json",
			contentType : 'json',
			dataType : 'json',
			success : function(weeklyAds) {
				weeklyAds.forEach(function(weeklyAd) {
					uploader.uploadWeeklyAd(weeklyAd);
				});
			}
		});

		$.ajax({
			url : "data/settings.json",
			contentType : 'json',
			dataType : 'json',
			success : function(settings) {
				settings.forEach(function(setting) {
					uploader.uploadSetting(setting);
				});
			}
		});

	

		$.ajax({
			url : "data/bankstores.json",
			contentType : 'json',
			dataType : 'json',
			success : function(bankstores) {
				bankstores.forEach(function(bankstores) {
					uploader.uploadBankStores(bankstores);
				});
			}
		});

		$.ajax({
			url : "data/homepagedetails.json",
			contentType : 'json',
			dataType : 'json',
			success : function(homepagedetails) {
				homepagedetails.forEach(function(homepagedetails) {
					uploader.uploadHomePagedetails(homepagedetails);
				});
			}
		});

		$.ajax({
			url : "data/accountdetails.json",
			contentType : 'json',
			dataType : 'json',
			success : function(accountdetails) {
				accountdetails.forEach(function(accountdetails) {
					uploader.uploadAccountdetails(accountdetails);
				});
			}
		});

		$.ajax({
			url : "data/accountssummary.json",
			contentType : 'json',
			dataType : 'json',
			success : function(accountssummary) {
				accountssummary.forEach(function(accountssummary) {
					uploader.uploadAccountsSummary(accountssummary);
				});
			}
		});

		$.ajax({
			url : "data/brokerage.json",
			contentType : 'json',
			dataType : 'json',
			success : function(brokerage) {
				brokerage.forEach(function(brokerage) {
					uploader.uploadBrokeragedetails(brokerage);
				});
			}
		});

		$.ajax({
			url : "data/budgetSpending.json",
			contentType : 'json',
			dataType : 'json',
			success : function(budgetSpending) {
				budgetSpending.forEach(function(budgetSpending) {
					uploader.uploadBudgetSpendingdetails(budgetSpending);
				});
			}
		});

		$.ajax({
			url : "data/testResponse.json",
			contentType : 'json',
			dataType : 'json',
			success : function(information) {
				information.forEach(function(information) {
					uploader.uploadInformations(information);
				});
			}
		});

		$.ajax({
			url : "data/videoshare.json",
			contentType : 'json',
			dataType : 'json',
			success : function(videoshare) {
				videoshare.forEach(function(videoshare) {
					uploader.uploadVideoshare(videoshare);
				});
			}
		});

		$.ajax({
			url : "data/brokerage.json",
			contentType : 'json',
			dataType : 'json',
			success : function(brokerage) {
				brokerage.forEach(function(brokerage) {
					uploader.uploadbrokerage(brokerage);
				});
			}
		});

		$.ajax({
			url : "data/branchLocator.json",
			contentType : 'json',
			dataType : 'json',
			success : function(branchLocator) {
				branchLocator.forEach(function(branchLocator) {
					uploader.uploadbranchlocator(branchLocator);
				});
			}
		});

		$.ajax({
			url : "data/atmLocator.json",
			contentType : 'json',
			dataType : 'json',
			success : function(atmLocator) {
				atmLocator.forEach(function(atmLocator) {
					uploader.uploadatmLocator(atmLocator);
				});
			}
		});
		
		

		uploader.uploadCategory = function(category) {
			$.ajax({
				url : baseurl + "api/categories",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(category),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>Category " + category.name
							+ " created !!</li>");
				}
			});

		};

		uploader.uploadProduct = function(product) {
			$.ajax({
				url : baseurl + "api/products",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(product),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>Product " + product.sku
							+ " created !!</li>");
				}
			});

		};

		uploader.uploadCoupon = function(coupon) {
			$.ajax({
				url : baseurl + "api/coupons",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(coupon),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>Coupon " + coupon.name
							+ " created !!</li>");
				}
			});

		};

		uploader.uploadWeeklyAd = function(weeklyAd) {
			$.ajax({
				url : baseurl + "api/weeklyAds",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(weeklyAd),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>Weekly Ad " + weeklyAd.name
							+ " created !!</li>");
				}
			});

		};

		uploader.uploadSetting = function(setting) {
			$.ajax({
				url : baseurl + "api/settings",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(setting),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>Setting " + setting.name
							+ " created !!</li>");
				}
			});

		};

		uploader.uploadHomePagedetails = function(homepagedetails) {
			$.ajax({
				url : baseurl + "/api/homepagedetails",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(homepagedetails),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>HomePagedetails "
							+ homepagedetails.name + " created !!</li>");
				}
			});

		};

		uploader.uploadAccountdetails = function(accountdetails) {
			$.ajax({
				url : baseurl + "/api/accountdetails",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(accountdetails),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>AccountDetails " + accountdetails.name
							+ " created !!</li>");
				}
			});

		};

		uploader.uploadAccountsSummary = function(accountssummary) {
			$.ajax({
				url : baseurl + "/api/accountssummary",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(accountssummary),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>AccountsSummary "
							+ accountssummary.name + " created !!</li>");
				}
			});

		};

		uploader.uploadBrokeragedetails = function(brokerage) {
			$.ajax({
				url : baseurl + "/api/brokeragedetails",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(brokerage),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>Brokeragedetails " + brokerage.name
							+ " created !!</li>");
				}
			});

		};

		uploader.uploadBudgetSpendingdetails = function(budgetSpending) {
			$.ajax({
				url : baseurl + "/api/budgetspending",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(budgetSpending),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>BudgetSpending " + budgetSpending.name
							+ " created !!</li>");
				}
			});

		};

		uploader.uploadInformations = function(information) {
			$.ajax({
				url : baseurl + "/api/informations",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(information),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>Information " + information.name
							+ " created !!</li>");
				}
			});

		};

		uploader.uploadVideoshare = function(videoshare) {
			$.ajax({
				url : baseurl + "/api/videoshare",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(videoshare),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>Videoshare " + videoshare.name
							+ " created !!</li>");
				}
			});

		};

		uploader.uploadbranchlocator = function(branchlocator) {
			$.ajax({
				url : baseurl + "/api/branchlocator",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(branchlocator),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>Branchlocator " + branchlocator.name
							+ " created !!</li>");
				}
			});

		};

		uploader.uploadatmLocator = function(atmLocator) {
			$.ajax({
				url : baseurl + "/api/atmlocator",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(atmLocator),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>AtmLocator " + atmLocator.name
							+ " created !!</li>");
				}
			});

		};

		uploader.uploadBankStores = function(bankstores) {
			$.ajax({
				url : baseurl + "/api/bankstores",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(bankstores),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>BankStores " + bankstores.name
							+ " created !!</li>");
				}
			});

		};
		
		
		uploader.uploadRetailStores = function(retailstores) {
			$.ajax({
				url : baseurl + "/api/stores",
				method : 'post',
				dataType : 'json',
				data : JSON.stringify(retailstores),
				contentType : 'application/json',
				success : function() {
					uploader.append("<li>RetailStores " + retailstores.name
							+ " created !!</li>");
				}
			});

		};

		return this;
	};

}(jQuery));
