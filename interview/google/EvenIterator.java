


/**
 * 包装一个普通的Iterator，只返回even number。要求实现next()和hasNext()方法
 */

public class EvenIterator<T> implements Iterator<T>{
  Iterator<T> it;
  T currItem;
  public EvenIterator(Iterator<T> it){
    this.it = it;
    advanceItem();
  }

  public boolean hasNext(){
    return it.hasNext();
  }

  public T next(){
    T curr = it.next();
    if(it.hasNext()){
      it.next();
    }else{
      
    }
    return curr;
  }
  
  @Override
  public void remove(){
    throw new UnsupportedOperationException();
  }

  private void advanceItem(){
    if(it.hasNext()){
      it.next();
    } 
  }
}
