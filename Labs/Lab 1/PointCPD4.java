/**
 * This class contains instances of coordinates in either polar or
 * cartesian format.  It also provides the utilities to convert
 * them into the other type. It is not an optimal design, it is used
 * only to illustrate some design issues.
 *
 *
 * Based upon the class PointCP.java provided for this Laboratory
 *
 * @author Matthew Langlois
 * @author Matthew Yaraskavitch
 * @version September 2015
 */
public class PointCPD4
{
    //Instance variables ************************************************

    /**
     * Contains C(artesian) or P(olar) to identify the type of
     * coordinates that are being dealt with.
     */
    private char typeCoord;

    /**
     * Contains the current value of X.
     */
    private double x;

    /**
     * Contains the current value of Y.
     */
    private double y;

    /**
     * Contains the current value of RHO.
     */
    private double rho;

    /**
     * Contains the current value of THETA.
     */
    private double theta;


    //Constructors ******************************************************

    /**
     * Constructs a coordinate object, with a type identifier.
     * Stores in both Polar and Cartesian
     * Performs the necessary conversion at initialization based on input
     */
    public PointCPD4(char type, double xOrRho, double yOrTheta)
    {
        if(type != 'C' && type != 'P')
            throw new IllegalArgumentException();
        if(type == 'C'){
            this.x = xOrRho;
            this.y = yOrTheta;
            this.rho = (Math.sqrt(Math.pow(xOrRho, 2) + Math.pow(yOrTheta, 2)));
            this.theta = Math.toDegrees(Math.atan2(yOrTheta, xOrRho));
        }else{
            this.rho = xOrRho;
            this.theta = yOrTheta;
            this.x = (Math.cos(Math.toRadians(yOrTheta)) * xOrRho);
            this.y = (Math.sin(Math.toRadians(yOrTheta)) * xOrRho);
        }

        typeCoord = type;
    }


    //Instance methods **************************************************


    public double getX()
    {
       return x;
    }

    public double getY()
    {
        return y;
    }

    public double getRho()
    {
        return rho;
    }

    public double getTheta()
    {
        return theta;
    }


    /**
     * Converts Cartesian coordinates to Polar coordinates.
     * Does nothing since this implementation has both storage formats
     */
    public void convertStorageToPolar()
    {
        return;
    }

    /**
     * Converts Polar coordinates to Cartesian coordinates.
     * Does nothing since this implementation has both storage formats
     */
    public void convertStorageToCartesian()
    {
        return;
    }


    /**
     * Calculates the distance in between two points using the Pythagorean
     * theorem  (C ^ 2 = A ^ 2 + B ^ 2). Not needed until E2.30.
     *
     * @param pointA The first point.
     * @param pointB The second point.
     * @return The distance between the two points.
     */
    public double getDistance(PointCPD4 pointB)
    {
        // Obtain differences in X and Y, sign is not important as these values
        // will be squared later.
        double deltaX = getX() - pointB.getX();
        double deltaY = getY() - pointB.getY();

        return Math.sqrt((Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
    }

    /**
     * Rotates the specified point by the specified number of degrees.
     * Not required until E2.30
     *
     * @param point The point to rotate
     * @param rotation The number of degrees to rotate the point.
     * @return The rotated image of the original point.
     */
    public PointCPD4 rotatePoint(double rotation)
    {
        double radRotation = Math.toRadians(rotation);
        double X = getX();
        double Y = getY();

        return new PointCPD4('C',
                (Math.cos(radRotation) * X) - (Math.sin(radRotation) * Y),
                (Math.sin(radRotation) * X) + (Math.cos(radRotation) * Y));
    }

    /**
     * Returns information about the coordinates.
     *
     * @return A String containing information about the coordinates.
     */
    public String toString()
    {
        return "Stored as Polar  (" + getTheta() + "," + getRho() + ")\n" +
                "Stored as Cartesian ("+getX()+", "+getY()+")";
    }
}

