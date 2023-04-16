app.controller("product-ctrl", function ($scope, $http) {
	$scope.items = [];
	$scope.form = {};

	$scope.category = [];
	$scope.nameitem = '';

	$scope.initial = function () {
		//load products
		$http.get("/rest/products").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate)
			})
		});

		//load category
		$http.get("/rest/category").then(resp => {
			$scope.category = resp.data;
		});
	}
	$scope.initial();

	//reset form
	$scope.reset = function () {
		$scope.form = {
			createDate: new Date(),
			image: 'cloud-upload.jpg',
			available: true
		};
	}

	//edit product
	$scope.edit = function (item) {
		$scope.form = angular.copy(item);
		$(".productTab li a:eq(1)").tab('show');
	}

	//creat
	$scope.create = function () {
		var item = angular.copy(this.form);
		// item.quantity = 10;
		$http.post("/rest/products", item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate);
			$scope.items.push(resp.data);
			$scope.reset();
			toastr.success("Thêm sản phẩm thành công!");
		}).catch(error => {
			toastr.success("Lỗi!");
			console.log("Error", error);
		});
	}

	//update
	$scope.update = function () {
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			toastr.success("Update sản phẩm thành công!");
		}).catch(error => {
			toastr.success("Lỗi!");
			console.log("Error", error);
		});
	}

	//delete


	$scope.delete = function (item) {
		var x = confirm("Are you sure you want to delete?");
		if(x){
			$http.delete(`/rest/products/${item.id}`).then(resp => {
				var index = $scope.items.findIndex(p => p.id == item.id);
				$scope.items.splice(index, 1);
				alert("success");
				$scope.reset();

			}).catch(error => {
				alert("fail");
				console.log("Error", error);
			});
			return true;

		}else{
			return false;
		}



	}

	//upload img
	$scope.imageChange = function (files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name;
			alert("upload successfully");
		}).catch(error => {
			alert("fail to load img");
			console.log("Error", error);
		});
	}


	//findby name
	$scope.findByName = function () {
		var name = document.getElementById("myText").value;
		if (name == '')
			name = 'all';
		$http.get(`/rest/products/name/{name}`).then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createdate = new Date(item.createdate)
			})
		}).catch(error => {
			alert('fail');
			console.log('Error', error);
		});

	}


	//pager
	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},

		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},

		first() {
			this.page = 0;
		},

		prev() {
			if (this.page > 0)
				this.page--;
			else
				this.page = 0;
		},

		next() {
			if (this.page < this.count - 1)
				this.page++;
			else
				this.page = this.count - 1;
		},

		last() {
			this.page = this.count - 1;
		}
	}


});