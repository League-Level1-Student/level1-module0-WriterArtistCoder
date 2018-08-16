/* This game is partially written by The League of Amazing Programmers
  (https://jointheleague.org) and partially by Percy Ghenburg, who added some features.
  Click to play/pause
  Move mouse to move paddle */
PImage bgImg;

import ddf.minim.*;
Minim minim;
AudioSample pongFX;

// Settings
int bouncesTillShrink = 5;
float gravity = 1;
float wind = 0;
// Variables
boolean play = false;
int bounces = 3;
int frameCoo = 0;
int endFrame = 0;
float xPos = 300;
float yPos = 20;
float xSpeed = 0;
float ySpeed = 0;
float xXlrate = wind;
float yXlrate = gravity;
float padWidth = 500/bounces;

void setup() {
  size(600, 600);
  background(0);
  minim = new Minim(this);
  pongFX = minim.loadSample("pong.wav", 128);
  bgImg = loadImage("img.jpg");
}

boolean straight() {
  if (xSpeed == 0) {
    return true;
  }
  return false;
}

boolean collision() {
  if (xPos >= mouseX-(padWidth/2) && xPos <= mouseX+(padWidth/2) && yPos+20 >= 500) {
    return true;
  }
  return false;
}

void updateBall() {
  xXlrate = wind;
  yXlrate = gravity;
  if (collision()) {
    xSpeed+=((xPos-mouseX)/20);
    ySpeed*=-1;
    bounces++;
    pongFX.trigger();
  } else {
    xSpeed+=xXlrate;
    ySpeed+=yXlrate;
  }
  xPos+=xSpeed;
  yPos+=ySpeed;
  
  if (yPos+20 >= 600 && endFrame == 0) {
    endFrame = frameCoo;
  }
}

void draw() {
  // Setup
  image(bgImg, 0, 0, width, height);
  // Draw ball
  fill(255, 0, 0);
  noStroke();
  ellipse(xPos, yPos, 40, 40);
  // Draw paddle
  fill(255);
  noStroke();
  rectMode(CENTER);
  rect(mouseX, 500, padWidth, 20);
  // Game over?
  if (endFrame != 0) {
    fill(255, 0, 0);
    textAlign(CENTER);
    textSize(80);
    text("GAME OVER!", 300, 300);
    textSize(50);
    text("Score: " + endFrame, 300, 400);
    textSize(30);
    text("Press r to replay at end", 300, 480);
  } else if (!play) {
    fill(0, 0, 255);
    textAlign(CENTER);
    textSize(80);
    text("Space to play", 300, 400);
  }
  
  if (key == ' ') {
    play = true;
  } else if (key == 'r' && endFrame != 0) {
    play = false;
    bounces = 3;
    frameCoo = 0;
    endFrame = 0;
    xPos = 300;
    yPos = 20;
    xSpeed = 0;
    ySpeed = 0;
    xXlrate = wind;
    yXlrate = gravity;
    padWidth = 500/bounces;
  }
  // Animate
  if (play) {
    frameCoo++;
    updateBall();
  }
  padWidth = 500/bounces;
}
