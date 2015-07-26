package com.KeywordDriven.executionEngine;

import com.KeywordDriven.config.ActionKeywords;
import com.KeywordDriven.config.Constants;
import com.KeywordDriven.utility.ExcelUtils;
import com.KeywordDriven.utility.Log;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class DriverScript {

	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	public static Properties OR;
	public static Method method[];

	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
	public static String sDataset;




	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		DOMConfigurator.configure("log4j.xml");
		String Path_OR = Constants.Path_OR;
		FileInputStream fs = new FileInputStream(Path_OR);
		OR = new Properties(System.getProperties());
		OR.load(fs);



		String sPath = Constants.Path_TestData;
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();
		ExcelUtils.setExcelFile(sPath);
		execcuteTestCase();


		/*try {
		
		
		for ( int iRow=1;iRow<=9;iRow++){
			sActionKeyword = ExcelUtils.getCellData(iRow, Constants.Col_ActionKeyword);
			sPageObject = ExcelUtils.getCellData(iRow, Constants.Col_PageObject);
			if (sPageObject==null){
				executeActions();
			} else {
				executeActions(sPageObject);
			}
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}*/
		}


	private static void executeActions(String pageObject) throws Exception{
		
		for(int i = 0;i < method.length;i++){
			
			if(method[i].getName().equals(sActionKeyword)){
				method[i].invoke(actionKeywords, pageObject);
				break;
				}
			}
	}

	private static void executeActions(String pageObject,String dataSet) throws Exception{

		for(int i = 0;i < method.length;i++){

			if(method[i].getName().equals(sActionKeyword)){
				method[i].invoke(actionKeywords, pageObject, dataSet);
				break;
			}
		}
	}
	private static void executeActions() throws Exception{

		for(int i = 0;i < method.length;i++){

			if(method[i].getName().equals(sActionKeyword)){
				method[i].invoke(actionKeywords);
				break;
			}
		}
	}

	public static void execcuteTestCase() throws Exception{
		int iTotalTestcases=ExcelUtils.getRowCount(Constants.Sheet_TestCases);
		for (int iTestCase = 1; iTestCase <= iTotalTestcases; iTestCase++) {
			sTestCaseID=ExcelUtils.getCellData(iTestCase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
			sRunMode=ExcelUtils.getCellData(iTestCase,Constants.Col_RunMode, Constants.Sheet_TestCases);

			Log.startTestCase(sTestCaseID);
			if (sRunMode.equalsIgnoreCase("yes")){
				iTestStep=ExcelUtils.getRowContains(sTestCaseID,Constants.Col_TestCaseID,Constants.Sheet_TestSteps);
				iTestLastStep=ExcelUtils.getTestCaseCount(sTestCaseID, iTestStep, Constants.Sheet_TestSteps);
				Log.startTestCase(sTestCaseID);

				for (; iTestStep < iTestLastStep; iTestStep++) {
					sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
					sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject,Constants.Sheet_TestSteps);
					sDataset=ExcelUtils.getCellData(iTestStep,Constants.Col_DataSet, Constants.Sheet_TestSteps);
					if (sPageObject!=null&&sDataset!=null){
						executeActions(sPageObject,sDataset);
					} else if (sPageObject==null&&sDataset==null) {
						executeActions();
					} else if (sPageObject!=null){
						executeActions(sPageObject);
					} else {
						executeActions(sDataset);
					}
				}
				Log.endTestCase(sTestCaseID);
			} else {
				Log.info("TestCase Runmode is set to No");
			}
		}
	}
		



}
