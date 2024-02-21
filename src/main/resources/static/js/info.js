const host = 'http://' + window.location.host;
let targetId;

$(document).ready(function () {
    const auth = getToken();

    if (auth !== undefined && auth !== '') {
        $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
            jqXHR.setRequestHeader('Authorization', auth);
        });
    } else {
        window.location.href = host + '/api/v1/users/login';
        return;
    }

    $.ajax({
        type: 'GET',
        url: `/api/v1/users/info`,
        contentType: 'application/json',
    })
        .done(function (res, status, xhr) {
            const username = res.username;
            const isAdmin = !!res.admin;

            if (!username) {
                window.location.href = '/api/v1/auth/error';
                return;
            }

            $('#username').text(username);
            if (isAdmin) {
                $('#admin').text(true);
                showProduct(true);
            } else {
                showProduct();
            }
        })
        .fail(function (jqXHR, textStatus) {
            logout();
        });
});