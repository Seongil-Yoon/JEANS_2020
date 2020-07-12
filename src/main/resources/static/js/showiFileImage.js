//태그 불러오기
const uploadDiv = document.querySelector("#js-uploadDiv"),
    uploadButton = uploadDiv.querySelector("input"), //파일선택 버튼.
    initButton = uploadDiv.querySelector("button");

//CSS클래스명 불러오기
const FLICK_PANEL = "flick_panel";
const LOOK_FLICK_CAMERA = "look_flick_camera";

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
let fileBuffer = 0; //파일목록 저장배열
// let fflick_panel = document.querySelector("#fflick_panel");

let ppreview = 0;
let fflick_panel = 0;
let iimg = 0;

function previewImage(targetObj, View_area, event) { //(this,'View_area')
    preview = document.getElementById(View_area); //div id
    const ua = window.navigator.userAgent;
    fileBuffer = Array.from(event.target.files);

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

            img.classList.add("look_img_file");
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

// function createFlick_panel(img) {
//     let flick_panel = document.createElement('div');
//     flick_panel.classList.add(FLICK_PANEL);
//     flick_panel.className = "flick_panel";
//     flick_panel.appendChild(img);

//     document.getElementsByClassName('flick_panel').files[length];

//     console.log(flick_panel);
//     return flick_panel;
// }


function excuteSlide() {

    slideList = document.querySelector('.look_flick_camera');  // Slide parent dom
    slideContents = document.querySelectorAll('.flick_panel');  // each slide dom
    slideBtnNext = document.querySelector('#look_slide_button_right'); // next button
    slideBtnPrev = document.querySelector('#look_slide_button_left'); // prev button
    slideLen = slideContents.length;  // slide length
    slideWidth = 300; // slide width
    slideSpeed = 300; // slide speed
    startNum = 0; // initial slide index (0 ~ 4)

    console.log(slideLen);
    slideList.style.width = slideWidth * (slideLen + 2) + "px";

    // Copy first and last slide
    let firstChild = slideList.firstElementChild;
    let lastChild = slideList.lastElementChild;
    let clonedFirst = firstChild.cloneNode(true);
    let clonedLast = lastChild.cloneNode(true);

    // Add copied Slides
    slideList.appendChild(clonedFirst);
    slideList.insertBefore(clonedLast, slideList.firstElementChild);

    slideList.style.transform = "translate3d(-" + (slideWidth * (startNum + 1)) + "px, 0px, 0px)";

    let curIndex = startNum; // current slide index (except copied slide)
    let curSlide = slideContents[curIndex]; // current slide dom
    curSlide.classList.add('slide_active');


    /** Next Button Event */
    slideBtnNext.addEventListener('click', function () {
        if (curIndex <= slideLen - 1) {
            slideList.style.transition = slideSpeed + "ms";
            slideList.style.transform = "translate3d(-" + (slideWidth * (curIndex + 2)) + "px, 0px, 0px)";
        }
        if (curIndex === slideLen - 1) {
            setTimeout(function () {
                slideList.style.transition = "0ms";
                slideList.style.transform = "translate3d(-" + slideWidth + "px, 0px, 0px)";
            }, slideSpeed);
            curIndex = -1;
        }
        curSlide.classList.remove('slide_active');
        curSlide = slideContents[++curIndex];
        curSlide.classList.add('slide_active');
    });

    /** Prev Button Event */
    slideBtnPrev.addEventListener('click', function () {
        if (curIndex >= 0) {
            slideList.style.transition = slideSpeed + "ms";
            slideList.style.transform = "translate3d(-" + (slideWidth * curIndex) + "px, 0px, 0px)";
        }
        if (curIndex === 0) {
            setTimeout(function () {
                slideList.style.transition = "0ms";
                slideList.style.transform = "translate3d(-" + (slideWidth * slideLen) + "px, 0px, 0px)";
            }, slideSpeed);
            curIndex = slideLen;
        }
        curSlide.classList.remove('slide_active');
        curSlide = slideContents[--curIndex];
        curSlide.classList.add('slide_active');
    });
}

function slide(event) {
    entryInit();
    if (slideLen < 1) {
        console.log("slide()진입");
        previewImage(this, "View_area", event); //슬라이드화면 생성전에 업로드할 이미지 생성
        console.log("previewImage 후");

        excuteSlide(event);

    } else {
        // this.files=null;
        alert("초기화 해주세요.");
    }

}

function resetImg() {
    while (preview.firstChild) {
        preview.removeChild(preview.firstChild);
    }
    slideLen = 0;

    if (fileBuffer.length >= 1) {
        fileBuffer.splice(0, fileBuffer.length); //파일버퍼 비우기
        console.log(fileBuffer);
    }
    printInit();

}

function entryInit() {
    while (preview.firstChild) {
        preview.removeChild(preview.firstChild);
    }
    while (ppreview.firstChild) {
        ppreview.removeChild(ppreview.firstChild);
    }
    slideLen = 0;

    if (fileBuffer.length >= 1) {
        fileBuffer.splice(0, fileBuffer.length); //파일버퍼 비우기
        console.log(fileBuffer);
    }

}

function printInit() {
    ppreview = document.querySelector("#View_area"); //div id
    fflick_panel = document.createElement('div');
    iimg = document.createElement("img");
    //태그 생성 부분

    console.log("View_area", ppreview);
    console.log("fflick_panel", fflick_panel);

    ppreview.appendChild(fflick_panel);
    fflick_panel.appendChild(iimg); //fflick_panel의자식으로 iimg태그를 연결
    //태그 연결 부분

    ppreview.classList.add(LOOK_FLICK_CAMERA);

    fflick_panel.classList.add(FLICK_PANEL);
    fflick_panel.className = "flick_panel";
    fflick_panel.style.left = -305;
    fflick_panel.id = "flick_panel";

    iimg.classList.add("look_img_file");
    iimg.style.width = '100%';
    iimg.style.height = '100%';
    iimg.src = "static/images/100.jpg";
    //태그 속성 적용 부분
    excuteSlide();
}


function init() {
    if (slideLen < 1) {
        printInit();
    }
    uploadButton.addEventListener('change', slide);
    initButton.addEventListener('click', resetImg);



}

init();