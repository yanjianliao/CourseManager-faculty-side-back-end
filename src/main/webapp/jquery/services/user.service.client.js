function UserServiceClient() {

    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    // this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    // this.updateUser = updateUser;


    var self = this;
    this.url = 'http://localhost:8080/api/user';


    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            method: 'delete'
        });
    }


    function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type' : 'application/json'
            }
        }).then(function(response) {
            return response.json();
        });
    }



    function findAllUsers() {
        return fetch(self.url)
            .then(function(response) {
                return response.json();
        });
    }


}