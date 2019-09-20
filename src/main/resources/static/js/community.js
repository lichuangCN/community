/**
 * 回复问题
 */
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2Target(questionId, 1, content);
}

/**
 * 回复评论
 * @param elem
 */
function comment(elem) {

    var commentId = elem.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2Target(commentId, 2, content);
}

/**
 * 发送评论内容
 * @param targetId
 * @param type
 * @param content
 */
function comment2Target(targetId, type, content) {
    if (!content) {
        alert("内容不能为空~");
        return;
    }
    $.ajax({
        type: 'post',
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
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
    });
}

/**
 * 展开二级评论
 */
function collapseComments(elem) {
    // 一级评论的id
    var id = elem.getAttribute("data-id");
    var comments = $("#comment-" + id);

    // 获取二级评论展开状态
    if (elem.getAttribute("data-collapse")) {
        // 隐藏二级评论
        comments.removeClass("in");
        elem.removeAttribute("data-collapse");
        elem.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            elem.setAttribute("data-collapse", "in");
            elem.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                if (!data.data) {
                    //展开二级评论
                    comments.addClass("in");
                    // 标记二级评论展开状态
                    elem.setAttribute("data-collapse", "in");
                    elem.classList.add("active");
                } else {
                    $.each(data.data.reverse(), function (index, comment) {
                        var mediaLeftElement = $("<div/>", {
                            "class": "media-left"
                        }).append($("<img/>", {
                            "class": "media-object img-rounded",
                            "src": comment.user.avatarUrl
                        }));

                        var mediaBodyElement = $("<div/>", {
                            "class": "media-body"
                        }).append($("<h5/>", {
                            "class": "media-heading",
                            "html": comment.user.name
                        })).append($("<div/>", {
                            "class": "comment-area",
                            "html": comment.content
                        })).append($("<div/>", {
                            "class": "menu"
                        }).append($("<span/>", {
                            "class": "pull-right",
                            "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                        })));

                        var separator = $("<div/>", {}).append("<hr>", {
                            "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-sp"
                        });

                        var mediaElement = $("<div/>", {
                            "class": "media"
                        }).append(mediaLeftElement).append(mediaBodyElement);

                        var commentElement = $("<div/>", {
                            "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                        }).append(mediaElement).append(separator);

                        subCommentContainer.prepend(commentElement);
                    });
                    //展开二级评论
                    comments.addClass("in");
                    // 标记二级评论展开状态
                    elem.setAttribute("data-collapse", "in");
                    elem.classList.add("active");
                }
            });
        }
    }
}

/**
 * 返回顶部
 */
$(function () {
    $(function () {
        $(window).scroll(function () {
            if ($(window).scrollTop() > 100) {
                $("#go-top").fadeIn(1000);//一秒渐入动画
            } else {
                $("#go-top").fadeOut(1000);//一秒渐隐动画
            }
        });
        $("#go-top").click(function () {
            $('body,html').animate({scrollTop: 0}, 1000);
        });
    });
});

/**
 * 标签输入栏
 * @param value
 */
function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}

/**
 * 显示标签框
 */
function showSelectTag() {
    $("#select-tag").show();
}