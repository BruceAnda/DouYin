package com.meishe.sdkdemo.superzoom.zoomutils;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

/**
 * @author MatrixCV
 * <p>
 * 用于开启重力传感器，以获得当前手机朝向
 * Used to turn on the gravity sensor to get the current phone orientation
 */
public class Accelerometer {
    /**
     * @author MatrixCV
     * <p>
     * CLOCKWISE_ANGLE为手机旋转角度，其Deg0定义如下图所示
     * CLOCKWISE_ANGLE is the rotation angle of the phone, and its Deg0 is defined as shown below
     * ___________________
     * | +--------------+  |
     * | |              |  |
     * | |              |  |
     * | |              | O|
     * | |              |  |
     * | |______________|  |
     * ---------------------
     * 顺时针旋转后得到Deg90，即手机竖屏向上，如下图所示
     *
     * Rotate clockwise to get Deg90, that is, the vertical screen of the phone is up, as shown in the figure below
     * ___________
     * |           |
     * |+---------+|
     * ||         ||
     * ||         ||
     * ||         ||
     * ||         ||
     * ||         ||
     * |+---------+|
     * |_____O_____|
     */
    public enum CLOCKWISE_ANGLE {
        Deg0(0), Deg90(1), Deg180(2), Deg270(3);
        private int value;

        private CLOCKWISE_ANGLE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private SensorManager sensorManager = null;

    private boolean hasStarted = false;

    private static CLOCKWISE_ANGLE rotation;

    /**
     * @param ctx
     */
    public Accelerometer(Context ctx) {
        sensorManager = (SensorManager) ctx
                .getSystemService(Context.SENSOR_SERVICE);
        rotation = CLOCKWISE_ANGLE.Deg90;
    }

    /**
     * 开始对传感器的监听
     * Start monitoring the sensor
     */
    public void start() {
        if (hasStarted) return;
        hasStarted = true;
        rotation = CLOCKWISE_ANGLE.Deg90;
        sensorManager.registerListener(accListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * 结束对传感器的监听
     * End listening to the sensor
     */
    public void stop() {
        if (!hasStarted) return;
        hasStarted = false;
        sensorManager.unregisterListener(accListener);
    }

    /**
     * @return 返回当前手机转向；Back to current phone turn
     */
    static public int getDirection() {
        return rotation.getValue();
    }

    /**
     * 传感器与手机转向之间的逻辑
     * Logic between sensor and phone steering
     */
    private SensorEventListener accListener = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1) {
        }

        @Override
        public void onSensorChanged(SensorEvent arg0) {
            if (arg0.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float x = arg0.values[0];
                float y = arg0.values[1];
                float z = arg0.values[2];
                if (Math.abs(x) > 3 || Math.abs(y) > 3) {
                    if (Math.abs(x) > Math.abs(y)) {
                        if (x > 0) {
                            rotation = CLOCKWISE_ANGLE.Deg0;
                            //Log.d("ROTATION","CLOCKWISE_ANGLE: Deg0");
                        } else {
                            rotation = CLOCKWISE_ANGLE.Deg180;
                            //Log.d("ROTATION","CLOCKWISE_ANGLE: Deg180");
                        }
                    } else {
                        if (y > 0) {
                            rotation = CLOCKWISE_ANGLE.Deg90;
                            //Log.d("ROTATION","CLOCKWISE_ANGLE: Deg90");
                        } else {
                            rotation = CLOCKWISE_ANGLE.Deg270;
                            //Log.d("ROTATION","CLOCKWISE_ANGLE: Deg270");
                        }
                    }
                }
            }
        }
    };

    /**
     * 获取屏幕方向
     * Get screen orientation
     */
    public static int getDisplayOrientation(Context context, int cameraId) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }

        return result;
    }
}
