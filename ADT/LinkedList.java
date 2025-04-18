/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ADT;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 *
 * @author Ching Keat, Kah Jun
 */
public class LinkedList<T> implements ListInterface<T>, Serializable {  //doubly linked list

    private Node firstNode;
    private Node lastNode;
    private int numberOfEntries;

    public LinkedList() {
        clear();
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);

        if (isEmpty()) {
            firstNode = newNode;
            lastNode = newNode;
        } else {
            lastNode.nextNode = newNode;
            newNode.preNode = lastNode;
            lastNode = newNode;
        }

        numberOfEntries++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            Node newNode = new Node(newEntry);

            if (isEmpty()) {
                firstNode = newNode;
                lastNode = newNode;
            } else if (newPosition == 1) {
                newNode.nextNode = firstNode;
                firstNode.preNode = newNode;
                firstNode = newNode;
            } else {
                Node nodeBefore = firstNode;
                for (int i = 1; i < newPosition - 1; ++i) {
                    nodeBefore = nodeBefore.nextNode;
                }

                newNode.nextNode = nodeBefore.nextNode;
                newNode.preNode = nodeBefore;
                nodeBefore.nextNode = newNode;

                if (newNode.nextNode != null) {
                    newNode.nextNode.preNode = newNode;
                }

                if (newPosition == numberOfEntries + 1) {
                    lastNode = newNode;
                }
            }
            numberOfEntries++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T remove(int givenPosition) {
        if (firstNode == null || givenPosition < 1 || givenPosition > numberOfEntries) {
            System.out.println("Invalid position or empty list");
            return null;
        }

        T result;
        Node nodeToRemove;

        if (givenPosition == 1) {
            result = firstNode.data;
            nodeToRemove = firstNode;
            firstNode = firstNode.nextNode;

            if (firstNode != null) {
                firstNode.preNode = null;
            } else {
                lastNode = null;
            }
        } else if (givenPosition == numberOfEntries) {
            result = lastNode.data;
            nodeToRemove = lastNode;
            lastNode = lastNode.preNode;

            if (lastNode != null) {
                lastNode.nextNode = null;
            } else {
                firstNode = null;
            }
        } else {
            Node nodeBefore = firstNode;
            for (int i = 1; i < givenPosition - 1; i++) {
                nodeBefore = nodeBefore.nextNode;
            }
            nodeToRemove = nodeBefore.nextNode;
            result = nodeToRemove.data;

            nodeBefore.nextNode = nodeToRemove.nextNode;
            nodeToRemove.nextNode.preNode = nodeBefore;
        }

        numberOfEntries--;
        return result;
    }

    @Override
    public void clear() {
        this.firstNode = null;
        this.lastNode = null;
        this.numberOfEntries = 0;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition - 1; ++i) {
                currentNode = currentNode.nextNode;
            }
            currentNode.data = newEntry;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition - 1; ++i) {
                currentNode = currentNode.nextNode;
            }
            result = currentNode.data;
        }

        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.nextNode;
            }
        }
        return found;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public void traverseForward() {
        if (firstNode == null) {
            System.out.println("List is empty.");
            return;
        }
        int n = 1;

        Node current = firstNode;
        System.out.println("Forward Traversal:");
        while (current != null) {
            System.out.print(n + ". ");
            System.out.println(current.data);
            current = current.nextNode;
            n++;
        }
        System.out.println();
    }

    @Override
    public void traverseBackward() {
        if (lastNode == null) {
            System.out.println("List is empty.");
            return;
        }

        int n = numberOfEntries;
        Node current = lastNode;
        System.out.println("Backward Traversal:");
        while (current != null) {
            System.out.print(n + ". ");
            System.out.println(current.data);
            current = current.preNode;
            n--;
        }
        System.out.println();
    }

    @Override
    public void bubbleSort(Comparator<? super T> comparator) {
        if (firstNode == null || firstNode.nextNode == null) {
            return;
        }

        boolean swapped;
        Node lastUnsorted = lastNode;

        do {
            swapped = false;
            Node current = firstNode;

            while (current != lastUnsorted && current.nextNode != null) {
                if (comparator.compare(current.data, current.nextNode.data) > 0) {
                    // Swap data (alternative: swap nodes if you prefer)
                    T temp = current.data;
                    current.data = current.nextNode.data;
                    current.nextNode.data = temp;
                    swapped = true;
                }
                current = current.nextNode;
            }
            lastUnsorted = lastUnsorted.preNode;
        } while (swapped);
    }

    @Override
    public ListInterface<T> filter(Predicate<T> criteria) {
        ListInterface<T> filteredList = new LinkedList<>();
        if (firstNode == null) {
            return filteredList;
        }

        Node current = firstNode;
        while (current != null) {
            if (criteria.test(current.data)) {
                filteredList.add(current.data);
            }
            current = current.nextNode;
        }

        return filteredList;
    }

    @Override
    public int getPosition(T entry) {
        if (firstNode == null) {
            return -1;
        }

        Node current = firstNode;
        int position = 1;

        while (current != null) {
            if (current.data.equals(entry)) {
                return position;
            }
            current = current.nextNode;
            position++;
        }

        return -1;
    }

    @Override
    public T getEntry(T entry) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator getIterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        String outputStr = "";
        Node currentNode = firstNode;
        while (currentNode != null) {
            outputStr += currentNode.data + "\n";
            currentNode = currentNode.nextNode;
        }
        return outputStr;
    }

    @Override
    public ListInterface<T> subList(int from, int to) {
        ListInterface<T> sub = new LinkedList<>();
        if (from < 1 || to > numberOfEntries || from > to) {
            return sub; // return empty if invalid
        }

        Node current = firstNode;
        for (int i = 1; i < from; i++) {
            current = current.nextNode;
        }

        for (int i = from; i <= to && current != null; i++) {
            sub.add(current.data);
            current = current.nextNode;
        }

        return sub;
    }

    @Override
    public boolean anyMatch(java.util.function.Predicate<T> predicate) {
        Node current = firstNode;
        while (current != null) {
            if (predicate.test(current.data)) {
                return true;
            }
            current = current.nextNode;
        }
        return false;
    }

    @Override
    public boolean allMatch(java.util.function.Predicate<T> predicate) {
        Node current = firstNode;
        while (current != null) {
            if (!predicate.test(current.data)) {
                return false;
            }
            current = current.nextNode;
        }
        return true;
    }

    private class Node implements Serializable {

        private T data;
        private Node preNode;
        private Node nextNode;

        public Node() {
        }

        public Node(T data, Node nextNode, Node preNode) {
            this.data = data;
            this.nextNode = nextNode;
            this.preNode = preNode;
        }

        public Node(T data) {
            this.data = data;
            this.nextNode = null;
            this.preNode = null;
        }
    }

}
