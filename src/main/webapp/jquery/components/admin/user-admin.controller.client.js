(function() {

    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn, $updateBtn;
    var $firstNameFld, $lastNameFld, $roleFld;
    var $userRowTemplate, $tbody;
    var currentUser;
    var currentId;
    var userService = new UserServiceClient();
    $(main);


    function main() {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $removeBtn = $('.wbdv-remove');
        $editBtn = $('.wbdv-edit');
        $createBtn = $('.wbdv-create');
        $firstNameFld = $('#firstNameFld');
        $lastNameFld = $('#lastNameFld');
        $userRowTemplate = $('.wbdv-template');
        $roleFld = $('#roleFld');
        $tbody = $('.wbdv-tbody');
        $updateBtn = $('.wbdv-update');

        findAllUser();
        $createBtn.click(createUser);
        $updateBtn.click(updateUser);

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

        userService.createUser(newUser).then(findAllUser);
    }

    function findAllUser() {
        return userService
            .findAllUsers()
            .then(renderUsers);
    }

    function findUserById(userId) {
        userService.findUserById(userId).then(renderUser);
    }

    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var id = deleteBtn.parent()
            .parent()
            .parent()
            .attr('id');
        userService.deleteUser(id).then(findAllUser);
    }

    function selectUser(event) {
        var editBtn = $(event.currentTarget);
        var id = editBtn.parent()
            .parent()
            .parent()
            .attr('id');
        currentId = id;
        findUserById(id);
    }

    function updateUser() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var firstName = $firstNameFld.val();
        var lastName = $lastNameFld.val();
        var role = $roleFld.val();
        var newUser = new User(username, password,
            firstName, lastName, currentUser.email,
            currentUser.phone, role,
            currentUser.dateOfBirth);

        userService.updateUser(currentId, newUser).then(findAllUser);

    }

    function renderUser(user) {
        currentUser = user;
        $usernameFld.val(user.username);
        $passwordFld.val(user.password);
        $firstNameFld.val(user.firstName);
        $lastNameFld.val(user.lastName);
        $roleFld.val(user.role);
    }

    function renderUsers(users) {
        $tbody.empty();
        for(var i = 0; i < users.length; i++) {
            var user = users[i];
            var clone = $userRowTemplate.clone();
            clone.attr('id', user.id);
            clone.find('.wbdv-remove').click(deleteUser);
            clone.find('.wbdv-edit').click(selectUser);
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