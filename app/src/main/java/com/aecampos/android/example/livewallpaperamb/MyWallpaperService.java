package com.aecampos.android.example.livewallpaperamb;

import android.app.WallpaperColors;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;

public class MyWallpaperService extends WallpaperService {

    // TODO: Be notified of user presence state (unlocked, locked, ambient)

    @Override
    public Engine onCreateEngine() {
        return new MyWallpaperEngine();
    }

    private class MyWallpaperEngine extends Engine {

        private List<TextPoint> points = new ArrayList<>();
        private Paint paint = new Paint();

        public MyWallpaperEngine() {
            paint.setAntiAlias(true);
            paint.setTextSize(100f);
        }


        @Override
        public void onTouchEvent(MotionEvent event) {
            addPoint(event.getX(), event.getY());

            super.onTouchEvent(event);
        }

        private void addPoint(float x, float y) {
            SurfaceHolder holder = getSurfaceHolder();
            Canvas canvas = null;
            try {
                canvas = holder.lockCanvas();
                if (canvas != null) {
                    points.add(new TextPoint(
                            String.valueOf(points.size() + 1), x, y)
                    );
                    drawScreen(canvas, points);

                }
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }

        private final int[] COLORS = {Color.RED, Color.MAGENTA, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN};
        private final int COLOR_BG = Color.BLACK;

        private void drawScreen(Canvas canvas, List<TextPoint> circles) {
            canvas.drawColor(COLOR_BG);
            for (TextPoint point : circles) {
                paint.setColor(COLORS[point.text.hashCode() % COLORS.length]);

                Rect r = new Rect();
                paint.getTextBounds(point.text, 0, point.text.length(), r);
                canvas.drawText(point.text,
                        point.x - r.width() / 2f,
                        point.y - r.height() / 2f,
                        paint);
            }
        }


        @androidx.annotation.Nullable
        @Override
        public WallpaperColors onComputeColors() {
            return new WallpaperColors(Color.valueOf(Color.GREEN), Color.valueOf(Color.RED), Color.valueOf(Color.BLUE));
        }
    }

    public static class TextPoint {
        String text;
        float x;
        float y;

        public TextPoint(String text, float x, float y) {
            this.text = text;
            this.x = x;
            this.y = y;
        }
    }
}