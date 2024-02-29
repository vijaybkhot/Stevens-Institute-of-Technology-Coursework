/*
Name: Vijay Khot
CWID: 20021838
*/

/**
 * Interface named `Machine`
 */
public interface Machine {
    /**
     * Starts the machine operation
     * @return: `true` if the machine starts successfully, or `false` if it's already running
     */
    boolean start();

    /**
     * Stops the machine operation.
     * @return: `true` if the machine stops successfully, or `false` if it's already stopped
     */
    boolean stop();

}
