import java.util.*;
import java.io.*;

class Student implements Comparable<Student>{
	
	int classNo;
	int power;
	
	public Student(int classNo, int power) {
		this.classNo = classNo;
		this.power = power;
	}
	
	public int compareTo(Student other) {
		return power - other.power;
	}
}

public class Main {
	
	public static int n, m;
	public static List<Student>[] studentList;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	studentList = new List[n];
    	for(int i = 0; i < n; i++) {
    		studentList[i] = new ArrayList<>();
    	}
    	
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < m; j++) {
    			studentList[i].add(new Student(i, Integer.parseInt(st.nextToken())));
    		}
    		Collections.sort(studentList[i]);
    	}
    	
    	int answer = Integer.MAX_VALUE;
    	int[] idx = new int[n];
    	int maxPower = 0;
    	
    	PriorityQueue<Student> pq = new PriorityQueue<>();
    	for(int i = 0; i < n; i++) {
    		maxPower = Math.max(maxPower, studentList[i].get(0).power);
    		pq.offer(studentList[i].get(0));
    	}
    	
    	while(!pq.isEmpty()) {
    		
    		// idx가 가리키는 학생 중 가장 최소 능력치를 가진 학생
    		Student now = pq.poll();
    		
    		int diff = maxPower - now.power;
    		answer = Math.min(answer, diff);
    		
    		// idx를 큰쪽으로 옮기고 큐에 넣기, maxPower 업뎃
    		idx[now.classNo]++;
    		
    		if(idx[now.classNo] == m) break;
    		
    		pq.offer(studentList[now.classNo].get(idx[now.classNo]));
    		maxPower = Math.max(maxPower, studentList[now.classNo].get(idx[now.classNo]).power);
    		
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
}