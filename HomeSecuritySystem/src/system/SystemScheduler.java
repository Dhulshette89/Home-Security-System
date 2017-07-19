package system;



import gui.SecurityLayout;
import system.config.SchedulerConfiguration;


/**
 * The Class SystemScheduler.
 */
public class SystemScheduler implements Runnable  {
	
	/** The t. */
	public Thread t;
	
	/** The config. */
	SchedulerConfiguration config;
	
	/** The ui. */
	SecurityLayout ui;
	
	/** The flag. */
	private boolean flag=false;
	
	/**
	 * Checks if is running.
	 *
	 * @return true, if is running
	 */
	public boolean isRunning()
	{
		return flag;
	}
	
	/**
	 * Instantiates a new system scheduler.
	 *
	 * @param config the config
	 * @param securityLayout the security layout
	 */
	public SystemScheduler(SchedulerConfiguration config, SecurityLayout securityLayout)
	{
		this.config = config;
		this.ui = securityLayout;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
	    System.out.println("Generating report");
	    flag = true;
	    while(flag)
	    {
	    	try {
				config.load();
				ui.applyConfig(config);
				Thread.sleep(5000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    
	    //TODO generate report
	  }
	
	 /**
 	 * Start.
 	 */
 	public void start () {
	      System.out.println("Starting scheduler" );
	      if (t == null) {
	         t = new Thread (this, "scheduler");
	         t.start ();
	      }
	   }

	/**
	 * Stop thread.
	 */
	public void stopThread() {
		// TODO Auto-generated method stub
		flag = false;
		
	}

}
