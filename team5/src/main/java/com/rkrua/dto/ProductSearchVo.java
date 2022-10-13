package com.rkrua.dto;

public class ProductSearchVo extends PageVo{
	public ProductSearchVo(int pageNum, int amount, int total) {
		super(pageNum, amount, total);
		// TODO Auto-generated constructor stub
	}
	private String searchKeyword;
	private String searchColumn;
	private String searchCategory;
	@Override
	public String toString() {
		return "ProductSearchVo [searchKeyword=" + searchKeyword + ", searchColumn=" + searchColumn
				+ ", searchCategory=" + searchCategory + "]";
	}
	
}
