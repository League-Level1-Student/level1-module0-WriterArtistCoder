import ddf.minim.*;
Minim minim;
AudioPlayer song;

PImage picOfRec;
int angle = 0;

void setup() {
  size(600, 600);
  frameRate(36);
  picOfRec = loadImage("vinyl.png");
  picOfRec.resize(width, height);
  minim = new Minim(this);
  song = minim.loadFile("songOfPeace.mp3", 512);
}

void rotateImage(PImage img, int amountRotate) {
  translate(width/2, height/2);
  rotate(amountRotate*TWO_PI/360);
  translate(-img.width/2, -img.height/2);
}

void draw() {
  background(255, angle%360, 255);
  rotateImage(picOfRec, angle);
  image(picOfRec, 0, 0);
  if (mousePressed) {
    song.pause();
  } else {
    song.play();
    angle+=10;
  }
}
