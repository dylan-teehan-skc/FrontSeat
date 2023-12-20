package MapAndTaxis;

public class MyArrayList<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;

    //Initializes the array with a capacity
    public MyArrayList() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    //Adds an element to the list
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    //Gets the element at a specified index
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    //Returns the current size of the list
    public int size() {
        return size;
    }

    // Ensures that the array has sufficient capacity and can doubles the capacity if needed
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }
}
