export class slideShowClass {

    constructor(slideWidth) {
        this.slideList = document.querySelector('.look_flick_camera');
        this.slideContents = document.querySelectorAll('.flick_panel');
        this.slideBtnNext = document.querySelector('#look_slide_button_right');
        this.slideBtnPrev = document.querySelector('#look_slide_button_left');
        this.slideLen = this.slideContents.length;
        this.slideWidth = slideWidth;
        this.slideSpeed = 300;
        this.startNum = 0;
    }

    slideShow() {
        console.log("클래스" + this.slideLen);
        this.slideList.style.width = this.slideWidth * (this.slideLen + 2) + "px";
        console.log(this.slideList);
        console.log(this.slideContents);

        // Copy first and last slide
        let firstChild = this.slideList.firstElementChild;
        let lastChild = this.slideList.lastElementChild;
        let clonedFirst = firstChild.cloneNode(true);
        let clonedLast = lastChild.cloneNode(true);

        // Add copied Slides
        this.slideList.appendChild(clonedFirst);
        this.slideList.insertBefore(clonedLast, this.slideList.firstElementChild);

        this.slideList.style.transform = "translate3d(-" + (this.slideWidth * (this.startNum + 1)) + "px, 0px, 0px)";

        let curIndex = this.startNum; // current slide index (except copied slide)
        let curSlide = this.slideContents[curIndex]; // current slide dom

        // console.log("curSlide",curSlide);
        curSlide.classList.add('slide_active');
        console.log("curSlide", curSlide);

        /** Next Button Event */
        let nextClick = 0;
        this.slideBtnNext.addEventListener('click', nextClick = () => {
            console.log("오른쪽 클릭");
            if (curIndex <= this.slideLen - 1) {
                this.slideList.style.transition = this.slideSpeed + "ms";
                this.slideList.style.transform = "translate3d(-" + (this.slideWidth * (curIndex + 2)) + "px, 0px, 0px)";
            }
            if (curIndex === this.slideLen - 1) {
                let setTimeouts = 0;
                setTimeout(setTimeouts = () => {
                    this.slideList.style.transition = "0ms";
                    this.slideList.style.transform = "translate3d(-" + this.slideWidth + "px, 0px, 0px)";
                }, this.slideSpeed);
                curIndex = -1;
            }
            curSlide.classList.remove('slide_active');
            curSlide = this.slideContents[++curIndex];
            curSlide.classList.add('slide_active');
        });

        /** Prev Button Event */
        let prevClick = 0;
        this.slideBtnPrev.addEventListener('click', prevClick = () => {
            if (curIndex >= 0) {
                this.slideList.style.transition = this.slideSpeed + "ms";
                this.slideList.style.transform = "translate3d(-" + (this.slideWidth * curIndex) + "px, 0px, 0px)";
            }
            if (curIndex === 0) {
                let setTimeouts = 0;
                setTimeout(setTimeouts = () => {
                    this.slideList.style.transition = "0ms";
                    this.slideList.style.transform = "translate3d(-" + (this.slideWidth * this.slideLen) + "px, 0px, 0px)";
                }, this.slideSpeed);
                curIndex = this.slideLen;
            }
            curSlide.classList.remove('slide_active');
            curSlide = this.slideContents[--curIndex];
            curSlide.classList.add('slide_active');
        });
    }
}
