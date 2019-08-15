package game.renderer;

import game.GameObject;
import game.Setting;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Renderer {
    static List<String> fileNamesRenderByDirection = Arrays.asList("Up.png", "Down.png", "Left.png", "Right.png");
    BufferedImage image;
    BufferedImage upImage;
    BufferedImage downImage;
    BufferedImage leftImage;
    BufferedImage rightImage;
    ArrayList<BufferedImage> images;
    int currentIndex;
    int frameCount;
    boolean isOnce;
    boolean byDirection;

    public Renderer(BufferedImage image) {
        this.image = image;
        this.currentIndex = 0;
        this.frameCount = 0;
    }

    public Renderer(String folderPath, boolean isOnce) {
        this(folderPath);
        this.isOnce = isOnce;
    }

    //1: upgrade fileName's order
    //2: load image ile .png only
    public Renderer(String folderPath) {
        File folder = new File(folderPath);
        List<String> fileNames = Arrays.asList(folder.list());
        fileNames.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        // check if folder contains 4 images "UP, DOWN, LEFT, RIGHT
        if(checkIfRenderByDirection(fileNames)) {
            upImage = SpriteUtils.loadImage(folderPath + "/Up.png");
            downImage = SpriteUtils.loadImage(folderPath + "/Down.png");
            leftImage = SpriteUtils.loadImage(folderPath + "/Left.png");
            rightImage = SpriteUtils.loadImage(folderPath + "/Right.png");
            byDirection = true;
        } else {
            images = new ArrayList<>();
            for (int i = 0; i < fileNames.size(); i++) {
                String fileName = fileNames.get(i);
                if (fileName.toLowerCase().endsWith(".png")) {
                    BufferedImage image = SpriteUtils.loadImage(folderPath + "/" + fileName);
                    images.add(image);
                }
            }
        }
    }

    private static boolean checkIfRenderByDirection(List<String> fileNames) {
        for (String fileName : fileNamesRenderByDirection) {
            if(!fileNames.contains(fileName)) {
                return false;
            }
        }
        return true;
    }

    public void render(Graphics g, GameObject master) {
        if (image != null) {
            g.drawImage(image, (int) (master.position.x - master.anchor.x * image.getWidth()),
                    (int) (master.position.y - master.anchor.y * image.getHeight()), null);
        } else if (images != null) {
            BufferedImage currentImage = images.get(currentIndex);
            g.drawImage(currentImage, (int) (master.position.x - master.anchor.x * currentImage.getWidth()),
                    (int) (master.position.y - master.anchor.y * currentImage.getHeight()), null);
            frameCount++;
            if (frameCount > 10) {
                currentIndex++;
                if (currentIndex >= images.size()) {
                    if (isOnce) {
                        master.deactive();
                    }
                    currentIndex = 0;
                }
                frameCount = 0;
            }
        } else if(byDirection) {
            BufferedImage directionImage = null;
            if(master.direction == Setting.RIGHT) {
                directionImage = rightImage;
            } else if(master.direction == Setting.DOWN) {
                directionImage = downImage;
            } else if(master.direction == Setting.LEFT) {
                directionImage = leftImage;
            } else {
                directionImage = upImage;
            }
            g.drawImage(directionImage, (int) (master.position.x - master.anchor.x * directionImage.getWidth()),
                    (int) (master.position.y - master.anchor.y * directionImage.getHeight()), null);
        }
    }
}
