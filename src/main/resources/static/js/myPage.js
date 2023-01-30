

const change_pass_btn = document.querySelector('.change_pass_btn');

change_pass_btn.addEventListener('click',()=>{
    const pa = document.querySelector('.desc__passworod');
    const change = document.createElement('div');
    const span = document.createElement('span');
    const span2 = document.createElement('span');
    const pre_input = document.createElement('input');
    const change_input = document.createElement('input');
    const  cancleBtn = document.createElement('button');
    const  applyBtn = document.createElement('button');
    //버튼 두개 추가
    
    change.setAttribute('class','div_change')
    pre_input.setAttribute("type", 'password')
    change_input.setAttribute("type", 'password')
    
    span.innerText = "이전 비밀번호";
    span2.innerText = "새 비밀번호";
    cancleBtn.innerText = "취소";
    applyBtn.innerText = "확인";

    pa.appendChild(change);
    change.appendChild(span);
    change.appendChild(pre_input);
    change.appendChild(span2);
    change.appendChild(change_input);
    change.appendChild(cancleBtn);
    change.appendChild(applyBtn);
    


});