//
// $(document).ready(function () {
//     Cookies.remove('Authorization', {path: '/'});  // 토큰 삭제
// });
//
// const host = 'http://' + window.location.host;
// const href = location.href;
// const queryString = href.substring(href.indexOf("?")+1)
// if (queryString === 'error') {
//     const errorDiv = document.getElementById('login-failed');
//     errorDiv.style.display = 'block';
// }
//
// function onLogin() {
//     //alert('save함수 호출됨');
//     let data = {
//         username: $('#username').val(),
//         password: $('#password').val(),
//         email: $('#email').val()
//     };
//     // console.log(data);
//     $.ajax({        //회원 가입 ajax*
//         type: "POST",
//         url: `/auth/signUp-page`,
//         contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
//         data : JSON.stringify(data),                    // http body 데이터(json형식)
//         dataType : "json",                              // 응답시 json 형태를 => javascript 오브젝트로 변환
//     })
//         .done(function (res, status, xhr) {
//             window.location.href = host;
//         })
//         .fail(function (xhr, textStatus, errorThrown) {
//             console.log('statusCode: ' + xhr.status);
//             window.location.href = host + '/auth/signUp-page?error'
//         });
// }
//
let into = {
    init: function() {
        $('#btn-login').on('click', () => {
            this.login();
        });
    }
    /* 로그인 ajax*/
    , login: function() {
        //alert('save함수 호출됨');
        let data = {
            username: $('#username').val(),
            password: $('#password').val()
        };
        // console.log(data);
        $.ajax({
            type: "POST",
            url: "/auth/signIn",
            data: JSON.stringify(data), // http body 데이터(json형식)
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json", // 응답시 json 형태를 => javascript 오브젝트로 변환
            success: function (res) {
                console.log(res);
            },
            error: function () {
                alert("error");
            }
        })
            /* 전송 후 */
            .done(function (resp) { //controller에서 return받은 message부분
                alert('로그인 완료');
                console.log(resp)
                alert('로그인 완료');
                location.href = 'http://localhost:8081/';  //저장이 완료된 이후 이동하는 url
            })
            .fail(function (error) { // xhr: XMLHttpRequest 객체를 가리키는 변수, xhr, status,
                console.log(error);
            });
    }
}
into.init();

// <!--    <script>-->
// <!--        jQuery(document).ready(function() {-->
// <!--            if(${id== null}) {-->
// <!--                alert("게시판을 이용하시려면 로그인하셔야 합니다.");-->
// <!--                location.href="/bbs/login.bbs"-->
// <!--            }-->
// <!--        });-->
// // <!--    </script>-->