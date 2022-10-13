package com.rkrua.dto;

public class PageVo {
	private int startPage;	 // 1��° ��ȣ
	private int endPage;	// ����ȣ
	private boolean prev, next;	// ���� , ����
	

	private int pageNum;	// ���� ������ ��ȣ
	private int amount = 9;	// ȭ�鿡 ������ ������ �� ��� ����
	private int total;		// �� ������ ��
	
	// ��ü�� ������ �� ��� ó��
	public PageVo(int pageNum, int amount, int total) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.total = total;
		
		// 1. end page ����
		// 1 -> 10
		// 9 -> 10
		// 11 -> 20
		// ceil = �ø�
//		(int)Math.ceil(��������ȣ / ���������̼ǰ���) * ���������̼ǰ���;
		this.endPage = (int)Math.ceil(this.pageNum * 0.1) * 10;
		
		// 2. start ������ ����
		// �������� 0 ���������̼ǰ��� +1
		this.startPage = this.endPage - 10 + 1;
		
		// 3. ��¥ ����ȣ ���ؼ� end Page�� �缳�� 
		// �ִ� ���������� ũ�Ⱑ ������ ex) page �ִ� ���� 6
		// �缳��
		int realEnd = (int)Math.ceil(this.total/ (double)this.amount);
		
//		������ ������ ������ ����ȣ �ٲ�
		// 141 ��° �Խù��� ���϶�, 1-10 -> endPage ����
		// 11- 14 -> realEnd ����
		if(this.endPage > realEnd) {
			this.endPage=realEnd;
		}
		
		//4. ���� ������
		this.prev = this.startPage >1;
		
		//5. ���� ������
		// 141 ��° �Խù��� ���϶�, 1-10 -> true
		// 11- 14 -> false
		this.next = this.endPage < realEnd;
		
		// �����
		System.out.println("���������� :" +this.startPage);
		System.out.println("�� ������ :" + this.endPage);
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