//-[x] 변경버튼을 누르면 변경버튼이 사라지고 -> display 속성을 none으로 하였음
//-[x] 비밀번호를 변경할 수 있는 모달창이 열린다.
//-[x] 취소 버튼을 누르면 모달창이 닫힌다.
//-[x] 변경 버튼이 다시 보이게 된다.

const pa = document.querySelector('.desc__passworod');
const change_pass_btn = document.querySelector('.change_pass_btn');
const inputbox_active = document.querySelector('.inputbox');
let modal = false;


change_pass_btn.addEventListener('click',()=>{

    modal= !modal;
    if(modal) {

        change_pass_btn.style.display = 'none';


        const change = document.createElement('div');
        const change_h5 = document.createElement('h5');
        const span = document.createElement('h5');
        const span2 = document.createElement('h5');
        const pre_input = document.createElement('input');
        const change_input = document.createElement('input');
        const btn_div = document.createElement('div');
        const  cancleBtn = document.createElement('button');
        const  applyBtn = document.createElement('button');
        //버튼 두개 추가

        change.setAttribute('class','div_change');
        change_h5.setAttribute("class","change_h5");


        pre_input.setAttribute("class", 'inputbox');
        pre_input.setAttribute("type", 'password');
        pre_input.setAttribute("placeholder","영문,숫자특수문자 조합 8~16자");

        change_input.setAttribute("class", 'inputbox');
        change_input.setAttribute("type", 'password');
        change_input.setAttribute("placeholder","영문,숫자특수문자 조합 8~16자");


        btn_div.setAttribute('class','btn_div');

        cancleBtn.setAttribute("class","cancleBtn btn")
        applyBtn.setAttribute("class","applyBtn btn")



        change_h5.innerText = "비밀번호 변경"
        span.innerText = "현재 비밀번호";
        span2.innerText = "새 비밀번호";



        cancleBtn.innerText = "취소";
        applyBtn.innerText = "확인";

        pa.appendChild(change);
        change.appendChild(change_h5);
        change.appendChild(span);
        change.appendChild(pre_input);
        change.appendChild(span2);
        change.appendChild(change_input);
        change.appendChild(btn_div);
        btn_div.appendChild(cancleBtn)
        btn_div.appendChild(applyBtn);


    }else{

    }






});

pa.addEventListener('click',(e)=>{
    const change = document.querySelector('.div_change');

    if (e.target.className==='cancleBtn btn'){
        modal = !modal;
        change_pass_btn.style.display = 'block';

        change.remove();
    }
    

})
