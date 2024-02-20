let index = {
    init: function() {
        $('#btn-save').on('click', () => {
            this.save();
        });
    }
    /* 글쓰기 ajax*/
    , save: function() {
        alert('save함수 호출됨');
        let data = {
            title: $('#title').val(),     // id=title 
            content: $('#content').val() //  id=content   content 확인하기
            // img: $('#img').val()
        };
        alert('save data : '+data);
         // console.log(data);
        $.ajax({
            type: "POST",
            url: "/users/posts",
            data: JSON.stringify(data), // http body 데이터(json형식)
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
            dataType: "json", // 응답시 json 형태를 => javascript 오브젝트로 변환
            error: function () {
                alert("error");
            },
            success: function (res) {
                if((res.status)===200){
                    console.log("status200:",res);
                    location.href = '/';
                }
                console.log("not success: ",res);
            }
        })
            /* 전송 후 */
            // .done(function (resp){ //controller에서 return받은 message부분?? 페이지가 들어오는것 같다.
            //     console.log(resp.status)
            //     alert('글쓰기 완료');
            //     location.href ='/';  //저장이 완료된 이후 이동하는 url
            // })
            // .fail(function(error) { // xhr: XMLHttpRequest 객체를 가리키는 변수, xhr, status,
            //     console.log(error);
            // });
        }
}
index.init();


