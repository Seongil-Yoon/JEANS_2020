//CSS클래스명 불러오기
const FLICK_PANEL = "flick_panel";

function previewImage(targetObj, View_area) { //(this,'View_area')
    let preview = document.getElementById(View_area); //div id
    let ua = window.navigator.userAgent;

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
        let files = targetObj.files;
        for (let i = 0; i < files.length; i++) {
            let file = files[i];
            let imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
            if (!file.type.match(imageType))
                continue;
            // let prevImg = document.getElementById("prev_" + View_area); //이전에 미리보기가 있다면 삭제
            // if (prevImg) {
            //     preview.removeChild(prevImg);
            // }
            let img = document.createElement("img");

            let flick_panel = document.createElement('div');
            flick_panel.classList.add(FLICK_PANEL);
            flick_panel.className = "flick_panel";
            flick_panel.appendChild(img);
            preview.appendChild(flick_panel);

            document.getElementsByClassName('flick_panel').files[length];

            console.log(flick_panel);

            img.id = "prev_" + View_area;
            img.classList.add("look_img_file");
            img.file = file;
            img.style.width = '100%';
            img.style.height = '100%';

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


function slide() {
    const slideList = document.querySelector('.look_flick_camera');  // Slide parent dom
    const slideContents = document.querySelectorAll('.flick_panel');  // each slide dom
    const slideBtnNext = document.querySelector('#look_slide_button_right'); // next button
    const slideBtnPrev = document.querySelector('#look_slide_button_left'); // prev button
    const slideLen = slideContents.length;  // slide length
    const slideWidth = 300; // slide width
    const slideSpeed = 300; // slide speed
    const startNum = 0; // initial slide index (0 ~ 4)

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


function init() {
    slide();
    previewImage(targetObj, View_area);

}

init();