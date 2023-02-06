const login_logout = document.querySelector('#login_logout');
let isLogin



trans_Logout();

const deleteCookie = function(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
}
function trans_Logout(){
    console.log('isLogin 작동중')
    if (document.cookie!==''&&document.cookie!==null){
        console.log('isLogin if 진입')
        login_logout.innerText = '로그아웃';
    }else if(document.cookie.length === 0){
        login_logout.innerText = '로그인';
    }
    
}

//로그아웃 클릭시 로그아웃
login_logout.addEventListener('click',()=>{
    const LOGIN = "로그인";
    const LOGOUT = '로그아웃';
    const LOGIN_STATE = login_logout.innerText;
    console.log(LOGIN_STATE);
    if (LOGIN_STATE===LOGIN){
        //비 로그인 상태
        console.log('지금',LOGIN);
        document.location.href = '/login'

    }else if(LOGIN_STATE===LOGOUT){
        deleteCookie('email');
        document.location.href = '/logout';

        console.log(LOGOUT);
    }
    console.log('클릭릭')
})




console.log('허미')