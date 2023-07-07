package com.wasabi.electron.object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.wasabi.electron.GameLoop;
import com.wasabi.electron.R;

/**
 * Enemy is a character which always moves in the direction of the player.
 * The Enemy class is an extension of a circle, which is an extension of a GameObject.
 */
public class Enemy extends Circle {

    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND * 0.6;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final Player player;

    public Enemy(Context context, Player player, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.enemy), positionX, positionY, radius);
        this.player = player;
    }

    @Override
    public void update() {
        // Update velocity of the enemy so that the velocity is in the direction of the player
        // Calculate vector from enemy to player (in x and y)
        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;
        // Calculate (absolute) distance between enemy (this) and player
        double distanceToPlayer = GameObject.getDistanceBetweenObjects(this, player);
        // Calculate direction from enemy to player
        double directionX = distanceToPlayerX / distanceToPlayer;
        double directionY = distanceToPlayerY / distanceToPlayer;
        // Set velocity in the direction of the player
        if (distanceToPlayer > 0) { // Avoid division by zero
            velocityX = directionX * MAX_SPEED;
            velocityY = directionY * MAX_SPEED;
        } else {
            velocityX = 0;
            velocityY = 0;
        }
        positionX += velocityX;
        positionY += velocityY;


    }

}
