/*
Name: Vijay Khot
CWID: 20021838
*/
/**
 *An Abstract class named `ElectricMachine` that implements `Machine
 */
public abstract class ElectricMachine implements Machine{
    boolean powerStatus; // Data field

    /**
     * No-argument constructor: Initializes `powerStatus` to ` false`
     */
    public ElectricMachine() {
        this.powerStatus = false;
    }
    /**
     * Starts the machine operation
     *
     * @return: `true` if the machine starts successfully, or `false` if it is already running
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
     * Stops the machine operation.
     *
     * @return: `true` if the machine stops successfully, or `false` if it is already stopped
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
