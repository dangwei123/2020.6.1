牛牛从小就有收集魔法卡的习惯，他最大的愿望就是能够集齐 k 种不同种类的魔法卡，现在有 n 张魔法卡，这 n 张魔法卡存在于一维坐标点上，
每张魔法卡可能属于某一种类。牛牛如果想收集魔法卡就需要从当前坐标点跳跃到另外一个魔法卡所在的坐标点，花费的代价是两个跳跃坐标点之间的距离差。
牛牛可以从任意的坐标点出发，牛牛想知道他集齐 k 种魔法卡所花费的最小代价是多少，如果集不齐 k 种魔法卡，输出-1。
第一行输入两个整数 n,k, 分别表示魔法卡的个数和种类个数。
接下来有n行，每行两个数x，y 分别表示属于哪一种魔法卡和魔法卡所在的坐标
import java.util.*;


public class Solution {
    /**
     * 
     * @param n int整型 
     * @param k int整型 
     * @param card int整型二维数组 
     * @return int整型
     */
    public int solve (int n, int k, int[][] card) {
        // write code here
        if(card.length==0||n<k) return -1;
        Arrays.sort(card,(o1,o2)->{
            if(o1[1]==o2[1]) return o1[0]-o2[0];
            return o1[1]-o2[1];
        });
        Map<Integer,Integer> map=new HashMap<>();
        int left=0;
        int right=0;
        int min=Integer.MAX_VALUE;
        while(right<card.length){
            map.put(card[right][0],map.getOrDefault(card[right][0],0)+1);
            while(map.get(card[left][0])>1){
                int num=map.get(card[left][0]);
                if(num==1){
                    map.remove(card[left][0]);
                }else{
                    map.put(card[left][0],num-1);
                }
                left++;
            }
            
            if(map.size()==k){
                min=Math.min(min,card[right][1]-card[left][1]);
            }
            right++;
        }
        
        return min==Integer.MAX_VALUE?-1:min;
    }
}


单词消消乐
import java.util.*;


public class Solution {
    /**
     * 
     * @param Words string字符串一维数组 
     * @return string字符串
     */
    public String WordsMerge (String[] Words) {
        // write code here
        if(Words.length==0) return "";
        if(Words.length==1) return Words[0];
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<Words[0].length();i++){
            stack.push(Words[0].charAt(i));
        }
        for(int i=1;i<Words.length;i++){
            int j=0;
            while(!stack.isEmpty()&&j<Words[i].length()&&stack.peek()==Words[i].charAt(j)){
                stack.pop();
                j++;
            }
            while(j<Words[i].length()){
                stack.push(Words[i].charAt(j++));
            }
        }
        StringBuilder sb=new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return new String(sb.reverse());
    }
}


给出n个只包含小写字母'a'~'z'的字符串，我们称一个字符串s为原根，当且仅当给出的其他任何字符串都不是它的前缀。

现在牛牛想知道给出的字符串中有多少个原根。
（相同字符串互为前缀）
import java.util.*;


public class Solution {
    /**
     * 原根
     * @param n int整型 
     * @param s string字符串一维数组 
     * @return int整型
     */
    
    
    public int solve (int n, String[] s) {
        // write code here
        if(n<=1) return n;
        Trie t=new Trie();
        for(String str:s){
            t.insert(str);
        }
        return t.res;
    }
    
}

class Trie{
    Node root=new Node();
    int res;
    public Trie(){
        
    }
     
    public void insert(String s){
        Node cur=root;
        for(int i=0;i<s.length();i++){
            int index=s.charAt(i)-'a';
            if(cur.arr[index]==null){
                cur.arr[index]=new Node();
            }
            cur=cur.arr[index];
            if(cur.count==1){
                res--;
                cur.count=3;
            } 
        }
        cur.count++;
        if(cur.count==1) res++;
        else if(cur.count==2) res--;
    }
    
    
}

class Node{
    Node[] arr=new Node[26];
    int count;
}


定义两个字符串{s}s,{t}t的相似度{f(s,t)}f(s,t)为他们的最长公共前缀长度，求任意两个字符串的相似度
import java.util.*;


public class Solution {
    /**
     * 相似和
     * @param n int整型 
     * @param s string字符串一维数组 
     * @return long长整型
     */
    public long solve (int n, String[] s) {
        // write code here
        if(n<=1) return n;
        Trie t=new Trie();
        for(String str:s){
            t.insert(str);
        }
        return t.res;
    }
}


class Trie{
    Node root=new Node();
    long res;
    public void insert(String s){
        Node cur=root;
        for(int i=0;i<s.length();i++){
            int index=s.charAt(i)-'a';
            if(cur.arr[index]==null){
                cur.arr[index]=new Node();
            }
            cur=cur.arr[index];
            res+=cur.count;
            cur.count++;
            
        }
    }
}

class Node{
    int count;
    Node[] arr=new Node[26];
}










牛牛作为牛客王国的探险先锋，来到了一片新的大陆。

这是一个工业化程度很高的大陆，遍地都是工厂，有些工厂之间有管道相连。

这片大陆一共有n个工厂，有n-1对工厂之间有管道相连，因为工厂之间需要合作，所以这n-1个管道保证任意两个工厂都可以通过管道互相抵达。

牛牛发现，从这片大陆开始工业化以来，一共发生了m次原始生产力提升。每一次原始生产力提升在一个工厂u发生，它会让工厂u以及和工厂u直接通过管道相连的工厂的生产力加1。

每个工厂最开始的生产力都是0。

现在牛牛知道了m次生产力提升发生的工厂位置。牛牛想知道这m次提升发生之后每个工厂的生产力是多少。

public class Solution {
    /**
     * 扩散
     * @param n int整型 
     * @param m int整型 
     * @param u int整型一维数组 
     * @param v int整型一维数组 
     * @param q int整型一维数组 
     * @return int整型一维数组
     */
    public int[] solve (int n, int m, int[] u, int[] v, int[] q) {
        // write code here
        ArrayList<Integer>[] map=new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            map[i]=new ArrayList<>();
        }
        for(int i=0;i<n-1;i++){
            map[u[i]].add(v[i]);
            map[v[i]].add(u[i]);
        }
        
       int p[] = new int[n+1];
        for (int i=0;i<m;++i) {
            p[q[i]]++;
        }
 
        int ret[] = new int[n];
        for (int i=1;i<=n;++i) {
            if(p[i]!=0){
            ret[i-1]+=p[i];
            for(int o:map[i]) {
                ret[o-1]+=p[i];
            }
            }
        }
        return ret;
    }
    
}
