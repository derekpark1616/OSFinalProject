/** PrioritySchedulingAlgorithm.java
 * 
 * A single-queue priority scheduling algorithm.
 *
 * @author: Charles Zhu
 * Spring 2016
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

import com.jimweller.cpuscheduler.Process;

public class PrioritySchedulingAlgorithm extends BaseSchedulingAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {
	
	boolean preemptive = false;
	private Vector<Process> jobs;
    
    PrioritySchedulingAlgorithm(){
        activeJob = null;
        jobs = new Vector<Process>();
    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p){
        jobs.add(p);
        sort(jobs);
    }
    
    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p){
        return jobs.remove(p);
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
    when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
        throw new UnsupportedOperationException();
    }


    /** Returns the next process that should be run by the CPU, null if none available.*/
    public Process getNextJob(long currentTime){
        if(preemptive) {
        	return jobs.firstElement();
        } else {
        	if(activeJob == null || activeJob.getBurstTime() < 1) {
        		activeJob = jobs.firstElement();
        		return activeJob;
        	} else {
        		return activeJob;
        	}
        }
    }

    public String getName(){
        return "Single-Queue Priority";
    }

    /**
     * @return Value of preemptive.
     */
    public boolean isPreemptive(){
        return preemptive;
    }
    
    /**
     * @param v Value to assign to preemptive.
     */
    public void setPreemptive(boolean v){
        preemptive = v;
    }
    
    public void sort(Vector<Process> jobs) {
		Collections.sort(jobs, new Comparator<Process>() {
			@Override
			public int compare(Process a, Process b) {
				if (a.getPriorityWeight() < b.getPriorityWeight())
					return -1;
				else if (a.getPriorityWeight() == b.getPriorityWeight()) {
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