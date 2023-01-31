let emailCheck;
let pwdCheck;
let nameCheck;
let birthCheck;
let phoneCheck;
let authNumCheck;

// 윈도우가 로드될 때(회원가입시 오류 발생하여 다시 회원가입폼으로 돌아올 경우) 저장된 값을 인식하게하기 위함.
window.onload = function () {
    checkEmail();
    checkPwd();
    checkName();
    checkBirth();
    checkPhone();
    allCheck();
}

// 이메일이 정규식에 맞는지 확인하여 true false 반환.
function email_check(email) {

    var regex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    return (email !== '' && email !== 'undefined' && regex.test(email));

}

// 사용자가 입력한 이메일 값을 ajax로 서버에 보내서 실시간으로 확인하여 중복된 메일인지 아닌지 확인.
function checkEmail() {
    let email = $("#email").val();

    if (email !== "") $("#valid_email").css("display", "none");
    else $("#valid_email").css("display", "inline-block");

    if (!(email_check(email))) {
        $(".email_ok").css("display", "none");
        $(".email_already").css("display", "none");
        return;
    } else {
        $.ajax({
            url: "emailCheck",
            type: "post",
            data: {email: email},
            success: function (cnt) {
                if (email === "") {
                    $(".email_ok").css("display", "none");
                    $(".email_already").css("display", "none");
                    emailCheck = false;
                    $("#authBtn").prop("disabled", true);
                    $("#authBtn").css("background-color", "#ebebeb")
                } else if (cnt === 0) {
                    $(".email_ok").css("display", "inline-block");
                    $(".email_already").css("display", "none");
                    emailCheck = true;
                    $("#authBtn").prop("disabled", false);
                    $("#authBtn").css("background-color", "#4CAF50")
                } else {
                    $(".email_ok").css("display", "none");
                    $(".email_already").css("display", "inline-block");
                    $(".reg").prop("disabled", true);
                    $(".reg").css("background-color", "#ebebeb");
                    $("#authBtn").prop("disabled", true);
                    $("#authBtn").css("background-color", "#ebebeb")
                    emailCheck = false;
                }
            },
            error: function () {
                alert("에러입니다.");
            }
        });
        allCheck();
    }
}

// 사용자가 입력한 비밀번호가 비밀번호 확인란에 입력한 값과 일치하는지 확인.
function checkPwd() {
    let pwd = $("#password").val();
    let chkPwd = $("#check__password").val();

    if (pwd !== "" && pwd === chkPwd && 8 <= pwd.length && pwd.length <= 16) {
        $(".pwd_ok").css("display", "inline-block");
        $(".pwd_duplicate").css("display", "none");
        $("#valid_pwd").css("display", "none");
        pwdCheck = true;
    } else {
        $(".pwd_ok").css("display", "none");
        $(".pwd_duplicate").css("display", "inline-block");
        $(".reg").prop("disabled", true);
        $("#valid_pwd").css("display", "inline-block");
        pwdCheck = false;
    }
    allCheck();
}

// 사용자가 이름을 입력했는지 확인.
function checkName() {
    if ($("#name").val() !== "") {
        $("#valid_name").css("display", "none");
        nameCheck = true;
    } else {
        $("#valid_name").css("display", "inline-block");
        nameCheck = false;
    }
    allCheck();
}

// 사용자가 생년월일을 입력했는지 확인.
function checkBirth() {
    if ($("#birth").val() !== "") {
        $("#valid_birth").css("display", "none");
        birthCheck = true;
    } else {
        $("#valid_birth").css("display", "inline-block");
        birthCheck = false;
    }
    allCheck();
}

// 사용자가 전화번호를 입력했는지 확인.
function checkPhone() {
    if ($("#phone").val() !== "" && $("#phone").val().length === 11) {
        let phone = $("#phone").val();
        $.ajax({
            url: "/phoneCheck",
            method: "post",
            data: {phone : phone},
            success: function (res) {
                if (res === 0) {
                    $("#valid_phone").css("display", "none");
                    $("#phoneAuthBtn").prop("disabled", false);
                    $("#phoneAuthBtn").css("background-color", "#4CAF50");
                    $("#phone_ok").css("display", "inline-block");
                    $("#phoneDuplicate").css("display", "none");
                }
                else {
                    $("#valid_phone").css("display", "inline-block");
                    $("#phoneAuthBtn").prop("disabled", true);
                    $("#phoneAuthBtn").css("background-color", "#ebebeb");
                    $("#phone_ok").css("display", "none");
                    $("#phoneDuplicate").css("display", "inline-block");
                    phoneCheck = false;
                }
            }
        })
    } else {
        $("#phoneDuplicate").css("display", "none");
        $("#phone_ok").css("display", "none");
        $("#phoneAuthBtn").prop("disabled", true);
        $("#phoneAuthBtn").css("background-color", "#ebebeb");
    }
    allCheck();
}

// 이메일인증 버튼 누르면 서버에 인증번호 저장.
function sendAuthNum() {
    let email = $("#email").val();
    $.ajax({
        url: "join/emailConfirm",
        method: "post",
        data: {email: email},
    })
    alert("이메일로 인증번호가 전송됐습니다.");
}

// 서버에 저장된 인증번호와 사용자가 입력한 값이 맞는지 확인.
function checkAuthNum() {
    let inputNum = $("#authNum").val();

    $.ajax({
        url: "join/emailAuth",
        method: "post",
        data: {inputNum: inputNum},
        success: function (res) {
            if (res) {
                $("#authNum").css("background-color", "#4CAF50");
                authNumCheck = true;
            } else {
                $("#authNum").css("background-color", "#ebebeb");
                authNumCheck = false;
            }
        }
    })
    allCheck();
}

// 핸드폰 인증번호 서버에 저장.
function sendPhoneAuthNum(e) {
    let phone = $("#phone").val();
    e.preventDefault();

    if (phone.length !== 11) {
        return;
    }
    alert("gogo");
    $.ajax({
        url: "/phoneAuth",
        method: "post",
        data: {phone: phone}
    })
    alert("핸드폰으로 인증번호가 전송됐습니다.");
}

function checkPhoneAuthNum() {
    let inputNum = $("#phoneAuthNum").val();

    if (inputNum.length === 6) {
        $.ajax({
            url: "/phoneAuthOk",
            method: "post",
            data: {inputNum: inputNum},
            success: function (res) {
                if (res === true) {
                    $("#phoneAuthNum").css("background-color", "#4CAF50");
                    phoneCheck = true;
                } else {
                    $("#phoneAuthNum").css("background-color", "#ebebeb");
                }
            }
        })
    }
    allCheck();
    return;
}

// 모든 input 항목을 적었는지 확인하고, 다 입력하면 가입버튼 활성화, 아니면 비활성화.
function allCheck() {

    if (emailCheck && pwdCheck && nameCheck && birthCheck && phoneCheck && authNumCheck) {
        $(".reg").prop("disabled", false);
        $(".reg").css("background-color", "#4CAF50");
    } else {
        $(".reg").prop("disabled", true);
        $(".reg").css("background-color", "#ebebeb");
    }
}