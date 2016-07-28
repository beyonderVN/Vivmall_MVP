package com.vinhsang.vivmall.presentation;

/**
 * Created by Long on 7/28/2016.
 */

public class TheImpl implements TheInterface {
    public static final int SHOW_PROCESS = 0;
    public static final int REFRESH = 1;
    public static final int LOADMORE = 2;

    @Override
    public Object doWork(int work, Object... prams) {
        switch (work) {
            case SHOW_PROCESS:
                showProcess();
                break;
            case REFRESH:
                refresh(prams);
                break;
            case LOADMORE:
                loadmore(prams);
                break;
        }
        return null;
    }

    private void showProcess() {

    }

    private void refresh(Object... prams) {
        int i;
        i = (Integer)prams[0];
    }

    private void loadmore(Object... prams) {
        int i;
        String s;
        i = (Integer)prams[0];
        s = (String) prams[1];
    }
}
