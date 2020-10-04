
//태그 불러오기
const uploadDiv = document.querySelector("#js-uploadDiv"),
    uploadButton = uploadDiv.querySelector("input"), //파일선택 버튼.
    initButton = uploadDiv.querySelector("button"),
    inputElement = document.querySelector('input[type="file"]');

let fileBuffer = [];
let pond = 0;
//현재 게시글 번호
let look_number = 0;

//무드의 갯수
let moodMany = 9;

let moodList = [];

//JSP파일 서버변수 호출함수
function getLooknum(lookNum) {
    look_number = lookNum;
}

//from JSP onClick이벤트 호출
function modifiy() {
    let look_num = document.getElementsByName("look_num")[0].value;
    let fk_userid_user_userid = document.getElementsByName("fk_userid_user_userid")[0].value;
    let title = document.getElementsByName("title")[0].value
    let season = document.getElementsByName("season");
    let look_public = document.getElementsByName("look_public");
    let memo = document.getElementsByName("memo")[0].value
    let moodTag = document.getElementsByName("mood");
    let seasonCheck = season_check(season);
    let look_publicCheck = look_public_check(look_public);
    let empty = '';
    let list = ["제목", "계절", "공개여부", "태그", "메모"];
    let formList = [title, seasonCheck, look_publicCheck, /*tag,*/ memo];

    for (let i = 0; i < formList.length; i++) {
        if (formList[i] == false) { //자바 스크립트는 null 을 false 로인식
            empty += list[i] + " ";
        }
    }

    let BoardDto = {
        look_num: look_num,
        title: title,
        season: seasonCheck,
        fk_userid_user_userid: fk_userid_user_userid,
        look_public: look_publicCheck,
        memo: memo
    }

    //서버에서 받아왔던 무드, 배열 비우고 새로 체크된것 넣음.
    moodList.splice(0, moodList.length);
    let mood_value = () => {
        for (let i = 0; i < moodTag.length; i++) {
            if (document.getElementsByName("mood")[i].checked === true) {
                //check 박스 체크된것만 배열안으로 객체넣고
                moodList.push(
                    {
                        look_num: look_number,
                        mood: document.getElementsByName("mood")[i].value
                    }
                    //push(new MoodDto(look_num,mood)), 푸쉬할떄마다 새로운 객체
                );
            }
        }
    }
    mood_value();

    let formData = new FormData();
    formData.append("BoardDto", new Blob([JSON.stringify(BoardDto)], { type: "application/json" }));
    if (moodList.length > 0) {
        formData.append("MoodDto", new Blob([JSON.stringify(moodList)], { type: "application/json" }));
    }

    if (empty == '') {
        if (fileBuffer == undefined) {
            let data = $('[name=writeForm]').serialize();
            //글수정하기
            $.ajax({
                url: "/looks",
                type: "PUT", //데이터 전달방식
                data: formData, //전송객체
                dataType: false,
                processData: false,
                contentType: false,
                success: function (result, textStatus, jqxHR) {
                    if (jqxHR.status == 201) {
                        swal('', '게시글 수정을 하였습니다', 'success');
                        //수정 성공하면 내가수정한 게시글화면으로 이동
                        setTimeout(function () { location.href = "/look?look_num=" + result.look_num; }, 2000);
                    }
                },
                error: function (error) {
                    //서버오류 500  찾는 자료없음 404  권한없음  401
                    if (error.status == 404) {
                        swal('수정 자료가 없습니다', '', 'error');
                    } else if (error.status == 401) {
                        swal('접근 권한이 없습니다', '', 'error');
                    } else if (error.status == 500) {
                        swal('서버 오류 관리자에게 문의 하세요', '', 'error');
                    }
                }
            })
        } else {
            let data = $('[name=writeForm]').serialize();
            function appendFile() {
                for (i = 0; i < fileBuffer.length; i++) {
                    formData.append("files", fileBuffer[i]);
                }

            }
            appendFile();

            //글수정하기
            $.ajax({
                url: "/looks",
                type: "PUT", //데이터 전달방식
                data: formData, //전송객체
                dataType: false,
                processData: false,
                // contentType: 'multipart/form-data',
                contentType: false,
                success: function (result, textStatus, jqxHR) {
                    if (jqxHR.status == 201) {
                        swal('', '게시글 수정을 하였습니다', 'success');
                        //수정 성공하면 내가수정한 게시글화면으로 이동
                        setTimeout(function () { location.href = "/look?look_num=" + result.look_num; }, 2000);
                    }
                },
                error: function (error) {
                    //서버오류 500  찾는 자료없음 404  권한없음  401
                    if (error.status == 404) {
                        swal('수정 자료가 없습니다', '', 'error');
                    } else if (error.status == 401) {
                        swal('접근 권한이 없습니다', '', 'error');
                    } else if (error.status == 500) {
                        swal('서버 오류 관리자에게 문의 하세요', '', 'error');
                    }
                }
            })
        }
    } else {
        //입력안한 부분있으면 다시작성하게함
        swal('', empty + ' 을 다시 입력하세요', '');
    }

}

function season_check(season) {
    let result = '';
    for (let i = 0; i < season.length; i++) {

        if (document.getElementsByName("season")[i].checked == true) {
            //check 박스 체크 헀는지 확인
            result += document.getElementsByName("season")[i].value + " ";
        }
    }
    return result;
}

function look_public_check(look_public) {
    let result = '';
    for (let i = 0; i < look_public.length; i++) {

        if (document.getElementsByName("look_public")[i].checked == true) {
            //radio 박스 체크 헀는지 확인
            result += document.getElementsByName("look_public")[i].value;
        }
    }
    return result;
}


//수정전 season 이랑 look_public 값 내가 원래 선택한 값 보여주기
function seasonLook_publicResult(season, look_public) {
    // 계절 하나씩 자르기
    let seasonString = season.split(' ');

    for (let i = 0; i < seasonString.length; i++) {
        //자른 계절 문자열 차례대로 반복해서 같은 값이있으면 check로 바꾸기
        for (let y = 0; y < 4; y++) {
            if (seasonString[i] == document.getElementsByName("season")[y].value) {
                document.getElementsByName("season")[y].checked = true;
            }
        }
    }

    if (look_public == 1) {  //1공개 2비공개
        document.getElementsByName("look_public")[0].checked = true;
    } else {
        document.getElementsByName("look_public")[1].checked = true;
    }
}
//JSP
function moodLook_Result(mood) {
    moodList.push(mood)
}
function setMood() {
    for (let i = 0; i < moodList.length; i++) {
        for (moodMany = 0; moodMany < 9; moodMany++) { //const타입 불가
            if (moodList[i] === document.getElementsByName("mood")[moodMany].value) {
                document.getElementsByName("mood")[moodMany].checked = true;
            }
        }

    }
}
//end of from JSP onClick이벤트 호출

//게시글에 있는 썸네일이미지 GET으로 받아오기.
function getThumbnail(lookNumber) {
    $.ajax({
        url: `/displayInthumbnail/${lookNumber}`,
        type: "GET",
        success: function (result) {
            if (result.length > 0) {
                createFilePond();
                putImage(result);
                filePondListner();
            } else {
                createFilePond();
                filePondListner();
            }
        },
        error: function (error) {
            //서버오류 500  찾는 자료없음 404  권한없음  401
            if (error.status == 404) {
                swal('찾는 자료가 없습니다', '', 'error');
            } else if (error.status == 401) {
                swal('접근 권한이 없습니다', '', 'error');
            } else if (error.status == 500) {
                createFilePond();
                filePondListner();
                swal('서버 오류 관리자에게 문의 하세요', '', 'error');
            }
        }
    })
}

// Register the plugin with FilePond
function createFilePond() {
    FilePond.registerPlugin(
        // FilePondPluginFileMetadata,
        // FilePondPluginImageCrop,
        FilePondPluginImagePreview,
        FilePondPluginFileEncode
    );
    // Create the FilePond instance
    pond = FilePond.create(inputElement, {
        allowMultiple: true,
        allowReorder: true
    });
}

let putImage = (result) => {
    for (let i = 0; i < result.length; i++) {
        pond.addFile(`data:image/jpg;base64, ${result[i]}`);
        // e.detail.items[i].filename = result[i].name;
    }
}

function filePondListner() {
    const filepondRoot = document.querySelector('.filepond--root');

    filepondRoot.addEventListener('FilePond:updatefiles', e => {
        fileBuffer.splice(0, fileBuffer.length);

        for (let i = 0; i < e.detail.items.length; i++) {
            fileBuffer[i] = dataURLtoFile(e.detail.items[i].getFileEncodeDataURL(), e.detail.items[i].filename);
        }

    });
}
//end of 파일폰드

//base64 to File객체
const dataURLtoFile = (dataurl, fileName) => {

    let arr = dataurl.split(','),
        mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]),
        n = bstr.length,
        u8arr = new Uint8Array(n);

    while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
    }

    return new File([u8arr], fileName, { type: mime });
}


//from JSP 서버변수 호출함수
function lookReady(lookNum) {
    let lookNumber = lookNum;
    init(lookNumber);
}

function init(lookNumber) {
    getThumbnail(lookNumber);
    setMood();

}

init();




