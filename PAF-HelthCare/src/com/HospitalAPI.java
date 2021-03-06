package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hospital;

/**
 * Servlet implementation class HospitalAPI
 */
@WebServlet("/HospitalAPI")
public class HospitalAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */

	Hospital hosObj = new Hospital();
	
	public HospitalAPI() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @param hosObj
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 // TODO Auto-generated method stub 
		String output = hosObj.insertHospital(request.getParameter("hospitalName"),
		  request.getParameter("address"), request.getParameter("hotline"),
		  request.getParameter("contactNumber"), request.getParameter("disctription"));
		  
		response.getWriter().write(output);

		 
	}

	/**
	 * @param hosObj
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Map paras = getParasMap(request); 
		 String output = hosObj.updateHospital(paras.get("hidIDSave").toString(),
		 paras.get("hospitalName").toString(), paras.get("address").toString(),
		 paras.get("hotline").toString(), paras.get("contactNumber").toString(),
		 paras.get("disctription").toString());
		  
		 response.getWriter().write(output);
		 
	}

	private Map getParasMap(HttpServletRequest request) { 
		// TODO Auto-generatedmethod stub 
		Map<String, String> map = new HashMap<String, String>(); try {
		Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); String
		queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
		scanner.close(); String[] params = queryString.split("&"); 
		for (String param: params) { 
			String[] p = param.split("="); 
			map.put(p[0], p[1]); 
			} 
		} catch(Exception e) { 
			
		} return map;
		
	}
	 


	/**
	 * @param hosObj 
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response,Hospital hosObj)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Map paras = getParasMap(request); 
		 String output = hosObj.deleteHospital(paras.get("hos_Id").toString());
		 response.getWriter().write(output);
		 
	}

}
