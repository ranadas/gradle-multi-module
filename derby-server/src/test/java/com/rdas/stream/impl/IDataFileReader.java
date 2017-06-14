package com.rdas.stream.impl;

import java.util.List;

public interface IDataFileReader {
    int rowNum(); // current row number!
    List<String[]> readRows(int batchSize) throws Exception;
}
