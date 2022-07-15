package io.sarc.bomboot.misc;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.sarc.bomboot.util.RandomUtil;

public class StackData {
	private static Logger log = LoggerFactory.getLogger(StackData.class);
	
	private List<String> animals = Arrays.asList("BIRDS", "FISH", "DOGS", "REPTILES", "CATS", "BIRDS");
	
	public void make() {
		int randomNum = RandomUtil.getRandomNumber(animals.size());
		
		Stack<String> stack = new Stack<String>();
		
		for ( int i=0; i<animals.size(); i++ ) {
			stack.push(animals.get(randomNum));
		}
	}
}