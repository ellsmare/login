/*

// 개인정보 수정

    $.ajax({
        type: 'GET',
        url: `/api/v1/users/info`,
        contentType: 'application/json',
    })
        .done(function (res, status, xhr) {
            const username = res.username;
            const isAdmin = !!res.admin;

            if (!username) {
                window.location.href = host + '/api/v1';
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
});*/
