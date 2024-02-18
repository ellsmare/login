let index = {
    init: function() {
        $('#btn-save').on('click', () => {
            this.save();
        });
    }
    /* 회원 가입 ajax*/
    , save: function() {
        //alert('save함수 호출됨');
        let data = {
            username: $('#username').val(),
            password: $('#password').val(),
            email: $('#email').val()
        };
        // console.log(data);
        $.ajax({
            type : "POST",
            url : "/auth/signup",
            data : JSON.stringify(data), // http body 데이터(json형식)
            contentType : "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json",
            success: function (res) {
                if (res.status == 200) {
                    console.log(res)
                    alert('가입 완료');
                } else {
                    alert(res['msg'])
                }
            },
            error: function () {
                alert("error");
            }
        })
        // .done(function (resp) { //controller에서 return받은 message부분
        //     alert('가입 완료');
        //     console.log(resp)
        //     alert('가입 완료');
        //     location.href = 'http://localhost:8081/';  //저장이 완료된 이후 이동하는 url
        // })
        // .fail(function (error) { // xhr: XMLHttpRequest 객체를 가리키는 변수, xhr, status,
        //     console.log(error);
        // });
    }
}
index.init();


/*$(document).ready(function () {
    // 토큰 삭제
    Cookies.remove('Authorization', {path: '/'});
});

const host = 'http://' + window.location.host;

const href = location.href;
const queryString = href.substring(href.indexOf("?")+1)
if (queryString === 'error') {
    const errorDiv = document.getElementById('login-failed');
    errorDiv.style.display = 'block';
}*/