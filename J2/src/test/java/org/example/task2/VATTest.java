package org.example.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class VATTest {

    private VAT vat;


    @Nested
    class TestCase_1200_00_Without_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal(1000), PriceType.WITHOUT_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("1200.00", vat.getFullPrice().toString());
        }

        @Test
        void getBasePrice() {
            assertEquals("1000.00", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("200.00", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("200", vat.getSumVATForDeclaration().toString());

        }
    }

    @Nested
    class TestCase_1200_00_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal(1200), PriceType.WITH_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("1200.00", vat.getFullPrice().toString());
        }

        @Test
        void getBasePrice() {
            assertEquals("1000.00", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("200.00", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("200", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_500_000_00_Without_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("416666.67"), PriceType.WITHOUT_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("500000.00", vat.getFullPrice().toString());
        }

        @Test
        void getBasePrice() {
            assertEquals("416666.67", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("83333.33", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("83333", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_500_000_00_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("500000"), PriceType.WITH_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("500000.00", vat.getFullPrice().toString());
        }

        @Test
        void getBasePrice() {
            assertEquals("416666.67", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("83333.33", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("83333", vat.getSumVATForDeclaration().toString());
        }
    }


    @Nested
    class TestCase_119_99_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("119.99"), PriceType.WITH_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("119.99", vat.getFullPrice().toString());

        }

        @Test
        void getBasePrice() {
            assertEquals("99.99", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("20.00", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("20", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_119_99_Without_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("99.99"), PriceType.WITHOUT_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("119.99", vat.getFullPrice().toString());
        }

        @Test
        void getBasePrice() {
            assertEquals("99.99", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("20.00", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("20", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_99_99_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("99.99"), PriceType.WITH_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("99.99", vat.getFullPrice().toString());

        }

        @Test
        void getBasePrice() {
            assertEquals("83.32", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("16.67", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("17", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_99_99_Without_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("83.32"), PriceType.WITHOUT_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("99.99", vat.getFullPrice().toString());

        }

        @Test
        void getBasePrice() {
            assertEquals("83.32", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("16.67", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("17", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_92_40_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("92.40"), PriceType.WITH_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("92.40", vat.getFullPrice().toString());

        }

        @Test
        void getBasePrice() {
            assertEquals("77.00", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("15.40", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("15", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_92_40_Without_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("77.00"), PriceType.WITHOUT_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("92.40", vat.getFullPrice().toString());

        }

        @Test
        void getBasePrice() {
            assertEquals("77.00", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("15.40", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("15", vat.getSumVATForDeclaration().toString());
        }
    }


    @Nested
    class TestCase_183_00_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("183.00"), PriceType.WITH_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("183.00", vat.getFullPrice().toString());

        }

        @Test
        void getBasePrice() {
            assertEquals("152.50", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("30.50", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("31", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_183_00_Without_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("152.50"), PriceType.WITHOUT_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("183.00", vat.getFullPrice().toString());

        }

        @Test
        void getBasePrice() {
            assertEquals("152.50", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("30.50", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("31", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_5_94_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("5.94"), PriceType.WITH_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("5.94", vat.getFullPrice().toString());

        }

        @Test
        void getBasePrice() {
            assertEquals("4.95", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("0.99", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("1", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_5_94_Without_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("4.95"), PriceType.WITHOUT_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("5.94", vat.getFullPrice().toString());

        }

        @Test
        void getBasePrice() {
            assertEquals("4.95", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("0.99", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("1", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_0_06_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("0.06"), PriceType.WITH_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("0.06", vat.getFullPrice().toString());

        }

        @Test
        void getBasePrice() {
            assertEquals("0.05", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("0.01", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("0", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_0_06_Without_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("0.05"), PriceType.WITHOUT_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("0.06", vat.getFullPrice().toString());

        }

        @Test
        void getBasePrice() {
            assertEquals("0.05", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("0.01", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("0", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_0_01_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("0.01"), PriceType.WITH_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("0.01", vat.getFullPrice().toString());

        }

        @Test
        void getBasePrice() {
            assertEquals("0.01", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("0.00", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("0", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_0_01_Without_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("0.01"), PriceType.WITHOUT_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("0.01", vat.getFullPrice().toString());

        }

        @Test
        void getBasePrice() {
            assertEquals("0.01", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("0.00", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("0", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_0_00_Without_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("0.00"), PriceType.WITHOUT_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("0.00", vat.getFullPrice().toString());

        }

        @Test
        void getBasePrice() {
            assertEquals("0.00", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("0.00", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("0", vat.getSumVATForDeclaration().toString());
        }
    }

    @Nested
    class TestCase_0_00_VAT {

        @BeforeEach
        public void setUp() {
            vat = new VAT(new BigDecimal("0.00"), PriceType.WITH_VAT);
        }

        @Test
        void getFullPrice() {
            assertEquals("0.00", vat.getFullPrice().toString());

        }

        @Test
        void getBasePrice() {
            assertEquals("0.00", vat.getBasePrice().toString());
        }

        @Test
        void getSumVAT() {
            assertEquals("0.00", vat.getSumVAT().toString());
        }

        @Test
        void getSumVATForDeclaration() {
            assertEquals("0", vat.getSumVATForDeclaration().toString());
        }
    }

    @Test
    void incorrectInput_VAT() {
        assertThrows(IllegalArgumentException.class, () ->
                new VAT(new BigDecimal("-1"), PriceType.WITH_VAT));
    }

    @Test
    void incorrectInput_Without_VAT() {
        assertThrows(IllegalArgumentException.class, () ->
                new VAT(new BigDecimal("-1"), PriceType.WITHOUT_VAT));
    }


}