package com.stydy.mysite;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class Blombok {
	private String id;
	private int num;
	
	private  String name;
	private  int code;
	
	
	public static void main(String[] args) {
		/*Blombok lombok = new Blombok();
		
		lombok.setId("zip");
		lombok.setNum(27);
		
		System.out.println(lombok.getId());
		System.out.println(lombok.getNum());*/
		
		Blombok lombok2 = new Blombok("27",27);
		
		
		System.out.println(lombok2.getCode());
		System.out.println(lombok2.getName());
		System.out.println(lombok2.getNum());
	}


	public Blombok(String string, int i) {
		// TODO Auto-generated constructor stub
	}



}
