package queue.prod.cons;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyQueue <E>{
	private Queue<E> q1;
	private int cap;
	private ReentrantLock lock=new ReentrantLock(true);
	private Condition notEmpty=lock.newCondition();
	private Condition notFull=lock.newCondition();
	MyQueue(int cap){
		q1=new LinkedList<E>();
		this.cap=cap;
	}
	public void put(E e) throws InterruptedException {
		lock.lock();
		try {
			if(q1.size()==cap) {
				System.out.println("Queue is Full");
				notFull.await();
			}
			q1.add(e);
			notEmpty.signalAll();
		}finally {
			lock.unlock();
		}
	}
	public E take() throws InterruptedException {
		lock.lock();
		try {
			while(q1.size()==0) {
				System.out.println("Queue is Empty");
				notEmpty.await();
			}
			E item=q1.remove();
			notFull.signalAll();
			return item;
		}finally {
			lock.unlock();
		}
	}
}
