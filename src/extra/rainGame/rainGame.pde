/* Some of this code was provided by The League of Amazing Programmers. Percy Ghenburg
wrote some of it himself, and the multiplier was his idea.
  Click to play/pause
  Move the mouse to collect raindrops
*/
import ddf.minim.*;
Minim minim;
AudioPlayer raindropFX;
AudioPlayer fanfareFX;

// Variables
int score = 0;
int multiplier = 1;
int dropX = (int) random(width);
float dropY = 0;
boolean play = true;
// Settings
float cupsPerBucket = 50;
float fallSpeed = 8;

void setup() {
  size(600, 600);
  minim = new Minim(this);
  raindropFX = minim.loadFile("waterdrop.aiff", 512);
  fanfareFX = minim.loadFile("fanfare.aiff", 512);
}

void resetDrop() {
  dropX = (int) random(width);
  dropY = 0;
}

void checkCatch() {
  if (dropX-10 >= mouseX-28 && dropX+10 <= mouseX+28 &&
    dropY+10 >= 550) {
    score+=multiplier;
    multiplier++;
    println("You earned a cup! Score: " + score);
    resetDrop();
    raindropFX.rewind();
    raindropFX.play();
  } else if (dropY >= 610) {
    multiplier = 1;
    resetDrop();
  }
}

void draw() {
  // Setup
  background(100, 100, 100);
  // Draw raindrop
  if (play) {
    checkCatch();
  }
  fill(0, 0, 255);
  stroke(100, 100, 255);
  ellipse(dropX, floor(dropY), 10, 10);
  // Draw bucket
    // Handle
  noFill();
  stroke(0, 0, 0);
  ellipse(mouseX, 450, 50, 50);
    // Pail
  fill(255, 0, 0);
  noStroke();
  rectMode(CORNER);
  rect(mouseX-28, 450, 56, 100);
    // Darkness
  fill(100, 0, 0);
  noStroke();
  rectMode(CORNERS);
  
  rect(mouseX-28, 550, mouseX+28, 550-((score*(100/cupsPerBucket))%101));
  // Draw score
    // Teaspoons
  fill(0, 0, 0);
  textSize(16);
  text("Cups: " + score, 20, 20);
    // Multiplier
  fill(0, 200, 0);
  textSize(16);
  text("x" + multiplier, 20, 40);
    // Achievement
  fill(0, 0, 200);
  textSize(16);
  if (score >= 2) {
    if (score < 8) {
      text("Water bottle", 20, 60);
    } else if (score < 16) {
      text("Day of consumption", 20, 60);
    } else if (score < 32) {
      text("Gallon", 20, 60);
    } else if (score < 56) {
      text("Two gallons", 20, 60);
    } else if (score < 100) {
      text("Week of consumption", 20, 60);
    } else if (score < 200) {
      text("Human cups-in-a-day limit", 20, 60);
    } else if (score < 400) {
      text("One eighth of a bathtub and counting...", 20, 60);
    } else if (score < 800) {
      text("One quarter of a bathtub and counting...", 20, 60);
    } else if (score < 1600) {
      text("One half of a bathtub and counting...", 20, 60);
    } else {
      text("One bathtub, finally!", 20, 60);
      fanfareFX.play();
    }
  }
  // Animate
  if (play) {
    dropY+=fallSpeed;
  }
}

void mouseClicked() {
  play = !play;
}
