(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $('#username');
        $passwordFld = $('#password');
        $loginBtn = $('#loginBtn');
        $loginBtn.click(login);
    }

    function login() {

        var username = $usernameFld.val();
        var password = $passwordFld.val();

        if(username === '') {
            alert('Empty username!');
            return;
        }

        if(password === ''){
            alert('Empty password!')
            return;
        }

        var newUser = new User(username, password,
            'firstName', 'lastName', 'default@default.com',
            '123456', 'role', '1990-01-01');

        userService.login(newUser).then(afterLogin);
    }

    function afterLogin(user) {
        if(user.role === 'role'){
            alert('Invalid username or password!');
        }

        console.log(user);
    }

})();