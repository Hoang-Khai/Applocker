package com.dkv.applocker.controller.service_and_state_pattern.State;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dkv.applocker.activity.UnlockActivity;
import com.dkv.applocker.controller.service_and_state_pattern.TopActivityProcessService;
import com.dkv.applocker.model.LockedAppList;

public class LockedState implements State {

    TopActivityProcessService parent;

    public LockedState(TopActivityProcessService topActivityProcessService) {
        parent = topActivityProcessService;
    }

    @Override
    public void changeActivity(String forePackage, Context context) {
        LockedAppList list = new LockedAppList(context);
        if(list.hasForePackageLocked(forePackage)) {
            askUserToEnterPassword(context);
        }
        parent.setCurrentPackage(forePackage);
    }

    private void askUserToEnterPassword(Context context) {
        Intent intent = new Intent(context, UnlockActivity.class);
        context.startActivity(intent);
        parent.setCurrentState(parent.getUnlockedState());
        Log.i("ParentState",parent.getCurrentState().toString());
    }

    @Override
    public String toString() {
        return "Locked";
    }
}
