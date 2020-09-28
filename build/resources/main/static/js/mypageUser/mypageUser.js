let my_img = document.querySelector("#js-my_img");
let myInfo = document.querySelector("#js-myInfo");
let modify_top = document.querySelector("#js-modify_top");

let follower = document.querySelector("#js-follower");
let following = document.querySelector("#js-following");
let board = document.querySelector("#js-board");

let messagePen = document.querySelector("#js-messagePen");
let messageWrap = document.querySelector("#js-message"),
    messageSpan = messageWrap.querySelector("span");

let content = ""; //상태메시지
let jsp_nickname = "";
let mypageInfo = "";//요청한 유저정보 담는변수


//JSP파일 서버변수 호출함수
function myUsernickname(usernickname) {
    jsp_nickname = usernickname;
    console.log(jsp_nickname);

    init();
}

function startMessage() {
    modifyMessage();
}

function putMessage() {
    content = messageWrap.querySelector("#js-message_textarea").value;
    console.log(content)
    if (content === "") {
        content = "NULL";
    }

    $.ajax({
        url: `/information/${content}`,
        type: "put", //데이터 전달방식
        contentType: "charset=utf-8", //json 형태로 댓글보내기
        success: function (result, textStatus, jqxHR) {
            console.log(result);
            $("#js-message").children().remove();

            let html = "";
            html += `<span class="my_message">${result}</span>`
            $("#js-message").append(html);
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

function getMypageinfo() {
    $.ajax({
        url: `/information/${jsp_nickname}`,
        type: "get", //데이터 전달방식
        // dataType: "json", //json 형태로 받기
        success: function (result, textStatus, jqxHR) {
            console.log(result);
            mypageInfo = result;

            gender();
            content = `${mypageInfo.user.message}`;
            function setMypageinfo() {
                my_img.src = `/displayGthumbnail/${mypageInfo.user.userid}`;
                myInfo.innerHTML = ` ${mypageInfo.user.nickname} &nbsp`;

                if (mypageInfo.user.privacy === 1) { //공개
                    myInfo.innerHTML = `${myInfo.innerHTML} 
                        ${mypageInfo.user.height}cm &nbsp
                        ${mypageInfo.user.weight}kg &nbsp
                        ${mypageInfo.user.sex}
                        `;
                    modify_top.innerHTML = `${mypageInfo.user.email}`;
                }
                messageSpan.innerHTML = `${mypageInfo.user.message}`;

                follower.innerHTML = `${mypageInfo.count.follower}`;
                following.innerHTML = `${mypageInfo.count.following}`;
                board.innerHTML = `${mypageInfo.count.board}`;
            }
            setMypageinfo();
        },
        error: function (error) {
            // startMessage();
            //서버오류 500  권한없음  401
            if (error.status == 401) {
                swal('접근 권한이 없습니다', '', 'error');
            } else if (error.status == 500) {
                swal('서버 오류 관리자에게 문의 하세요', '', 'error');
            }
        }
    })
}

function gender() {
    if (mypageInfo.user.sex === 0) {
        mypageInfo.user.sex = "여성"
    } else {
        mypageInfo.user.sex = "남성"
    }
}

//startMessage() =>
function modifyMessage() {
    messageWrap = document.querySelector("#js-message"),
        messageSpan = messageWrap.querySelector("span");
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

    let submit = messageWrap.querySelector('button[value="1"]'); //저장
    let cancel = messageWrap.querySelector('button[value="2"]'); //취소

    submit.addEventListener("click", putMessage);
    cancel.addEventListener("click", function () {
        $("#js-message").children().remove();

        let html = "";
        html += `<span class="my_message">${content}</span>`
        $("#js-message").append(html);
    });

}

function init() {
    getMypageinfo(); //유저정보 요청
    messagePen.addEventListener("click", startMessage); //상태메시지 수정
}

