//태그 불러오기
const uploadDiv = document.querySelector("#js-uploadDiv"),
    uploadButton = uploadDiv.querySelector("input"), //파일선택 버튼.
    initButton = uploadDiv.querySelector("button"),
    inputElement = document.querySelector('input[type="file"]');


//CSS클래스명 불러오기
const FLICK_PANEL = "flick_panel";
const LOOK_FLICK_CAMERA = "look_flick_camera";
const LOOK_IMG_FILE = "look_img_file";

//slide();
let slideList = 0
    , slideContents = 0
    , slideBtnNext = 0
    , slideBtnPrev = 0
    , slideLen = 0
    , slideWidth = 0
    , slideSpeed = 0
    , startNum = 0;

//previewImage();
let preview = 0;
let flick_panel = 0;
let img = 0;
let files = 0;
let fileBuffer = 0;  //formdData에 날릴 배열, input태그의 배열에 들어간 파일을 보내지 않음.
// let fflick_panel = document.querySelector("#fflick_panel");

let ppreview = 0;
let fflick_panel = 0;
let iimg = 0;

let slideCounter = 0;

let initBuffer = 0;


//
// 전송실행
//
const saveDiv = document.querySelector(".save"),
    saveButton = saveDiv.querySelector("button") //전송버튼

function lookWrite() {

    if (sessionStorage.getItem("userid") == null) {
        swal('', '로그인을 먼저하세요', '');
        return
    }

    let title = document.getElementsByName("title")[0].value
    let season = document.getElementsByName("season");
    let look_public = document.getElementsByName("look_public");
    let tag = document.getElementsByName("tag")[0].value
    let memo = document.getElementsByName("memo")[0].value
    let seasonCheck = season_check(season);
    let look_publicCheck = look_public_check(look_public);

    let empty = '';
    let list = ["제목", "계절", "공개여부", "태그", "메모"];
    let formList = [title, seasonCheck, look_publicCheck, tag, memo];

    for (let i = 0; i < formList.length; i++) {
        if (formList[i] == false) { //자바 스크립트는 null 을 false 로인식
            empty += list[i] + " ";
        }
    }

    let BoardDto = {
        title: title,
        season: seasonCheck,
        look_public: look_publicCheck,
        tag: tag,
        memo: memo
    }

    let formData = new FormData();
    formData.append("BoardDto", JSON.stringify(BoardDto));

    if (empty == '') {
        if (fileBuffer == undefined) {
            let data = $('[name=writeForm]').serialize();
            $.ajax({
                url: "/looks",
                type: "POST", //데이터 전달방식
                data: formData,
                dataType: false,
                processData: false,
                // contentType: 'multipart/form-data',
                contentType: false,
                success: function (result, textStatus, jqxHR) {
                    if (jqxHR.status == 201) {
                        alert(JSON.stringify(formData));
                        swal('', '게시글 등록을 하였습니다', 'success');
                        //등록 성공하면 내가등록한 게시글화면으로 이동
                        setTimeout(function () { location.href = "/look?look_num=" + result.look_num; }, 2000);
                    }
                },
                error: function (error) {
                    //서버오류 500  찾는 자료없음 404  권한없음  401
                    if (error.status == 404) {
                        swal('찾는 자료가 없습니다', '', 'error');
                    } else if (error.status == 401) {
                        swal('접근 권한이 없습니다', '', 'error');
                    } else if (error.status == 500) {
                        swal('서버 오류 관리자에게 문의 하세요', '', 'error');
                    }
                }
            })
        } else {
            function appendFile() {
                for (i = 0; i < fileBuffer.length; i++) {
                    formData.append("files", fileBuffer[i]);
                }

            }
            appendFile();

            $.ajax({
                url: "/looks",
                type: "post",
                data: formData,
                dataType: false,
                processData: false,
                // contentType: 'multipart/form-data',
                contentType: false,
                success: function (result, textStatus, jqxHR) {
                    if (jqxHR.status == 201) {
                        alert(JSON.stringify(formData));
                        swal('', '게시글 등록을 하였습니다', 'success');
                        //등록 성공하면 내가등록한 게시글화면으로 이동
                        setTimeout(function () { location.href = "/look?look_num=" + result.look_num; }, 2000);
                    }
                },
                error: function (error) {
                    //서버오류 500  찾는 자료없음 404  권한없음  401
                    if (error.status == 404) {
                        swal('찾는 자료가 없습니다', '', 'error');
                    } else if (error.status == 401) {
                        swal('접근 권한이 없습니다', '', 'error');
                    } else if (error.status == 500) {
                        swal('서버 오류 관리자에게 문의 하세요', '', 'error');
                    }
                }
            });

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
//
// end of 전송실행
//

// Register the plugin with FilePond

function filePond() {

    FilePond.registerPlugin(
        // FilePondPluginFileMetadata,
        // FilePondPluginImageCrop,
        FilePondPluginImagePreview,
        FilePondPluginFileEncode
    );

    // Get a reference to the file input element

    // Create the FilePond instance
    const pond = FilePond.create(inputElement, {
        allowMultiple: true,
        allowReorder: true
    });

    pond.on('addfile', (error, file) => {
        if (error) {
            console.log('Oh no');
            return;
        }
        function fileBufferUpdate(file) {
            console.log(pond.getFiles(0).getFileEncodeBase64String());
        }fileBufferUpdate(file);
        
        // console.log(pond.getFiles().length);
        // console.log(pond.getFiles(0));

        // for(let i=0; i<getFiles.length)
        // console.log('File added', file);
    })

}

function init() {
    // if (slideCounter < 1) { //파일선택 버튼 누른 횟수
    //     //초기 진입할때
    //     printInit();
    //     //또는 초기화 누르면 카운터는 다시0이 되고 화면초기화
    // }
    filePond();
    // inputElement.addEventListener('change', filePond); //파일선택 이벤트
    // initButton.addEventListener('click', resetImg); //초기화 이벤트

    // fileBuffer =Array.prototype.slice.call(event.target.files);
    // saveButton.addEventListener('click', lookWrite); //폼전송 부분



}

init();