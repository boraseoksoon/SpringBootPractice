/* catch delete click event */
$("a.link-delete-article").click(function(e) {
    e.preventDefault();

    var deleteBtn = $(this);
    var url = deleteBtn.attr("href");
    console.log("url : " + url);

    $.ajax({
        type : 'delete',
        url : url,
        dataType : 'json',
        error : function (xhr, status) {
            console.log("error");
        },
        success : function (data, status) {
            console.log(data);
            if (data.valid) {
                deleteBtn.closest("article").remove();
            } else {
                alert(data.errorMessage);
            }
        }
    });
});

/*
$("a.link-delete-article").click(deleteAnswer);

function deleteAnswer(e) {
    // this below was not working
    // because of simply cookie
    // though source was perfectly same.
    e.preventDefault();

    var deleteBtn = $(this);
    var url = deleteBtn.attr("href");
    console.log("url : " + url);

    $.ajax({
        type : 'delete',
        url : url,
        dataType : 'json',
        error : function (xhr, status) {
            console.log("error");
        },
        success : function (data, status) {
            console.log(data);
            if (data.valid) {
                deleteBtn.closest("article").remove();
            } else {
                alert(data.errorMessage);
            }
        }
    });
}
*/

$(".answer-write input[type=submit]").click(addAnswer);

function addAnswer(e) {
  e.preventDefault();
  console.log(e);

  var queryString = $(".answer-write").serialize()
  console.log("queryString : " + queryString);

  var url = $(".answer-write").attr("action");
  console.log("url : " + url);

  $.ajax(
      {
        type : 'post',
        url : url,
        data : queryString,
        dataType : 'json',
        error : onError,
        success : onSuccess
      }
  );
}

function onError() {
  console.log("AJAX onError")
}

function onSuccess(data, status) {
  console.log("AJAX Success")
  console.log("[" + status + "]" + " : " + data.toString());

  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(data.writer.userId, data.formattedCreatedDate, data.contents, data.question.id, data.id);
  $(".qna-comment-slipp-articles").prepend(template);

  $("textarea[name=contents]").val("");
}

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};