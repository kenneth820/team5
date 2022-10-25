/**
 * 
 */

function checkShowroom() {
	if(document.frm.name.value.length===0){
		alert("쇼룸 이름을 입력하세요. ");
		frm.name.foucs();
		return false;
	}
	if(!document.frm.pictureUrl.value) {
		alert("사진을 등록하세요. ");
		frm.pictureUrl.foucs();
		return false;
	}
	return true;
}

function checkTrand(){
	if(document.frm.title.value.length===0){
		alert("타이틀을 입력하세요");
		frm.title.focus();
		return false;
	}
	if(document.frm.pictureUrl.value.length===0){
		alert("사진을 등록하세요");
		frm.pictureUrl.focus();
		return false;
	}
	if(document.frm.content.value.length===0){
		alert("글 내용을 입력하세요");
		frm.content.focus();
		return false;
	}
	return true;
}