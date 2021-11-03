package com.content.util;

import com.content.model.QueryRequest;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    private CSVUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static List<QueryRequest> getQueryRequestList(File csvFile) throws IOException {
        List<QueryRequest> queryRequestList = new ArrayList<>();
        Reader file = new FileReader(csvFile);
        Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.parse(file);
        for (CSVRecord csvRecord : csvRecords) {
            queryRequestList.add(buildQueryRequest(csvRecord));
        }
        return queryRequestList;
    }

    private static QueryRequest buildQueryRequest(CSVRecord csvRecord) {
        QueryRequest queryRequest = new QueryRequest();
        queryRequest.setName(csvRecord.get(0));
        queryRequest.setCity(csvRecord.get(1));
        queryRequest.setState(csvRecord.get(2));
        queryRequest.setZipcode(csvRecord.get(3));
        return queryRequest;
    }

}
