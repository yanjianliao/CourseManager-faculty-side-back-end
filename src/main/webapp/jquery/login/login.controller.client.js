(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    var get;
    $(main);

    function main() {
        $usernameFld = $('#username');
        $passwordFld = $('#password');
        $loginBtn = $('#loginBtn');
        $loginBtn.click(login);
        get = $('#get');
        get.click(test);
        document.cookie = '123';
    }

    function test() {
        userService.get().then(after);
    }

    function after(user) {
        console.log(user);
    }

    function login() {

        var username = $usernameFld.val();
        var password = $passwordFld.val();

        if(username === '') {
            alert('Empty username!');
            return;
        }


        if(password === ''){
            alert('Empty password!');
            return;
        }

        var newUser = new User(username, password,
            '', '', '',
            '', '', '');

        userService.login(newUser).then(afterLogin);
    }

    function afterLogin(user) {
        if(user.role === ''){
            alert('Invalid username or password!');
            return;
        }
        alert('logedIn');
        console.log(user);
        window.location.href = "../profile/profile.template.client.html";

    }

})();