(function() {

    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld, $roleFld;
    var $userRowTemplate, $tbody;
    var userService = new UserServiceClient();
    $(main);


    function main() {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $removeBtn = $('#wbdv-remove');
        $editBtn = $('#wbdv-edit');
        $createBtn = $('.wbdv-create');
        $firstNameFld = $('#firstNameFld');
        $lastNameFld = $('#lastNameFld');
        $userRowTemplate = $('.wbdv-template');
        $roleFld = $('#roleFld');
        $tbody = $('.wbdv-tbody');

        findAllUser();

        $createBtn.click(createUser);
    }

    function createUser() {
        //username, password, firstName, lastName,
        //               email, phone, role, dateOfBirth
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var firstName = $firstNameFld.val();
        var lastName = $lastNameFld.val();
        var role = $roleFld.val();
        var newUser = new User(username, password,
            firstName, lastName, 'default@default.com',
            '123456', role, '1990-01-01');

        userService.createUser(newUser);
    }

    function findAllUser() {
        return userService
            .findAllUsers()
            .then(renderUsers);
    }

    function findUserById() {

    }

    function deleteUser() {

    }

    function selectUser() {

    }

    function updateUser() {

    }

    function renderUser(user) {

    }

    function renderUsers(users) {
        $tbody.empty();
        console.log(users);
        for(var i = 0; i < users.length; i++) {
            var user = users[i];
            var clone = $userRowTemplate.clone();
            clone.find('.wbdv-username')
                .html(user.username);
            clone.find('.wbdv-first-name')
                .html(user.firstName);
            clone.find('.wbdv-last-name')
                .html(user.lastName);
            clone.find('.wbdv-role')
                .html(user.role);
            $tbody.append(clone);
        }
    }


})();