/*********************************************************************/
//window.location.replace("/api/v1")  조심!!

/* 회원가입 ajax*/
function singUp() {
    const href = location.href;   //현재 페이지의 전체 URL
    const host = 'http://' + window.location.host;

    //alert('save함수 호출됨');
    let username = $('#username').val();
    let password = $('#password').val();
    let email = $('#email').val();

    // alert(username);
    // console.log(username);
    $.ajax({
        type: "POST",
        url: "/api/v1/auth/signup",
        contentType: "application/json",
        data: JSON.stringify({username: username, password: password, email: email}),
    })
        .done(function (res) {
            if (res.status === 200) {
                console.log(res)
                alert("회원 가입 성공"+res.data);
                window.location.href = host + '/api/v1/auth/login-page'
                // 새로운 URL로 페이지를 리디렉션
            } else {
                alert(res)
                console.log(res)
            }
        })
        .fail(function (error) {
            console.log(error)
            alert("회원 가입 Fail" + error);
            window.location.href = host + '/api/v1'
        });
}


/*********************************************************************/


/* 로그인 ajax*/
function onLogin() {
    const href = location.href;   //현재 페이지의 전체 URL
    const host = 'http://' + window.location.host;

    // alert('onLogin 호출됨');
    let data = {
        username: $('#username').val(),
        password: $('#password').val()
    };
    //alert(data.username);
    // console.log(username);
    $.ajax({
        type: "POST",
        url: "/api/v1/auth/login",
        contentType: "application/json",
        data: JSON.stringify(data),
       // dataType: "json"
    })
        .done(function (res) {
            console.log(res.status)
            alert(res.message);
            window.location.replace("/api/v1")

        })
        .fail(function (error) {
            console.log(error)
            alert(error.data);
            window.location.replace("/api/v1/auth/login-page")
        });
}


//
//         .done(function (res, status, xhr) {
//            /* 성공으로 안들어오고 실패고 간다 */
//            /* 쿠키에 토큰 들어옴 */
//
//             const token = xhr.getResponseHeader('Authorization');
//             // Cookies.set('Authorization', token, {path: '/'})
//             alert(token);
//
//             // $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
//             //     jqXHR.setRequestHeader('Authorization', token);
//             //     alert("ajaxPrefilter");
//             // });
//
//             window.location.href = host + '/api/v1'
//         }).fail(function (error) {
//         alert(JSON.stringify(error.status));
//        // console.log(error.status);
//         window.location.href = host + '/api/v1'
//     });
//
// }
//
/*success: function (response) {
    // 성공적인 응답 처리 로직
    alert("성공"+response.status);
    window.location.replace("/api/v1")
    // 필요한 성공 처리 로직을 구현합니다.
}*/

// //     $.ajax({
// //         type: "POST",
// //         url: "/sign_up/save",
// //         data: {
// //             username_give: username,
// //             password_give: password,
// //             nickname_give: nickname
// //         },
// //         success: function (response) {
// //             alert("회원가입을 축하드립니다!")
// //             window.location.replace("/login")
// //         },

// //     });
// //
// // }

//index.init();


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

