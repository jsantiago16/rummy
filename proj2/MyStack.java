package proj2;
/**
* File: MyStack.java
* Project2
* @author Javier Santiago
* Date 5/16/21
* Course: CCOM4029

*/

import java.util.*;


public class MyStack <T> {

	private ArrayList<T> stackArray;

	public MyStack(int size){
		stackArray = new ArrayList<T>();
	}

	public void push(T element){
		stackArray.add(element);
	}

	public T pop(){
		int index = stackArray.size()-1;
		return stackArray.remove(index);
	}


	public T top(){
		int index = stackArray.size()-1;
		return stackArray.get(index);
	}

	public boolean isEmpty(){
		return stackArray.isEmpty();
	}


}
