package Interfaces;

import java.util.ArrayList;

public interface IObservable {


    void add(IObserver x);
    void remove(IObserver x);
    void notifyObservers();

}
