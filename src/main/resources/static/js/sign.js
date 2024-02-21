$(document).ready(function () {
    // 토큰 삭제
    Cookies.remove('Authorization', {path: '/'});
});

const href = location.href;
const host = 'http://' + window.location.host;

/*********************************************************************/

/* 회원가입 ajax*/
function signup() {
    //alert('save함수 호출됨');
    let username = $('#username').val();
    let password = $('#password').val();
    let email = $('#email').val();

    // alert(username);
    // console.log(username);
    $.ajax({
        type: "POST",
        url: "/auth/signup",
        contentType: "application/json",
        data: JSON.stringify({username: username, password: password, email: email}),
    })
        .done(function (res) {
            if (res.status === 200) {
                console.log(res)
                alert('가입 완료');
                window.location.href = host + '/auth/login-form'
            } else {
                alert(res)
            }
        })
        .fail(function (res) {
            alert("회원 가입 Fail" +res);
            window.location.href = host + '/auth/page/error'
        });
}




/*********************************************************************/

/* 로그인 ajax*/
function onLogin() {
    //alert('save함수 호출됨');
    let username = $('#username').val();
    let password = $('#password').val();

    // alert(username);
    // console.log(username);
    $.ajax({
        type: "POST",
        url: "/auth/login",
        contentType: "application/json",
        data: JSON.stringify({username: username, password: password}),
    })
        .done(function (res, status, xhr) {
            const token = xhr.getResponseHeader('Authorization');
            Cookies.set('Authorization', token, {path: '/'})
            alert(token);
            alert(document.cookie)

            $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
                jqXHR.setRequestHeader('Authorization', token);
                alert("ajaxPrefilter");
            });

            window.location.href = host;
        })
        .fail(function (jqXHR, textStatus) {
            alert("Login Fail");
            window.location.href = host + '/auth/page/error'
        });
}
























// 로그인 ajax
/* function onLogin()
{
    //alert('save함수 호출됨');
    let data = {
        username: $('#username').val(),
        password: $('#password').val()
    };
    // console.log(data);
    $.ajax({
        type: "POST",
        url: "/auth/join",
        data: JSON.stringify(data), // http body 데이터(json형식)
        contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
        dataType: "json" // 응답시 json 형태를 => javascript 오브젝트로 변환
    }).done(function (resp) {  ///!* 전송 후 *!/
        alert('로그인 완료');
        //console.log(resp);
        // location.href = '/auth/join-form';
        location.href = '/';
    }).fail(function (error) {
        //alert(JSON.stringify(error));
        console.log(error.status);
    });
}
*/

/*

const setCookie = function(name, value, exp) {
    const date = new Date();
    date.setTime(date.getTime() + exp * 60 * 60 * 24 * 1000);  //1day
    document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
};




*/
