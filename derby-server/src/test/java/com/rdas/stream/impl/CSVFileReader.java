package com.rdas.stream.impl;

import java.io.FileReader;
import java.util.*;
import au.com.bytecode.opencsv.CSVReader;
public class CSVFileReader implements IDataFileReader {
    private int rowNum = 0; // current row number!
    private CSVReader reader;

    public CSVFileReader(String csvPath, char delimiter) throws Exception {
        this.reader = new CSVReader(new FileReader(csvPath), delimiter);
    }

    @Override
    public int rowNum() {
        return rowNum;
    }

    @Override
    public List<String[]> readRows(int batchSize) throws Exception {
        List<String[]> dataRows = new ArrayList<String[]>();
        if (batchSize > 0) {
            String[] dataRow = null;
            while ((dataRow = reader.readNext()) != null) {
                rowNum++;
                dataRows.add(dataRow);
                if (dataRows.size() == batchSize)
                    break;
            }
        }
        return dataRows;
    }
}
