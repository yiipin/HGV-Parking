package com.example.hgvparking;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.SeekBar;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class  Activity_Parking extends AppCompatActivity {

    private ImageView carImageView;
    private SeekBar throttleSeekBar;
    private SeekBar steeringSeekBar;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        // 創建遊戲視圖對象
                gameView = new GameView(this);
                setContentView(gameView);
            }

            @Override
            protected void onResume() {
                super.onResume();
                // 恢復遊戲視圖
                gameView.resume();
            }

            @Override
            protected void onPause() {
                super.onPause();
                // 暫停遊戲視圖
                gameView.pause();
            }

            static class GameView extends SurfaceView implements Runnable {

                private Thread gameThread = null;
                private SurfaceHolder surfaceHolder;
                private boolean playing;
                private PlayerCar playerCar;
                private List<Obstacle> obstacles;
                private Paint paint;
                private int screenWidth, screenHeight;
                private boolean gameOver;

                public GameView(Context context) {
                    super(context);
                    // 獲取SurfaceHolder並初始化畫筆
                    surfaceHolder = getHolder();
                    paint = new Paint();
                    // 初始化障礙物列表和玩家車輛
                    obstacles = new ArrayList<>();
                    playerCar = new PlayerCar(context);
                    gameOver = false;
                }

                @Override
                public void run() {
                    while (playing) {
                        // 更新遊戲邏輯
                        update();
                        // 繪製遊戲畫面
                        draw();
                        // 控制遊戲循環的速度
                        control();
                    }
                }

                private void update() {
                    if (!gameOver) {
                        // 更新玩家車輛的位置
                        playerCar.update();
                        // 檢查是否與障礙物發生碰撞
                        for (Obstacle obstacle : obstacles) {
                            if (playerCar.collidesWith(obstacle)) {
                                // 若碰撞，遊戲結束
                                gameOver = true;
                            }
                        }
                    }
                }

                private void draw() {
                    if (surfaceHolder.getSurface().isValid()) {
                        // 獲取Canvas並繪製背景
                        Canvas canvas = surfaceHolder.lockCanvas();
                        canvas.drawColor(Color.WHITE); // 背景色

                        // 繪製玩家車輛
                        playerCar.draw(canvas);

                        // 繪製障礙物
                        for (Obstacle obstacle : obstacles) {
                            obstacle.draw(canvas);
                        }

                        // 若遊戲結束，顯示結束文字
                        if (gameOver) {
                            paint.setColor(Color.RED);
                            paint.setTextSize(50);
                            canvas.drawText("遊戲結束", screenWidth / 2 - 150, screenHeight / 2, paint);
                        }

                        // 解鎖Canvas，顯示畫面
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }

                private void control() {
                    try {
                        // 控制遊戲循環的速度，約 60 FPS
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                public void pause() {
                    // 暫停遊戲循環
                    playing = false;
                    try {
                        gameThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                public void resume() {
                    // 恢復遊戲循環
                    playing = true;
                    gameThread = new Thread(this);
                    gameThread.start();

                    // 初始化遊戲元素
                    screenWidth = getWidth();
                    screenHeight = getHeight();
                    playerCar.init(screenWidth, screenHeight);

                    obstacles.clear();
                    generateObstacles();
                }

                private void generateObstacles() {
                    // 隨機生成障礙物
                    Random random = new Random();
                    int obstacleCount = random.nextInt(5) + 3; // 3 到 7 個障礙物
                    for (int i = 0; i < obstacleCount; i++) {
                        Obstacle obstacle = new Obstacle(screenWidth, screenHeight);
                        obstacles.add(obstacle);
                    }
                }

                @Override
                public boolean onTouchEvent(MotionEvent event) {
                    if (!gameOver) {
                        // 處理玩家輸入以移動玩家車輛
                        playerCar.handleInput(event);
                    } else {
                        // 若遊戲結束，重新開始遊戲
                        gameOver = false;
                        resume();
                    }
                    return true;
                }
            }

            static class PlayerCar {

                private int x, y;
                private int speed;
                private int screenWidth, screenHeight;

                public PlayerCar(Context context) {
                    // 初始化玩家車輛
                    // ...
                }

                public void init(int screenWidth, int screenHeight) {
                    // 初始化玩家車輛位置和速度
                    // ...
                }

                public void update() {
                    // 更新玩家車輛位置
                    // ...
                }

                public void draw(Canvas canvas) {
                    // 在Canvas上繪製玩家車輛
                    // ...
                }

                public void handleInput(MotionEvent event) {
                    // 處理玩家輸入以移動玩家車輛
                    // ...
                }

                public boolean collidesWith(Obstacle obstacle) {
                    // 檢查是否與障礙物發生碰撞
                    // ...
                }
            }

            static class Obstacle {

                private int x, y;
                private int speed;

                public Obstacle(int screenWidth, int screenHeight) {
                    // 初始化障礙物
                    // ...
                }

                public void update() {
                    // 更新障礙物位置
                    // ...
                }

                public void draw(Canvas canvas) {
                    // 在Canvas上繪製障礙物
                    // ...
                }
            }
        }

