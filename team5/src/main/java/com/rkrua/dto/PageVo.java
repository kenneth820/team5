package com.rkrua.dto;

public class PageVo {
	private int startPage;	 // 1번째 번호
	private int endPage;	// 끝번호
	private boolean prev, next;	// 이전 , 다음
	

	private int pageNum;	// 현재 페이지 번호
	private int amount = 9;	// 화면에 나오는 페이지 속 요소 개수
	private int total;		// 총 페이지 수
	
	// 객체가 생성될 때 계산 처리
	public PageVo(int pageNum, int amount, int total) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.total = total;
		
		// 1. end page 결정
		// 1 -> 10
		// 9 -> 10
		// 11 -> 20
		// ceil = 올림
//		(int)Math.ceil(페이지번호 / 페이지네이션개수) * 페이지네이션개수;
		this.endPage = (int)Math.ceil(this.pageNum * 0.1) * 10;
		
		// 2. start 페이지 결정
		// 끝페이지 0 페이지네이션개수 +1
		this.startPage = this.endPage - 10 + 1;
		
		// 3. 진짜 끝번호 구해서 end Page값 재설정 
		// 최대 페이지보다 크기가 작을시 ex) page 최대 개수 6
		// 재설정
		int realEnd = (int)Math.ceil(this.total/ (double)this.amount);
		
//		마지막 페이지 도착시 끝번호 바뀜
		// 141 번째 게시물이 끝일때, 1-10 -> endPage 셋팅
		// 11- 14 -> realEnd 셋팅
		if(this.endPage > realEnd) {
			this.endPage=realEnd;
		}
		
		//4. 이전 페이지
		this.prev = this.startPage >1;
		
		//5. 다음 페이지
		// 141 번째 게시물이 끝일때, 1-10 -> true
		// 11- 14 -> false
		this.next = this.endPage < realEnd;
		
		// 디버깅
		System.out.println("시작페이지 :" +this.startPage);
		System.out.println("끝 페이지 :" + this.endPage);
		System.out.println(this.total);
				
	}

public int getStartPage() {
	return startPage;
}

public void setStartPage(int startPage) {
	this.startPage = startPage;
}

public int getEndPage() {
	return endPage;
}

public void setEndPage(int endPage) {
	this.endPage = endPage;
}

public boolean isPrev() {
	return prev;
}

public void setPrev(boolean prev) {
	this.prev = prev;
}

public boolean isNext() {
	return next;
}

public void setNext(boolean next) {
	this.next = next;
}

public int getPageNum() {
	return pageNum;
}

public void setPageNum(int pageNum) {
	this.pageNum = pageNum;
}

public int getAmount() {
	return amount;
}

public void setAmount(int amount) {
	this.amount = amount;
}

public int getTotal() {
	return total;
}

public void setTotal(int total) {
	this.total = total;
}
}