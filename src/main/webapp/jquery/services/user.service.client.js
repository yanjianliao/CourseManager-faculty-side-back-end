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
    this.url = 'https://first-yanjianliao.herokuapp.com/api/user';
    this.loginUrl = 'https://first-yanjianliao.herokuapp.com/api/login';
    this.registerUrl = 'https://first-yanjianliao.herokuapp.com/api/register';
    this.profileUrl = 'https://first-yanjianliao.herokuapp.com/api/profile';


    function logout() {

    }


    function updateProfile() {
        return fetch(self.profileUrl)
    }


    function profile() {
        return fetch(self.profileUrl)
            .then(function (response) {
                console.log(response);
            return response.json();
        });
    }

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