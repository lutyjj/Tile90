package com.lutyjj.tile90;

import android.content.ContentResolver;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class RefreshTileService extends TileService {
    private final int REFRESH_90 = 0;
    private final int REFRESH_60 = 1;
    private final int REFRESH_AUTO = 2;

    @Override
    public void onClick() {
        super.onClick();
        cycleState();
    }

    private int getState() {
        ContentResolver cr = getContentResolver();
        try {
            return Settings.Global.getInt(cr, "oneplus_screen_refresh_rate");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void setState(int state) {
        ContentResolver cr = getContentResolver();
        Settings.Global.putInt(cr, "oneplus_screen_refresh_rate", state);

        changeTile(state);
    }

    private void cycleState() {
        int currentState = getState();

        if (currentState == REFRESH_60) setState(REFRESH_90);
        else if (currentState == REFRESH_90) setState(REFRESH_AUTO);
        else setState(REFRESH_60);
    }

    private void changeTile(int state) {
        String[] labels = getResources().getStringArray(R.array.label);

        Tile tile = getQsTile();

        if (state == REFRESH_60)
            tile.setState(Tile.STATE_INACTIVE);
        else tile.setState(Tile.STATE_ACTIVE);

        tile.setLabel(labels[state]);
        tile.updateTile();
    }
}
