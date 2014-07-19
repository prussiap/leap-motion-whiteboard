<!DOCTYPE HTML>
<!DOCTYPE html PUBLIC "" ""><HTML><HEAD>
<META http-equiv="Content-Type" 
content="text/html; charset=windows-1252"></HEAD>
<BODY>
<PRE>package com.NoahHuppert.LeapMotion.Mouse;
/*
 * THIS PROGRAM WAS CREATED BY NOAH HUPPERT
 * DO NOT ATTEMPT TO CLAIM THIS PROGRAM AS YOUR OWN
 * IF YOU HAVE ANY QUESTION CONTACT ME UNDER NOAH_h ON THE LEAP DEVELOPER FORMS
 * THIS PROGRAM IS MEANT TO EMULATE A BASIC MOUSE DRIVER FOR THE LEAP MOTION
 * THE CLICKING IS A LITTLE HARD TO GET USED TO
 * POINT WERE YOU WANT THE MOUSE TO GO
 * TO CLICK TRY AND KEEP POINTING AT WHAT YOU WANT TO CLICK ON AND JAB BUT DON'T JAB TO HARD
 * I MADE THIS PROGRAM SO THAT US DEVELOPERS COULD PLAY AROUND FOR A LITTLE
 * 
 * MADE BY NOAH HUPPERT
 */
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.Console;
import java.util.Scanner;
import java.util.Vector;
import java.lang.Math;
import java.util.*;


import com.leapmotion.leap.*;

public class MouseFinal{
  
	

	Vector point;
	Listener listener = new Listener();//Create New Listener For The Leap
	Controller controller = new Controller();//Create New Controller For The Leap
	
	public static void main (String[] args) throws InterruptedException, AWTException 
	{
		
		float xx = 0;//For Moving Mouse
		float yy = 0;//For Moving Mouse
		int loop = 1;//For Looping
		int sy;//Screen Width y
		int sx;//Screen Width x
		int x;//X position of were finger is pointing on screen in pixels
		int y;//Y position of were finger is pointing on screen in pixels
		int counti = 0;//Timer for program so if i am unable to stop program with finger mouse it will give me back control
		int i = 0;//for reading vectors
		int z1 = 0;//z from inter vector
		int z2 = 0;// second z from vector
		int zz = 0;//Difference in z vector
		int fin = 0;//# of fingers
		int arg0 = 0;//For Vector Stuff
		//
		int[] xArray;//Array that holds 100 X values at a time for averaging
		int[] yArray;//Array that holds 100 Y values at a time for averaging
		xArray = new int[999];//Defining how many spaces are in the array
		yArray = new int[999];//Defining how many spaces are in the array
		int sumOfXArray = 0;//Sum of X Array
		int sumOfYArray = 0;//Sum of Y Array
		int arrayIndexCounter = 0;//For inserting X and Y values into xArray and yArray
		int sumAddingCounter = 0;//For adding all the slots of the arrays up
		int averageX = 0;//The Average of X
		int averageY = 0;//The Average of Y
		//
		int numberOfFingers = 0;//Number of fingers for two finger clicking method
		int[] zArray;
		zArray = new int[999];
		int sumOfZArray =0;
		//int zArrayIndexCounter = 0;
		//int zAddingCounter = 0;
		int averageZ = 0;
		int z = 0;
		
		
		Robot robot = new Robot();//Moves mouse around
		Scanner input=new Scanner(System.in);//For Text Input
		
		
		Listener listener = new Listener();//Create New Listener For The Leap
		Controller controller = new Controller();//Create New Controller For The Leap
		
		while(loop==1)
		{
			
			Frame frame = controller.frame();
			Finger finger = frame.fingers().get(arg0);
			ScreenList screenList = controller.calibratedScreens();
			Screen screen = screenList.get(0);

			com.leapmotion.leap.Vector inter = screen.intersect(finger, true);
			com.leapmotion.leap.Vector sped = finger.tipVelocity();
			sx = screen.widthPixels();
			sy = screen.heightPixels();
			i = 0;
			xx = inter.get(i)*sx;
			i = 1;
			yy = 900-inter.get(i)*sy;
			i = 2;
			zz = (int) sped.get(i);
			x = (int) xx;
			y = (int) yy;
			z = (int) zz;
			//Beginning of X, Y averaging
			arrayIndexCounter = arrayIndexCounter + 1;//Add one to the index counter so it will insert into the next spot of the array
			
			if(arrayIndexCounter==999)
			{
				arrayIndexCounter = 0;//Once it has gotten through all the spots of the arrays it will reset so it is a Moveing Average Filter
			}
			
			xArray[arrayIndexCounter] = x;//Inserts X value into next slot
			yArray[arrayIndexCounter] = y;//Inserts Y value into next slot
			zArray[arrayIndexCounter] = z;//Inserts Z value into next slot
			
			while(sumAddingCounter!=999)
			{
				sumOfXArray = sumOfXArray + xArray[sumAddingCounter];//Adds next spot in xArray
				sumOfYArray = sumOfYArray + yArray[sumAddingCounter];//Adds next spot in yArray
				sumOfZArray = sumOfZArray + zArray[sumAddingCounter];//Adds next spot in zArray
				sumAddingCounter = sumAddingCounter + 1;//Increments by 1
			}
			
			sumAddingCounter = 0;//Resets sumAddingCounter after adding arrays
			
			averageX = sumOfXArray/1000;//Gets average X value
			averageY = sumOfYArray/1000;//Gets average Y value
			averageZ = sumOfZArray/1000;//Gets average Z value
			
			sumOfXArray = 0;
			sumOfYArray = 0;
			
			
			
			System.out.println("\rMade By Noah Huppert	" + averageX + "	" + averageY +   "	" + averageZ  + "	"  + numberOfFingers);

			if(loop ==1)
				{
					robot.mouseMove(averageX, averageY);
					counti = counti+1;
				}
			
			
			//Jab clicking method
			/*if(zz&gt;75)//if finger moves forward//UNCOMENT THIS SECTION FOR JAB CLICKING, MAKE SURE TO COMMENT TWO FINGURE CLICKING
				{
					robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					Thread.sleep(200);
					robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				}*/
			
			if(frame.fingers().count()==2)//COMMENT THIS STUFF OUT IF YOU HAVE UNCOMMENTED JAB CLICKING
			{
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(200);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				System.out.println("CLICK");
			}
			
			if(frame.fingers().count()==8)//Exiting App
			{
				loop = 0;
			}
				
			if(frame.fingers().count()==3)//Scrolling down
			{
				robot.mouseWheel(1);
				Thread.sleep(100);
			}
			
			if(frame.fingers().count()==4)//Scrolling Up
			{
				robot.mouseWheel(-1);
				Thread.sleep(100);
			}
			sumOfZArray = 0;


		}
		
		System.out.println("End");
    }
		
}
	
</PRE></BODY></HTML>
