window.onload = function() {
    let emailCheck;
    let pwdCheck;
    let nameCheck;
    let birthCheck;
    let phoneCheck;
    let authNumCheck;

// 윈도우가 로드될 때(회원가입시 오류 발생하여 다시 회원가입폼으로 돌아올 경우) 저장된 값을 인식하게하기 위함.

// checkEmail();
// checkPwd();
// checkName();
// checkBirth();
// checkPhone();
// allCheck();




// 이메일이 정규식에 맞는지 확인하여 true false 반환.
    function validate_check(email) {

        let regex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        return (email !== '' && email !== 'undefined' && regex.test(email) );

    }

// 사용자가 입력한 이메일 값을 ajax로 서버에 보내서 실시간으로 확인하여 중복된 메일인지 아닌지 확인.
    function checkEmail() {
        const check_email_feedback = document.querySelector('#check_email_feedback');
        const check_email_feedback_after = document.querySelector('#check_email_feedback_after');
        const auth_email_num = document.querySelector('#auth_email_num');


        let email = $("#email").val();

        if (email === "")  return check_email_feedback.innerText = `이메일 주소를 입력해 주세요.`;

        if (!(validate_check(email))) {
            check_email_feedback.innerText = `이메일 형식에 맞게 입력해 주세요.`;
            // auth_email_num.style.marginTop = '15px';
            return;
        } else {

            $.ajax({
                url: "emailCheck",
                type: "post",
                data: {email: email},
                success: function (cnt) {
                    if (email === "") {
                        emailCheck = false;

                    } else if (cnt === 0) {
                        check_email_feedback.innerText = `사용 가능한 이메일입니다.`;
                        check_email_feedback.style.color = 'green';
                        check_email_feedback_after.style.display = "block";
                        emailCheck = true;

                    } else {
                        check_email_feedback.innerText = `이미 존재하는 이메일 입니다.`;
                        check_email_feedback.style.display = "block";
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
        const check_password_feedback = document.querySelector('#check_password_feedback');
        let pwd = $("#password").val();
        let chkPwd = $("#check_password").val();

        if (pwd !== "" && pwd === chkPwd && 8 <= pwd.length && pwd.length <= 16) {

            check_password_feedback.innerText = '비밀번호가 일치합니다.'
            check_password_feedback.style.color = "#146c43";

            pwdCheck = true;
        } else if (chkPwd === "") {
            check_password_feedback.innerText = '비밀번호를 다시 입력해주세요.'
            pwdCheck = false;
        } else {
            check_password_feedback.innerText = '비밀번호가 일치하지 않습니다.'
            pwdCheck = false;
        }

        allCheck();
    }


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
        let inputNum = $("#auth_email_num").val();
        $.ajax({
            url: "join/emailAuth",
            method: "post",
            data: {inputNum: inputNum},
            success: function (res) {
                if (res) {
                    // $("#auth_email_num").css("background-color", "#4CAF50");
                    authNumCheck = true;
                } else {
                    // $("#authNum").css("background-color", "#ebebeb");
                    authNumCheck = false;
                }
            }
        })
        allCheck();
    }

// 핸드폰 인증번호 서버에 저장.
    function sendPhoneAuthNum() {
        let phone = $("#phone").val();

        if (phone.length !== 11) {
            return;
        }
        $.ajax({
            url: "/phoneAuth",
            method: "post",
            data: {phone: phone}
        })
        alert("핸드폰으로 인증번호가 전송됐습니다.");
    }

    function checkPhoneAuthNum() {
        let inputNum = $("#phone_auth_Num").val();

        if (inputNum.length === 6) {
            $.ajax({
                url: "/phoneAuthOk",
                method: "post",
                data: {inputNum: inputNum},
                success: function (res) {
                    if (res === true) {
                        $("#phone_auth_Num").css("background-color", "#4CAF50");
                        phoneCheck = true;
                    } else {
                        $("#phone_auth_Num").css("background-color", "#ebebeb");
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



    (function() {
        'use strict';
        window.addEventListener('load', function() {


            const forms = document.getElementsByClassName('needs-validation');

            let validation = Array.prototype.filter.call(forms, function(form) {

                form.password.addEventListener('keypress', function(event){
                    console.log("keypress");
                    console.log("event.which: " + event.which);


                    let checkx = true;
                    let chr = String.fromCharCode(event.which);
                    console.log("char: " + chr);


                    let matchedCase = new Array(); //배열 변수 선언
                    matchedCase.push("[!@#$%&*_?]"); // 특수문자
                    matchedCase.push("[A-Z]");      // 대문자
                    matchedCase.push("[0-9]");      // 숫자
                    matchedCase.push("[a-z]");      //소문자


                    for (let i = 0; i < matchedCase.length; i++) {
                        if (new RegExp(matchedCase[i]).test(chr)) {
                            console.log("checkx: is true");
                            checkx = false;
                        }
                    }

                    if(form.password.value.length >= 20)
                        checkx = true;
                    if ( checkx ) {
                        event.preventDefault();
                        event.stopPropagation();
                        //
                    }

                }); //form 이벤트 리스너 종료

                let matchedCase = new Array();
                matchedCase.push("[$@$$!%*#?&]"); // Special Charector
                matchedCase.push("[A-Z]");      // Uppercase Alpabates
                matchedCase.push("[0-9]");      // Numbers
                matchedCase.push("[a-z]");     // Lowercase Alphabates


                form.password.addEventListener('keyup', function(){

                    let messageCase = new Array();
                    messageCase.push(" 특수문자"); // Special Charector
                    messageCase.push(" 알파벳 대문자");      // Uppercase Alpabates
                    messageCase.push(" 숫자");      // Numbers
                    messageCase.push(" 알파벳 소문자");     // Lowercase Alphabates

                    let ctr = 0;
                    let rti = "";
                    for (let i = 0; i < matchedCase.length; i++) {
                        if (new RegExp(matchedCase[i]).test(form.password.value)) {
                            if(i === 0) messageCase.splice(messageCase.indexOf(" 특수문자"), 1);
                            if(i === 1) messageCase.splice(messageCase.indexOf(" 알파벳 대문자"), 1);
                            if(i === 2) messageCase.splice(messageCase.indexOf(" 숫자"), 1);
                            if(i === 3) messageCase.splice(messageCase.indexOf(" 알파벳 소문자"), 1);
                            ctr++;
                        }
                    }


                    let progressbar = 0;
                    let strength = "";
                    let bClass = "";
                    switch (ctr) {
                        case 0:
                        case 1:
                            strength = "[보안 : 취약]";
                            progressbar = 15;
                            bClass = "bg-danger";
                            break;
                        case 2:
                            strength = "[보안 : 약함]";
                            progressbar = 25;
                            bClass = "bg-danger";
                            break;
                        case 3:
                            strength = "[보안 : 보통]";
                            progressbar = 34;
                            bClass = "bg-warning";
                            break;
                        case 4:
                            strength = "[보안 : 강함]";
                            progressbar = 65;
                            bClass = "bg-warning";
                            break;
                    }

                    if (strength === "[보안 : 강함]" && form.password.value.length >= 8 ) {
                        strength = "[보안 : 강함] 아주 좋습니다!";
                        bClass = "bg-success";
                        form.password.setCustomValidity("");
                    } else {
                        form.password.setCustomValidity(strength);
                    }

                    let sometext = "";

                    if(form.password.value.length < 8 ){
                        let lengthI = 8 - form.password.value.length;
                        sometext += ` 최소  ${lengthI} 글자 이상, `;
                    }

                    sometext += messageCase;


                    if(sometext){
                        sometext = sometext + `가(이) 필요합니다.`;
                    }


                    $("#feedbackin, #feedbackirn").text(strength + sometext);
                    $("#progressbar").removeClass( "bg-danger bg-warning bg-success" ).addClass(bClass);
                    let plength = form.password.value.length ;
                    if(plength > 0) progressbar += ((plength - 0) * 1.75) ;
                    //console.log("plength: " + plength);
                    let  percentage = progressbar + "%";
                    form.password.parentNode.classList.add('was-validated');
                    //console.log("pacentage: " + percentage);
                    $("#progressbar").width( percentage );

                    if(form.password.checkValidity() === true){
                        console.log('성공')
                        allCheck();
                        //   form.verifyPassword.disabled = false;
                    } else {
                        console.log('실패')
                        //   form.verifyPassword.disabled = true;
                    }


                });



            });
        }, false);
    })();

}