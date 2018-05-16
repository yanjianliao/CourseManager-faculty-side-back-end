(function () {

    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var registerBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $verifyPasswordFld = $('#verifyPasswordFld');
        registerBtn = $('#registerBtn');

        registerBtn.click(register);
    }

    function register() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();

        var newUser = new User(username, password,
            '', '', '',
            '', '', '1990-01-01');

        userService.register(newUser).then(printOut);
    }

    function printOut(users){
        console.log(users.length);
        console.log(users);
        console.log(users[0]);
    }

})();