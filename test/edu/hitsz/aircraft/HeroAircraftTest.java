package edu.hitsz.aircraft;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import edu.hitsz.aircraft.HeroAircraft;

class HeroAircraftTest {

    private HeroAircraft heroAircraft;
    @BeforeAll
    static void beforeAll() {
        System.out.println("**--- Executed once before all test methods in this class ---**");
    }

    @BeforeEach
    void setUp() {
        System.out.println("**--- Executed before each test method in this class ---**");
        this.heroAircraft = HeroAircraft.getInstance();
    }


    @DisplayName("Test decreaseHp by a positive value")
    @Test
    void testDecreaseHpPositiveValue() {
        int initialHp = heroAircraft.getHp();
        int decrease = 50;
        heroAircraft.decreaseHp(decrease);
        assertEquals(initialHp - decrease, heroAircraft.getHp());
    }

    @DisplayName("Test decreaseHp hp by zero")
    @Test
    void testDecreaseHpZeroValue() {
        int initialHp = heroAircraft.getHp();
        heroAircraft.decreaseHp(0);
        assertEquals(initialHp, heroAircraft.getHp()); // Hp should remain unchanged
        System.out.println("TC001:True");
    }

    @DisplayName("Test decreaseHp by a negative value")
    @Test
    void testDecreaseHpNegativeValue() {
        int initialHp = heroAircraft.getHp();
        heroAircraft.decreaseHp(-30);
        assertEquals(initialHp, heroAircraft.getHp()); // Hp should remain unchanged
        System.out.println("TC001:True");
    }

    @DisplayName("Test decreaseHp by a negative huge value")
    @Test
    void testDecreaseHpHugeNegativeValue() {
        int initialHp = heroAircraft.getHp();
        heroAircraft.decreaseHp(-heroAircraft.getMaxHp());
        assertEquals(heroAircraft.getMaxHp(), heroAircraft.getHp()); // Hp should remain unchanged
        System.out.println("TC001:True");
    }

    @DisplayName("Test vanish method")
    @Test
    void testVanish() {
        heroAircraft.vanish();
        assertFalse(!heroAircraft.notValid()); // After calling vanish method, isValid should be false
        System.out.println("TC003:True");
    }


    @DisplayName("Test setShootNum method")
    @Test
    void setShootNum() {
        System.out.println("**--- Test setShootNum method executed ---**");

        // Verify default shootNum
        Assertions.assertEquals(1, this.heroAircraft.getShootNum());
        System.out.println("TC002:True");

        // Test setting shootNum to a positive value
        this.heroAircraft.setShootNum(3);
        Assertions.assertEquals(3, this.heroAircraft.getShootNum());
        System.out.println("TC002:True");

        // Test setting shootNum to zero
        this.heroAircraft.setShootNum(0);
        Assertions.assertEquals(0, this.heroAircraft.getShootNum());
        System.out.println("TC002:True");

        // Test setting shootNum to a negative value
        this.heroAircraft.setShootNum(-2);
        Assertions.assertEquals(0, this.heroAircraft.getShootNum()); // Negative value should be treated as zero
        System.out.println("TC002:True");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("**--- Executed once after all test methods in this class ---**");
    }
}
