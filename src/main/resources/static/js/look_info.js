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

function previewImage(targetObj, View_area, event) { //(this,'View_area')
    preview = document.getElementById(View_area); //div id
    const ua = window.navigator.userAgent;
    fileBuffer = Array.prototype.slice.call(event.target.files);

    storeBuffer();


    //ie일때(IE8 이하에서만 작동)
    if (ua.indexOf("MSIE") > -1) {
        targetObj.select();
        try {
            let src = document.selection.createRange().text; // get file full path(IE9, IE10에서 사용 불가)
            let ie_preview_error = document.getElementById("ie_preview_error_" + View_area);


            if (ie_preview_error) {
                preview.removeChild(ie_preview_error); //error가 있으면 delete
            }

            let img = document.getElementById(View_area); //이미지가 뿌려질 곳

            //이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
            img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + src + "', sizingMethod='scale')";
        } catch (e) {
            if (!document.getElementById("ie_preview_error_" + View_area)) {
                let info = document.createElement("<p>");
                info.id = "ie_preview_error_" + View_area;
                info.innerHTML = e.name;
                preview.insertBefore(info, null);
            }
        }
        //ie가 아닐때(크롬, 사파리, FF)
    } else {

        files = targetObj.files;
        console.log("파일s", files);
        for (let i = 0; i < fileBuffer.length; i++) {
            let file = fileBuffer[i];
            console.log("파일", file);
            console.log("this.value", targetObj);
            let imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
            if (!file.type.match(imageType))
                continue;
            // let prevImg = document.getElementById("prev_" + View_area); //이전에 미리보기가 있다면 삭제
            // if (prevImg) {
            //     preview.removeChild(prevImg);
            // }


            flick_panel = document.createElement('div');
            img = document.createElement("img");
            //태그 생성 부분

            console.log("View_area", preview);
            console.log("flick_panel", flick_panel);

            preview.appendChild(flick_panel);
            flick_panel.appendChild(img); //flick_panel의자식으로 img태그를 연결
            //태그 연결 부분

            preview.classList.add(LOOK_FLICK_CAMERA);

            flick_panel.classList.add(FLICK_PANEL);
            flick_panel.className = "flick_panel" + " s" + i;
            flick_panel.style.left = i * 305;
            flick_panel.id = "flick_panel";

            img.classList.add(LOOK_IMG_FILE);
            img.id = "prev_" + View_area;
            img.file = file;
            img.style.width = '100%';
            img.style.height = '100%';
            //태그 속성 적용 부분



            console.log(flick_panel);
            // document.getElementsByClassName('flick_panel').files[length];
            // preview.appendChild(createFlick_panel(img));

            if (window.FileReader) { // FireFox, Chrome, Opera 확인.
                let reader = new FileReader();
                reader.onloadend = (function (aImg) {
                    return function (e) {
                        aImg.src = e.target.result;
                    };
                })(img);
                reader.readAsDataURL(file);
            } else { // safari is not supported FileReader
                //alert('not supported FileReader');
                if (!document.getElementById("sfr_preview_error_"
                    + View_area)) {
                    let info = document.createElement("p");
                    info.id = "sfr_preview_error_" + View_area;
                    info.innerHTML = "not supported FileReader";
                    preview.insertBefore(info, null);
                }
            }
            console.log("반복횟수", i);
        }
    }
}

function infoSlide(result) {

}

function lookReady(lookNum) {
    let lookNumer = lookNum;
    init(lookNumer);
}

function startAjax(lookNumer) {
    mainScrollTime = false;

    $.ajax({
        url: `/displayInthumbnail/${lookNumer}`,
        type: "GET",
        success: function (result) {
            infoSlide(result);
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
}

function init(lookNumer) {
    startAjax(lookNumer);
}

init();