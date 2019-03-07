let password = document.getElementById('password');
let passwordConfirmed = document.getElementById('confirmedPassword');
let name = document.getElementById('name');
let surname = document.getElementById('surname');
let username = document.getElementById('username');
let flagPassword, flagName, flagSurname, flagUsername, flagConfirmedPassword;

let button = document.createElement("INPUT");
button.setAttribute("type", "submit");
button.setAttribute("value", "Register");
button.setAttribute("class","btn float-left login_btn");
button.setAttribute("id","confirm");
let link = /*[[@{/}]]*/'';
button.setAttribute("href",link);


password.onkeyup = function() {
    if(password.value.length >= 8 && password.value.length <= 15) {
        password.style.borderColor = "green";
        flagPassword = true;
    } else {
        password.style.borderColor = "red";
        flagPassword = false;
        if(document.getElementById('validationContainer').contains(button)!==null)
           document.getElementById('validationContainer').removeChild(button);
    }
    checkFlags();
};

passwordConfirmed.onkeyup = function() {
    if(passwordConfirmed.value.length >= 8 && passwordConfirmed.value.length <= 15 && passwordConfirmed.value===password.value) {
        passwordConfirmed.style.borderColor = "green";
        flagConfirmedPassword = true;
    } else {
        passwordConfirmed.style.borderColor = "red";
        flagConfirmedPassword = false;
        if(document.getElementById('validationContainer').contains(button)!==null)
            document.getElementById('validationContainer').removeChild(button);
    }
    checkFlags();
};

name.onkeyup = function () {
    //il nome può contenere sia maiuscole che minuscole
    let letters = /^[A-Za-z]+$/;
    if(name.value.match(letters)) {
        name.style.borderColor = "green";
        flagName = true;
    } else {
        name.style.borderColor = "red";
        flagName = false;
        if(document.getElementById('validationContainer').contains(button)!==null)
            document.getElementById('validationContainer').removeChild(button);
    }
    checkFlags();
};

surname.onkeyup = function () {
    //il cognnome può contenere sia maiuscole che minuscole
    let letters = /^[A-Za-z]+$/;
    if(surname.value.match(letters)) {
        surname.style.borderColor = "green";
        flagSurname = true;
    } else {
        surname.style.borderColor = "red";
        flagSurname = false;
        if(document.getElementById('validationContainer').contains(button)!==null)
            document.getElementById('validationContainer').removeChild(button);
    }
    checkFlags();
};

username.onkeyup = function () {
    //l'username può contenere lettere, numeri e _
    let letters = /([A-Za-z0-9\_]+)/;
    if(username.value.match(letters)){
        username.style.borderColor = "green";
        flagUsername = true;
    }else{
        username.style.borderColor = "red";
        flagUsername = false;
        if(document.getElementById('validationContainer').contains(button)!==null)
            document.getElementById('validationContainer').removeChild(button);
    }
    checkFlags();
};

function checkFlags(){
    //se tutti i flag sono true (i campi sono stati collegati correttamente) allora viene creato il bottone
    if(flagName === true && flagSurname===true && flagUsername===true && flagPassword===true && flagConfirmedPassword===true && flagPassword.value === flagConfirmedPassword.value) {
        document.getElementById('validationContainer').appendChild(button);
    }
};