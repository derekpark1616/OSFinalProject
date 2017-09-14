/** SJFSchedulingAlgorithm.java
 * 
 * A shortest job first scheduling algorithm.
 *
 * @author: Charles Zhu
 * Spring 2016
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

import com.jimweller.cpuscheduler.Process;

public class SJFSchedulingAlgorithm extends BaseSchedulingAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {

	boolean preemptive = false;
	private Vector<Process> jobs;

	SJFSchedulingAlgorithm() {
		activeJob = null;
		jobs = new Vector<Process>();
	}

	/** Add the new job to the correct queue. */
	public void addJob(Process p) {
		jobs.add(p);
		//sort jobs by burst time
		sort(jobs);
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
		//if preemptive, the jobs are resorted after every new job so we can just grab fist element
		if (preemptive) {
			return jobs.firstElement();
		} else {
			//if nonpreemptive, store current job and continuously return it until it is done executing
			if (activeJob == null || activeJob.getBurstTime() < 1) {
				activeJob = jobs.firstElement();
				return activeJob;
			} else {
				return activeJob;
			}

		}

	}

	public String getName() {
		return "Shortest Job First";
	}

	/**
	 * @return Value of preemptive.
	 */
	public boolean isPreemptive() {
		return preemptive;
	}

	/**
	 * @param v
	 *            Value to assign to preemptive.
	 */
	public void setPreemptive(boolean v) {
		preemptive = v;
	}

	//function to sort jobs by burst time
	public void sort(Vector<Process> jobs) {
		Collections.sort(jobs, new Comparator<Process>() {
			@Override
			public int compare(Process a, Process b) {
				if (a.getBurstTime() < b.getBurstTime())
					return -1;
				else if (a.getBurstTime() == b.getBurstTime()) {
					if (a.getPID() < b.getPID())
						return -1;
					else
						return 1;
				}

				else
					return 1;
			}
		});
	}

}