package com.wasabi.electron;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content view to game, so that objects in the Game class can be rendered to the screen
        setContentView(new Game(this));
    }
}