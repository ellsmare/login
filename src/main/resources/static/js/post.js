let index = {
    init: function() {
        $('#btn-save').on('click', () => {
            this.save();
        });
    }
    /* 글쓰기 ajax*/
    , save: function() {
        //alert('save함수 호출됨');
        let data = {
            title: $('#title').val(),     // id=title 
            content: $('#content').val(), //  id=comment   content 확인하기
            username: $('#username').val()
        };
         // console.log(data);
        $.ajax({
            type: "POST",
            url: "/test/post",
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
            .done(function (resp){ //controller에서 return받은 message부분?? 페이지가 들어오는것 같다.
                alert('글쓰기 완료');
                console.log(resp.status)
                alert('글쓰기 완료');
                location.href ='http://localhost:8081/';  //저장이 완료된 이후 이동하는 url
            })
            .fail(function(error) { // xhr: XMLHttpRequest 객체를 가리키는 변수, xhr, status,
                console.log(error);
            });
        }
}
index.init();