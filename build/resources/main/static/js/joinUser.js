//정규식을 통한 아이디 확인
let idreg = /^[0-9a-z]{4,12}$/;//id식별을 위한 자바스크립트정규식 4~12자 숫자0~9 소문자a~z조합
let pwreg = /^(?=.*[!@#$%^&*()?])[0-9ㄱ-힣a-zA-Z!@#$%^&*()?]{8,12}$/;//패스워드정규식 8~12특수문자한자이상무조건포함
let nickreg = /^[0-9a-z가-힣]{4,8}$/;//닉네임정규식 숫자0~9,소문자a~z,한글가-힣조합
let heightweightreg = /^[0-9]{2,3}$/;//몸무게 및 키 2~3숫자
let emailreg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;
//이메일 정규식
let sel_file;

//정규식을 통해 아이디 값 확인
$('#id').blur(function () {
    //blur 엘리먼트가 포커스를 잃을때 발생되는 이벤트
    if (idreg.test($('#id').val())) {
        console.log('true');
        $('#id_check').text('');//값이 true라면 경고문을 지운다.
    } else {
        console.log('false');
        $('#id_check').text('4~12자리 영어소문자,숫자 조합 만 가능합니다.');
        $('#id_check').css('color', 'red'); //정규식에 맞지 않는 다면 빨간색 경고문이뜬다.
    }
});

//정규식을 통한 비밀번호 값 확인
$('#pw').blur(function () {
    //blur 엘리먼트가 포커스를 잃을때 발생되는 이벤트
    if (pwreg.test($('#pw').val())) {
        console.log('true');
        $('#pass_check').text('');//값이 true라면 경고문을 지운다.
    } else {
        console.log('false');
        $('#pass_check').text('8~12자리 !@#$%^&*()? 중 하나이상이 들어가야합니다.'); //정규식에 맞지 않는 다면 빨간색 경고문이뜬다.
        $('#pass_check').css('color', 'red');
    }
});

//정규식을 통한 닉네임체크
$('#nick').blur(function () {
    if (nickreg.test($('#nick').val())) {
        console.log('true');
        $('#nick_check').text('');//값이 true라면 경고문을 지운다.
    } else {
        $('#nick_check').text('4~8자리 영어소분자,숫자,한글조합만 가능합니다.'); //정규식에 맞지 않는 다면 빨간색 경고문이뜬다.
        $('#nick_check').css('color', 'red');
    }
});

//정규식을 통한 키확인
$('#height').blur(function () {
    if (heightweightreg.test($('#height').val())) {
        console.log('true');
        $('#height_check').text('');//값이 true라면 경고문을 지운다.
    } else {
        $('#height_check').text('2~3자리 숫자를 입력해주세요'); //정규식에 맞지 않는 다면 빨간색 경고문이뜬다.
        $('#height_check').css('color', 'red');
    }
});

//정규식을 통한 몸무게확인
$('#weight').blur(function () {
    if (heightweightreg.test($('#height').val())) {
        console.log('true');
        $('#weight_check').text('');//값이 true라면 경고문을 지운다.
    } else {
        $('#weight_check').text('2~3자리 숫자를 입력해주세요'); //정규식에 맞지 않는 다면 빨간색 경고문이뜬다.
        $('#weight_check').css('color', 'red');
    }
});

//정규식을 통한 이메일체크
$('#emailbox').blur(function () {
    if (emailreg.test($('#emailbox').val())) {
        console.log('true');
        $('#email_check').text('');//값이 true라면 경고문을 지운다.
    } else {
        $('#email_check').text('이메일형식에 맞게 입력해주세요'); //정규식에 맞지 않는 다면 빨간색 경고문이뜬다.
        $('#email_check').css('color', 'red');
    }
});

//성별이 선택될 시 발생.
$("input:radio[name=sex]").click(function () {
    //라디오버튼이 눌릴 시 발생
    //라디오버튼의 경우 한번 눌리면 다른 값을 눌러야만 이전에 눌려진값이 해제된다.
    //그렇기때문에 한번 눌린 이후로는 경고문을 띄울필요가없다.
    $('#gender_check').text('');//값이 true라면 경고문을 지운다.
});

//프로필사진업로드 시 발생 handleImageFileSelect 기능을 불러온다
$('#input_profile').on("change", handleImgFileSelect);

//파일형식을 통해 파일을 선택한후 선택된 이미지가 보이도록하는 기능.
//파일의 크기가 10MB가 넘거나 확장자가 jpg,jpeg,png가 아닐 시 경고
function handleImgFileSelect(e) {
    let files = e.target.files;
    let filesArr = Array.prototype.slice.call(files);
    let name = files[0].name;
    let size = files[0].size;
    let MaxSize = 10 * 1024 * 1024;//최대 10MB
    let RegExtFilter = /\.(jpg|jpeg|png|bmp)$/i;
    let ex = name.lastIndexOf(".");//마지막 .위치 즉 확장자를 찾는다.
    let ext = name.substr(ex, name.length);


    if (size > MaxSize) {
        swal("용량이 너무 커요!", "10MB보다 큼\n", "error");
        if($.browser.msie){
            $("#input_profile").replaceWith($("#input_profile").clone(true));
        }else{
            $("#input_profile").val("");
        }
        return;
    }

    if (RegExtFilter.test(ext)) {
    } else {
        if($.browser.msie){
          $("#input_profile").replaceWith($("#input_profile").clone(true));
        }else{
            $("#input_profile").val("");
        }
        swal("이미지파일만 넣어주세요", "jpg,jpeg,png확장자만 허용됩니다.", "error");
        return;
    }

    filesArr.forEach(function (f) {
        if (!f.type.match("image.*")) {

            return;
        }
        sel_file = f;
        let reader = new FileReader();
        reader.onload = function (e) {
            $("#img").attr("src", e.target.result);
        }
        reader.readAsDataURL(f);
    });
}

//회원가입 버튼 누를 시 발생
function joinUser() {
    //꼭 들어가야하는 값들이 들어가있는지 확인하기 위한 배열. 기본은 false값으로 맞춰져있다. 정규식을 통과하면 true로 변한다.
    let Arr = new Array(5).fill(false);
    let msg = '';//경고메시지. false로 나온 값들을 하나씩 추가해서 swal라이브러리에서 경고문을 띄어준다.
    let userid = document.getElementsByName("userid")[0].value;//유저아이디값
    let password = document.getElementsByName("password")[0].value;//비밀번호
    let nickname = document.getElementsByName("nickname")[0].value;//닉네임
    let height = document.getElementsByName("height")[0].value;//키
    let weight = document.getElementsByName("weight")[0].value;//몸무게
    let sex = $("input:radio[name='sex']:checked").val();//성별
    //라디오버튼(sex라는 이름)에서 체크된 값을 뽑는 JQuery. 아무것도 입력되지 않았으면 undefined다.
    let email = document.getElementsByName("email")[0].value;//이메일값

    let file=$("input[name=picture]")[0].files[0];//joinUser.jsp에서 이미지 파일을 들고온다.
    console.log(file);

    //스크립트문에서 확인한 후 통과 되야지 백엔드로 넘긴다
    //위에서 만든 정규식 메소드는 오류를 출력해줄수는 있지만 백엔드로 넘기는 걸 제어할수는 없다.
    //그러니 여기서 제어하겠다.
    if (idreg.test(userid)) {
        //아이디값을 정규식과 비교
        Arr[0] = true;//정규식을 통과하면 true값
    } else {
        msg += "아이디가 입력되지 않았습니다.\n";//swal에서 보여줄 msg.
        $('#id_check').text('4~12자리 영어소문자,숫자 조합 만 가능합니다.');
        $('#id_check').css('color', 'red');
        //정규식에 맞지 않는 다면 빨간색 경고문이뜬다.
        Arr[0] = false;//정규식을 통과하지 못하면 false값
    }
    if (pwreg.test(password)) {
        //패스워드 값을 정규식과 비교
        Arr[1] = true;//정규식을 통과하면 true값
    } else {
        msg += "비밀번호가 입력되지 않았습니다.\n";//swal에서 보여줄 msg.
        $('#pass_check').text('8~12자리 !@#$%^&*()? 중 하나이상이 들어가야합니다.');
        $('#pass_check').css('color', 'red');
        //정규식에 맞지 않는 다면 빨간색 경고문이뜬다.
        Arr[1] = false;//정규식을 통과하지 못하면 false값
    }
    if (nickreg.test(nickname)) {
        Arr[2] = true;//정규식을 통과하면 true값
    } else {
        msg += "닉네임이 입력되지 않았습니다.\n";//swal에서 보여줄 msg.
        $('#nick_check').text('4~8자리 영어소문자,숫자,한글조합만 가능합니다.');
        $('#nick_check').css('color', 'red');
        //정규식에 맞지 않는 다면 빨간색 경고문이뜬다.
        Arr[2] = false;//정규식을 통과하지 못하면 false값
    }
    if ($('input:radio[name=sex]').is(':checked')) {
        //라디오버튼(sex라는이름의)의 체크여부확인
        Arr[3] = true;//정규식을 통과하면 true값
    } else {
        Arr[3] = false;//정규식을 통과하지 못하면 false값
        msg += "성별이 입력되지 않았습니다\n";//swal에서 보여줄 msg.
        $('#gender_check').text('성별을 입력해주세요');
        $('#gender_check').css('color', 'red');
        //정규식에 맞지 않는 다면 빨간색 경고문이뜬다.
    }
    if (emailreg.test(email)) {
        Arr[4] = true;//정규식을 통과하면 true값
    } else {
        msg += "이메일이 입력되지 않았습니다.\n"//swal에서 보여줄 msg.
        $('#email_check').text('이메일형식에 맞게 입력해주세요');
        $('#email_check').css('color', 'red');
        //정규식에 맞지 않는 다면 빨간색 경고문이뜬다.
        Arr[4] = false;//정규식을 통과하지 못하면 false값
    }

    for (let i = 0; i < Arr.length; i++) {
        if (Arr[i] == false) {
            //입력된 값 중 단 하나라도 false가 있다면 백엔드로 넘겨선 안된다.
            //swal이라는 경고문 자바스크립트 형식으로 경고문을 출력한다.
            //swal("제목","내용","타입");
            swal("다시 입력하시오", msg, "error")
            //종료
            return;
        }
    }

    console.log("정규식 통과 후");

    let UserDto = {
        userid: userid,
        password: password,
        nickname: nickname,
        height: height,
        weight: weight,
        sex: sex,
        email: email
    };

    //FormData를 이용하여 업로드를 처리한다.
    //FormData 객체를 생성하여 업로드할 파일과 파일정보를 함께 보낸다.
    //JSON 데이터가 있는 다중 요청의 형태인데 혼합 멀티파트라고 한다.
    let formData=new FormData();
    formData.append("UserDto",JSON.stringify(UserDto));

    if(file==undefined) {

        //JQuery 옵션
        //data : 서버로 보낼 데이터
        //dataType : 서버에서 반환되는 데이터 형식을 지정
        //processData : 데이터를 querystring형태로 보내지 않고 DOMDocument 또는 다른 형태로 보내려면 false로 설정
        //contentType : 서버에 데이터를 보낼 때 사용 content-type 헤더의 값.
        $.ajax({
            url: "/user",
            type: "post",
            data: formData,
            dataType : false,
            processData: false,
            // contentType:'multipart/form-data',
            contentType:false,
            success: function () {
                alert(JSON.stringify(formData));//성공 시 출력 나중에 지울것!
                location.href = "/loginUser";
            },
            error: function (request, status, error) {
                alert("code :" + request.status + "\n" + "message : " + request.responseText + "\n" + "error : " + error);
            }
        });
        // $.ajax({
        //     url: "/user",
        //     type: "post",
        //     data: formData,
        //     dataType: "text",
        //     processData: false,
        //     contentType:false,
        //     success: function () {
        //         alert(JSON.stringify(formData));//성공 시 출력 나중에 지울것!
        //         location.href = "/loginUser";
        //     },
        //     error: function (request, status, error) {
        //         alert("code :" + request.status + "\n" + "message : " + request.responseText + "\n" + "error : " + error);
        //     }
        // });
   }else{
        formData.append("file",file);

        //JQuery 옵션
        //data : 서버로 보낼 데이터
        //dataType : 서버에서 반환되는 데이터 형식을 지정
        //processData : 데이터를 querystring형태로 보내지 않고 DOMDocument 또는 다른 형태로 보내려면 false로 설정
        //contentType : 서버에 데이터를 보낼 때 사용 content-type 헤더의 값.
        $.ajax({
            url: "/userfile",
            type: "post",
            data: formData,
            dataType : false,
            processData: false,
            // contentType:'multipart/form-data',
            contentType:false,
            success: function () {
                alert(JSON.stringify(formData));//성공 시 출력 나중에 지울것!
                location.href = "/loginUser";
            },
            error: function (request, status, error) {
                alert("code :" + request.status + "\n" + "message : " + request.responseText + "\n" + "error : " + error);
            }
        });

    }
}

//익스플로러 판별
jQuery.browser = {};
(function () {
    jQuery.browser.msie = false;
    jQuery.browser.version = 0;
    if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
        jQuery.browser.msie = true;
        jQuery.browser.version = RegExp.$1;
    }
})();

