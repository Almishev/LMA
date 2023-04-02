package com.library.contract;

import java.util.List;

public abstract class AccessorCRUDAble<E> {


  public  abstract List<String>  readAll();

  public   abstract void overWriteFile(String items);

  public   abstract void add(String string);


}
