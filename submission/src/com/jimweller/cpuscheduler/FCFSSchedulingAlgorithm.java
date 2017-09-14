/** FCFSSchedulingAlgorithm.java
 * 
 * A first-come first-served scheduling algorithm.
 *
 * @author: Charles Zhu
 * Spring 2016
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class FCFSSchedulingAlgorithm extends BaseSchedulingAlgorithm {

	private Vector<Process> jobs;

	FCFSSchedulingAlgorithm() {
		activeJob = null;
		jobs = new Vector<Process>();
	}

	/** Add the new job to the correct queue. */
	public void addJob(Process p) {
		System.out.println(jobs.capacity());
		int i = jobs.size() - 1;
		if (jobs.size() > 0) {
			
			while ((jobs.get(i).getArrivalTime() == p.getArrivalTime())
					&& (p.getPID() < jobs.get(i).getPID())) {
				if (--i < 0) {
					jobs.add(0, p);
					break;
				}
			}

		} 
		jobs.add(i+1, p);
		
	}

	/** Returns true if the job was present and was removed. */
	public boolean removeJob(Process p) {
		return jobs.remove(p);
	}

	/**
	 * Transfer all the jobs in the queue of a SchedulingAlgorithm to another,
	 * such as when switching to another algorithm in the GUI
	 */
	public void transferJobsTo(SchedulingAlgorithm otherAlg) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the next process that should be run by the CPU, null if none
	 * available.
	 */
	public Process getNextJob(long currentTime) {
		return jobs.firstElement();
	}

	public String getName() {
		return "First-Come First-Served";
	}

}