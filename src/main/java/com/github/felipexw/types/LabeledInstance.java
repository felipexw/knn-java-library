package com.github.felipexw.types;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class LabeledInstance<L, F> extends Instance<F> {
  protected final L label;
  private int count;

  public LabeledInstance(L label) {
    this.label = label;
  }

  public L getLabel() {
    return this.label;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getCount() {
    return count;
  }
}
