//[]사용자가 현재 비밀번호를 입력하고 바뀔 비밀번호를 입력했을때
//[] 현재 비밀번호와 새로운 비밀번호가 정규식에 맞는지 체크
//[] 체크 통과하면 버튼 활성화 [색상변경, 클릭가능]

const modal = document.querySelector('.modal');
const hidden = document.querySelector('.hidden');
const applyBtn = document.querySelector('.applyBtn');
const cancleBtn = document.querySelector('.cancleBtn');
const currentPwd = document.querySelector(' .currentPwd');
const newPwd = document.querySelector(' .newPwd');
const change_pass_btn = document.querySelector('.change_pass_btn');
const hint1 = document.querySelector('.hint1');
const hint2 = document.querySelector('.hint2');


const change__phone__button = document.querySelector('.change__phone__title');

let isModalTrue = false;


//비밀번호가 정규식에 맞는지 체크하는 함수
function pwd_check(pwd){
    let regex=/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;
    return regex.test(pwd);
}


currentPwd.addEventListener('input',(e)=>{
    const hint = document.querySelector('.hint1');
    let pass_vaild = pwd_check(currentPwd.value);
    if (pass_vaild){
        hint.style.display = "none";
    }else if (!pass_vaild){
        hint.style.display = "block";

    }

})
newPwd.addEventListener('input',(e)=>{
    const hint = document.querySelector('.hint2');
    let pass_vaild = pwd_check(newPwd.value);
    if (pass_vaild){
        hint.style.display = "none";
    }else if (!pass_vaild){
        hint.style.display = "block";
    }


})
//



change_pass_btn.addEventListener('click',()=>{
    isModalTrue = !isModalTrue;
    if (isModalTrue){
        hidden.style.display = 'flex';

    }
})

applyBtn.addEventListener('click', () => {
    // 현재 비밀번호와 새로운 비밀번호를 변수에 저장한다.
    let pwd = currentPwd.value;
    let chgPwd = newPwd.value;
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
        success: function(res) {
            if (res) {
                alert("비밀번호 변경을 완료했습니다.");
                window.location.reload();
            } else {
                alert("입력하신 비밀번호가 일치하지 않습니다.");
            }
        }
    })
})

cancleBtn.addEventListener('click', () => {
    isModalTrue = false;
    if (isModalTrue===false){
        hidden.style.display = 'none';
        hint1.style.display = "none";
        hint2.style.display = "none";
        currentPwd.value = '';
        newPwd.value = '';

    }

})



change__phone__button.addEventListener('click',()=>{
    console.log('ddd')
})


