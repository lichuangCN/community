function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    if (!content) {
        alert("内容不能为空~");
        return;
    }
    $.ajax({
        type: 'post',
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        dataType: 'json',
        success: function (response) {
            if (response.code == 200) {
                // 评论成功，刷新页面
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    // 未登录
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        // 登录页面
                        window.open("https://github.com/login/oauth/authorize?client_id=009778391ff7a0e735d1&redirect_uri=http://localhost:8088/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        }
    })
}