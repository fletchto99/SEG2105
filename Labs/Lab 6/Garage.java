/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/



/**
 * This is a more fully-featured state machine for
 * a garage door corresponding to the diagram above
 */
// line 3 "garage.ump"
public class Garage
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Garage State Machines
  enum GarageDoor { Closed, Opening, Open, Closing, Stopped }
  private GarageDoor garageDoor;
  enum Entrance { Clear, Obstructed }
  private Entrance entrance;
  enum StateMachine1 { topLevel }
  enum StateMachine1TopLevel { Null, thread1 }
  private StateMachine1 stateMachine1;
  private StateMachine1TopLevel stateMachine1TopLevel;

  //Garage Do Activity Threads
  Thread doActivityStateMachine1TopLevelThread1Thread = null;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Garage()
  {
    setGarageDoor(GarageDoor.Closed);
    setEntrance(Entrance.Clear);
    setStateMachine1TopLevel(StateMachine1TopLevel.Null);
    setStateMachine1(StateMachine1.topLevel);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getGarageDoorFullName()
  {
    String answer = garageDoor.toString();
    return answer;
  }

  public String getEntranceFullName()
  {
    String answer = entrance.toString();
    return answer;
  }

  public String getStateMachine1FullName()
  {
    String answer = stateMachine1.toString();
    if (stateMachine1TopLevel != StateMachine1TopLevel.Null) { answer += "." + stateMachine1TopLevel.toString(); }
    return answer;
  }

  public GarageDoor getGarageDoor()
  {
    return garageDoor;
  }

  public Entrance getEntrance()
  {
    return entrance;
  }

  public StateMachine1 getStateMachine1()
  {
    return stateMachine1;
  }

  public StateMachine1TopLevel getStateMachine1TopLevel()
  {
    return stateMachine1TopLevel;
  }

  public boolean pressButton()
  {
    boolean wasEventProcessed = false;
    
    GarageDoor aGarageDoor = garageDoor;
    switch (aGarageDoor)
    {
      case Closed:
        exitGarageDoor();
        // line 10 "garage.ump"
        turnLightOn();
        setGarageDoor(GarageDoor.Opening);
        wasEventProcessed = true;
        break;
      case Opening:
        setGarageDoor(GarageDoor.Stopped);
        wasEventProcessed = true;
        break;
      case Open:
        if (getEntrance().equals(Entrance.Clear))
        {
          setGarageDoor(GarageDoor.Closing);
          wasEventProcessed = true;
          break;
        }
        break;
      case Closing:
        // line 25 "garage.ump"
        flashLightOn();
        setGarageDoor(GarageDoor.Opening);
        wasEventProcessed = true;
        break;
      case Stopped:
        if (getEntrance().equals(Entrance.Clear))
        {
          setGarageDoor(GarageDoor.Opening);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean openingCompleted()
  {
    boolean wasEventProcessed = false;
    
    GarageDoor aGarageDoor = garageDoor;
    switch (aGarageDoor)
    {
      case Opening:
        setGarageDoor(GarageDoor.Open);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean closingCompleted()
  {
    boolean wasEventProcessed = false;
    
    GarageDoor aGarageDoor = garageDoor;
    switch (aGarageDoor)
    {
      case Closing:
        setGarageDoor(GarageDoor.Closed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean obstruct()
  {
    boolean wasEventProcessed = false;
    
    Entrance aEntrance = entrance;
    switch (aEntrance)
    {
      case Clear:
        setEntrance(Entrance.Obstructed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean clearObstruction()
  {
    boolean wasEventProcessed = false;
    
    Entrance aEntrance = entrance;
    switch (aEntrance)
    {
      case Obstructed:
        setEntrance(Entrance.Clear);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean enterTopLevel()
  {
    boolean wasEventProcessed = false;
    
    StateMachine1TopLevel aStateMachine1TopLevel = stateMachine1TopLevel;
    switch (aStateMachine1TopLevel)
    {
      case Null:
        setStateMachine1TopLevel(StateMachine1TopLevel.thread1);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private boolean exitTopLevel()
  {
    boolean wasEventProcessed = false;
    
    StateMachine1TopLevel aStateMachine1TopLevel = stateMachine1TopLevel;
    switch (aStateMachine1TopLevel)
    {
      case thread1:
        setStateMachine1TopLevel(StateMachine1TopLevel.Null);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitGarageDoor()
  {
    switch(garageDoor)
    {
      case Closed:
        // line 9 "garage.ump"
        triggerNormalEnergyMode();
        break;
    }
  }

  private void setGarageDoor(GarageDoor aGarageDoor)
  {
    garageDoor = aGarageDoor;

    // entry actions and do activities
    switch(garageDoor)
    {
      case Closed:
        // line 7 "garage.ump"
        stopMotor();
        // line 8 "garage.ump"
        triggerEnergySaveMode();
        break;
      case Opening:
        // line 13 "garage.ump"
        runMotorForward();
        break;
      case Open:
        // line 18 "garage.ump"
        stopMotor();
        break;
      case Closing:
        // line 23 "garage.ump"
        runMotorInReverse();
        break;
      case Stopped:
        // line 28 "garage.ump"
        stopMotor();
        break;
    }
  }

  private void setEntrance(Entrance aEntrance)
  {
    entrance = aEntrance;
  }

  private void exitStateMachine1()
  {
    switch(stateMachine1)
    {
      case topLevel:
        exitTopLevel();
        break;
    }
  }

  private void setStateMachine1(StateMachine1 aStateMachine1)
  {
    stateMachine1 = aStateMachine1;

    // entry actions and do activities
    switch(stateMachine1)
    {
      case topLevel:
        if (stateMachine1TopLevel == StateMachine1TopLevel.Null) { setStateMachine1TopLevel(StateMachine1TopLevel.thread1); }
        break;
    }
  }

  private void exitStateMachine1TopLevel()
  {
    switch(stateMachine1TopLevel)
    {
      case thread1:
        if (doActivityStateMachine1TopLevelThread1Thread != null) { doActivityStateMachine1TopLevelThread1Thread.interrupt(); }
        break;
    }
  }

  private void setStateMachine1TopLevel(StateMachine1TopLevel aStateMachine1TopLevel)
  {
    stateMachine1TopLevel = aStateMachine1TopLevel;
    if (stateMachine1 != StateMachine1.topLevel && aStateMachine1TopLevel != StateMachine1TopLevel.Null) { setStateMachine1(StateMachine1.topLevel); }

    // entry actions and do activities
    switch(stateMachine1TopLevel)
    {
      case thread1:
        doActivityStateMachine1TopLevelThread1Thread = new DoActivityThread(this,"doActivityStateMachine1TopLevelThread1");
        break;
    }
  }

  private void doActivityStateMachine1TopLevelThread1()
  {
    try
    {
      System.out.println("Garage door is now active");
    pressButton();
    obstruct();
    pressButton();
    clearObstruction();
    pressButton();
    System.exit(0);
      Thread.sleep(1);
    }
    catch (InterruptedException e)
    {

    }
  }

  private static class DoActivityThread extends Thread
  {
    Garage controller;
    String doActivityMethodName;
    
    public DoActivityThread(Garage aController,String aDoActivityMethodName)
    {
      controller = aController;
      doActivityMethodName = aDoActivityMethodName;
      start();
    }
    
    public void run()
    {
      if ("doActivityStateMachine1TopLevelThread1".equals(doActivityMethodName))
      {
        controller.doActivityStateMachine1TopLevelThread1();
      }
    }
  }

  public void delete()
  {}

  // line 43 "garage.ump"
  public boolean runMotorInReverse(){
    System.out.println("Running motor in reverse");
    return true;
  }

  // line 48 "garage.ump"
  public boolean flashLightOn(){
    System.out.println("Flashing light on");
    return true;
  }

  // line 53 "garage.ump"
  public boolean turnLightOn(){
    System.out.println("Turning light on");
    return true;
  }

  // line 58 "garage.ump"
  public boolean turnLightOff(){
    System.out.println("Turning light off");
    return true;
  }

  // line 63 "garage.ump"
  public boolean runMotorForward(){
    System.out.println("Running motor forwards");
    return true;
  }

  // line 68 "garage.ump"
  public boolean triggerEnergySaveMode(){
    System.out.println("Triggering Energy Saving Mode");
    return true;
  }

  // line 73 "garage.ump"
  public boolean stopMotor(){
    System.out.println("Stopping motor");
    return true;
  }

  // line 78 "garage.ump"
  public boolean triggerNormalEnergyMode(){
    System.out.println("Triggering Normal Energy Mode");
     return true;
  }

  // line 83 "garage.ump"
  public boolean waitawhile(){
    System.out.println("Waiting");
    return true;
  }

  // line 88 "garage.ump"
  public boolean test(){
    System.out.println("Testing");
    return true;
  }

  // line 103 "garage.ump"
   public static  void main(String [] argc){
    Thread.currentThread().setUncaughtExceptionHandler(new UmpleExceptionHandler());
    Thread.setDefaultUncaughtExceptionHandler(new UmpleExceptionHandler());
    new Garage();
  }

  public static class UmpleExceptionHandler implements Thread.UncaughtExceptionHandler
  {
    public void uncaughtException(Thread t, Throwable e)
    {
      translate(e);
      if(e.getCause()!=null)
      {
        translate(e.getCause());
      }
      e.printStackTrace();
    }
    public void translate(Throwable e)
    {
      java.util.List<StackTraceElement> result = new java.util.ArrayList<StackTraceElement>();
      StackTraceElement[] elements = e.getStackTrace();
      try
      {
        for(StackTraceElement element:elements)
        {
          String className = element.getClassName();
          String methodName = element.getMethodName();
          boolean methodFound = false;
          int index = className.lastIndexOf('.')+1;
          try {
            java.lang.reflect.Method query = this.getClass().getMethod(className.substring(index)+"_"+methodName,new Class[]{});
            UmpleSourceData sourceInformation = (UmpleSourceData)query.invoke(this,new Object[]{});
            for(int i=0;i<sourceInformation.size();++i)
            {
              int distanceFromStart = element.getLineNumber()-sourceInformation.getJavaLine(i)-(("main".equals(methodName))?2:0);
              if(distanceFromStart>=0&&distanceFromStart<=sourceInformation.getLength(i))
              {
                result.add(new StackTraceElement(element.getClassName(),element.getMethodName(),sourceInformation.getFileName(i),sourceInformation.getUmpleLine(i)+distanceFromStart));
                methodFound = true;
                break;
              }
            }
          }
          catch (Exception e2){}
          if(!methodFound)
          {
            result.add(element);
          }
        }
      }
      catch (Exception e1)
      {
        e1.printStackTrace();
      }
      e.setStackTrace(result.toArray(new StackTraceElement[0]));
    }
  //The following methods Map Java lines back to their original Umple file / line    
    public UmpleSourceData Garage_setGarageDoor(){ return new UmpleSourceData().setFileNames("garage.ump"," garage.ump"," garage.ump"," garage.ump"," garage.ump"," garage.ump").setUmpleLines(6, 7, 12, 17, 22, 27).setJavaLines(261, 262, 266, 270, 274, 278).setLengths(1, 1, 1, 1, 1, 1);}
    public UmpleSourceData Garage_triggerEnergySaveMode(){ return new UmpleSourceData().setFileNames("garage.ump").setUmpleLines(67).setJavaLines(409).setLengths(3);}
    public UmpleSourceData Garage_test(){ return new UmpleSourceData().setFileNames("garage.ump").setUmpleLines(87).setJavaLines(433).setLengths(3);}
    public UmpleSourceData Garage_triggerNormalEnergyMode(){ return new UmpleSourceData().setFileNames("garage.ump").setUmpleLines(77).setJavaLines(421).setLengths(3);}
    public UmpleSourceData Garage_waitawhile(){ return new UmpleSourceData().setFileNames("garage.ump").setUmpleLines(82).setJavaLines(427).setLengths(3);}
    public UmpleSourceData Garage_pressButton(){ return new UmpleSourceData().setFileNames("garage.ump"," garage.ump"," garage.ump"," garage.ump").setUmpleLines(9, 19, 24, 28).setJavaLines(95, 106, 114, 121).setLengths(1, 1, 1, 1);}
    public UmpleSourceData Garage_main(){ return new UmpleSourceData().setFileNames("garage.ump").setUmpleLines(102).setJavaLines(439).setLengths(3);}
    public UmpleSourceData Garage_turnLightOff(){ return new UmpleSourceData().setFileNames("garage.ump").setUmpleLines(57).setJavaLines(397).setLengths(3);}
    public UmpleSourceData Garage_runMotorInReverse(){ return new UmpleSourceData().setFileNames("garage.ump").setUmpleLines(42).setJavaLines(379).setLengths(3);}
    public UmpleSourceData Garage_runMotorForward(){ return new UmpleSourceData().setFileNames("garage.ump").setUmpleLines(62).setJavaLines(403).setLengths(3);}
    public UmpleSourceData Garage_doActivityStateMachine1TopLevelThread1(){ return new UmpleSourceData().setFileNames("garage.ump").setUmpleLines(91).setJavaLines(342).setLengths(7);}
    public UmpleSourceData Garage_flashLightOn(){ return new UmpleSourceData().setFileNames("garage.ump").setUmpleLines(47).setJavaLines(385).setLengths(3);}
    public UmpleSourceData Garage_turnLightOn(){ return new UmpleSourceData().setFileNames("garage.ump").setUmpleLines(52).setJavaLines(391).setLengths(3);}
    public UmpleSourceData Garage_exitGarageDoor(){ return new UmpleSourceData().setFileNames("garage.ump").setUmpleLines(8).setJavaLines(247).setLengths(1);}
    public UmpleSourceData Garage_stopMotor(){ return new UmpleSourceData().setFileNames("garage.ump").setUmpleLines(72).setJavaLines(415).setLengths(3);}

  }
  public static class UmpleSourceData
  {
    String[] umpleFileNames;
    Integer[] umpleLines;
    Integer[] umpleJavaLines;
    Integer[] umpleLengths;
    
    public UmpleSourceData(){
    }
    public String getFileName(int i){
      return umpleFileNames[i];
    }
    public Integer getUmpleLine(int i){
      return umpleLines[i];
    }
    public Integer getJavaLine(int i){
      return umpleJavaLines[i];
    }
    public Integer getLength(int i){
      return umpleLengths[i];
    }
    public UmpleSourceData setFileNames(String... filenames){
      umpleFileNames = filenames;
      return this;
    }
    public UmpleSourceData setUmpleLines(Integer... umplelines){
      umpleLines = umplelines;
      return this;
    }
    public UmpleSourceData setJavaLines(Integer... javalines){
      umpleJavaLines = javalines;
      return this;
    }
    public UmpleSourceData setLengths(Integer... lengths){
      umpleLengths = lengths;
      return this;
    }
    public int size(){
      return umpleFileNames.length;
    }
  } 
}