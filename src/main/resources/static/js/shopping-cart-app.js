const app= angular.module("shopping-cart-app",[]);
app.controller("shopping-cart-ctrl", function($scope, $http){
    // Quản lý giỏ hàng
    $scope.cart={
        items: [],
        //Thêm sản phẩm vào giỏ hàng
        add(id){
			var item = this.items.find(item => item.id == id);
			if(item){
				item.quantity++;
				this.saveToLocalStorage();
				toastr.success("Đã thêm sản phẩm " + item.name + " vào giỏ hàng");

			}
			else{
				$http.get(`/rest/products/${id}`)
				.then(resp =>{
					resp.data.quantity=1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
					toastr.success("Đã thêm sản phẩm " + item.name + " vào giỏ hàng");
				})
			}
		},
        // Xoá  sản phẩm khỏi giỏ hàng
        remove(id){
			var index = this.items.findIndex(item => item.id == id);
			var item = this.items.find(item => item.id == id);
			var ok = confirm("Xoá sản phẩm: "+item.name + " khỏi giỏ hàng");
			if(ok){
				this.items.splice(index,1);
				this.saveToLocalStorage();
				toastr.success("Đã xoá sản phẩm " + item.name + " khỏi giỏ hàng");
			}
			
		},
        // Xoá tất cả sản phẩm khỏi giỏ hàng
        clear(){
			var ok = confirm("Thưa ngài, ngài muốn xoá hết sản phẩm sao?")
			if(ok){
				this.items=[];
				this.saveToLocalStorage();
				toastr.success("Đã xoá tất cả sản phẩm");
			}
		},
        //Tính thành tiền của 1 sản phẩm
        amt_of(item){},
        //Tính tổng số lượng các mặt hàng trong giỏ
        get count(){
        	return this.items
			.map(item => item.quantity)
			.reduce((total,quantity) => total+= quantity,0);
        },
        //Tổng thành tiền các mặt hàng trong giỏ
        get amount(){
			return this.items
				.map(item => item.quantity * item.price)
				.reduce((total,quantity) => total+= quantity,0);
		},
        
        //Lưu giỏ hàng vào local storage
        saveToLocalStorage(){
        	var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart",json);
        },
        //Đọc giỏ hàng từ local storage
        loadFromLocalStorage(){
        	var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json):[];
        }
        
    }

	/*
   ORDER
*/
    $scope.cart.loadFromLocalStorage();
    

	$scope.order = {
		create_date : new Date(),
		address: "",

	account:{username:$("#username").text()},
	get orderDetails(){
		return $scope.cart.items.map(item => {
			return {
				product:{id:item.id},
				price : item.price,
				quantity: item.quantity
			}
		});
	},
	
	purchase(){
		var valid = document.getElementById("myTextarea").value;
		if(valid.length == 0){
			toastr.danger("Mời nhập địa chỉ");
			document.getElementById("myTextarea").focus();
		}else{
			var order = angular.copy(this);
			//do order
			$http.post("/rest/orders",order).then(resp => {
				toastr.success("Order thành công");
				$scope.cart.clear();
				location.href="/order/detail/"+resp.data.id;
			}).catch(error => {
				alert("fail");
				console.log(error);
			})
		}
	}
}

});
