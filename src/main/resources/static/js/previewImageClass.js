// //previewImage();
// let preview = 0;
// let flick_panel = 0;
// let img = 0;
// let files = 0;
// let fileBuffer = 0;

export class previewImageClass {
    constructor() {
        this.preview = 0;
        this.flick_panel = 0;
        this.img = 0;
        this.files = 0;
        this.fileBuffer = 0;
        this.FLICK_PANEL = "flick_panel";
        this.LOOK_FLICK_CAMERA = "look_flick_camera";
        this.LOOK_IMG_FILE = "look_img_file";
    }
    previewImage(targetObj, View_area, event, event_fileBuffer) {
        console.log(targetObj, View_area, event);
        this.preview = document.getElementById(View_area); //div id
        const ua = window.navigator.userAgent;
        this.fileBuffer = event_fileBuffer;
        // this.fileBuffer = Array.prototype.slice.call(event.target.files);
        console.log("버퍼에 담은 것.", this.fileBuffer);

        // let storeBuffer = () => {
        //     let i = 0;
        //     for (i = 0; i < this.fileBuffer.length; i++) {
        //         event_fileBuffer[i] = this.fileBuffer[i];
        //         // event.target.files[i] = this.fileBuffer[i];

        //     }
        //     console.log("버퍼에 저장 하고 다시 files에 저장", event.target.files[i], i);
        //     console.log("버퍼에 저장 하고 다시 files에 저장", event_fileBuffer[i], i);
        // }
        // storeBuffer();


        //ie일때(IE8 이하에서만 작동)
        if (ua.indexOf("MSIE") > -1) {
            targetObj.select();
            try {
                let src = document.selection.createRange().text; // get file full path(IE9, IE10에서 사용 불가)
                let ie_preview_error = document.getElementById("ie_preview_error_" + View_area);


                if (ie_this._error) {
                    this.preview.removeChild(ie_preview_error); //error가 있으면 delete
                }

                this.img = document.getElementById(View_area); //이미지가 뿌려질 곳

                //이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
                this.img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + src + "', sizingMethod='scale')";
            } catch (e) {
                if (!document.getElementById("ie_preview_error_" + View_area)) {
                    let info = document.createElement("<p>");
                    info.id = "ie_preview_error_" + View_area;
                    info.innerHTML = e.name;
                    this.preview.insertBefore(info, null);
                }
            }
            //ie가 아닐때(크롬, 사파리, FF)
        } else {
            for (let i = 0; i < this.fileBuffer.length; i++) {
                let file = this.fileBuffer[i];
                console.log("파일", file);
                console.log("this.value", targetObj);
                let imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
                if (!file.type.match(imageType))
                    continue;
                // let prevImg = document.getElementById("prev_" + View_area); //이전에 미리보기가 있다면 삭제
                // if (prevImg) {
                //     preview.removeChild(prevImg);
                // }


                this.flick_panel = document.createElement('div');
                this.img = document.createElement("img");
                //태그 생성 부분

                console.log("View_area", this.preview);
                console.log("flick_panel", this.flick_panel);

                this.preview.appendChild(this.flick_panel);
                this.flick_panel.appendChild(this.img); //flick_panel의자식으로 img태그를 연결
                //태그 연결 부분

                this.preview.classList.add(this.LOOK_FLICK_CAMERA);

                this.flick_panel.classList.add(this.FLICK_PANEL);
                this.flick_panel.className = "flick_panel" + " s" + i;
                this.flick_panel.style.left = i * 305;
                this.flick_panel.id = "flick_panel";

                this.img.classList.add(this.LOOK_IMG_FILE);
                this.img.id = "prev_" + View_area;
                this.img.file = file;
                this.img.style.width = '100%';
                this.img.style.height = '100%';
                //태그 속성 적용 부분



                console.log(this.flick_panel);
                // document.getElementsByClassName('flick_panel').files[length];
                // preview.appendChild(createFlick_panel(img));

                if (window.FileReader) { // FireFox, Chrome, Opera 확인.
                    let reader = new FileReader();
                    reader.onloadend = (function (aImg) {
                        return function (e) {
                            aImg.src = e.target.result;
                        };
                    })(this.img);
                    reader.readAsDataURL(file);
                } else { // safari is not supported FileReader
                    //alert('not supported FileReader');
                    if (!document.getElementById("sfr_preview_error_"
                        + View_area)) {
                        let info = document.createElement("p");
                        info.id = "sfr_preview_error_" + View_area;
                        info.innerHTML = "not supported FileReader";
                        this.preview.insertBefore(info, null);
                    }
                }
                console.log("반복횟수", i);
            }
        }
    }
}
