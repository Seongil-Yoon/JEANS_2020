
//태그 불러오기
const uploadDiv = document.querySelector("#js-uploadDiv"),
    uploadButton = uploadDiv.querySelector("input"), //파일선택 버튼.
    initButton = uploadDiv.querySelector("button"),
    inputElement = document.querySelector('input[type="file"]');

let fileBuffer = [];

//from JSP onClick이벤트 호출
function modifiy() {

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
            console.log("fileBuffer == undefined");
            let data = $('[name=writeForm]').serialize();
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
//end of from JSP onClick이벤트 호출

//게시글에 있는 썸네일이미지 GET으로 받아오기.
function getThumbnail(lookNumber) {
    $.ajax({
        url: `/displayInthumbnail/${lookNumber}`,
        type: "GET",
        success: function (result) {
            filePond(result);
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

// Register the plugin with FilePond
function filePond(result) {


    FilePond.registerPlugin(
        // FilePondPluginFileMetadata,
        // FilePondPluginImageCrop,
        FilePondPluginImagePreview,
        FilePondPluginFileEncode
    );

    // Create the FilePond instance
    const pond = FilePond.create(inputElement, {
        allowMultiple: true,
        allowReorder: true
    });

    console.log("result name" + result[0]);

    let putImage = (result) => {
        for (let i = 0; i < result.length; i++) {
            pond.addFile(`data:image/jpg;base64, ${result[i]}`);
            // e.detail.items[i].filename = result[i].name;
            console.log(result[i].name);
        }
    }
    putImage(result);


    const filepondRoot = document.querySelector('.filepond--root');

    filepondRoot.addEventListener('FilePond:updatefiles', e => {
        fileBuffer.splice(0, fileBuffer.length);

        for (let i = 0; i < e.detail.items.length; i++) {
            console.log('File updatefiles e.detail.items[i]', e.detail.items[i]);
            fileBuffer[i] = dataURLtoFile(e.detail.items[i].getFileEncodeDataURL(), e.detail.items[i].filename);
            console.log(fileBuffer);
        }
        console.log('File updatefiles' + e.detail);
        console.log('File updatefiles  e.detail.items', e.detail.items);

    });
}

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

}

init();




