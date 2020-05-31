
function lookDelete(lookNum,lookUserId){

    alert("여기로옴");

    if(sessionStorage.getItem("userid")==null) {
        swal("로그인 안됨!!", "로그인을 해주세요", "error")
        location.href="loginUser";
    }
    if(sessionStorage.getItem("userid")!=lookUserId) {
        swal("경고!!", "로그인한 아이디와 작성자 아이디가 다릅니다", "error")
        location.href="main";
    }

    if(confirm("삭제할까요?"))
    {
        location.href="delete?look_num=<%=lookNum%>+&look_viewUserId=<%=lookUserId%>";
    }
    else
    {
        alert('아니오를 누르셨습니다');
        location.href="main";
    }


}


