package RahulDP.data;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static RahulDP.data.DataReader.getJsonData;

public class DataProviders {
    @DataProvider(name = "flight")
    public Object[][] flightData() {

        HashMap<String, String> reservationDetails = new HashMap<>();
        reservationDetails.put("origin", "PNQ");
        reservationDetails.put("destination", "HYD");
        reservationDetails.put("destination2", "BLR");

        HashMap<String, String> reservationDetails1 = new HashMap<>();
        reservationDetails1.put("origin", "DEL");
        reservationDetails1.put("destination", "PNQ");
        reservationDetails1.put("destination2", "BLR");

        List<HashMap<String, String>> listOfMaps = new ArrayList<>();
        listOfMaps.add(reservationDetails1);
        listOfMaps.add(reservationDetails);

        return new Object[][]{{listOfMaps.get(0)}, {listOfMaps.get(1)}};
    }

    @DataProvider(name = "flights")
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> dataList = getJsonData("src/test/java/RahulDP/data/reservationDetails.json");
        return new Object[][]{{dataList.get(0)}, {dataList.get(1)}};
    }
}
