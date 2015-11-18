package com.zappos.json.util;

import java.util.Arrays;

/**
 * Trimmed version of ArrayList
 * 
 * @author hussachai
 *
 */
public class ObjectArray {

  private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

  private static final int DEFAULT_CAPACITY = 10;

  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

  transient Object[] elementData;

  transient int modCount = 0;

  private int size;

  private void ensureExplicitCapacity(int minCapacity) {
    modCount++;
    // overflow-conscious code
    if (minCapacity - elementData.length > 0)
      grow(minCapacity);
  }

  private void ensureCapacityInternal(int minCapacity) {
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
      minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
    }

    ensureExplicitCapacity(minCapacity);
  }

  private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity - minCapacity < 0)
      newCapacity = minCapacity;
    if (newCapacity - MAX_ARRAY_SIZE > 0)
      newCapacity = hugeCapacity(minCapacity);
    // minCapacity is usually close to size, so this is a win:
    elementData = Arrays.copyOf(elementData, newCapacity);
  }

  private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) // overflow
      throw new OutOfMemoryError();
    return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
  }

  public boolean add(Object e) {
    ensureCapacityInternal(size + 1); // Increments modCount!!
    elementData[size++] = e;
    return true;
  }

  public Object get(int index) {
    if (index >= size)
      throw new IndexOutOfBoundsException(
          "Index: " + index + ", Size: " + size);
    return elementData[index];
  }

  public int size() {
    return size;
  }

  public Object[] toArray() {
    return Arrays.copyOf(elementData, size);
  }

  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] a) {
    return (T[]) Arrays.copyOf(elementData, size, a.getClass());
  }

}
