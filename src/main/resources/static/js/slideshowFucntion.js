
export function slideShow(paraSlideWidth) {
    let slideList = document.querySelector('.look_flick_camera');  // Slide parent dom
    let slideContents = document.querySelectorAll('.flick_panel');  // each slide dom
    let slideBtnNext = document.querySelector('#look_slide_button_right'); // next button
    let slideBtnPrev = document.querySelector('#look_slide_button_left'); // prev button
    let slideLen = slideContents.length;  // slide length
    let slideWidth = paraSlideWidth; // slide width
    let slideSpeed = 300; // slide speed
    let startNum = 0; // initial slide index (0 ~ 4)

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
            console.log("slideWidth", slideWidth);
            console.log("curIndex", curIndex);
            console.log("slideList.style.transform", slideList.style.transform);
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
