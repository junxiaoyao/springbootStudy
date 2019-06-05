package TestE;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Date;

public class TestSoftRe {

  public static void main(String[] args)throws Exception {
    WeakReference[] reference=new WeakReference[20];
    long bT=System.currentTimeMillis();
    for (int i=0;i<20;i++){
      Object o=new Object(){
        protected void finalize() throws Throwable {

         System.out.println("时间："+(System.currentTimeMillis()-bT));
          throw new Exception("");
        }
      };
      reference[i]=new WeakReference(o);
    }
   // System.gc();
    Thread.sleep(500000);
  }
}
