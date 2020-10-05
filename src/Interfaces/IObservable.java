package Interfaces;

import java.util.ArrayList;

public interface IObservable {

    ArrayList<IObserver> views = new ArrayList<>();

    void add(IObserver x);
    void remove(IObserver x);
    void notifyObservers();

}
