package backend;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class BackendMainApplication
{
	/*
	 * For this project, we are using embedded Tomcat for a Java web application with Heroku. This file is known as the launcher class. It is required to launch
	 * the server. Complete guide can be found at: https://devcenter.heroku.com/ articles/create-a-java-web-application-using-embedded-tomcat
	 */
	public static void main( final String[] args ) throws Exception
	{

		final String webappDirLocation = "src/main/webapp/"; //webApp property files
		final Tomcat tomcat = new Tomcat();

		//The port that we should run on can be set into an environment variable
		//Look for that variable and default to 8080 if it isn't there.
		String webPort = System.getenv( "PORT" );
		if( webPort == null || webPort.isEmpty() )
		{
			webPort = "8080";
		}

		tomcat.setPort( Integer.valueOf( webPort ) );

		final StandardContext ctx = (StandardContext)tomcat.addWebapp( "/", new File( webappDirLocation ).getAbsolutePath() );
		System.out.println( "configuring app with basedir: " + new File( "./" + webappDirLocation ).getAbsolutePath() );

		// Declare an alternative location for your "WEB-INF/classes" dir
		// Servlet 3.0 annotation will work
		final File additionWebInfClasses = new File( "target/classes" );
		final WebResourceRoot resources = new StandardRoot( ctx );
		resources.addPreResources( new DirResourceSet( resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/" ) ); // bind each servlet to the WebApp
		ctx.setResources( resources );

		tomcat.start();
		tomcat.getServer().await();
	}
}
