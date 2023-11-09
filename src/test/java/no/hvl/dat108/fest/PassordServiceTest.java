package no.hvl.dat108.fest;

import no.hvl.dat108.fest.Service.PassordService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PassordServiceTest {

    PassordService passordService = new PassordService();

    @Test
    public void genererTilfeldigSaltTest() {
        // Tester at det fungerer å generere salt
        String salt = passordService.genererTilfeldigSalt();
        assertNotNull(salt);
        assertEquals(32, salt.length());
    }

    @Test
    public void hashMedSaltTest() {
        String passord = "detteErEtPassord";
        try {
            String salt = passordService.genererTilfeldigSalt();
            String hash = passordService.hashMedSalt(passord, salt);
            assertNotNull(hash);
            assertEquals(64, hash.length());
        } catch (Exception e) {
            fail("Exception should not occur during hashing");
        }
    }

    @Test
    public void erKorrektPassordTest() {
        String passord = "detteErEtPassord";
        try {
            String salt = passordService.genererTilfeldigSalt();
            String hash = passordService.hashMedSalt(passord, salt);
            boolean isCorrect = passordService.erKorrektPassord(passord, salt, hash);
            assertTrue(isCorrect);
        } catch (Exception e) {
            fail("Exception should not occur when verifying password");
        }
    }
}
