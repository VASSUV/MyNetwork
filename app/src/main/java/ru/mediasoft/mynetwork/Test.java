package ru.mediasoft.mynetwork;

public class Test {

    public void run(final CallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                callBack.getResult("Test");

            }
        }).start();
    }

    public interface CallBack {
        void getResult(String result);
    }
}
