package prime.sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeNumeberTest {

    @Test
    void getPrime() {

        assertEquals(2, PrimeNumeber.getPrime(1));
        assertEquals(7, PrimeNumeber.getPrime(4));
        assertEquals(19, PrimeNumeber.getPrime(8));
        assertEquals(29, PrimeNumeber.getPrime(10));
    }
}