package com.company.app;

import java.util.ArrayList;
import java.util.List;

class Student {
	private int no;
	private String name;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [no=" + no + ", name=" + name + "]";
	}
}

class School {
	private final Student student;	//주입의 형태

	public School(Student student) {	//생성자 주입
		this.student = student;
	}
}

public class TestExample {
	public static void main() {
		Student st1 = new Student();
		School sc1 = new School(st1);
		
		//제네릭: 타입과 관계없이 아무 타입이나 처리나 저장이 가능한 데이터 클래스 => 컬렉션프레임워크
		//컬렉션프레임워크: List: ArrayList, Set: HashSet, Map: HashMap, Deque, Queue, Stack,...
		List<Student> stList = new ArrayList<Student>();
		
		Student st2 = new Student();
		Student st3 = new Student();
		
		stList.add(st1);
		stList.add(st2);
		stList.add(st3);
		
		for(Student st:stList) {	//컬렉션프레임워크의 순회 방식
			
		}
		
	}
}
