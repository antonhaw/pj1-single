package impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AprioriImpl {

	private Integer minSupport;
	private List<Set<String>> dataset;

	// private List<List<String>> candidates;

	public AprioriImpl(List<Set<String>> dataset, Integer minSupport) {
		this.dataset = dataset;
		this.minSupport = minSupport;
	}

	public static void main(String[] args) {

		// Set<String> power = new HashSet<>(Arrays.asList("I3", "I4", "I5"));
		// for(Set<String> s : getSetsWithLenghth(powerSet(power),3)){
		// System.out.println(s);
		// }
	}

	public Map<Set<String>, Integer> calculateFrequentItemSets() {
		Map<Set<String>, Integer> result = generateCandidatesFirst();
		Map<Set<String>, Integer> temp = result;
		while (!result.isEmpty()) {
			System.out.println("--------------");
			temp = result;
			result = generateCandidatesRest(result);
		}
		return temp;
	}

	private Map<Set<String>, Integer> generateCandidatesFirst() {
		Map<Set<String>, Integer> tempCandidates = new HashMap<>();
		for (Set<String> set : dataset) {
			for (String s : set) {
				Set<String> newSet = new HashSet<>();
				newSet.add(s);
				if (tempCandidates.containsKey(newSet)) {
					tempCandidates.put(newSet, tempCandidates.get(newSet) + 1);
				} else {
					tempCandidates.put(newSet, 1);
				}
			}
		}
		return checkSupport(tempCandidates);
	}

	// TODO: �berpr�fen, ob das auch mit Second l�uft. Testen. Durchgehen und
	// pr�fen, ob das korrekt ist.
	private Map<Set<String>, Integer> generateCandidatesRest(
			Map<Set<String>, Integer> candidates) {
		System.out.println("candidates: " + candidates);
		System.out.println("data: " + dataset);
		Set<Set<String>> newCandidates = new HashSet<>();
		
		//Generate new candidates
		for (Set<String> itemset : candidates.keySet()) {
			for (Set<String> otherset : candidates.keySet()) {
				if (!otherset.equals(itemset)) {
					Set<String> current = new HashSet<>(itemset);
					current.addAll(otherset);
					// System.out.println("current: "+current);
					if (current.size() == otherset.size() + 1) {
						newCandidates.add(current);
					}
				}
			}
		}
		System.out.println("newCandidates: " + newCandidates);

		Map<Set<String>, Integer> tempCandidates = new HashMap<>();
		for (Set<String> cand : newCandidates) {
			Set<Set<String>> subsets = getSetsWithLenghth(powerSet(cand), cand.size() - 1);
			for (Set<String> s : subsets) {
				// Prunning
				if (candidates.containsKey(s)) {
					Integer support = getSupportOfSet(cand, dataset);
					tempCandidates.put(cand, support);
				}
			}

		}
		System.out.println("tempCandidates: " + tempCandidates);
		return checkSupport(tempCandidates);
	}

	private Map<Set<String>, Integer> checkSupport(
			Map<Set<String>, Integer> candidates) {
		Map<Set<String>, Integer> result = new HashMap<>();
		for (Entry<Set<String>, Integer> e : candidates.entrySet()) {
			Set<String> candidate = e.getKey();
			Integer support = e.getValue();
			if (support >= minSupport) {
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

	private Set<Set<String>> getSetsWithLenghth(Set<Set<String>> sets,
			int length) {
		Set<Set<String>> result = new HashSet<>();
		for (Set<String> s : sets) {
			if (s.size() == length)
				result.add(s);
		}
		return result;
	}

	private Integer getSupportOfSet(Set<String> current, List<Set<String>> data) {
		// Map<Set<String>, Integer> result = new HashMap<>();
		Integer result = 0;
		for (Set<String> dataset : data) {
			if (dataset.containsAll(current))
				result++;
		}

		return result;
	}
}
