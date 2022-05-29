package org.csc133.a2.gameobjects;


import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class GameObjectCollection<T> extends GameObject implements Iterator<T> {
    CopyOnWriteArrayList<T> gameObjects;

    class GameObjectIterator implements Iterator<T>{
        int index=0;
        @Override
        public boolean hasNext() {
            return index < gameObjects.size();
        }

        @Override
        public T next() {
            return gameObjects.get(index++);
        }
    }
    public GameObjectCollection(){
        gameObjects=new CopyOnWriteArrayList<>();
    }
    public void add(T gameObject){
        gameObjects.add(gameObject);
    }
    public void remove(T gameObject){
        gameObjects.remove(gameObject);
    }
    public int Size(){
        return gameObjects.size();
    }
}
