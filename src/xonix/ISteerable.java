package xonix;

/**
 * Interface for any steerable objects
 */
interface ISteerable {
    int getHeading();
    void setHeading(int heading);
    float getSpeed();
    void setSpeed(float speed);
}
