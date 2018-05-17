(function () {


    var $phone , $email, $role, $dateOfBirth, $username;
    var $updateBtn, $logoutBtn;
    var userService = new UserServiceClient();
    var currentUser;

    $(main);

    function main() {

        $phone = $('#phone');
        $email = $('#email');
        $role = $('#role');
        $username = $('#usernameInvalid');
        $dateOfBirth = $('#dateOfBirth');

        $updateBtn = $('#updateBtn');
        $logoutBtn = $('#loginBtn');
        $updateBtn.click(updateProfile);
        $logoutBtn.click(logout);
        showProfile();

    }

    function updateProfile() {
        //username, password, firstName, lastName,
        // email, phone, role, dateOfBirth
        var user = currentUser;
        var newUser = new User(user.username, user.password,
            user.firstName, user.lastName, $email.val(),
            $phone.val(), $role.val(), $dateOfBirth.val().toString());

        userService.updateProfile(newUser).then(afterUpdate);

    }

    function afterUpdate(user) {
        console.log(user);
    }

    function logout() {

    }

    function showProfile() {
        userService.profile().then(renderUser);
    }

    function renderUser(user) {
        console.log(user);
        currentUser = user;
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

        if(user.dateOfBirth != null) {
            var date = user.dateOfBirth.split('T');
            $dateOfBirth.val(date[0]);
        }

    }


})();