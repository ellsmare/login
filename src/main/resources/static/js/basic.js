// //
// // const host = 'http://' + window.location.host;
// function logout() {
//     // 토큰 삭제
//     Cookies.remove('Authorization', {path: '/'});
//     window.location.href = host + '/';
// }
//
// function getToken() {
//     let auth = Cookies.get('Authorization');
//
//     if (auth === undefined) {
//         return '';
//     }
//
//     return auth;
// }
//
//
// // 파라미터로 받은 토큰이 있다면, 토큰을 로컬 스토리지로 저장한다.
// const token = searchParam('token')
//
// if (token) {
//     localStorage.setItem("access_token", token)
// }
//
// function searchParam(key){
//     return new URLSearchParams(location.search).get(key)
// }
//
// // HTTP 요청을 보내는 함수
// function httpRequest(method, url, body, success, fail){
//     fetch(url, {
//         method: method,
//         headers: {
//             // 로컬 스토리지에서 액세스 토큰 값을 가져와 헤더에 추가한다.
//             Authorization: "Bearer " + localStorage.getItem("access_token"),
//             "Content-Type": "application/json"
//         },
//         body: body
//     }).then((response) => {
//         if (response.status === 200 || response.status === 201){
//             return success()
//         }
//         const refresh_token = getCookie("refresh_token")
//         // access_token 이 만료되어 권한이 없고, 리프레시 토큰이 있다면 그 리프레시 토큰을 이용해서 새로운 access token 을 요청
//         if (response.status === 401 && refresh_token) {
//             fetch("/api/token", {
//                 method: "POST",
//                 headers: {
//                     Authorization: "Bearer " + localStorage.getItem("access_token"),
//                     "Content-Type": "application/json"
//                 },
//                 body: JSON.stringify({
//                     refresh_token: getCookie("refresh_token")
//                 })
//             }).then((res) => {
//                 if (res.ok){
//                     return res.json()
//                 }
//             }).then((result) => {
//                 // refresh token 재발급에 성공하면 로컬 스토리지 값을 새로운 access token 으로 교체
//                 localStorage.setItem("access_token", result.accessToken)
//                 // 새로운 access token 으로 http 요청을 보낸다.
//                 httpRequest(method, url, body, success, fail)
//             })
//         }
//         else {
//             return fail()
//         }
//     })
// }
//
//
//
//
// // function sign_up() {
// //     let username = $("#input-username").val()
// //     let password = $("#input-password").val()
// //     let nickname = $("#input-nickname").val()
// //     let password2 = $("#input-password2").val()
// //     console.log(username, nickname, password, password2)
// //
// //
// //
// //     if ($("#help-id").hasClass("is-danger")) {
// //         alert("아이디를 다시 확인해주세요.")
// //         return;
// //     } else if (!$("#help-id").hasClass("is-success")) {
// //         alert("아이디 중복확인을 해주세요.")
// //         return;
// //     }
// //
// //     if (password == "") {
// //         $("#help-password").text("비밀번호를 입력해주세요.").removeClass("is-safe").addClass("is-danger")
// //         $("#input-password").focus()
// //         return;
// //     } else if (!is_password(password)) {
// //         $("#help-password").text("비밀번호의 형식을 확인해주세요. 영문과 숫자 필수 포함, 특수문자(!@#$%^&*) 사용가능 8-20자").removeClass("is-safe").addClass("is-danger")
// //         $("#input-password").focus()
// //         return
// //     } else {
// //         $("#help-password").text("사용할 수 있는 비밀번호입니다.").removeClass("is-danger").addClass("is-success")
// //     }
// //     if (password2 == "") {
// //         $("#help-password2").text("비밀번호를 입력해주세요.").removeClass("is-safe").addClass("is-danger")
// //         $("#input-password2").focus()
// //         return;
// //     } else if (password2 != password) {
// //         $("#help-password2").text("비밀번호가 일치하지 않습니다.").removeClass("is-safe").addClass("is-danger")
// //         $("#input-password2").focus()
// //         return;
// //     } else {
// //         $("#help-password2").text("비밀번호가 일치합니다.").removeClass("is-danger").addClass("is-success")
// //     }
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
// //         }
// //     });
// //
// // }
// //
// // <!--================로그인==================-->
// //
// // function sign_in() {
// //     let username = $("#input-username").val()
// //     let password = $("#input-password").val()
// //
// //     if (username == "") {
// //         $("#help-id-login").text("아이디를 입력해주세요.")
// //         $("#input-username").focus()
// //         return;
// //     } else {
// //         $("#help-id-login").text("")
// //     }
// //
// //     if (password == "") {
// //         $("#help-password-login").text("비밀번호를 입력해주세요.")
// //         $("#input-password").focus()
// //         return;
// //     } else {
// //         $("#help-password-login").text("")
// //     }
// //     $.ajax({
// //         type: "POST",
// //         url: "/sign_in",
// //         data: {
// //             username_give: username,
// //             password_give: password
// //         },
// //         success: function (response) {
// //             if (response['result'] == 'success') {
// //                 $.cookie('mytoken', response['token'], {path: '/'});
// //                 window.location.replace("/")
// //             } else {
// //                 alert(response['msg'])
// //             }
// //         }
// //     });
// // }
//
//
//
