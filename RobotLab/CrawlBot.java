package RobotLab;
/**
 * @author huan zhou
 * @version Feb 18, 2025
 */

public class CrawlBot implements Robot {
    int x;
    int y;
    int direction;

    public CrawlBot(){
        x = 0;
        y = 0;
        direction = 0;
    }

    public CrawlBot(int x, int y, int d){
        this.x = x;
        this.y = y;
        direction = d;
    }

    public int getX(){
        return x;

    }

    public int getY(){
        return y;

    }

    public int getDirection(){
        return direction;
    }

    public void turnRight(){
        this.direction = (this.direction + 270) % 360;

    }
        //Turns the crawl bot 90 degrees right
        //Note: ending direction should be between 0 and 359 degrees
    public void turnLeft(){
        this.direction = (this.direction + 90) % 360; // Equivalent to subtracting 90
    }
        //Turns the crawl bot 90 degrees left
        //Note: ending direction should be between 0 and 359 degrees
     /*
        moveForward(int dist)
        Move in the direction that the crawl bot is facing
        Note: Since the bot can only turn 90 degrees, the valid directions are 0, 90, 180, and 270
        moveBackward(int dist)
        Move in the opposite direction that the crawl bot is facing
        Note: Since the bot can only turn 90 degrees, the valid directions are 0, 90, 180, and 270 m
        toString()
        Returns a string in the following format: “CrawlBot at (<x>, <y>) heading <degree> degrees”
        Where <x>, <y>, and <degree> are replaced with the crawl bot’s information
     */
    @Override
    public void moveForward(int dist) {
        switch (this.direction) {
            case 0:   // Moving East
                this.x += dist;
                break;
            case 90:  // Moving North
                this.y += dist;
                break;
            case 180: // Moving West
                this.x -= dist;
                break;
            case 270: // Moving South
                this.y -= dist;
                break;
        }
    }

    @Override
    public void moveBackward(int dist) {
        switch (this.direction) {
            case 0:   // Moving West
                this.x -= dist;
                break;
            case 90:  // Moving South
                this.y -= dist;
                break;
            case 180: // Moving East
                this.x += dist;
                break;
            case 270: // Moving North
                this.y += dist;
                break;
        }
    }
    
      // toString method
    @Override
    public String toString() {
    return "CrawlBot at (" + this.x + ", " + this.y + ") heading " + this.direction + " degrees";
    }
    
}
