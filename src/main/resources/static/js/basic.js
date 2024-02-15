// const host = 'http://' + window.location.host;
// let targetId;
//
// $(document).ready(function () {
//     const auth = getToken();
//
//     if (auth !== undefined && auth !== '') {
//         $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
//             jqXHR.setRequestHeader('Authorization', auth);
//         });
//     } else {
//         window.location.href = host + '/auth/signIn-page';
//         return;
//     }
//
//     $.ajax({
//         type: 'GET',
//         url: `/auth/users/info-page`,
//         contentType: 'application/json',
//     })
//         .done(function (res, status, xhr) {
//             const username = res.username;
//             const isAdmin = !!res.admin;
//
//             if (!username) {
//                 window.location.href = '/auth/signIn-page';
//                 return;
//             }
//
//             $('#username').text(username);
//             if (isAdmin) {
//                 $('#admin').text(true);
//                 showProduct(true);
//             } else {
//                 showProduct();
//             }
//         })
//         .fail(function (jqXHR, textStatus) {
//             logout();
//         });
// })
//
//     function logout() {
//         // 토큰 삭제
//         Cookies.remove('Authorization', {path: '/'});
//         window.location.href = host + '/';
//     }
//
//     function getToken() {
//         let auth = Cookies.get('Authorization');
//
//         if (auth === undefined) {
//             return '';
//         }
//
//         return auth;
//     }
//
//