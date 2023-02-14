
// //[]사용자가 현재 비밀번호를 입력하고 바뀔 비밀번호를 입력했을때
// //[] 현재 비밀번호와 새로운 비밀번호가 정규식에 맞는지 체크
// //[] 체크 통과하면 버튼 활성화 [색상변경, 클릭가능]

// //비밀번호 길이만큼 '*'를 표시해줌

trans_password();

function trans_password() {
    const pwd_length = document.querySelector('#staticPassword');
    let length = parseInt(pwd_length.value);
    let star = '';
        for (let i = 1; i <= length; i++) {
            star += '●'
        }
        pwd_length.value = star;
}

//비밀번호 길이별 프로그레스바 반응
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

