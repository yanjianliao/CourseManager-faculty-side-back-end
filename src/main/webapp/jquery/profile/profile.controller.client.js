(function () {

    var $phone , $email, $role, $dateOfBirth, $username;
    var $updateBtn, $logoutBtn, $notification;
    var userService = new UserServiceClient();
    var currentUser;

    $(main);

    function main() {

        $phone = $('#phone');
        $email = $('#email');
        $role = $('#role');
        $username = $('#usernameInvalid');
        $dateOfBirth = $('#dateOfBirth');

        $notification = $('#notification');
        $updateBtn = $('#updateBtn');
        $logoutBtn = $('#logoutBtn');
        $updateBtn.click(updateProfile);
        $logoutBtn.click(logout);
        showProfile();

    }

    function updateProfile() {
        //username, password, firstName, lastName,
        // email, phone, role, dateOfBirth
        if(!currentUser){
            alert('You can\'t update now, Please login first');
            return;
        }


        var user = currentUser;
        var newUser = new User(user.username, user.password,
            user.firstName, user.lastName, $email.val(),
            $phone.val(), $role.val(), $dateOfBirth.val().toString());

        userService.updateProfile(newUser).then(afterUpdate);

    }

    function afterUpdate(user) {
        if(user === null){
            alert('You can\'t update now, please login first!');
            return;
        }
        alert('Update success!!');
        console.log(user);
    }

    function logout() {
        currentUser = null;
        userService.logout().then(afterLogout);
    }




    function afterLogout() {
        $notification.css('display', 'block');
        // window.location.href = "../login/login.template.client.html";
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