/**
 * A class for testing the amortized doubling of the capacity of a Message Queue
 */


public class MessageQueueTester
{

    /**
     * Creates a queue, adds messages, and prints the size and capacity
     * @arg args unused
     */
    public static void main(String[] args)
    {
        MessageQueue q = new MessageQueue(2);

        System.out.println(q.size() + " : " + q.getCapacity());

        for (int i = 0; i < 17; i++)
        {
            q.add(new Message("HELLO"));
            System.out.println(q.size() + " : " + q.getCapacity());
        }
    }
}

