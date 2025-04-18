/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ADT;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;


/**
 *
 * @author Ching Keat, Kah Jun
 */
public interface ListInterface<T> {
    
    public boolean add(T newEntry);
    public boolean add(int newPosition, T newEntry);
    public T remove(int givenPosition);
    public void clear();
    public boolean replace(int givenPosition, T newEntry);
    public T getEntry(T entry);
    public T getEntry(int givenPosition);
    public boolean contains(T anEntry);
    public int getNumberOfEntries();
    public boolean isEmpty();
    public boolean isFull();
    public Iterator getIterator();
    
    public int getPosition(T entry);
    ListInterface<T> filter(Predicate<T> criteria);
    public void traverseForward();
    public void traverseBackward();
    public void bubbleSort(Comparator<? super T> comparator);

    public ListInterface<T> subList(int from, int to);
    public boolean anyMatch(java.util.function.Predicate<T> predicate);
    public boolean allMatch(java.util.function.Predicate<T> predicate);


}
