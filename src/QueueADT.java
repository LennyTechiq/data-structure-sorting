class QueueADT implements Queue {

   final int MAXSIZE = 100;
   private int size;
   private int[] queueADT;
   private int front = 0;
   private int rear = -1;

   public QueueADT () {
     size = MAXSIZE;
     queueADT = new int[size];
   }

  public QueueADT (int inputsize) {
     size = inputsize;
     queueADT = new int[size];
   }

   public boolean empty () {
     return (rear < front);
   }

   public boolean full () {  
     return (rear == size - 1);
   }
   public void enqueue (int number) {
	  
	   	rear++;
	     queueADT[rear] = number;
	   }

	   public int dequeue () {
	     int i = queueADT[front];
	     front++;
	     return i;
	   }

	   public int front () {
	     return queueADT[front];
	   }

	   public int size () {
	      return (rear + 1 - front);
	   }
	}
