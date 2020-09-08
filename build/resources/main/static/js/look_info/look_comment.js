// 처음 게시글 들어 갈때 최신 댓글 10개 받아 오기 위해 0을보냄
// 그 이후부턴 마지막 값에 부모키 보냄
let comment_id = 0;
let scrollTime = true;
let fk_look_num_Look_look_num;
let userId;
let userNickname;

let look_comment = document.querySelector("#js-look_comment"),
    js_comment_id = look_comment.querySelector("#js-comment_id"),
    js_comment_sender_id = look_comment.querySelector("#js-comment_sender_id"),
    js_profile_img = look_comment.querySelector("#js-profile_img");

let comment_list = document.querySelector("#js-comment-list");

console.log(js_comment_sender_id);
console.log($("#js-comment-list"));
console.log($("#js-look_comment"));


let result = {
    comment_id:19,
    comment_sender_id: "dbs1501189",
    comment_sender_name: "무민97",
    fk_look_num_Look_look_num: 55,
    comment_content: "로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘",
    parents: 0, //대댓글 아니므로 값을 0줌
};

function commentReady(look_num, userid, usernickname) {
    //게시글 기본키 가져 오기
    fk_look_num_Look_look_num = look_num;
    //세션 아이디 값 가져 오기
    userId = userid;
    //세션 닉네임 값 가져 오기
    userNickname = usernickname;

    // //처음 화면 들어 갔을 떄 댓글 데이터 10개 가져 오기
    init();
    init();

    // $(window).scroll(function () {   //스크롤 감지 이벤트
    //     let scroll = $(document).scrollTop(); //현재 스크롤 값
    //     let documentHeight = $(document).height();//문서 전체높이
    //     let windowHeight = window.innerHeight; //윈도우 높이


    //     //윈도우 높이에 스크롤값을 계속더해서 문서 전체 길이에서 50 px 앞에 스크롤이 왔을때 데이터 불러옴
    //     if ((windowHeight + scroll) >= documentHeight - 50) {
    //         if (scrollTime == true) {
    //             //다음 댓글 10개 가져옴
    //             init();
    //         }
    //     }
    // })

    $("#js-look_comment").first().remove();
}


function aa(result) {
    let ss = $("#js-look_comment").clone();

    $("#js-look_comment").find("#js-profile_img").attr("src", `displayMthumbnail/${result.comment_sender_id}`); 
    $("#js-look_comment").find("#js-other_people_name").html(result.comment_sender_name);
    $("#js-look_comment").find("#js-comment_id").val(result.comment_id);
    $("#js-look_comment").find("#js-comment_sender_id").val(result.comment_sender_id);
    $("#js-look_comment").find("#js-view_comment_textarea").html(result.comment_content);
    $("#js-comment-list").prepend(ss);
}

function init() {
    for (let i = 0; i < 10; i++) {
        result.comment_id +=1;
        result.comment_sender_id +=1;
        aa(result);
    }
}



// function init() {

//     let result = {
//         comment_sender_id: "moomin97",
//         comment_sender_name: "무민97",
//         fk_look_num_Look_look_num: 55,
//         comment_content: "로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘로렘입슘",
//         parents: 0, //대댓글 아니므로 값을 0줌
//     };

//     let html = "";
//     html += '<li class=\"look_comment\">';
//     html += '<div class=\"look_comment_wrap\">';
//     html = commentHTML(result, html);
//     html += '</div>';
//     // html +=  <div> 대댓글 </div>
//     html += '</li>';
//     $("#js-comment-list").prepend(html); //ul태그안에 시작부분에 삽입.
// }
// init();
// init();
// init();
// init();
// init();
// init();
// init();







