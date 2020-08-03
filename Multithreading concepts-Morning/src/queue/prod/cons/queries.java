package queue.prod.cons;

public class queries {
	public static void main(String[] args) {
		  MyQueue q2 = new MyQueue(5);
		  // Creating two threads
		  Thread producer = new Thread(new Producer(q2));
		  Thread consumer = new Thread(new Consumer(q2)); 
		  // starting threads
		  producer.start();
		  consumer.start();
		 }
		}

		class Producer implements Runnable {
		  private MyQueue q2;    
		  public Producer(MyQueue q2){
		    this.q2 = q2;
		  }
		  @Override
		  public void run() {
		    for (int i = 1; i <= 4; i++) {
		      try {
		        //Thread.sleep(10);                            
		        q2.put(i);                            
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		    }
		  }
		}

		class Consumer implements Runnable {
			private MyQueue q2;    
		  public Consumer(MyQueue q2){
		    this.q2 = q2;
		  }
		  @Override
		  public void run() {
		    for (int i = 1; i <= 3; i++) {
		      try {
		        // Thread.sleep(500);
		        q2.take();               
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		    }
		  }
}
