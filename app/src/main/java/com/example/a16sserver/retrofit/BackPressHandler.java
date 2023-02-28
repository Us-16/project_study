package com.example.a16sserver.retrofit;

import android.app.Activity;
import android.widget.Toast;

public class BackPressHandler {

    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;
    // 종료시킬 Activity
    private Activity activity;

    /**
     * 생성자
     * @param activity 종료시킬 Activity.
     */
    public BackPressHandler(Activity activity) {
        this.activity = activity;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            System.out.println("뒤로가기종료직전");
            activity.finishAffinity();
            System.out.println("뒤로가기종료완료");
            toast.cancel();
            /*finish() 같은 경우 스택에 이전 view들이 존재 할 경우
            앱 종료가 아니라 그 이전 view 가 나온다.
            finishAffinity() 를 할 경우 스택에 다른 view가 있다 하더라도
            어느 경우에나 앱이 종료할 수 있다*/
        }
    }

    private void showGuide() {
        toast = Toast.makeText(activity, "뒤로가기 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }

}
