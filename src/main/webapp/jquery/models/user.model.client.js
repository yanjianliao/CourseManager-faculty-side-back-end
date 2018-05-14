//username, password, email, firstName, lastName, phone, role, and dateOfBirth
function User(username, password, firstName, lastName,
              email, phone, role, dateOfBirth) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.role = role;
    this.dateOfBirth = dateOfBirth;

    this.setUsername = setUsername;
    this.getUsername = getUsername;
    this.setPassword = setPassword;
    this.getPassword = getPassword;
    this.setFirstName = setFirstName;
    this.getFirstName = getFirstName;
    this.getLastName = getLastName;
    this.setLastName = setLastName;
    this.getEmail = getEmail;
    this.setEmail = setEmail;
    this.getPhone = getPhone;
    this.setPhone = setPhone;
    this.getRole = getRole;
    this.setRole = setRole;
    this.setDataOfBirth = setDataOfBirth;
    this.getDataOfBirth = getDataOfBirth;

    var self = this;


    function getUsername() {
        return self.username;
    }
    function setUsername(username) {
        self.username = username;
    }
    function getPassword() {
        return self.password;
    }
    function setPassword(password) {
        self.password = password;
    }
    function getFirstName() {
        return self.firstName;
    }
    function setFirstName(firstName) {
        self.firstName = firstName;
    }
    function getLastName() {
        return self.lastName;
    }
    function setLastName(lastName) {
        self.lastName = lastName;
    }
    function getDataOfBirth() {
        return self.dateOfBirth;
    }
    function setDataOfBirth(dataOfBirth) {
        self.dateOfBirth = dataOfBirth;
    }
    function getRole() {
        return self.role;
    }
    function setRole(role) {
        self.role = role;
    }
    function getEmail() {
        return self.email;
    }
    function setEmail(email) {
        self.email = email;
    }
    function getPhone() {
        return self.phone;
    }
    function setPhone(phone) {
        self.phone = phone;
    }

}