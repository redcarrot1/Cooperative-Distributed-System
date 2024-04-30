package com.example.demo.gui;

import com.example.demo.Message;

import java.awt.*;

public class Rect implements Figure {
    public final Color[] colorList = {
            Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY,
            Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA,
            Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW,
            null
    };

    public int id, x, y, x2, y2, lineWidth, lineColorIdx, fillColorIdx;
    public long creationTime;

    public Rect(int id, int x, int y, int lineWidth, int lineColorIdx) {
        this.id = id;
        this.x = this.x2 = x;
        this.y = this.y2 = y;
        this.lineWidth = lineWidth;
        this.lineColorIdx = lineColorIdx;
        this.fillColorIdx = 12;
        this.creationTime = System.currentTimeMillis();
    }

    public Rect(int id, int x, int y, int x2, int y2, int lineWidth, int lineColorIdx, int fillColorIdx, long creationTime) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.lineWidth = lineWidth;
        this.lineColorIdx = lineColorIdx;
        this.fillColorIdx = fillColorIdx;
        this.creationTime = creationTime;
    }

    public void set(int x, int y, int x2, int y2, int lineWidth, int lineColorIdx, int fillColorIdx, long creationTime) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.lineWidth = lineWidth;
        this.lineColorIdx = lineColorIdx;
        this.fillColorIdx = fillColorIdx;
        this.creationTime = creationTime;
    }

    @Override
    public long getCreationTime() {
        return creationTime;
    }

    @Override
    public void draw(Graphics g) {
        int minX = Math.min(x, x2);
        int minY = Math.min(y, y2);
        int width = Math.abs(x2 - x);
        int height = Math.abs(y2 - y);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(lineWidth));

        if (colorList[fillColorIdx] != null) {
            g.setColor(colorList[fillColorIdx]);
            g.fillRect(minX, minY, width, height);
        }
        g.setColor(colorList[lineColorIdx]);
        g.drawRect(minX, minY, width, height);
    }

    @Override
    public void setEndPoint(Point p) {
        this.x2 = p.x;
        this.y2 = p.y;
    }

    @Override
    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    @Override
    public void setLineColor(int lineColorIdx) {
        this.lineColorIdx = lineColorIdx;
    }

    @Override
    public void setFillColor(int fillColorIdx) {
        if (this.fillColorIdx == fillColorIdx)
            this.fillColorIdx = 12;
        else
            this.fillColorIdx = fillColorIdx;
    }

    @Override
    public boolean contains(Point p) {
        Rectangle rectBounds = new Rectangle(Math.min(x, x2), Math.min(y, y2), Math.abs(x2 - x), Math.abs(y2 - y));
        return rectBounds.contains(p);
    }

    @Override
    public int compareTo(Figure other) {
        return Long.compare(this.getCreationTime(), other.getCreationTime());
    }

    @Override
    public Message getMessage(){
        return Message.figure(1, creationTime, id, lineWidth, fillColorIdx, lineColorIdx, x, y, x2, y2);
    }
}