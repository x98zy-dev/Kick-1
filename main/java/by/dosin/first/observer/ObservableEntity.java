package by.dosin.first.observer;

public interface ObservableEntity {
    void attach(ArrayObserver observer);
    void detach(ArrayObserver observer);
    void notifyObservers();
}