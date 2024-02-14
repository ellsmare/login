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
            url : "/auth/signUp",
            data : JSON.stringify(data), // http body 데이터(json형식)
            contentType : "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType : "json", // 응답시 json 형태를 => javascript 오브젝트로 변환
            /* 전송 후 */
            success: function (resp){ //controller에서 return받은 message부분
                alert('가입 완료'+resp.status);
                location.href ='http://localhost:8081/';  //저장이 완료된 이후 이동하는 url

            },
            error: function(error) { // xhr: XMLHttpRequest 객체를 가리키는 변수, xhr, status,
                console.log(error);
            }
        })
            // .done(function(resp) {
            // console.log("ajax 완료");

    }
}
index.init();