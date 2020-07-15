var page_status = 0;
var status1 = 0;
var status2 = 0;
var status3 = 0;


function page_select1() {
  page_status = 0;
}

function page_select2() {
  page_status = 1;

  document.getElementById("closet1").style.display = "none";
  document.getElementById("closet2").style.display = "none";
  document.getElementById("closet3").style.display = "none";

}

function page_move() {
  if (page_status == 1) {
    location.href = "exdiv1.html";
  }
  else {
    location.href = "exdiv.html";
  }
}

//보이기
function div_show1() {
  if (status1 == 0) {
    document.getElementById("closet1").style.display = "block";
    document.getElementById("closet2").style.display = "none";
    document.getElementById("closet3").style.display = "none";
    status1 = 1;

    status2 = 0;
    status3 = 0;

  }
  else {
    document.getElementById("closet1").style.display = "none";
    document.getElementById("closet2").style.display = "none";
    document.getElementById("closet3").style.display = "none";
    status1 = 0;
    status2 = 0;
    status3 = 0;
  }
}

//보이기
function div_show2() {
  if (status2 == 0) {
    document.getElementById("closet1").style.display = "none";
    document.getElementById("closet2").style.display = "block";
    document.getElementById("closet3").style.display = "none";
    status2 = 1;

    status1 = 0;
    status3 = 0;
  }
  else {

    document.getElementById("closet1").style.display = "none";
    document.getElementById("closet2").style.display = "none";
    document.getElementById("closet3").style.display = "none";
    status1 = 0;
    status2 = 0;
    status3 = 0;
  }
}

//보이기
function div_show3() {
  if (status3 == 0) {
    document.getElementById("closet1").style.display = "none";
    document.getElementById("closet2").style.display = "none";
    document.getElementById("closet3").style.display = "block";
    status3 = 1;

    status1 = 0;
    status2 = 0;
  }
  else {
    document.getElementById("closet1").style.display = "none";
    document.getElementById("closet2").style.display = "none";
    document.getElementById("closet3").style.display = "none";
    status1 = 0;
    status2 = 0;
    status3 = 0;
  }
}
