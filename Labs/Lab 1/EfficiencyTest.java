/*
* Class for testing the PointCP, PointCPD2, and PointCPD4 classes
* @author Matthew Langlois
* @author Matthew Yaraskavitch
* @version September 2015
*/
public class EfficiencyTest {

    /**
     * Find average time to initialize PointCP
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long initPointCP(char type, long testTimeSeconds){
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        while(System.nanoTime() < limit){
            new PointCP(type, 10*Math.random(), 10*Math.random());
            count++;
        }
        long result = testTimeNano/count;
        return result;
    }

    /**
     * Find average time to initialize PointCP Design 2
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long initPointCPD2(char type, long testTimeSeconds){
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        while(System.nanoTime() < limit){
            new PointCPD2(type, 10*Math.random(), 10*Math.random());
            count++;
        }
        long result = testTimeNano/count;
        return result;
    }

    /**
     * Find average time to initialize PointCP Design 4
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long initPointCPD4(char type, long testTimeSeconds){
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        while(System.nanoTime() < limit){
            new PointCPD4(type, 10*Math.random(), 10*Math.random());
            count++;
        }
        long result = testTimeNano/count;
        return result;
    }

    /**
     * Find average time to get each Cartesian coordinate from getter method in PointCP
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long getCPointCP(char type, long testTimeSeconds){
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        PointCP tmp = new PointCP(type, 10*Math.random(), 10*Math.random());
        while(System.nanoTime() < limit){
            tmp.getX();
            count++;
            tmp.getY();
            count++;
        }
        long result = testTimeNano/count;
        return result;
    }

    /**
     * Find average time to get each Cartesian coordinate from getter method in PointCP Design 2
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long getCPointCPD2(char type, long testTimeSeconds) {
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        PointCPD2 tmp = new PointCPD2(type, 10 * Math.random(), 10 * Math.random());
        while (System.nanoTime() < limit) {
            tmp.getX();
            count++;
            tmp.getY();
            count++;
        }
        long result = testTimeNano / count;
        return result;
    }

    /**
     * Find average time to get each Cartesian coordinate from getter method in PointCP Design 4
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long getCPointCPD4(char type, long testTimeSeconds) {
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        PointCPD4 tmp = new PointCPD4(type, 10 * Math.random(), 10 * Math.random());
        while (System.nanoTime() < limit) {
            tmp.getX();
            count++;
            tmp.getY();
            count++;
        }
        long result = testTimeNano / count;
        return result;
    }

    /**
     * Find average time to get each Polar  coordinate from getter method in PointCP
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long getPPointCP(char type, long testTimeSeconds) {
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        PointCP tmp = new PointCP(type, 10 * Math.random(), 10 * Math.random());
        while (System.nanoTime() < limit) {
            tmp.getRho();
            count++;
            tmp.getTheta();
            count++;
        }
        long result = testTimeNano / count;
        return result;
    }

    /**
     * Find average time to get each Polar  coordinate from getter method in PointCP Design 2
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long getPPointCPD2(char type, long testTimeSeconds) {
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        PointCPD2 tmp = new PointCPD2(type, 10 * Math.random(), 10 * Math.random());
        while (System.nanoTime() < limit) {
            tmp.getRho();
            count++;
            tmp.getTheta();
            count++;
        }
        long result = testTimeNano / count;
        return result;
    }

    /**
     * Find average time to get each Polar  coordinate from getter method in PointCP Design 4
     * @param type char value of either C or P
     * @param testTimeSeconds time to run test in seconds
     * @return average operation time in nanoseconds
     */
    public static long getPPointCPD4(char type, long testTimeSeconds) {
        long testTimeNano = testTimeSeconds * 1000 * 1000 * 1000;
        long count = 0;
        long start = System.nanoTime();
        long limit = start + testTimeNano;
        PointCPD4 tmp = new PointCPD4(type, 10 * Math.random(), 10 * Math.random());
        while (System.nanoTime() < limit) {
            tmp.getRho();
            count++;
            tmp.getTheta();
            count++;
        }
        long result = testTimeNano / count;
        return result;
    }

    /**
     * Main loop to run tests and print results out to user
     * @param args
     */
    public static void main(String[] args){
        int testTime = 5;
        System.out.println("Average time for initializing C PointCP: "+initPointCP('C', testTime)+"ns");
        System.out.println("Average time for initializing C PointCPD2: "+initPointCPD2('C', testTime)+"ns");
        System.out.println("Average time for initializing C PointCPD4: "+initPointCPD4('C', testTime)+"ns");
        System.out.println("Average time for initializing P PointCP: "+initPointCP('P', testTime)+"ns");
        System.out.println("Average time for initializing P PointCPD2: "+initPointCPD2('P', testTime)+"ns");
        System.out.println("Average time for initializing P PointCPD4: "+initPointCPD4('P', testTime)+"ns");

        System.out.println("Average time for getting Cartesian from Cartesian PointCP: "+getCPointCP('C', testTime)+"ns");
        System.out.println("Average time for getting Cartesian from Cartesian PointCPD2: "+getCPointCPD2('C', testTime)+"ns");
        System.out.println("Average time for getting Cartesian from Cartesian PointCPD4: "+getCPointCPD4('C', testTime)+"ns");
        System.out.println("Average time for getting Polar from Cartesian PointCP: "+getPPointCP('C', testTime)+"ns");
        System.out.println("Average time for getting Polar from Cartesian PointCPD2: "+getPPointCPD2('C', testTime)+"ns");
        System.out.println("Average time for getting Polar from Cartesian PointCPD4: "+getPPointCPD4('C', testTime)+"ns");

        System.out.println("Average time for getting Cartesian from Polar PointCP: "+getCPointCP('P', testTime)+"ns");
        System.out.println("Average time for getting Cartesian from Polar PointCPD2: "+getCPointCPD2('P', testTime)+"ns");
        System.out.println("Average time for getting Cartesian from Polar PointCPD4: "+getCPointCPD4('P', testTime)+"ns");
        System.out.println("Average time for getting Polar from Polar PointCP: "+getPPointCP('P', testTime)+"ns");
        System.out.println("Average time for getting Polar from Polar PointCPD2: "+getPPointCPD2('P', testTime)+"ns");
        System.out.println("Average time for getting Polar from Polar PointCPD4: "+getPPointCPD4('P', testTime)+"ns");
    }
}
