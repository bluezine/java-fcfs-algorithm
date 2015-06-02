package kr.co.bluezine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Main {
	public static void main(String args[]) {
		new Main().go();
	}
	
	public void go() {
		System.out.println("*****************************************************");
		System.out.println("*                                                   *");
		System.out.println("*                  FCFS Algorithm                   *");
		System.out.println("*                                                   *");
		System.out.println("*****************************************************");

		int currentTime = 0;
		
		ArrayList<Job> jobList = new ArrayList<Job>();
		for (int i=0;i<5;i++) {
			Random ran = new Random();
			int ranNum = 0;
			while(ranNum == 0)
				ranNum = ran.nextInt(5);
			jobList.add(new Job("Job" + (i+1), ranNum, 0));
		}
		
		System.out.println("                     Job List");
		for (Job j : jobList) {
			System.out.println("JobName : " + j.name + "\tBurstTime : " + j.runTime + "\tApproachTime : " + j.approachTime);
		}
		
		System.out.println("=====================================================");
		Collections.sort(jobList, new ShortestJob());
		System.out.print("Sorting...\t");
		System.out.println("complete!");
		System.out.println("=====================================================");
		while(jobList.size() != 0) {
			currentTime++;
			System.out.println("Current Time : " + currentTime + "s");
			System.out.print("Remain Job List : ");
			for(Job j : jobList) {
				System.out.print(j.name+ "(" + j.runTime + ")  ");
			}
			System.out.println();
			jobList.get(0).runTime--;
			if (jobList.get(0).runTime <= 0) {
				System.out.println(jobList.get(0).name + " Complete!");
				jobList.remove(0);
			}
			System.out.println("=====================================================");
		}
	}
	
	class Job {
		String name;
		int runTime;
		int approachTime;
		
		public Job(String name, int runTime, int approachTime) {
			this.name = name;
			this.runTime = runTime;
			this.approachTime = approachTime;
		}
	}

	class ShortestJob implements Comparator<Job> {

		@Override
		public int compare(Job o1, Job o2) {
			return Integer.compare(o1.runTime, o2.runTime);
		}
		
	}
}