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
            url : "/auth/signIn",
            data : JSON.stringify(data), // http body 데이터(json형식)
            contentType : "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType : "json", // 응답시 json 형태를 => javascript 오브젝트로 변환
            /* 전송 후 */
            success: function (resp){ //controller에서 return받은 message부분
                alert('로그인 완료'+resp.status);
                location.href ='http://localhost:8081/';  //저장이 완료된 이후 이동하는 url
            },
            error: function(error) { // xhr: XMLHttpRequest 객체를 가리키는 변수, xhr, status,
                console.log(error);
            }
        })
    }

    <!--    <script>-->
    <!--        jQuery(document).ready(function() {-->
    <!--            if(${id== null}) {-->
    <!--                alert("게시판을 이용하시려면 로그인하셔야 합니다.");-->
    <!--                location.href="/bbs/login.bbs"-->
    <!--            }-->
    <!--        });-->
    // <!--    </script>-->

// 실패해도 성공했다고 뜬다 !!! 반환값이 object object로 나옴
// if(resp == "success"){ //응답 결과 함수의 파라미터로 전달
//     alert('회원가입이 완료되었습니다.');
//     // console.log(resp);
//     location.href = '/v1';
// } else if (resp == "fail"){
//     alert('다시 입력해주세요');
// } else {
//     alert('회원가입 실패');
// }
