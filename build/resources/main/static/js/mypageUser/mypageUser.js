let changeUser = document.querySelector("#js-changeUser");
let deleteUser = document.querySelector("#js-deleteUser");
let messagePen = document.querySelector("#js-messagePen");
let messageWrap = document.querySelector("#js-message"),
    messageSpan = messageWrap.querySelector("span");

let jsp_nickname = ``;


//JSP파일 서버변수 호출함수
function myUsernickname(usernickname) {
    console.log(usernickname);
    jsp_nickname = usernickname;
}

function startMessage(event) {
    modifyMessage();
    // ajaxMessage();
}

function ajaxMessage() {
    let content = messageWrap.querySelector("#js-message_textarea").value;
/*
    console.log(content);
    let User = {
        message: content
    };
    let UserDto = JSON.stringify(User);
    alert(UserDto);
*/
    $.ajax({
        url: "/information/"+content,
        type: "put", //데이터 전달방식
        contentType: "charset=utf-8", //json 형태로 댓글보내기
        success: function (result, textStatus, jqxHR) {
           // if (jqxHR.status == 201) {
                console.log(result);
                $("#js-message").children().remove();
               // swal('', result);
                let html = "";
                html += `<span class="my_message">${result}</span>`
                $("#js-message").append(html);
          //  }
        },
        error: function (error) {
            //서버오류 500  권한없음  401
            if (error.status == 401) {
                $("#js-message").children().remove();
                swal('접근 권한이 없습니다', '', 'error');
            } else if (error.status == 500) {
                $("#js-message").children().remove();
                swal('서버 오류 관리자에게 문의 하세요', '', 'error');
            }
        }
    })
}

function modifyMessage() {
    messageWrap.removeChild(messageSpan);

    let html = "";
    html += `<div class="comment_center">`;
    html += `<div class="comment_center_textarea">`;
    html += `<textarea id="js-message_textarea" class="view_comment_textarea" placeholder="수정할 내용을 입력하세요" name="comment_content"></textarea>`;
    html += `</div>`; //<div class="comment_center_textarea">

    html += `<div id="js-message_chg_btn" class="comment_center_footer">`;
    html += '<button  class="comment_change_button" value="2" type="button" >취소</button>';
    html += '<button  class="comment_change_button" value="1" type="button" >저장</button>';
    html += `</div>`;//<div class="comment_center_footer">
    html += `</div>`;//<div class="comment_center">

    $("#js-message").append(html);

    let changeBtn = messageWrap.querySelector("#js-message_chg_btn");
    //부모태그로 이벤트하면 여러개 자식버튼 바인딩가능
    changeBtn.addEventListener("click", ajaxMessage);
}

function init() {
    messagePen.addEventListener("click", startMessage);
}

init();