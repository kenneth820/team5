/**
 * 만약 개발자도구에서 소스가 안보이는 경우
 1. Network 탭으로 이동하여 disable cache 체크박스에 체크
 => sources 탭으로 이동하여 F5(새로고침)
 2. 그래도 안보이면 Console탭으로 이동
 => Source 탭으로 이동하여 F5(새로고침)
 */

// id를 입력 후 중복체크 버튼을 눌렀을 때, 동작 함수
function checkId(){
	if(document.frm.userid.value===""){
		alert("아이디를 입력해주세요.");
		return;
	}
	var url = "checkId.do?userid=" + document.frm.userid.value;
	window.open(url, "_blank_1","menubar=no", "resizable=no","width=450, height=200");
}

// idCheck.jsp에서 사용 버튼 클릭시, 동작할 함수
function idOk(){
	// 중복체크 창에 입력된 아이디값을 회원가입창 아이디 입력양식에 표시
	opener.frm.userid.value = document.frm.userid.value;
	opener.frm.checkid.value = document.frm.userid.value;
	self.close();			// 생성된 창 닫기
}

// 로그인 페이지에서 입력된 양식에 정상 데이터가 포함되었는지 확인
function checkLogin(){
	if(document.frm.userId.value.length ===0){
		alert('아이디를 입력해주세요.');
		frm.userId.focus();
		return false;
	}
	if(document.frm.userPwd.value.length === "" ){
		alert('암호를 입력해주세요.');
		frm.userPwd.focus();
		return false;
	}
}

// 회원 가입/수정 페이지에서 입력된 양식에 정상 데이터가 포함되었는지 확인
function checkJoin() {

	if(document.frm.name.value.length ===0){
		alert('이름을 입력해주세요.');
		frm.name.focus();
		return false;
	}
	if(document.frm.userid.value.length ===0){
		alert('아이디를 입력해주세요.');
		frm.userid.focus();
		return false;
	}
	if(document.frm.userid.value.length <4 ){
		alert('아이디는 4자 이상 입력해야 합니다.');
		frm.userid.focus();
		return false;
	}
	if(document.frm.pwd.value.length ===0){
		alert('암호를 입력해주세요.');
		frm.pwd.focus();
		return false;
	}
	if(document.frm.pwd.value !== document.frm.pwd_check.value){
		alert('암호가 일치하지 않습니다..');
		frm.pwd.focus();
		return false;
	}
	if (document.frm.checkid.value.length===0) {
		alert('중복 체크를 하지 않았습니다.');
		frm.userid.focus();
		return false;
	}
	return true;
}