/** RoundRobinSchedulingAlgorithm.java
 * 
 * A scheduling algorithm that randomly picks the next job to go.
 *
 * @author: Kyle Benson
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class RoundRobinSchedulingAlgorithm extends BaseSchedulingAlgorithm {

	/** the time slice each process gets */
	private int quantum;
	private Vector<Process> jobs;
	private long endTime;
	private int jobIndex;

	RoundRobinSchedulingAlgorithm() {
		quantum = 10;
		activeJob = null;
		jobIndex = 0;
		jobs = new Vector<Process>();
	}

	/** Add the new job to the correct queue. */
	public void addJob(Process p) {
		jobs.add(p);
		sort(jobs);
	}

	/** Returns true if the job was present and was removed. */
	public boolean removeJob(Process p) {
		if(jobs.remove(p)) {
			System.out.println("Removing " + p.getPID() + " at index " + jobIndex);
			jobIndex--;
			return true;
		}	
		return false;
	}

	/**
	 * Transfer all the jobs in the queue of a SchedulingAlgorithm to another,
	 * such as when switching to another algorithm in the GUI
	 */
	public void transferJobsTo(SchedulingAlgorithm otherAlg) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Get the value of quantum.
	 * 
	 * @return Value of quantum.
	 */
	public int getQuantum() {
		return quantum;
	}

	/**
	 * Set the value of quantum.
	 * 
	 * @param v
	 *            Value to assign to quantum.
	 */
	public void setQuantum(int v) {
		this.quantum = v;
	}

	/**
	 * Returns the next process that should be run by the CPU, null if none
	 * available.
	 */
	public Process getNextJob(long currentTime) {
		/*if (activeJob == null) {
			activeJob = jobs.remove(0);
			// jobs.add(activeJob);
			if (activeJob.getBurstTime() - quantum < 0) {
				endTime = 0;
			} else {
				endTime = activeJob.getBurstTime() - quantum;
				jobs.add(activeJob);
			}
			System.out.println("1: PID:  " + activeJob.getPID() + " " + endTime);
			return activeJob;
		} else {
			if (activeJob.getBurstTime() == endTime) {
				activeJob = jobs.remove(0);
				// jobs.add(activeJob);
				if (activeJob.getBurstTime() - quantum < 0) {
					endTime = 0;
				} else {
					endTime = activeJob.getBurstTime() - quantum;
					jobs.add(activeJob);
				}
				System.out.println("PID: " + activeJob.getPID() + " " + endTime);
				return activeJob;
			} else {
				return activeJob;
			}
		}*/
		if(activeJob == null) {
			activeJob = jobs.get(jobIndex);
			if (activeJob.getBurstTime() - quantum < 0) {
				endTime = 0;
			} else {
				endTime = activeJob.getBurstTime() - quantum;
			}
			return activeJob;
		} else {
			if (activeJob.getBurstTime() == endTime) {
				jobIndex = ((jobIndex + 1) %jobs.size());
				activeJob = jobs.get(jobIndex);
				if (activeJob.getBurstTime() - quantum < 0) {
					endTime = 0;
				} else {
					endTime = activeJob.getBurstTime() - quantum;
				}
				return activeJob;
			} else {
				return activeJob;
			}
		}
		
	}

	public String getName() {
		return "Round Robin";
	}

	public void sort(Vector<Process> jobs) {
		Collections.sort(jobs, new Comparator<Process>() {
			@Override
			public int compare(Process a, Process b) {
				if (a.getPID() < b.getPID())
					return -1;
				else
					return 1;
			}
		});
	}

}