package com.ssafy.util;

public class RandomPw {

	static char[] a = new char[10]; //10자리 숫자 임시 비밀번호
	static char pwCollectionAll[] = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')' }; //랜덤한 숫자 영어 특수문자 배열
	public static String randomPw() {
		getRandPw(10, pwCollectionAll, 0); //중복 순열 
		String str = new String(a);
		return str;
	}

	public static void getRandPw(int size, char[] pwCollection, int cnt) { 
		if (cnt == size) { //임시 비밀번호 자릿 수만큼 반복하면 
			return; //리턴
		}
		int selectRandomPw = (int) (Math.random() * (pwCollection.length)); //랜덤한 값 받아서
		a[cnt] = pwCollection[selectRandomPw]; //배열에 넣기
		getRandPw(size, pwCollectionAll, cnt + 1); //중복 순열 

	}
}
