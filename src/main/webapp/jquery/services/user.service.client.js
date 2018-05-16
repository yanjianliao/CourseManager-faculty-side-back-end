function UserServiceClient() {

    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.register = register;
    this.login = login;

    var self = this;
    this.url = 'http://localhost:8080/api/user';
    this.loginUrl = 'http://localhost:8080/api/login';
    this.registerUrl = 'http://localhost:8080/api/register';



    function login(user) {
        return fetch(self.loginUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type' : 'application/json'
            }
        }).then(function (response) {
            return response.json();
        });
    }

    function register(user) {
        return fetch(self.registerUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type' : 'application/json'
            }
        }).then(function (response) {
            return response.json();
        });
    }


    function updateUser(userId, newUser) {
        return fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(newUser),
            headers: {
                'content-type' : 'application/json'
            }
        });
    }

    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(function(response) {
                return response.json();
            });
    }

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