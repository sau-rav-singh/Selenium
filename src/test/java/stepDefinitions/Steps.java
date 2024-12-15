package stepDefinitions;

import BNYExcel.ReadExcel;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.*;

public class Steps {
    public static ThreadLocal<Map<String, String>> currentIterationMap = new ThreadLocal<Map<String, String>>();
    private static ThreadLocal<String> testDataId = new ThreadLocal<>();
    private static ThreadLocal<String> scenarioName = new ThreadLocal<>();
    private static ThreadLocal<String> excel = new ThreadLocal<>();
    private static ThreadLocal<String> sheet = new ThreadLocal<>();
    private static ThreadLocal<ArrayList<Map<String, String>>> excelData = new ThreadLocal<ArrayList<Map<String, String>>>() {
        @Override
        protected ArrayList<Map<String, String>> initialValue() {
            return new ArrayList<>();
        }
    };
    @Before
    public void readScenarioName(Scenario scenario) {
        testDataId.set(scenario.getName());
    }


    @Given("A Workbook named {string} and sheetname as{string} is read and to write Data")
    public void aWorkbookNamedAndSheetnameAsIsReadAndToWriteData(String excelName, String sheetName) {
        //currentIterationMap = null;
        if (scenarioName.get() == null || !sheetName.equals(sheet.get()) || !excelName.equals(excel.get()) || !testDataId.get().equals(scenarioName.get())) {
            excelData.get().addAll(ReadExcel.readData(excelName, sheetName));
        }

        List<Map<String, String>> removeData = new ArrayList<>();
        for (Map<String, String> map : excelData.get()) {
            if (map.get("TestDataID").equals(testDataId.get())) {
                currentIterationMap.set(map);
                removeData.add(map);
                break;
            }
        }
        if (!removeData.isEmpty()) {
            excelData.get().remove(removeData.get(0));
        }
        scenarioName.set(testDataId.get());
        sheet.set(sheetName);
        excel.set(excelName);

    }

    @When("Read symbol as {string} and orderType as {string} and print values")
    public void readSymbolAsAndOrderTypeAsAndPrintValues(String symbol, String orderType) {
        symbol=currentIterationMap.get().get(symbol);
        orderType=currentIterationMap.get().get(orderType);
        System.out.println(symbol);
        System.out.println(orderType);
    }
}
