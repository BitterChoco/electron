package com.wasabi.electron;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Joystick {
    private int outerCircleCenterPostitionX;
    private int outerCircleCenterPostitionY;
    private int innerCircleCenterPostitionX;
    private int innerCircleCenterPostitionY;
    private int outerCircleRadius;
    private int innerCircleRadius;
    private Paint outerCirclePaint;
    private Paint innerCirclePaint;
    private double joystickCenterToTouchDistance;
    private boolean isPressed;
    private double actuatorX;
    private double actuatorY;

    public Joystick(int centerPositionX, int centerPositionY, int outerCircleRadius, int innerCircleRadius) {

        // Outer and inner circle make up the joystick
        outerCircleCenterPostitionX = centerPositionX;
        outerCircleCenterPostitionY = centerPositionY;
        innerCircleCenterPostitionX = centerPositionX;
        innerCircleCenterPostitionY = centerPositionY;

        // Radii of circles
        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;

        // Paint circles
        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.GRAY);
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.BLUE);
        innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

    }

    public void draw(Canvas canvas) {
        // Draw outer circle
        canvas.drawCircle(
                outerCircleCenterPostitionX,
                outerCircleCenterPostitionY,
                outerCircleRadius,
                outerCirclePaint
        );

        // Draw inner circle
        canvas.drawCircle(
                innerCircleCenterPostitionX,
                innerCircleCenterPostitionY,
                innerCircleRadius,
                innerCirclePaint
        );
    }

    public void update() {
        updateInnerCirclePosition();
    }

    private void updateInnerCirclePosition() {
        innerCircleCenterPostitionX = (int) (outerCircleCenterPostitionX + actuatorX * outerCircleRadius);
        innerCircleCenterPostitionY = (int) (outerCircleCenterPostitionY + actuatorY * outerCircleRadius);
    }

    public boolean isPressed(double touchPositionX, double touchPositionY) {
        joystickCenterToTouchDistance = Math.sqrt(
                Math.pow(outerCircleCenterPostitionX - touchPositionX, 2) +
                        Math.pow(outerCircleCenterPostitionY - touchPositionY, 2)
        );
        return joystickCenterToTouchDistance < outerCircleRadius;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean getIsPressed() {
        return this.isPressed;
    }

    public void setActuator(double touchPositionX, double touchPositionY) {
        double deltaX = touchPositionX - outerCircleCenterPostitionX;
        double deltaY = touchPositionY - outerCircleCenterPostitionY;
        double deltaDistance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

        if (deltaDistance < outerCircleRadius) {
            actuatorX = deltaX / outerCircleRadius;
            actuatorY = deltaY / outerCircleRadius;
        } else {
            actuatorX = deltaX / deltaDistance;
            actuatorY = deltaY / deltaDistance;
        }
    }

    public void resetActuator() {
        actuatorX = 0.0;
        actuatorY = 0.0;
    }

    public double getActuatorX() {
        return actuatorX;
    }
    public double getActuatorY() {
        return actuatorY;
    }
}
