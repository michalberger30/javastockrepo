package com.mta.javastock;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class Exercise03 extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//circle
		
		int radius;
		double area;
		
		radius = 50;
		area= Math.PI * radius*radius;
		String line1 = new String("calculation 1: Area of circle with radius 50 is "+area+" square­cm.");
		
		// triangle
		
		int angleB = 30;
		int hypotenuse = 50;
		double opposite;
		double conversion;
		conversion = angleB * Math.PI / 180;
		opposite = Math.sin(conversion) * hypotenuse;
		
		String line2 = new String("calculation 2: Length of opposite where angle B is "+angleB+" degrees and Hypotenuse length is "+hypotenuse+" cm is: "+opposite+" cm");
		
		//power
		
		double base = 20.0;
		double  exp = 13.0;
		double res;
		
		res =  Math.pow(base, exp);
		String line3 = new String("calculation 3: Power of "+base+" with exp of "+exp+" is: "+res+"");
		
		//print results
		String resultStr = new String(line1 + "<br>" + line2 + "<br>" + line3);
	
				

	
		resp.setContentType("text/html");
		resp.getWriter().println(resultStr);
		
	}

}
