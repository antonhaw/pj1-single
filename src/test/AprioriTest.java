package test;

import impl.AprioriImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;

public class AprioriTest {

	@Ignore
	@Test
	public void checkAprioriTest() {
		List<Set<String>> inputList = new ArrayList<>();
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();
		Set<String> set3 = new HashSet<>();
		Set<String> set4 = new HashSet<>();

		set1.add("I1");
		set1.add("I2");
		set1.add("I5");
		inputList.add(set1);
		
		set2.add("I2");
		set2.add("I4");
		inputList.add(set2);
		
		set3.add("I2");
		set3.add("I3");
		inputList.add(set3);
		
		set4.add("I1");
		set4.add("I2");
		set4.add("I4");
		inputList.add(set4);
		
		List<Set<String>> outputList = new ArrayList<>();
	}
	
	@Ignore
	@Test
	public void generateCandidatesFirstTest(){
		List<Set<String>> inputList = new ArrayList<>();
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();
		Set<String> set3 = new HashSet<>();
		Set<String> set4 = new HashSet<>();

		set1.add("I1");
		set1.add("I2");
		set1.add("I5");
		inputList.add(set1);
		
		set2.add("I2");
		set2.add("I4");
		inputList.add(set2);
		
		set3.add("I2");
		set3.add("I3");
		inputList.add(set3);
		
		set4.add("I1");
		set4.add("I2");
		set4.add("I4");
		inputList.add(set4);
		
		Map<Set<String>, Integer> expectedResult = new HashMap<>();
		Set<String> rset1 = new HashSet<>();
		rset1.add("I1");
		Set<String> rset2 = new HashSet<>();
		rset2.add("I2");
		Set<String> rset4 = new HashSet<>();
		rset4.add("I4");
		
		expectedResult.put(rset1, 2);
		expectedResult.put(rset2, 4);
		expectedResult.put(rset4, 2);
		
		Map<Set<String>, Integer> result = AprioriImpl.generateCandidatesFirst(inputList);
		
		Assert.assertEquals(expectedResult, result);
				
		Set<String> rset5 = new HashSet<>(Arrays.asList("I4", "I2"));
		Set<String> rset6 = new HashSet<>(Arrays.asList("I2", "I1"));

		Map<Set<String>, Integer> expectedResult2 = new HashMap<>();
		expectedResult2.put(rset6, 2);
		expectedResult2.put(rset5, 2);
		
		Map<Set<String>, Integer> result2 = AprioriImpl.generateCandidatesRest(result, inputList);
		
		Assert.assertEquals(result2,expectedResult2);
		
	}
	
	@Test
	public void generateCandidatesTest(){
		List<Set<String>> inputList = new ArrayList<>();
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();
		Set<String> set3 = new HashSet<>();
		Set<String> set5 = new HashSet<>();
		Set<String> set6 = new HashSet<>();
		Set<String> set7 = new HashSet<>();
		Set<String> set8 = new HashSet<>();
		Set<String> set9 = new HashSet<>();
		Set<String> set4 = new HashSet<>();

		
		set1.add("I1");
		set1.add("I2");
		set1.add("I5");
		inputList.add(set1);
		
		set2.add("I2");
		set2.add("I4");
		inputList.add(set2);
		
		set3.add("I2");
		set3.add("I3");
		inputList.add(set3);
		
		set4.add("I1");
		set4.add("I2");
		set4.add("I4");
		inputList.add(set4);
		
		set5.add("I1");
		set5.add("I3");
		inputList.add(set5);
		
		set6.add("I2");
		set6.add("I3");
		inputList.add(set6);
		
		set7.add("I1");
		set7.add("I3");
		inputList.add(set7);
		
		set8.add("I1");
		set8.add("I2");
		set8.add("I3");
		set8.add("I5");
		inputList.add(set8);
		
		set9.add("I1");
		set9.add("I2");
		set9.add("I3");
		inputList.add(set9);
		System.out.println("foo: "+AprioriImpl.generateCandidates(inputList));
	}

}
