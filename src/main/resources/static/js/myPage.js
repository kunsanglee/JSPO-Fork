
// //[]사용자가 현재 비밀번호를 입력하고 바뀔 비밀번호를 입력했을때
// //[] 현재 비밀번호와 새로운 비밀번호가 정규식에 맞는지 체크
// //[] 체크 통과하면 버튼 활성화 [색상변경, 클릭가능]
//


// //비밀번호 길이만큼 '*'를 표시해줌

document.addEventListener('load',trans_password);
// trans_password()
function trans_password() {
    const pwd_length = document.querySelector('#staticPassword');
    console.log(pwd_length.value)
    let length = parseInt(pwd_length.value);
    let star = '';
        for (let i = 1; i <= length; i++) {
            star += '●'

        }
        pwd_length.value = star;
}





// const modal = document.querySelector('.modal');
// const hidden = document.querySelector('.hidden');
// const applyBtn = document.querySelector('.applyBtn');
// const cancleBtn = document.querySelector('.cancleBtn');
// const currentPwd = document.querySelector(' .currentPwd');
// const newPwd = document.querySelector(' .newPwd');
// const change_pass_btn = document.querySelector('.change_pass_btn');
// const hint1 = document.querySelector('.hint1');
// const hint2 = document.querySelector('.hint2');
//
// const phone__modal__hidden = document.querySelector('.phone__modal__hidden');
// const phone__cancleBtn = document.querySelector('.phone__cancleBtn');
// const newPhone = document.querySelector('.newPhone');
// const authBtn = document.querySelector('.phone__authBtn');
// const authPhone = document.querySelector('.authPhone');
// const phone__applyBtn = document.querySelector('.phone__applyBtn');
//
// const secession__modal__hidden = document.querySelector('.secession__modal__hidden');
// const secession__password = document.querySelector('.secession__password');
// const secession__apply__btn = document.querySelector('.secession__apply__btn');
//
// const change__phone__button = document.querySelector('.change__phone__title');
//
// let isModalTrue = false;
// let isPhoneModalTrue = false;
// let isSecessionModalTrue = false;
//
//
// //비밀번호가 정규식에 맞는지 체크하는 함수
// function pwd_check(pwd) {
//     let regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;
//     return (pwd !== '' && pwd !== 'undefined' && regex.test(pwd));
// }
//
//
// function phone_check(phone) {
//     let regex = /^010[0-9]{8}$/;
//     return (phone !== '' && phone !== 'undefined' && regex.test(phone));
// }
//
//
// currentPwd.addEventListener('input', (e) => {
//     const hint = document.querySelector('.hint1');
//     let pass_vaild = pwd_check(currentPwd.value);
//     if (pass_vaild) {
//         hint.style.display = "none";
//     } else if (!pass_vaild) {
//         hint.style.display = "block";
//
//     }
//
// })
// newPwd.addEventListener('input', (e) => {
//     const hint = document.querySelector('.hint2');
//     let pass_vaild = pwd_check(newPwd.value);
//     if (pass_vaild) {
//         hint.style.display = "none";
//     } else if (!pass_vaild) {
//         hint.style.display = "block";
//     }
//
//
// })
//
//
// change_pass_btn.addEventListener('click', () => {
//     isModalTrue = !isModalTrue;
//     if (isModalTrue) {
//         hidden.style.display = 'flex';
//
//     }
// })
//
// applyBtn.addEventListener('click', () => {
//     // 현재 비밀번호와 새로운 비밀번호를 변수에 저장한다.
//     let pwd = currentPwd.value;
//     let chgPwd = newPwd.value;
//     $.ajax({
//         // modifyPwd 라는 url로
//         url: "modifyPwd",
//         // post형식으로 보낸다
//         type: "post",
//         // pwd라는 이름으로 pwd값을, chgPwd라는 이름으로 chgPwd값을
//         data: {
//             pwd: pwd,
//             chgPwd: chgPwd
//         },
//         // 성공하면 function을 실행하고 서버로부터 받아온 res(결과값)을 받아와서 작업한다.
//         success: function (res) {
//             if (res) {
//                 alert("비밀번호 변경을 완료했습니다.");
//                 window.location.reload();
//             } else {
//                 alert("입력하신 비밀번호가 일치하지 않습니다.");
//             }
//         }
//     })
// })
//
// cancleBtn.addEventListener('click', () => {
//
//     isModalTrue = false;
//
//     if (isModalTrue === false) {
//         hidden.style.display = 'none';
//         hint1.style.display = "none";
//         hint2.style.display = "none";
//         currentPwd.value = '';
//         newPwd.value = '';
//
//     }
//
// });
//
// change__phone__button.addEventListener('click', () => {
//
//
//     isPhoneModalTrue = !isPhoneModalTrue;
//     if (isPhoneModalTrue) {
//         phone__modal__hidden.style.display = 'block';
//
//     }
//
//
// });
//
// // 핸드폰 인증번호 서버에 저장.
// authBtn.addEventListener('click', () => {
//     // 핸드폰 인증번호 전송
//     let phone = newPhone.value;
//
//     if (!phone_check(phone)) {
//         return;
//     } else {
//         $.ajax({
//             url: "/phoneAuth",
//             method: "post",
//             data: {phone: phone},
//             success: function(res) {
//                 if (res) {
//                     alert("핸드폰으로 인증번호가 전송됐습니다.");
//                 } else {
//                     alert("이미 등록된 번호입니다.")
//                 }
//             }
//         })
//     }
// });
//
// authPhone.addEventListener('input', () => {
//
//     let inputNum = authPhone.value;
//
//     if (inputNum.length === 6) {
//         $.ajax({
//             url: "/phoneAuthOk",
//             method: "post",
//             data: {inputNum: inputNum},
//             success: function (res) {
//                 if (res === true) {
//                     authPhone.style.backgroundColor = "#4CAF50";
//                     phone__applyBtn.disabled = false;
//                     phone__applyBtn.style.backgroundColor = "#4CAF50";
//                 }
//             }
//         })
//     } else {
//         authPhone.style.backgroundColor = "#ebebeb";
//         phone__applyBtn.disabled = true;
//     }
// })
//
// phone__applyBtn.addEventListener('click', () => {
//     $.ajax({
//         url: "modifyPhone",
//         type: "post",
//         data: {
//             phone: newPhone.value
//         },
//         success: function(res) {
//             if (res) {
//                 alert("핸드폰 번호 변경을 완료했습니다.");
//                 phone__cancleBtn.click();
//                 window.location.reload();
//             }
//         },
//         error: function () {
//             alert("핸드폰 번호 변경중 오류가 발생했습니다.");
//         }
//     })
// })
//
// phone__cancleBtn.addEventListener('click', () => {
//     const phoneHint = document.querySelector('.phoneHint');
//
//     isPhoneModalTrue = false;
//     if (isPhoneModalTrue === false) {
//         phone__modal__hidden.style.display = 'none';
//         phoneHint.style.display = "none";
//         newPhone.value = '';
//     }
//
// });
//
//
// newPhone.addEventListener('input', () => {
//
//     const hint = document.querySelector('.phoneHint');
//     let phone_vaild = phone_check(newPhone.value);
//     if (phone_vaild) {
//         hint.style.display = "none";
//     } else if (!phone_vaild) {
//         hint.style.display = "block";
//
//     }
//
// });
//
// const secession = document.querySelector('.secession');
// const secession__cancle__btn = document.querySelector('.secession__cancle__btn')
// secession.addEventListener('click', () => {
//     isSecessionModalTrue = !isSecessionModalTrue;
//     if (isSecessionModalTrue) {
//         secession__modal__hidden.style.display = 'flex';
//     }
//
// });
//
//
// secession__cancle__btn.addEventListener('click', () => {
//     isSecessionModalTrue = false;
//     if (isSecessionModalTrue === false) {
//         secession__modal__hidden.style.display = 'none';
//         secession__password.value = '';
//     }
//
// })
//
// secession__apply__btn.addEventListener('click', () => {
//     let pwd = secession__password.value;
//
//     $.ajax({
//         url: "/secession",
//         type: "post",
//         data: {pwd},
//         success: function(res) {
//             if (res) {
//                 alert("탈퇴가 정상적으로 처리됐습니다.");
//                 window.location.reload();
//             } else {
//                 alert("비밀번호가 일치하지 않습니다.");
//             }
//         },
//         error: function() {
//             alert("회원 탈퇴 중 오류가 발생했습니다.");
//         }
//     })
// })



(function () {
    'use strict';
    window.addEventListener('load', function() {


        const forms = document.getElementsByClassName('needs-validation-modal');

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
            matchedCase.push("[$@$$!%*#?&]");
            matchedCase.push("[A-Z]");
            matchedCase.push("[0-9]");
            matchedCase.push("[a-z]");


            form.password.addEventListener('keyup', function(){

                let messageCase = new Array();
                messageCase.push(" 특수문자");
                messageCase.push(" 알파벳 대문자");
                messageCase.push(" 숫자")
                messageCase.push(" 알파벳 소문자")

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
                    sometext = sometext + `가 필요합니다.`;
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



//비밀번호 변경 > 취소버튼 > value 초기화
function password_cancle() {
    const current_password = document.querySelector('#current_password');
    const password = document.querySelector('#password');
    current_password.value = '';
    password.value = '';
}

//비밀번호 변경 > 변경 버튼 > 비번 변경 메서드
function password_change(){
    const change_password_apply = document.querySelector('#change_password_apply');
    const current_password = document.querySelector('#current_password');
    const password = document.querySelector('#password');
    change_password_apply.addEventListener('click', () => {
        // 현재 비밀번호와 새로운 비밀번호를 변수에 저장한다.
        let pwd = current_password.value;
        let chgPwd = password.value;
        console.log(pwd,'>>>',chgPwd)
        $.ajax({
            // modifyPwd 라는 url로
            url: "modifyPwd",
            // post형식으로 보낸다
            type: "post",
            // pwd라는 이름으로 pwd값을, chgPwd라는 이름으로 chgPwd값을
            data: {
                pwd: pwd,
                chgPwd: chgPwd
            },
            // 성공하면 function을 실행하고 서버로부터 받아온 res(결과값)을 받아와서 작업한다.
            success: function (res) {
                if (res) {
                    alert("비밀번호 변경을 완료했습니다.");
                    window.location.reload();
                } else {
                    alert("입력하신 비밀번호가 일치하지 않습니다.");
                }
            }
        })

    })
}




//핸드폰 번호 변경


//핸드폰번호 변경 > 취소버튼 > value 초기화
function phone_cancle() {
    const current_phone_number = document.querySelector('#current_phone_number');
    const new_phone_number = document.querySelector('#new_phone_number');
    current_phone_number.value = '';
    new_phone_number.value = '';

}


function phone_change() {

//미구현

}

