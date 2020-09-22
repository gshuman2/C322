/**
 A first-in, first-out bounded collection of messages.
 */
public class MessageQueue
{
    /**
     Constructs an empty message queue.
     @param capacity the maximum capacity of the queue
     @precondition capacity > 0
     */

    public MessageQueue(int capacity)
    {
        assert capacity != -1;
        elements = new Message[capacity];
        count = 0;
        head = 0;
        tail = 0;
    }

    /**
     Remove message at head.
     @return the message that has been removed from the queue
     @precondition size() > 0
     */
    public Message removeFirst()
    {
        Message r = elements[head];
        head = (head + 1) % elements.length;
        count--;
        return r;
    }

    /**
     Append a message at tail.
     @param aMessage the message to be appended
     */
    public void add(Message aMessage)
    {
        // precondition size() < getCapacity(); no longer required
        if (count == elements.length)  // ie size() == getCapacity()
        {
            // Double the array size (amortized doubling)
            int newSize = 2 * elements.length;
            Message[] newElements = new Message[newSize];
            int i = 0;
            for (Message e : elements)
            {
                newElements[i] = e;
                i++;
            }
            elements = newElements;
        }

        elements[tail] = aMessage;
        tail = (tail + 1) % elements.length;
        count++;
    }

    /**
     Get the total number of messages in the queue.
     @return the total number of messages in the queue
     */
    public int size()
    {
        return count;
    }

    /**
     Get the maximum number of messages in the queue.
     @return the capacity of the queue
     */
    public int getCapacity()
    {
        return elements.length;
    }


    /**
     Get message at head.
     @return the message that is at the head of the queue
     @precondition size() > 0
     */
    public Message getFirst()
    {
        return elements[head];
    }

    private Message[] elements;
    private int head;
    private int tail;
    private int count;
}