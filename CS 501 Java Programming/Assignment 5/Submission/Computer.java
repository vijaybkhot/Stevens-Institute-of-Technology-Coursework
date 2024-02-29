/*
Name: Vijay Khot
CWID: 20021838
*/
/**
 * A class named `Computer` that extends `ElectricMachine`
 */
public class Computer extends ElectricMachine{
    // Data fields
    String os;

    /**
     * No-argument constructor
     */
    public Computer() {
        super();
    }

    /**
     * Constructor with parameters: sets `powerStatus` to `false`
     * @param os: Initializes the `os` with given values
     */
    public Computer(String os) {
        super();
        this.powerStatus = false;
        this.os = os;
    }

    /**
     * Starts the computer operation
     *
     * @return: `true` if the computer starts successfully, or `false` if it is already running
     */
    @Override
    public boolean start() {
        if (powerStatus) {
            return false;
        }
        else {
            this.powerStatus = true;
            return true;
        }
    }

    /**
     * Stops the computer operation.
     *
     * @return: `true` if the computer stops successfully, or `false` if it is already stopped
     */
    @Override
    public boolean stop() {
        if (!powerStatus) {
            return false;
        }
        else {
            this.powerStatus = false;
            return true;
        }
    }

}
