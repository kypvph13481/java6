app.controller("authority-ctrl", function($scope, $http, $location){
    alert("author")

    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];

    $scope.initialize = function (){

        //load all roles
        $http.get("/rest/roles").then(resp =>{
            $scope.roles = resp.data;
        })

        //load staff and director(admin)
        $http.get("/rest/accounts?admin=true").then(resp =>{
            $scope.admins = resp.data;
        })

        //load authorities of staff and director
        $http.get("/rest/authorities?admin=true").then(resp =>{
            $scope.authorities = resp.data;
        }).catch(error =>{
            $location.path("/unauthorized");
        })
    }

    $scope.authority_of = function (acc, role){
        if ($scope.authorities){
            return $scope.authorities.find(ur => ur.account == acc.username && ur.role.id ==role.id);
        }
    }
    $scope.authority_changed = function (acc, role){
        var authority = $scope.authority_of(acc, role);
        if(authority){//đã cấp quyền, thu hồi quyền xoá
            $scope.revoke_authority(authority);
        } else {
            authority = {account: acc, role: role};
            $scope.grant_authority(authority);
        }
    }
    
    //Xoá authority

    $scope.revoke_authority = function (authority){
        $http.delete(`/rest/authorities/${authority.id}`).then(resp =>{
            var index = $scope.authorities.findIndex(a => a.id == authority.id);
            $scope.authorities.splice(index, 1);
            alert("Thu hồi quyền thành công");

        }).catch(error =>{
            alert("Thu hồi quyền thất bại");
            console.log("Error", error);
        })
    }
    $scope.grant_authority = function (authority){
        $http.post(`/rest/authorities`, authority).then(resp =>{
            $scope.authorities.push(resp.data)
            alert("Cấp quyền sử dụng thành công");
        }).catch(error =>{
            alert("Cấp quyền thất bại")
            console.log("Error", error);
        })
    }

})