/* 회원 가입 ajax*/
function onLogin() {
    //alert('save함수 호출됨');
    let data = {
        username: $('#username').val(),
        password: $('#password').val()
    };
    // console.log(data);
    $.ajax({
        type : "POST",
        url : "/auth/signin",
        data : JSON.stringify(data), // http body 데이터(json형식)
        contentType : "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
        dataType : "json" // 응답시 json 형태를 => javascript 오브젝트로 변환
    }).done(function(resp) {
        alert('로그인 완료');
        //console.log(resp);
        // location.href = '/auth/signIn-page';
        location.href = '/';
    }).fail(function(error) {
        //alert(JSON.stringify(error));
        console.log(error.status);
    });
}



/*
// id로 지정
let sign = {
    init: function() {
        $('#btn-login').on('click', () => {
            this.login();
        });
    }
    /!* 로그인 ajax*!/
    ,login: function() {
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
                alert("success",res)
                if (res.status == 200) {
                    console.log(res);
                    alert('가입 완료');
                    console.log(resp)
                    alert('가입 완료');
                }else{
                    alert(res['msg'])
                }
            },
            error: function () {
                alert("error");
            }
        })
        // /!* 전송 후 *!/
        // .done(function (resp) { //controller에서 return받은 message부분
        //     alert('로그인 완료');
        //     console.log(resp)
        //     alert('로그인 완료');
        // })
        // .fail(function (error) { // xhr: XMLHttpRequest 객체를 가리키는 변수, xhr, status,
        //     console.log(error);
        // });
    }
}
sign.init();


*/
