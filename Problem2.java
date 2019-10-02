//Time : O(n^3)
//Space : O(n)
//Leetcode : Failed for last case

//Approach:
//1.Since we need to return lowest index in case of a tie, sort the initial array.
//2.Add all these to a set.
//3.Now for each value in initial remove that from set (now this node is not infected) and iterate over graph
//4.Now find total number of infected nodes and update min and minindex respectively.Finally add removed node.
//5.Repeat step 3 and 4 for all values in initial array and return node with minimum number of infected nodes.
class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        int count;
        HashSet<Integer> set = new HashSet<>();
        Arrays.sort(initial);
        boolean[] malware ;
        for(int i:initial)
            set.add(i);
        for(int a = 0;a<initial.length;a++){
            set.remove(initial[a]);
            HashSet<Integer> dupe = new HashSet<>(set);
            malware = new boolean[graph.length];
            for(int i = 0;i<graph.length;i++){
                for(int j = 0;j<graph[0].length;j++){
                    if(i == j || graph[i][j]!=1)
                        continue;
                    if(dupe.contains(i) || dupe.contains(j)){
                        malware[i] = true;
                        malware[j] = true;
                        dupe.add(i);
                        dupe.add(j);
                    }
                }
            }
            count = 0;
            for(boolean b:malware)
                if(b) count++;
            if(count<min){
                min = count;
                index = initial[a];
            }
            set.add(initial[a]);
        }
        return index;
    }
}
