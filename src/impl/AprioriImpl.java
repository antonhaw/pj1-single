package impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class AprioriImpl {
	
	private static final Integer MIN_SUPPORT = 2;
	
	private List<Set<String>> dataset;
	//private List<List<String>> candidates;

	public AprioriImpl(List<Set<String>> dataset) {
		this.dataset = dataset;
	}

	public static void main(String[] args) {
		
		Set<String> power = new HashSet<>(Arrays.asList("I3", "I4", "I5"));
		for(Set<String> s : getSetsWithLenghth(powerSet(power),3)){
			System.out.println(s);
		}
	}
	
	
	public static Map<Set<String>, Integer> generateCandidates(List<Set<String>> data){
		Map<Set<String>, Integer> result = generateCandidatesFirst(data);
		Map<Set<String>, Integer> temp = result;
		while(!result.isEmpty()){
			System.out.println("--------------");
			temp = result;
			result = generateCandidatesRest(result, data);
		}
		return temp;
		
	}

	public static Map<Set<String>, Integer> generateCandidatesFirst(List<Set<String>> candidates){
//		System.out.println("Input: "+candidates);
		Map<Set<String>, Integer> tempCandidates = new HashMap<>();
		for(Set<String> set : candidates){
//			System.out.println("--------------------");
//			System.out.println(set);
			for(String s : set){
				Set<String> newSet = new HashSet<>();
				newSet.add(s);
//				System.out.println(newSet);
				if(tempCandidates.containsKey(newSet)){
					tempCandidates.put(newSet, tempCandidates.get(newSet) + 1);
				} else{
					tempCandidates.put(newSet, 1);
				}
			}
		}
//		System.out.println(tempCandidates);
		return checkApriori(tempCandidates);
	}
	
	
	//TODO: Überprüfen, ob das auch mit Second läuft. Testen. Durchgehen und prüfen, ob das korrekt ist.
	public static Map<Set<String>, Integer> generateCandidatesRest(Map<Set<String>, Integer> candidates, List<Set<String>> data){
		System.out.println("candidates: "+candidates);
		System.out.println("data: "+data);
		Set<Set<String>> newCandidates = new HashSet<>();
		for(Set<String> itemset: candidates.keySet()){
			for(Set<String> otherset: candidates.keySet()){
				if(!otherset.equals(itemset)){
				Set<String> current = new HashSet<>(itemset);
				current.addAll(otherset);
				//System.out.println("current: "+current);
				newCandidates.add(current);
				}
			}
		}
		System.out.println("newCandidates: "+newCandidates);
		
		Map<Set<String>, Integer> tempCandidates = new HashMap<>();
		for(Set<String> cand : newCandidates){
			Set<Set<String>> subsets = getSetsWithLenghth(powerSet(cand), cand.size()-1);
			boolean frequent = true;
			for(Set<String> s : subsets	){
				//Prunning
//				if(candidates.containsKey(s)){
					Integer support = getSupportOfSet(s, data);
					//System.out.println("support: "+support+" map: "+candidates.get(s));
					if(support<MIN_SUPPORT) frequent = false;
//				}
			}
			if(frequent){
				Integer support = getSupportOfSet(cand, data);
				tempCandidates.put(cand, support);
			}
			
		}
		System.out.println("tempCandidates: "+tempCandidates);
		return checkApriori(tempCandidates);
	}
	
	// CHECk min support, not apriori
	private static Map<Set<String>, Integer> checkApriori(Map<Set<String>, Integer> candidates){
		Map<Set<String>, Integer> result = new HashMap<>();
		for(Entry<Set<String>, Integer> e : candidates.entrySet()){
			Set<String> candidate = e.getKey();
			Integer support = e.getValue();
			if(support>=MIN_SUPPORT){
				result.put(candidate, support);
			}
		}
		
		return result;
	}
	
	public static Set<Set<String>> powerSet(Set<String> originalSet) {
        Set<Set<String>> sets = new HashSet<>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<String>());
            return sets;
        }
        List<String> list = new ArrayList<>(originalSet);
        String head = list.get(0);
        Set<String> rest = new HashSet<>(list.subList(1, list.size()));
        for (Set<String> set : powerSet(rest)) {
            Set<String> newSet = new HashSet<>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }
	
	public static Set<Set<String>> getSetsWithLenghth(Set<Set<String>> sets, int length){
		Set<Set<String>> result = new HashSet<>();
		for(Set<String> s : sets){
			if(s.size()==length) result.add(s);
		}
		return result;
	}
	
	
	public static Integer getSupportOfSet(Set<String> current, List<Set<String>> data){
		//Map<Set<String>, Integer> result = new HashMap<>();
		Integer result = 0;
		for(Set<String> dataset : data){
			if(dataset.containsAll(current)) result++;
		}
		
		return result;
	}
}
