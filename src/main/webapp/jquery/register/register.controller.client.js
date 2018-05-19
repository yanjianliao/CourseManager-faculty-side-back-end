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
        if(username === '') {
            alert('The username can\'t be empty');
            return ;
        }

        var password = $passwordFld.val();
        if(password === '') {
            alert('The password can\'t be empty');
            return ;
        }
        var verify = $verifyPasswordFld.val();

        if(verify === '') {
            alert('The verify password can\'t be empty');
            return ;
         }

         if(verify !== password) {
            alert('The two password must be same');
            return ;
         }

        var newUser = new User(username, password,
            '', '', '',
            '', '', '1990-01-01');

        userService.register(newUser).then(afterRegister);
    }

    function afterRegister(user) {
        if(user.role === 'invalid'){
            alert('Register failed. This username is already taken, please try another one!')
            return;
        }

        window.location.href = '../profile/profile.template.client.html';
    }


})();