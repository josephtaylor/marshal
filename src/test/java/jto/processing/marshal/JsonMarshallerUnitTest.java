package jto.processing.marshal;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonMarshallerUnitTest {

    @Test
    public void testMarshal() {
        TestClass testClass = new TestClass();
        testClass.setId(100);
        testClass.setName("testing");

        String expected = "{\"name\":\"testing\",\"id\":100}";
        assertEquals(expected, new JsonMarshaller().marshal(testClass));
    }

    @Test
    public void testMarshal_list() {
        TestClass testClass = new TestClass();
        testClass.setId(100);
        testClass.setName("testing");

        TestClass other = new TestClass();
        other.setId(120);
        other.setName("theOther");

        List<TestClass> testClasses = Lists.newArrayList(testClass, other);

        String expected = "[{\"name\":\"testing\",\"id\":100},{\"name\":\"theOther\",\"id\":120}]";
        assertEquals(expected, new JsonMarshaller().marshal(testClasses));
    }

    private static class TestClass {
        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}