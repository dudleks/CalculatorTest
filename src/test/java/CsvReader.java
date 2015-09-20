import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 20.09.2015.
 */
public class CsvReader {

    private String filePath;

    public CsvReader(String filePath) {
        this.filePath = filePath;
    }

    public List<TestData> parse() throws IOException {
        Reader in = new FileReader(filePath);
        try {
            CSVParser parser = new CSVParser(in, CSVFormat.newFormat(';')
                    .withHeader("operand1", "operand2","operation", "result" ));
            List<CSVRecord> list = parser.getRecords();

            List<TestData> data = new ArrayList<TestData>(list.size());
            for (CSVRecord record : list) {
                String operand1 = record.get("operand1");
                String operand2 = record.get("operand2");
                String operation = record.get("operation");
                String result = record.get("result");

                TestData.Operation oop = null;
                for (TestData.Operation op : TestData.Operation.values()) {
                    if (op.getOperation().equals(operation)) {
                        oop = op;
                        break;
                    }
                }
                if (null == oop) {
                    throw new IllegalArgumentException("Illegal operation: " + operation);
                }

                data.add(new TestData(Integer.parseInt(operand1), Integer.parseInt(operand2), oop, Integer.parseInt(result)));
            }

            return data;
        } finally {
            in.close();
        }
    }
}
