function UserServiceClient() {

    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.register = register;
    this.login = login;
    this.profile = profile;
    this.updateProfile = updateProfile;
    this.logout = logout;

    var self = this;
    this.url = 'http://localhost:8080/api/user';
    this.loginUrl = 'http://localhost:8080/api/login';
    this.registerUrl = 'http://localhost:8080/api/register';
    this.profileUrl = 'http://localhost:8080/api/profile';
    this.logoutUrl = 'http://localhost:8080/api/logout';

    function logout() {
        return fetch(self.logoutUrl, {
            method: 'post',
            credentials: 'same-origin'
        });
    }


    function updateProfile(user) {
        return fetch(self.profileUrl, {
            method: 'put',
            body: JSON.stringify(user),
            credentials: 'same-origin',
            headers: {
                'content-type' : 'application/json'
            }
        }).then(function (response) {
            return response.json();
        });
    }


    function profile() {
        return fetch(self.profileUrl, {
            credentials: 'same-origin'
        })
            .then(function (response) {
                return response.json();
            });
    }

    function login(user) {
        return fetch(self.loginUrl, {
            method: 'post',
            body: JSON.stringify(user),
            credentials: 'same-origin',
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
            credentials: 'same-origin',
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