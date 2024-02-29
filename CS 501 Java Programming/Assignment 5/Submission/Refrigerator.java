/*
Name: Vijay Khot
CWID: 20021838
*/
/**
 * A class named `Refrigerator` that extends `ElectricMachine'
 */
public class Refrigerator extends ElectricMachine {
    //Data field:
    double temperature;  // The current temperature of the refrigerator

    /**
     * No-argument constructor
     */
    public Refrigerator() {
        super();
    }

    /**
     * Constructor with parameters: sets `powerStatus` to `false`
     * @param temperature: Initializes the `temperature` with given values
     */
    public Refrigerator(double temperature) {
        super();
        this.powerStatus = false;
        this.temperature = temperature;
    }

    /**
     * Starts the Refrigerator operation
     *
     * @return: `true` if the Refrigerator starts successfully, or `false` if it is already running
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
     * Stops the Refrigerator operation.
     *
     * @return: `true` if the Refrigerator stops successfully, or `false` if it is already stopped
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
