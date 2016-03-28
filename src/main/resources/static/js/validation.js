function getURLParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1));
    var sURLVariables = sPageURL.split("&");
    for (var i = 0; i < sURLVariables.length; i++) {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) {
            return sParameterName[1];
        }
    }
}

function validateFieldsSignin($email, $password, $password2) {
    validateEmail($email);
    validatePassword($password);
    validatePassword2($password2, $password);
    return validateEmail($email) && validatePassword($password) && validatePassword2($password2, $password);
}

function validateFieldsLogin($email, $password) {
    validateEmail($email);
    validatePassword($password);
    return validateEmail($email) && validatePassword($password);
}

function validateEmail($email) {
    var isValid = $email.val();
    if (!isValid) {
        $email.parent().removeClass("has-success").addClass("has-error");
    } else {
        $email.parent().removeClass("has-error").addClass("has-success");
    }
    return isValid;
}

function validatePassword($password) {
    var isValid = $password.val();
    if (!isValid) {
        $password.parent().removeClass("has-success").addClass("has-error");
    } else {
        $password.parent().removeClass("has-error").addClass("has-success");
    }
    return isValid;
}

function validatePassword2($password2, $password) {
    var isValid = $password2.val() && $password2.val() == $password.val();
    if (!isValid) {
        $password2.parent().removeClass("has-success").addClass("has-error");
    } else {
        $password2.parent().removeClass("has-error").addClass("has-success");
    }
    return isValid;
}

function getFormData($form) {
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};
    $.map(unindexed_array, function (n, i) {
        indexed_array[n['name']] = n['value'];
    });
    return indexed_array;
}