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
		
		AprioriImpl apriori = new AprioriImpl(inputList, 2);
		
		Map<Set<String>, Integer> result = apriori.calculateFrequentItemSets();
		
//		Assert.assertEquals(expectedResult, result);
				
		Set<String> rset5 = new HashSet<>(Arrays.asList("I4", "I2"));
		Set<String> rset6 = new HashSet<>(Arrays.asList("I2", "I1"));

		Map<Set<String>, Integer> expectedResult2 = new HashMap<>();
		expectedResult2.put(rset6, 2);
		expectedResult2.put(rset5, 2);
		
//		Map<Set<String>, Integer> result2 = AprioriImpl.generateCandidatesRest(result, inputList);
		
		Assert.assertEquals(result,expectedResult2);
		
	}
	
	@Test
	public void exelExampleTest(){
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
		
		Map<Set<String>, Integer> expectedResult = new HashMap<>();
		Set<String> rset1 = new HashSet<>(Arrays.asList("I1","I2","I5"));
		expectedResult.put(rset1, 2);
		Set<String> rset2 = new HashSet<>(Arrays.asList("I1","I3","I2"));
		expectedResult.put(rset2, 2);
		AprioriImpl apriori = new AprioriImpl(inputList, 2);
		
		Map<Set<String>, Integer> result = apriori.calculateFrequentItemSets();
		
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void wikiExampleTest(){
		List<Set<String>> inputList = new ArrayList<>();
		
		Set<String> t1 = new HashSet<>();
		t1.addAll(Arrays.asList("a","b","c"));
		inputList.add(t1);
		
		Set<String> t2 = new HashSet<>();
		t2.addAll(Arrays.asList("a","b","d"));
		inputList.add(t2);
		
		Set<String> t3 = new HashSet<>();
		t3.addAll(Arrays.asList("a","b","e"));
		inputList.add(t3);
		
		Set<String> t4 = new HashSet<>();
		t4.addAll(Arrays.asList("a","c","d"));
		inputList.add(t4);
		
		Set<String> t5 = new HashSet<>();
		t5.addAll(Arrays.asList("a","c","e"));
		inputList.add(t5);
		
		Set<String> t6 = new HashSet<>();
		t6.addAll(Arrays.asList("b","c","d"));
		inputList.add(t6);
		
		Map<Set<String>, Integer> expectedResult = new HashMap<>();
		Set<String> rset1 = new HashSet<>(Arrays.asList("b","c"));
		expectedResult.put(rset1, 2);
		
		Set<String> rset2 = new HashSet<>(Arrays.asList("a","d"));
		expectedResult.put(rset2, 2);
		
		Set<String> rset3 = new HashSet<>(Arrays.asList("c","a"));
		expectedResult.put(rset3, 3);
		
		Set<String> rset4 = new HashSet<>(Arrays.asList("c","d"));
		expectedResult.put(rset4, 2);
		
		Set<String> rset5 = new HashSet<>(Arrays.asList("a","e"));
		expectedResult.put(rset5, 2);
		
		Set<String> rset6 = new HashSet<>(Arrays.asList("b","d"));
		expectedResult.put(rset6, 2);
		
		Set<String> rset7 = new HashSet<>(Arrays.asList("a","b"));
		expectedResult.put(rset7, 3);
		
		AprioriImpl apriori = new AprioriImpl(inputList, 2);
		
		Map<Set<String>, Integer> result = apriori.calculateFrequentItemSets();
		
//		System.out.println(result+" \n"+expectedResult);
		
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void hagenTest(){
		List<Set<String>> inputList = new ArrayList<>();
		
		Set<String> t1 = new HashSet<>();
		t1.addAll(Arrays.asList("a","b","c"));
		inputList.add(t1);
		
		Set<String> t2 = new HashSet<>();
		t2.addAll(Arrays.asList("a","b","d"));
		inputList.add(t2);
		
		Set<String> t3 = new HashSet<>();
		t3.addAll(Arrays.asList("a","e"));
		inputList.add(t3);
		
		Set<String> t4 = new HashSet<>();
		t4.addAll(Arrays.asList("a","c","b"));
		inputList.add(t4);
		
		Set<String> t5 = new HashSet<>();
		t5.addAll(Arrays.asList("a","c","b","d"));
		inputList.add(t5);
		
		Set<String> t6 = new HashSet<>();
		t6.addAll(Arrays.asList("e"));
		inputList.add(t6);
		
		
		Map<Set<String>, Integer> expectedResult = new HashMap<>();
		Set<String> rset1 = new HashSet<>(Arrays.asList("a","b","c"));
		expectedResult.put(rset1, 3);
		
		AprioriImpl apriori = new AprioriImpl(inputList, 3);
		
		Map<Set<String>, Integer> result = apriori.calculateFrequentItemSets();
		
		Assert.assertEquals(expectedResult, result);
		
	}

}
