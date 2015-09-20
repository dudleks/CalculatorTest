import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 19.09.2015.
 */
@RunWith(Parameterized.class)
public class CalculationTest {

    @Parameterized.Parameters()
    public static Iterable<Object[]> data() throws IOException {
        String path = CalculationTest.class.getClassLoader().getResource("DataFile.csv").getPath();

        CsvReader reader = new CsvReader(path);

        List<TestData> testData = reader.parse();

        List<Object[]> objects = new ArrayList<Object[]>(testData.size());

        for (TestData data : testData) {
            objects.add(new Object[]{data});
        }

        return objects;
    }

    private final TestData data;

    public CalculationTest(TestData data) {

        this.data = data;
    }

    @Test
    public void calculation() throws Exception {
        checkData(data);
    }

    @Step
    public void checkData(TestData data) {
        switch (data.getOperation()) {
            case PLUS: {
                Assert.assertEquals(data.getResult(), data.getOperand1() + data.getOperand2());
                break;
            }
            case MINUS: {
                Assert.assertEquals(data.getResult(), data.getOperand1() - data.getOperand2());
                break;
            }
            case MULTIPLY: {
                Assert.assertEquals(data.getResult(), data.getOperand1() * data.getOperand2());
                break;
            }
            case DIVIDE: {
                Assert.assertEquals(data.getResult(), data.getOperand1() / data.getOperand2());
                break;
            }
            default: {
                Assert.fail("No operation");
                break;
            }
        }
    }
}
