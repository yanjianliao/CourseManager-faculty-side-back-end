(function () {


    var $phone , $email, $role, $dateOfBirth, $username;
    var $updateBtn, $logoutBtn;
    var userService = new UserServiceClient();

    $(main);

    function main() {

        $phone = $('#phone');
        $email = $('#email');
        $role = $('#role');
        $username = $('#usernameInvalid');
        $dateOfBirth = $('#dateOfBirth');

        $updateBtn = $('#updateBtn');
        $logoutBtn = $('#loginBtn');
        $updateBtn.click(showProfile);
        $logoutBtn.click(logout);
        document.cookie = '123';
        showProfile();

    }

    function updateProfile() {

    }

    function logout() {

    }

    function showProfile() {
        console.log(document.cookie);
        userService.profile().then(renderUser);
    }

    function renderUser(user) {
        console.log(user);
        if(user.phone !== '') {
            $phone.val(user.phone);
        }

        if(user.email !== '') {
            $email.val(user.email);
        }

        if(user.role !== '') {
            $role.val(user.role.toUpperCase());
        }

        if(user.username !== ''){
            $username.val(user.username);
        }

    }


})();